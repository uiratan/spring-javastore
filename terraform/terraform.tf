terraform {
  backend "s3" {
    bucket = "uira-testes"
    key    = "grogstore-infra"
    region = "us-east-1"
  }
}

# data "terraform_remote_state" "network" {
#   backend = "s3"
#   config = {
#     bucket = "uira-testes"
#     key    = "grogstore-infra"
#     region = "us-east-1"
#   }
# }
