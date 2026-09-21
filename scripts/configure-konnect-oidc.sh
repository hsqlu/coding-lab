#!/usr/bin/env bash
set -Eeuo pipefail

# Configure organization-level OIDC SSO in Kong Konnect. The script previews
# by default and mutates Konnect only when APPLY=true.

KONNECT_TOKEN="${KONNECT_TOKEN:-}"
OIDC_ISSUER_URL="${OIDC_ISSUER_URL:-}"
OIDC_CLIENT_ID="${OIDC_CLIENT_ID:-}"
OIDC_CLIENT_SECRET="${OIDC_CLIENT_SECRET:-}"
OIDC_LOGIN_PATH="${OIDC_LOGIN_PATH:-}"
OIDC_NAME_CLAIM="${OIDC_NAME_CLAIM:-name}"
OIDC_EMAIL_CLAIM="${OIDC_EMAIL_CLAIM:-email}"
OIDC_GROUPS_CLAIM="${OIDC_GROUPS_CLAIM:-groups}"
ENABLE_OIDC="${ENABLE_OIDC:-false}"
APPLY="${APPLY:-false}"

api_base="https://global.api.konghq.com/v3"

fail() {
  printf 'ERROR: %s\n' "$*" >&2
  exit 1
}

log() {
  printf '\n==> %s\n' "$*"
}

for command_name in curl jq; do
  command -v "${command_name}" >/dev/null 2>&1 || \
    fail "${command_name} is required"
done

[[ -n "${KONNECT_TOKEN}" ]] || fail "KONNECT_TOKEN is required"
[[ -n "${OIDC_ISSUER_URL}" ]] || fail "OIDC_ISSUER_URL is required"
[[ -n "${OIDC_CLIENT_ID}" ]] || fail "OIDC_CLIENT_ID is required"
[[ -n "${OIDC_CLIENT_SECRET}" ]] || fail "OIDC_CLIENT_SECRET is required"
[[ "${OIDC_LOGIN_PATH}" =~ ^[A-Za-z0-9]+$ ]] || \
  fail "OIDC_LOGIN_PATH must contain only letters and numbers"
[[ "${ENABLE_OIDC}" == "true" || "${ENABLE_OIDC}" == "false" ]] || \
  fail "ENABLE_OIDC must be true or false"
[[ "${APPLY}" == "true" || "${APPLY}" == "false" ]] || \
  fail "APPLY must be true or false"

if [[ -n "${GITHUB_ACTIONS:-}" ]]; then
  printf '::add-mask::%s\n' "${KONNECT_TOKEN}"
  printf '::add-mask::%s\n' "${OIDC_CLIENT_SECRET}"
fi

work_dir="$(mktemp -d)"
trap 'rm -rf "${work_dir}"' EXIT
chmod 700 "${work_dir}"

providers_file="${work_dir}/identity-providers.json"
settings_file="${work_dir}/authentication-settings.json"
payload_file="${work_dir}/oidc-payload.json"
response_file="${work_dir}/response.json"

auth_header="Authorization: Bearer ${KONNECT_TOKEN}"

log "Validate OIDC discovery endpoint"
issuer="${OIDC_ISSUER_URL%/}"
curl --silent --show-error --fail-with-body \
  --connect-timeout 10 \
  --max-time 30 \
  --header 'Accept: application/json' \
  "${issuer}/.well-known/openid-configuration" |
  jq -e --arg issuer "${issuer}" '
    .issuer != null and
    .authorization_endpoint != null and
    .token_endpoint != null and
    .jwks_uri != null
  ' >/dev/null || fail "The issuer did not return valid OIDC discovery metadata"

log "Read current Konnect identity providers"
curl --silent --show-error --fail-with-body \
  --connect-timeout 10 \
  --max-time 30 \
  --header "${auth_header}" \
  --header 'Accept: application/json' \
  "${api_base}/identity-providers" >"${providers_file}"

oidc_provider_count="$(
  jq '[((.data // .items // .) | if type == "array" then . else [] end)[] | select(.type == "oidc")] | length' \
    "${providers_file}"
)"

