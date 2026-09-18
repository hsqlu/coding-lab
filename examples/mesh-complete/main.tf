resource "konnect_mesh" "this" {
  provider = konnect-beta

  cp_id = var.mesh_control_plane_id
  name  = var.mesh_name
  type  = "Mesh"

  # Require every joining dataplane proxy to have a non-empty zone tag.
  # Multiple requirement entries are OR conditions; tags within one entry
  # are AND conditions.
  constraints = {
    dataplane_proxy = {
      requirements = [
        {
          tags = {
            "kuma.io/zone" = "*"
          }
        }
      ]
    }
  }

  # Generate MeshService resources for services in every connected zone.
  mesh_services = {
    mode = {
      str = "Everywhere"
    }
  }

  # Built-in CA: RSA 2048, CA expires after 10 years, and dataplane
  # certificates expire after one day.
  mtls = {
    enabled_backend = "baas-ca"

    backends = [
      {
        name = "baas-ca"
        type = "builtin"

        dp_cert = {
          rotation = {
            expiration = "1d"
          }
        }

        conf = {
          builtin_certificate_authority_config = {
            ca_cert = {
              rsa_bits   = 2048
              expiration = "10y"
            }
          }
        }
      }
    ]
  }

  networking = {
    outbound = {
      # Allow traffic to destinations not known to the Mesh.
      passthrough = true
    }
  }

  routing = {
    # Keep these false unless ZoneEgress and external-service restrictions
    # have been deliberately designed and deployed.
    default_forbid_mesh_external_service_access = false
    locality_aware_load_balancing               = false
    zone_egress                                 = false
  }

  # Do not let Konnect create policies outside Terraform ownership.
  # Define the required MeshTrafficPermission before connecting production
  # workloads because mTLS defaults to denying traffic without permission.
  skip_creating_initial_policies = ["*"]
}
