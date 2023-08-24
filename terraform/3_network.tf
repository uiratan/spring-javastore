resource "aws_vpc" "main" {
  cidr_block = var.cidr_vpc
  tags = {
    Name = "grogstore-vpc"
  }
}

resource "aws_subnet" "public_subnet" {
  count                   = 3
  vpc_id                  = aws_vpc.main.id
  cidr_block              = cidrsubnet(var.cidr_vpc, 8, count.index + 10)
  availability_zone       = var.availability_zones[count.index]
  map_public_ip_on_launch = true
  tags = {
    Name = "grogstore_public_subnet_${count.index}"
  }
}

resource "aws_subnet" "private_subnet" {
  count             = 3
  vpc_id            = aws_vpc.main.id
  cidr_block        = cidrsubnet(var.cidr_vpc, 8, count.index + 20)
  availability_zone = var.availability_zones[count.index]
  tags = {
    Name = "grogstore_private_subnet_${count.index}"
  }
}

resource "aws_internet_gateway" "igw" {
  vpc_id = aws_vpc.main.id
  tags = {
    Name = "grogstore-igw"
  }
}

resource "aws_route_table" "route_igw" {
  vpc_id = aws_vpc.main.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.igw.id
  }
  tags = {
    Name = "grogstore-route-table"
  }
}

# route associations public
resource "aws_route_table_association" "route_table_association" {
  count          = 3
  subnet_id      = element(aws_subnet.public_subnet.*.id, count.index)
  route_table_id = aws_route_table.route_igw.id
}

resource "aws_db_subnet_group" "db_subnet" {
  name       = "grogstore_db_subnet"
  subnet_ids = flatten(chunklist(aws_subnet.private_subnet.*.id, 1))
  tags = {
    Name = "grogstore_db_subnet"
  }
}