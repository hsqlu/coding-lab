variable "konnect_region" {
  description = "Kong Konnect geographic API region."
  type        = string
  default     = "au"

  validation {
    condition     = contains(["us", "eu", "au", "me", "in", "sg"], var.konnect_region)
    error_message = "konnect_region must be one of: us, eu, au, me, in, sg."
  }
}

variable "mesh_control_plane_id" {
  description = "ID of an existing Konnect Mesh Control Plane."
  type        = string
}

variable "mesh_name" {
  description = "Name of the Mesh to create."
  type        = string
  default     = "baas"
}
