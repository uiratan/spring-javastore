output "public_ip" {
  value = join(", ", aws_instance.instances.*.public_ip)
}

output "aws_vpc_main" {
  description = "VPC"
  value       = aws_vpc.main
}

# output "public_subnet" {
#   description = "Subnets púlicas"
#   value       = aws_subnet.public_subnet
# }

output "private_subnet" {
  description = "Subnets privadas"
  value       = aws_subnet.private_subnet
}

output "aws_db_subnet_group" {
  description = "Subnet do Banco de Dados"
  value       = aws_db_subnet_group.db_subnet
}

# output "aws_internet_gateway" {
#   description = "Internet Gateway"
#   value       = aws_internet_gateway.igw
# }

# output "aws_route_table_route_igw" {
#   description = "Tabela de rotas do tráfego externo para o internet gateway"
#   value       = aws_route_table.route_igw
# }

# output "aws_route_table_association" {
#   description = "Associação da tabela de rotas com uma subrede"
#   value       = aws_route_table_association.route_table_association
# }

# output "aws_instance" {
#   description = "Instâncias"
#   value       = aws_instance.instances
# }

# output "aws_security_group_allow_ssh" {
#   description = "Grupos de Segurança"
#   value       = aws_security_group.allow_ssh
# }

output "module_db" {
  description = "Banco de Dados"
  value       = module.db
  sensitive   = true
}


