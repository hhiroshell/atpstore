resource "oci_core_instance" "gatling" {
    compartment_id      = "${var.compartment_ocid}"
    availability_domain = "${var.core_instance_availability_domain}"
    display_name        = "${var.core_instance_name}"
    shape               = "${var.core_instance_shape}"

    create_vnic_details {
        subnet_id        = "${var.core_instance_subnet_ocid}"
    }

    source_details {
        source_type = "image"
        source_id   = "${var.instance_image_ocid[var.region]}"
    }

    metadata = {
        ssh_authorized_keys = "${file(var.core_instance_ssh_public_key_file)}"
    }

    connection {
        type        = "ssh"
        host        = "${self.public_ip}"
        user        = "opc"
        private_key = "${file(var.core_instance_ssh_private_key_file)}"
    }

    provisioner "remote-exec" {
        inline = [
            "echo 'This instance was provisioned by Terraform.' | sudo tee /etc/motd",
        ]
    }

}