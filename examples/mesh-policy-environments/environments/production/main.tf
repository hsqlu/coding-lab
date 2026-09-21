module "common_policies" {
  source = "../../modules/common-policies"

  providers = {
    konnect-beta = konnect-beta
  }

  mesh_control_plane_id = var.mesh_control_plane_id
  mesh_name             = var.mesh_name

  default_circuit_breaker = {
    max_connections      = 1024
    max_pending_requests = 1024
    max_requests         = 1024
    max_retries          = 3
  }
}

module "service_circuit_breakers" {
  for_each = var.service_circuit_breakers

  source = "../../modules/service-circuit-breaker"

  providers = {
    konnect-beta = konnect-beta
  }

  mesh_control_plane_id = var.mesh_control_plane_id
  mesh_name             = var.mesh_name
  policy_name           = "${var.environment}-${each.key}-circuit-breaker"
  service_name          = each.value.service_name
  section_name          = try(each.value.section_name, null)
  limits                = each.value.limits
}
