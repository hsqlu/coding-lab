#!/usr/bin/env bash
set -uo pipefail

# Read-only inventory for a Kubernetes-hosted Kong Mesh global control plane.
# Every kubectl invocation receives --context explicitly; this script never
# changes the current context in kubeconfig.

KUBE_CONTEXT="${KUBE_CONTEXT:-${ACTIVE_KUBE_CONTEXT:-}}"
MESH_NAME="${MESH_NAME:-}"

section() {
  printf '\n################################################################################\n'
  printf '# %s\n' "$1"
  printf '################################################################################\n'
}

info() {
  printf 'INFO: %s\n' "$*"
}

warn() {
  printf 'WARN: %s\n' "$*" >&2
}

fail() {
  printf 'ERROR: %s\n' "$*" >&2
  exit 1
}

kctl() {
  kubectl --context "${KUBE_CONTEXT}" "$@"
}

list_resource() {
  local scope="$1"
  local resource="$2"
  local -a args=(get "${resource}" --ignore-not-found)

  if [[ "${scope}" == "namespaced" ]]; then
    args+=(-A)
    if [[ -n "${MESH_NAME}" ]]; then
      args+=(-l "kuma.io/mesh=${MESH_NAME}")
    fi
  fi

  printf '\n--- %s ---\n' "${resource}"
  if ! kctl "${args[@]}"; then
    warn "Could not list ${resource}; check RBAC permissions and CRD health."
    return 1
  fi
}

command -v kubectl >/dev/null 2>&1 || fail "kubectl is required"

if [[ -z "${KUBE_CONTEXT}" ]]; then
  fail "Set KUBE_CONTEXT (or ACTIVE_KUBE_CONTEXT) to the kubeconfig context to query."
fi

context_found=false
while IFS= read -r configured_context; do
  if [[ "${configured_context}" == "${KUBE_CONTEXT}" ]]; then
    context_found=true
    break
  fi
done < <(kubectl config get-contexts -o name)

if [[ "${context_found}" != "true" ]]; then
  fail "Kubeconfig context '${KUBE_CONTEXT}' does not exist. Run: kubectl config get-contexts"
fi

section "Target cluster"
printf 'Context: %s\n' "${KUBE_CONTEXT}"
printf 'Mesh filter: %s\n' "${MESH_NAME:-<all meshes>}"
printf 'UTC time: %s\n' "$(date -u +'%Y-%m-%dT%H:%M:%SZ')"

if ! kctl cluster-info; then
  fail "Cannot connect to context '${KUBE_CONTEXT}'."
fi

section "Kong Mesh control-plane workloads"
if ! kctl get deployments -A \
  -l 'app.kubernetes.io/component=control-plane' \
  -o wide; then
  warn "Unable to list control-plane deployments."
fi

section "Installed kuma.io API resources"
if ! kctl api-resources --api-group=kuma.io; then
  fail "The kuma.io API group is unavailable or cannot be listed."
fi

section "Meshes"
if [[ -n "${MESH_NAME}" ]]; then
  if ! kctl get meshes.kuma.io "${MESH_NAME}" -o wide; then
    fail "Mesh '${MESH_NAME}' was not found or is not readable."
  fi
else
  if ! kctl get meshes.kuma.io -o wide; then
    fail "Unable to list meshes. Verify this is the global control-plane cluster."
  fi
fi

section "Cluster-scoped kuma.io resources"
cluster_resources="$(
  kctl api-resources \
    --api-group=kuma.io \
    --namespaced=false \
    --verbs=list \
    -o name
)" || fail "Unable to discover cluster-scoped kuma.io resources."

while IFS= read -r resource; do
  [[ -z "${resource}" ]] && continue
  list_resource cluster "${resource}" || true
done <<<"${cluster_resources}"

section "Namespaced kuma.io resources"
if [[ -n "${MESH_NAME}" ]]; then
  info "Filtering resources with label kuma.io/mesh=${MESH_NAME}"
fi

namespaced_resources="$(
  kctl api-resources \
    --api-group=kuma.io \
    --namespaced=true \
    --verbs=list \
    -o name
)" || fail "Unable to discover namespaced kuma.io resources."

while IFS= read -r resource; do
  [[ -z "${resource}" ]] && continue
  list_resource namespaced "${resource}" || true
done <<<"${namespaced_resources}"

section "Inventory complete"
info "No kubeconfig context was changed. All queries used --context ${KUBE_CONTEXT}."
