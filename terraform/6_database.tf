module "db" {
  source = "terraform-aws-modules/rds/aws"
  # version                = "6.1.1"
  identifier        = "grogstore-rds"
  engine            = "postgres"
  engine_version    = "15.3"
  family            = "postgres15"
  instance_class    = "db.t3.micro"
  allocated_storage = "10"
  db_name           = "grogstore"
  username          = "grogstore"
  password          = var.db_password
  port              = "5432"

  vpc_security_group_ids = [aws_security_group.allow_db_connection.id]
  db_subnet_group_name   = aws_db_subnet_group.db_subnet.id

  maintenance_window = "Thu:03:30-Thu:05:30"
  backup_window      = "05:30-06:30"
  storage_type       = "gp2"
  multi_az           = "false"
  # sensitive = true
  tags = {
    Name = "grogstore_db"
  }
}

