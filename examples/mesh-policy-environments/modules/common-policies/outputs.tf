output "circuit_breaker_name" {
  description = "Name of the common MeshCircuitBreaker."
  value       = konnect_mesh_circuit_breaker.default.name
}
