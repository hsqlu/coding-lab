#!/usr/bin/env bash
set -Eeuo pipefail

KONNECT_REGION="${KONNECT_REGION:-au}"
KONNECT_HOST="${KONNECT_REGION}.api.konghq.com"
KONNECT_URL="https://${KONNECT_HOST}"

log() {
  printf '\n==> %s\n' "$*"
}

fail() {
  printf 'ERROR: %s\n' "$*" >&2
  exit 1
}

command -v curl >/dev/null || fail "curl is required"
command -v openssl >/dev/null || fail "openssl is required"

log "Environment"
printf 'UTC time: %s\n' "$(date -u +'%Y-%m-%dT%H:%M:%SZ')"
printf 'Runner: %s %s\n' "$(uname -s)" "$(uname -m)"
printf 'Konnect endpoint: %s\n' "${KONNECT_URL}"
printf 'HTTPS_PROXY configured: %s\n' "$([[ -n "${HTTPS_PROXY:-${https_proxy:-}}" ]] && printf yes || printf no)"
printf 'NO_PROXY configured: %s\n' "$([[ -n "${NO_PROXY:-${no_proxy:-}}" ]] && printf yes || printf no)"

log "DNS resolution"
if command -v getent >/dev/null; then
  getent ahosts "${KONNECT_HOST}" | awk '!seen[$1]++ { print $1 }' || fail "DNS lookup failed"
else
  nslookup "${KONNECT_HOST}" || fail "DNS lookup failed"
fi

log "TLS handshake on port 443"
tls_output="$(
  timeout 20 openssl s_client \
    -connect "${KONNECT_HOST}:443" \
    -servername "${KONNECT_HOST}" \
    -brief </dev/null 2>&1
)" || {
  printf '%s\n' "${tls_output}" >&2
  fail "TCP connection or TLS handshake failed"
}
printf '%s\n' "${tls_output}" | sed -n '1,8p'

log "Anonymous HTTPS request"
anonymous_status="$(
  curl --silent --show-error \
    --globoff \
    --output /dev/null \
    --connect-timeout 10 \
    --max-time 30 \
    --retry 2 \
    --write-out '%{http_code} dns=%{time_namelookup}s connect=%{time_connect}s tls=%{time_appconnect}s total=%{time_total}s' \
    "${KONNECT_URL}/"
)" || fail "HTTPS request failed before receiving an HTTP response"
printf 'Result: %s\n' "${anonymous_status}"
printf 'Any HTTP status (including 401/403/404) proves network and TLS connectivity.\n'

if [[ -z "${KONNECT_TOKEN:-}" ]]; then
  log "Authenticated API request skipped"
  printf 'KONNECT_TOKEN is not set. Network checks completed successfully.\n'
  exit 0
fi

log "Authenticated read-only API request"
authenticated_status="$(
  curl --silent --show-error \
    --globoff \
    --output /dev/null \
    --connect-timeout 10 \
    --max-time 30 \
    --retry 2 \
    --header "Authorization: Bearer ${KONNECT_TOKEN}" \
    --write-out '%{http_code} dns=%{time_namelookup}s connect=%{time_connect}s tls=%{time_appconnect}s total=%{time_total}s' \
    "${KONNECT_URL}/v3/system-accounts?page[size]=1"
)" || fail "Authenticated request failed before receiving an HTTP response"

printf 'Result: %s\n' "${authenticated_status}"
http_code="${authenticated_status%% *}"

case "${http_code}" in
  2??)
    printf 'SUCCESS: Konnect is reachable and the token can access the API.\n'
    ;;
  401)
    fail "Konnect is reachable, but the token is missing, invalid, or expired"
    ;;
  403)
    fail "Konnect is reachable and authenticated, but the token lacks permission"
    ;;
  *)
    fail "Konnect is reachable but returned unexpected HTTP ${http_code}"
    ;;
esac
