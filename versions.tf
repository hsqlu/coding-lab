terraform {
  required_version = ">= 1.5.0"

  required_providers {
    konnect = {
      source  = "kong/konnect"
      version = "~> 3.22.0"
    }

    konnect-beta = {
      source  = "kong/konnect-beta"
      version = "~> 0.20.0"
    }
  }
}

