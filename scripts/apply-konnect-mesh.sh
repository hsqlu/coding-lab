#!/usr/bin/env bash
set -Eeuo pipefail

# Create or update a Konnect Mesh through the Mesh HTTP API without using the
# konnect-beta Terraform provider. The script is a dry run unless APPLY=true.

KONNECT_REGION="${KONNECT_REGION:-au}"
KONNECT_CONTROL_PLANE_ID="${KONNECT_CONTROL_PLANE_ID:-}"
KONNECT_TOKEN="${KONNECT_TOKEN:-}"
MESH_NAME="${MESH_NAME:-baas}"
CA_BACKEND_NAME="${CA_BACKEND_NAME:-baas-ca}"
CA_RSA_BITS="${CA_RSA_BITS:-2048}"
CA_EXPIRATION="${CA_EXPIRATION:-10y}"
DP_CERT_EXPIRATION="${DP_CERT_EXPIRATION:-1d}"
APPLY="${APPLY:-false}"

log() {
  printf '\n==> %s\n' "$*"
}

fail() {
  printf 'ERROR: %s\n' "$*" >&2
  exit 1
}

command -v curl >/dev/null 2>&1 || fail "curl is required"
command -v jq >/dev/null 2>&1 || fail "jq is required"

[[ -n "${KONNECT_CONTROL_PLANE_ID}" ]] || \
  fail "KONNECT_CONTROL_PLANE_ID is required"
[[ -n "${KONNECT_TOKEN}" ]] || fail "KONNECT_TOKEN is required"
[[ "${KONNECT_REGION}" =~ ^(us|eu|au|me|in|sg)$ ]] || \
  fail "KONNECT_REGION must be one of: us, eu, au, me, in, sg"
[[ "${MESH_NAME}" =~ ^[a-z0-9]([a-z0-9.-]*[a-z0-9])?$ ]] || \
  fail "MESH_NAME contains unsupported characters"
[[ "${CA_BACKEND_NAME}" =~ ^[a-z0-9]([a-z0-9.-]*[a-z0-9])?$ ]] || \
  fail "CA_BACKEND_NAME contains unsupported characters"
[[ "${CA_RSA_BITS}" =~ ^[0-9]+$ ]] || fail "CA_RSA_BITS must be an integer"
[[ "${APPLY}" == "true" || "${APPLY}" == "false" ]] || \
  fail "APPLY must be true or false"

if [[ -n "${GITHUB_ACTIONS:-}" ]]; then
  printf '::add-mask::%s\n' "${KONNECT_TOKEN}"
fi

api_base="https://${KONNECT_REGION}.api.konghq.com/v1/mesh/control-planes/${KONNECT_CONTROL_PLANE_ID}/api"
mesh_url="${api_base}/meshes/${MESH_NAME}"
work_dir="$(mktemp -d)"
trap 'rm -rf "${work_dir}"' EXIT

existing_file="${work_dir}/existing.json"
desired_file="${work_dir}/desired.json"
response_file="${work_dir}/response.json"

log "Read current Mesh configuration"
http_status="$(
  curl --silent --show-error \
    --output "${existing_file}" \
    --write-out '%{http_code}' \
    --connect-timeout 10 \
    --max-time 30 \
    --header "Authorization: Bearer ${KONNECT_TOKEN}" \
    --header 'Accept: application/json' \
    "${mesh_url}"
)" || fail "Failed to connect to the Konnect Mesh API"

case "${http_status}" in
  2??)
    jq empty "${existing_file}" || fail "Konnect returned invalid JSON"
    info="update existing Mesh"
    ;;
  404)
    printf '{"type":"Mesh","name":"%s"}\n' "${MESH_NAME}" >"${existing_file}"
    info="create new Mesh"
    ;;
  401)
    fail "Konnect is reachable, but KONNECT_TOKEN is invalid or expired"
    ;;
  403)
    fail "KONNECT_TOKEN lacks Admin access to this Mesh Control Plane"
    ;;
  *)
    printf 'Konnect response:\n' >&2
    jq . "${existing_file}" 2>/dev/null >&2 || sed -n '1,40p' "${existing_file}" >&2
    fail "Unexpected HTTP ${http_status} while reading Mesh"
    ;;
esac

# Preserve existing Mesh settings but remove server-managed fields and replace
# the mTLS section with the desired built-in CA configuration.
jq \
  --arg mesh_name "${MESH_NAME}" \
  --arg backend_name "${CA_BACKEND_NAME}" \
  --arg ca_expiration "${CA_EXPIRATION}" \
  --arg dp_expiration "${DP_CERT_EXPIRATION}" \
  --argjson rsa_bits "${CA_RSA_BITS}" \
  '
    del(
      .creationTime,
      .modificationTime,
      .kri,
      .warnings
    )
    | .type = "Mesh"
    | .name = $mesh_name
    | .mtls = {
        enabledBackend: $backend_name,
        backends: [
          {
            name: $backend_name,
            type: "builtin",
            dpCert: {
              rotation: {
                expiration: $dp_expiration
              }
            },
            conf: {
              caCert: {
                RSAbits: $rsa_bits,
                expiration: $ca_expiration
              }
            }
          }
        ]
      }
  ' "${existing_file}" >"${desired_file}"

log "Planned Mesh configuration (${info})"
jq . "${desired_file}"

log "Configuration diff"
diff -u \
  <(jq --sort-keys . "${existing_file}") \
  <(jq --sort-keys . "${desired_file}") || true

if [[ "${APPLY}" != "true" ]]; then
  log "Dry run complete"
  printf 'No change was made. Re-run with APPLY=true after reviewing the diff.\n'
  exit 0
fi

log "Apply Mesh configuration"
apply_status="$(
  curl --silent --show-error \
    --output "${response_file}" \
    --write-out '%{http_code}' \
    --request PUT \
    --connect-timeout 10 \
    --max-time 60 \
    --header "Authorization: Bearer ${KONNECT_TOKEN}" \
    --header 'Accept: application/json' \
    --header 'Content-Type: application/json' \
    --data-binary "@${desired_file}" \
    "${mesh_url}"
)" || fail "Failed while writing to the Konnect Mesh API"

case "${apply_status}" in
  2??)
    printf 'SUCCESS: Mesh %s was applied to Control Plane %s.\n' \
      "${MESH_NAME}" "${KONNECT_CONTROL_PLANE_ID}"
    ;;
  *)
    printf 'Konnect response:\n' >&2
    jq . "${response_file}" 2>/dev/null >&2 || sed -n '1,40p' "${response_file}" >&2
    fail "Konnect rejected the Mesh configuration with HTTP ${apply_status}"
    ;;
esac

log "Verify applied Mesh"
curl --silent --show-error --fail-with-body \
  --connect-timeout 10 \
  --max-time 30 \
  --header "Authorization: Bearer ${KONNECT_TOKEN}" \
  --header 'Accept: application/json' \
  "${mesh_url}" |
  jq '{name, type, mtls}'
