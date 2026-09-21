# Kong Mesh policy modules by environment

This example shows how to manage policies that apply to every Kong Mesh
environment alongside policies that apply to only one environment.

The design has three rules:

1. Reusable modules contain common policy definitions and small policy
   building blocks.
2. Every environment is a separate Terraform root module with its own state.
3. A Konnect resource is owned by exactly one Terraform state.

## Layout

```text
mesh-policy-environments/
├── modules/
│   ├── common-policies/
│   └── service-circuit-breaker/
└── environments/
    ├── poc/
    └── production/
```

`common-policies` is instantiated once in every environment. The
`service-circuit-breaker` module is instantiated with `for_each` only for the
services configured by that environment.

## Configure an environment

Copy the example variable file in the environment to be managed:

```shell
cd environments/poc
cp terraform.tfvars.example terraform.tfvars
```

Set the existing Konnect Mesh Control Plane ID and Mesh name. Authentication is
read from `KONNECT_TOKEN`; do not put a token in `terraform.tfvars`:

```shell
export KONNECT_TOKEN='<personal-or-system-account-token>'
terraform init
terraform plan
```

The service names and section names must match the `MeshService` resources
visible in Konnect after the zone and workloads have registered.

## Remote state

Keep each environment in a separate state. For an Azure backend, initialize
each root with a different key, for example:

```shell
terraform init \
  -backend-config=resource_group_name=rg-terraform-state \
  -backend-config=storage_account_name=sttfstate \
  -backend-config=container_name=tfstate \
  -backend-config=key=kong-mesh/poc.tfstate \
  -backend-config=use_azuread_auth=true
```

Use `kong-mesh/production.tfstate` for production. Backend values are not
committed in this example because they are organization-specific.

## Promotion

For a common-policy change, generate and apply plans in this order:

```text
POC -> validate -> production plan -> approval -> production apply
```

The example deliberately does not use Terraform workspaces. Separate roots and
state keys make environment ownership, approvals, plans, and rollback clearer.
