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
