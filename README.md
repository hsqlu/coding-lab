# Kong Konnect meshes

This Terraform configuration creates:

- `poc` and `sd` Mesh Control Planes
- one same-named Mesh in each control plane
- one policy-manager system account per mesh with the `Admin` role
- one connector system account per mesh with only the `Connector` role

All resources are restricted to the Konnect `au` region.

## Usage

Create a Konnect personal access token and expose it without committing it:

```shell
export KONNECT_TOKEN="<your-personal-access-token>"
```

```shell
terraform init
terraform plan
terraform apply
```

The Mesh resources use Kong's beta provider because the Mesh API is not yet
available in the GA provider. Mesh Control Planes, system accounts, and role
assignments use the GA provider.

## GitHub Actions

The `Terraform Plan` workflow runs for changes targeting `main`, pushes to
`main`, and manual dispatches. Add the Konnect personal access token as an
Actions repository secret named `KONNECT_TOKEN`. The workflow only creates a
plan and never applies it.

## Kong Mesh resource inventory

Use the read-only inventory script against a Kubernetes-hosted global control
plane. The target context is required and is passed explicitly to every
`kubectl` command, so the script never changes the kubeconfig current context.

```shell
KUBE_CONTEXT=my-global-cluster \
  ./scripts/list-kong-mesh-resources.sh
```

Optionally restrict namespaced resources to one mesh:

```shell
KUBE_CONTEXT=my-global-cluster MESH_NAME=poc \
  ./scripts/list-kong-mesh-resources.sh
```

`ACTIVE_KUBE_CONTEXT` is accepted as an alias for `KUBE_CONTEXT`.

## Apply a Mesh through the Konnect API

Use the Konnect Mesh HTTP API when the beta Terraform provider is not approved.
The script configures a built-in CA and defaults to a dry run:

```shell
KONNECT_CONTROL_PLANE_ID=<control-plane-id> \
KONNECT_TOKEN=<personal-access-token> \
  ./scripts/apply-konnect-mesh.sh
```

Review the payload and diff, then apply it explicitly:

```shell
KONNECT_CONTROL_PLANE_ID=<control-plane-id> \
KONNECT_TOKEN=<personal-access-token> \
APPLY=true \
  ./scripts/apply-konnect-mesh.sh
```

Defaults are region `au`, mesh `baas`, backend `baas-ca`, CA RSA 2048 with a
10-year expiration, and a one-day data-plane certificate expiration. Override
them with `KONNECT_REGION`, `MESH_NAME`, `CA_BACKEND_NAME`, `CA_RSA_BITS`,
`CA_EXPIRATION`, and `DP_CERT_EXPIRATION`.

## Mesh policies by environment

The [`examples/mesh-policy-environments`](examples/mesh-policy-environments)
example separates policies shared by every environment from policies owned by
one environment. It uses reusable modules and a separate Terraform root and
state for POC and production.

The example includes a common `MeshCircuitBreaker` baseline and environment-
specific service circuit breakers managed with `for_each`.

## Configure Konnect organization OIDC

The `Configure Konnect OIDC` workflow configures organization-level OIDC SSO
through the Konnect Identity API. It runs only through manual dispatch and
always executes a preview before an optional apply.

Configure these GitHub Actions secrets:

- `KONNECT_TOKEN`: a PAT or system-account token with Konnect identity-admin
  permissions
- `KONNECT_OIDC_CLIENT_SECRET`: the client secret from the OIDC application

Create a protected GitHub Environment named `konnect-production` and require
reviewers for it. Select `apply=true` to enter that approval gate. Keep
`enable_oidc=false` for the initial provider configuration, then test the
login path before enabling OIDC in a later run.

The workflow intentionally never disables built-in authentication. Kong
recommends keeping it enabled until OIDC login has been tested successfully.
