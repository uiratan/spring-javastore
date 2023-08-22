resource "aws_key_pair" "keypair" {
  key_name   = "grogstore"
  public_key = file("key/beer_key.pub")
}

resource "aws_instance" "instances" {
  count                  = 3
  ami                    = var.aws_linux_ami
  instance_type          = var.instance_type
  subnet_id              = element(aws_subnet.public_subnet.*.id, count.index)
  vpc_security_group_ids = ["${aws_security_group.allow_ssh.id}"]
  key_name               = aws_key_pair.keypair.key_name
  tags = {
    Name = "grogstore_instances"
  }
  depends_on = [aws_internet_gateway.igw]
}

output "public_ip" {
  value = join(", ", aws_instance.instances.*.public_ip)
}
