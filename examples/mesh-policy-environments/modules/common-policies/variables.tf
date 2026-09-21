variable "mesh_control_plane_id" {
  description = "ID of the Konnect Mesh Control Plane."
  type        = string
}

variable "mesh_name" {
  description = "Name of the Mesh that owns these policies."
  type        = string
}

variable "default_circuit_breaker" {
  description = "Connection limits applied to all traffic in the Mesh."
  type = object({
    max_connections      = number
    max_pending_requests = number
    max_requests         = number
    max_retries          = number
  })

  default = {
    max_connections      = 1024
    max_pending_requests = 1024
    max_requests         = 1024
    max_retries          = 3
  }
}
