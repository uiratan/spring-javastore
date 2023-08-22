variable "cidr_vpc" {
  default = "192.168.0.0/16"
}

# variable "aws_region" {
#   description = "AWS region"
#   type        = string
#   default     = "us-east-1"
# }

variable "availability_zones" {
  default = [
    "us-east-1a",
    "us-east-1b",
    "us-east-1c",
    "us-east-1d",
    "us-east-1e",
    "us-east-1f",
  ]
}

variable "aws_linux_ami" {
  default = "ami-08a52ddb321b32a8c"
}

variable "instance_type" {
  default = "t2.micro"
}

variable "my_public_cidr" {}