output "common_circuit_breaker" {
  value = module.common_policies.circuit_breaker_name
}

output "environment_circuit_breakers" {
  value = { for key, policy in module.service_circuit_breakers : key => policy.name }
}
