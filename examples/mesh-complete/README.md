# Complete Konnect Mesh example

This standalone example creates a Mesh inside an existing Konnect Mesh Control
Plane. It demonstrates:

- `constraints.dataplane_proxy.requirements`
- `mesh_services.mode`
- a built-in mTLS CA and dataplane certificate rotation
- outbound networking passthrough
- routing controls
- skipping automatically created policies

Authenticate without putting a token in Terraform configuration or state:

```shell
export KONNECT_TOKEN="<personal-access-token>"
cp terraform.tfvars.example terraform.tfvars
```

Replace `mesh_control_plane_id`, then run:

```shell
terraform init
terraform plan
terraform apply
```

The dataplane requirement uses `"kuma.io/zone" = "*"`, so only dataplanes
with a non-empty zone tag can join. Adapt the requirements to the tags emitted
by your workloads before applying this example.

The built-in backend creates a new CA in Konnect; matching an old CA's name,
RSA size, and expiration does not preserve its certificate or private key.

Because all initial policies are skipped and mTLS is enabled, create an
appropriate `MeshTrafficPermission` before connecting production workloads.
