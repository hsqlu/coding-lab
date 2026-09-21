variable "konnect_region" {
  description = "Konnect geographic region."
  type        = string
  default     = "au"

  validation {
    condition     = var.konnect_region == "au"
    error_message = "This example is restricted to the Konnect au region."
  }
}

variable "environment" {
  description = "Environment identifier used in policy names."
  type        = string
  default     = "production"
}

variable "mesh_control_plane_id" {
  description = "ID of the existing Konnect Mesh Control Plane."
  type        = string
}

variable "mesh_name" {
  description = "Name of the existing Mesh."
  type        = string
}

variable "service_circuit_breakers" {
  description = "Circuit breakers that exist only in this environment."
  type = map(object({
    service_name = string
    section_name = optional(string)
    limits = object({
      max_connections      = number
      max_pending_requests = number
      max_requests         = number
      max_retries          = number
    })
  }))
  default = {}
}
