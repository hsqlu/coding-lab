resource "konnect_mesh_circuit_breaker" "this" {
  provider = konnect-beta

  cp_id = var.mesh_control_plane_id
  mesh  = var.mesh_name
  type  = "MeshCircuitBreaker"
  name  = var.policy_name

  spec = {
    target_ref = {
      kind = "Mesh"
    }

    to = [
      {
        target_ref = {
          kind         = "MeshService"
          name         = var.service_name
          section_name = var.section_name
        }

        default = {
          connection_limits = {
            max_connections      = tostring(var.limits.max_connections)
            max_pending_requests = tostring(var.limits.max_pending_requests)
            max_requests         = tostring(var.limits.max_requests)
            max_retries          = tostring(var.limits.max_retries)
          }
        }
      }
    ]
  }
}
