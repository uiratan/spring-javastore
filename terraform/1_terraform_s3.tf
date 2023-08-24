terraform {
  backend "s3" {
    bucket = "uira-testes"
    key    = "grogstore-infra"
    region = "us-east-1"
  }
}