locals {
  mesh_names = toset(["poc", "sd"])

  system_accounts = {
    for account in flatten([
      for mesh_name in local.mesh_names : [
        for account_type, role_name in {
          policy-manager = "Admin"
          connector      = "Connector"
          } : {
          key          = "${mesh_name}-${account_type}"
          mesh_name    = mesh_name
          account_type = account_type
          role_name    = role_name
        }
      ]
    ]) : account.key => account
  }
}

resource "konnect_mesh_control_plane" "this" {
  for_each = local.mesh_names

  name        = each.key
  description = "${upper(each.key)} Mesh Control Plane managed by Terraform."
}

resource "konnect_mesh" "this" {
  provider = konnect-beta
  for_each = local.mesh_names

  cp_id                          = konnect_mesh_control_plane.this[each.key].id
  name                           = each.key
  type                           = "Mesh"
  skip_creating_initial_policies = ["*"]
}

resource "konnect_system_account" "this" {
  for_each = local.system_accounts

  name            = "${each.value.mesh_name}-mesh-${each.value.account_type}"
  description     = "${each.value.account_type} system account for the ${each.value.mesh_name} mesh."
  konnect_managed = false
}

resource "konnect_system_account_role" "mesh_control_plane" {
  for_each = local.system_accounts

  account_id       = konnect_system_account.this[each.key].id
  entity_id        = konnect_mesh_control_plane.this[each.value.mesh_name].id
  entity_region    = "au"
  entity_type_name = "Mesh Control Planes"
  role_name        = each.value.role_name
}
