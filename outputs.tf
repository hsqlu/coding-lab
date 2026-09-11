output "mesh_control_plane_ids" {
  description = "IDs of the poc and sd Mesh Control Planes."
  value = {
    for name, control_plane in konnect_mesh_control_plane.this : name => control_plane.id
  }
}

output "mesh_names" {
  description = "Names of the meshes created in Konnect."
  value       = sort([for mesh in konnect_mesh.this : mesh.name])
}

output "system_account_ids" {
  description = "System account IDs grouped by their Terraform keys."
  value = {
    for key, account in konnect_system_account.this : key => account.id
  }
}

