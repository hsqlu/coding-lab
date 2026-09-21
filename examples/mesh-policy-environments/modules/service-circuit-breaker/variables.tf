variable "mesh_control_plane_id" {
  description = "ID of the Konnect Mesh Control Plane."
  type        = string
}

variable "mesh_name" {
  description = "Name of the Mesh that owns this policy."
  type        = string
}

variable "policy_name" {
  description = "Unique name of the MeshCircuitBreaker."
  type        = string
}

variable "service_name" {
  description = "Name of the target MeshService in Konnect."
  type        = string
}

variable "section_name" {
  description = "Optional named port section on the target MeshService."
  type        = string
  default     = null
}

variable "limits" {
  description = "Connection limits for the target MeshService."
  type = object({
    max_connections      = number
    max_pending_requests = number
    max_requests         = number
    max_retries          = number
  })
}
