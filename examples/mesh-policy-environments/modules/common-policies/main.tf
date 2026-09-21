resource "konnect_mesh_circuit_breaker" "default" {
  provider = konnect-beta

  cp_id = var.mesh_control_plane_id
  mesh  = var.mesh_name
  type  = "MeshCircuitBreaker"
  name  = "common-default-circuit-breaker"

  spec = {
    target_ref = {
      kind = "Mesh"
    }

    to = [
      {
        target_ref = {
          kind = "Mesh"
        }

        default = {
          connection_limits = {
            max_connections      = tostring(var.default_circuit_breaker.max_connections)
            max_pending_requests = tostring(var.default_circuit_breaker.max_pending_requests)
            max_requests         = tostring(var.default_circuit_breaker.max_requests)
            max_retries          = tostring(var.default_circuit_breaker.max_retries)
          }
        }
      }
    ]
  }
}