if [[ "${oidc_provider_count}" -gt 1 ]]; then
  fail "More than one OIDC identity provider was returned; refusing an ambiguous update"
fi

oidc_provider_id="$(
  jq -r '((.data // .items // .) | if type == "array" then . else [] end)[] | select(.type == "oidc") | .id' \
    "${providers_file}"
)"

jq -n \
  --arg type "oidc" \
  --argjson enabled "${ENABLE_OIDC}" \
  --arg login_path "${OIDC_LOGIN_PATH}" \
  --arg issuer_url "${issuer}" \
  --arg client_id "${OIDC_CLIENT_ID}" \
  --arg client_secret "${OIDC_CLIENT_SECRET}" \
  --arg name_claim "${OIDC_NAME_CLAIM}" \
  --arg email_claim "${OIDC_EMAIL_CLAIM}" \
  --arg groups_claim "${OIDC_GROUPS_CLAIM}" \
  '{
    type: $type,
    enabled: $enabled,
    login_path: $login_path,
    config: {
      issuer_url: $issuer_url,
      client_id: $client_id,
      client_secret: $client_secret,
      scopes: ["openid", "profile", "email"],
      claim_mappings: {
        name: $name_claim,
        email: $email_claim,
        groups: $groups_claim
      }
    }
  }' >"${payload_file}"

log "Desired OIDC configuration"
jq '.config.client_secret = "<redacted>"' "${payload_file}"

if [[ -n "${oidc_provider_id}" ]]; then
  printf 'Action: update OIDC identity provider %s\n' "${oidc_provider_id}"
else
  printf 'Action: create OIDC identity provider\n'
fi

printf 'OIDC authentication requested: %s\n' "${ENABLE_OIDC}"
printf 'Built-in authentication will not be disabled by this script.\n'
printf 'Login test URL: https://cloud.konghq.com/login/%s\n' "${OIDC_LOGIN_PATH}"

if [[ "${APPLY}" != "true" ]]; then
  log "Preview complete"
  printf 'No Konnect configuration was changed.\n'
  exit 0
fi

log "Create or update the OIDC identity provider"
if [[ -n "${oidc_provider_id}" ]]; then
  method="PATCH"
  provider_url="${api_base}/identity-providers/${oidc_provider_id}"
else
  method="POST"
  provider_url="${api_base}/identity-providers"
fi

curl --silent --show-error --fail-with-body \
  --request "${method}" \
  --connect-timeout 10 \
  --max-time 60 \
  --header "${auth_header}" \
  --header 'Accept: application/json' \
  --header 'Content-Type: application/json' \
  --data-binary "@${payload_file}" \
  "${provider_url}" >"${response_file}"

created_provider_id="$(jq -r '.id // empty' "${response_file}")"
if [[ -n "${created_provider_id}" ]]; then
  oidc_provider_id="${created_provider_id}"
fi

if [[ "${ENABLE_OIDC}" == "true" ]]; then
  log "Enable OIDC authentication"
  curl --silent --show-error --fail-with-body \
    --request PATCH \
    --connect-timeout 10 \
    --max-time 30 \
    --header "${auth_header}" \
    --header 'Accept: application/json' \
    --header 'Content-Type: application/json' \
    --data-binary '{"oidc_auth_enabled":true}' \
    "${api_base}/authentication-settings" >"${settings_file}"
else
  log "OIDC provider saved but authentication was not enabled"
fi

log "Verify Konnect authentication settings"
curl --silent --show-error --fail-with-body \
  --connect-timeout 10 \
  --max-time 30 \
  --header "${auth_header}" \
  --header 'Accept: application/json' \
  "${api_base}/authentication-settings" |
  jq '{basic_auth_enabled, oidc_auth_enabled, saml_auth_enabled, idp_mapping_enabled, konnect_mapping_enabled}'

printf '\nSUCCESS: OIDC identity provider %s was configured.\n' "${oidc_provider_id:-<unknown>}"
printf 'Test login at https://cloud.konghq.com/login/%s before changing built-in authentication.\n' \
  "${OIDC_LOGIN_PATH}"
