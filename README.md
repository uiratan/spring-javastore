# Spring Boot na AWS
Projeto para estudo de desenvolvimento de API REST com Spring Boot, deploy na clound com AWS e práticas DevOps.

## API REST
### Spring Boot
Aplicação criada com o [Spring Initializr](https://start.spring.io/) em **Java 17** com **Spring Boot** do tipo **Gradle** Project para gerenciamento das dependências e build do projeto

#### Dependências
* Spring Web
* Spring Actuator
* Spring Data Jpa
* PostgreSQL
* DevTools
* Lombok
* Bean Validation
* OpenAPI 3.0

### Database
* Postgres via Docker 

`docker run –-network beer-net -p 5432:5432 --name postgres -e POSTGRES_USER=grogstore -e POSTGRES_PASSWORD=grogstore -e POSTGRES_DB=grogstore -d postgres:15-alpine`

docker run -p 5432:5432 --name postgres -e POSTGRES_USER=grogstore -e POSTGRES_PASSWORD=grogstore -e POSTGRES_DB=grogstore -d postgres:15-alpine

### Tratamento de erros centralizado com ControllerAdvice
Não trata apenas mensagens de validação, vamos já
prepara a aplicação para retornar mensagens de erro mais amigáveis em diversos cenários.

Utilização do padrão Resource Based Error Codes, que consiste basicamente em criar códigos de erro por recurso. As mensagens são cadastradas em um arquivo de propriedades à parte, podendo também estar em vários idiomas.

### TDD
* JUnit5
* Mockito
* Hamcrest

## DevOps

### AWS
* CloudWatch - Billing
* IAM - Identity & Access Management
* VPC - Virtual Private Cloud
* EC2 - Elastic Cloud Computing
* RDS - Relational Database Service
* ALB - Application Load Balancer
* SNS - Amazon Simple Notification Service

### Terraform
Ferramenta para criars, atualizar e versionar a infraestrutura de uma maneira segura e eficiente.

Provisionar tudo que for necessário na AWS: VPC, instâncias EC2, RDS e Load Balancer. 

O Terraform mantém um estado da infraestrutura, o que permite trabalhar nas mudanças de forma incremental, ou seja, se você criou uma instância EC2 num primeiro momento mas depois resolveu criar um banco RDS, não será necessário destruir o EC2, será somente criado o novo RDS.

Antes de toda a execução é apresentado o plano de execução com todas as mudanças que serão aplicadas na AWS, fornecendo a segurança de executar somente o que se espera que seja aplicado.

`aws rds describe-db-engine-versions --default-only --engine postgres`

### Ansible
Instalar e configurar os softwares necessários nas EC2.

### Docker e Docker Swarm
docker images

docker system prune --all
chmod a+x docker-entrypoint.sh
docker-compose up -d db
docker-compose up -d app
docker-compose logs -f app
docker run -p 8080:8080 -m 600M --memory-swap 800M e JAVA_OPTIONS='-Xmx300m' -d uiratan/grogstore
chmod a+x run-ansible.sh

### Deploy em produção


## Passo a passo 

#### Buildar a aplicação
gradle clean
gradle build

#### Gerar imagem Docker
docker build -t uiratan/grogstore:0.1 .
docker push uiratan/grogstore:0.1

#### Provisionar infraestrutura na AWS
cd terraform
./util/run.sh

Resources: 26

* Backend no S3
* 1 VPC
* 1 subenet pública
* 1 subenet privada
* 1 subnet group de banco de com as subnets privadas associadas a ele
* 1 internet gateway
* 1 tabela de rotas com saída para o igw
* 5 grupos de segurança
* * SSH
* * Conexão ao DB
* * Internet
* * Comunicação do cluster swarm
* * Acesso ao Portainer
* 3 instâncias EC2 (t2.micro) rodando Amazon Linux 
* * 1 par de chaves
* * 1 instância nas primeiras 2 zonas de disponibilidade dentro de uma mesma zona
* * Grupos de segurança: SSH, Internet, cluster e portainer
* * Geração do arquivo hosts.tpl com os IPs das instâncias para utilização pelo Ansible
* 1 DB Postgres 15.3 no RDS
* * Grupo de segurança: DB
* * Associado ao subnet group de banco

public_ip = "3.93.63.105, 54.205.44.59, 18.206.241.133"

#### Instalar software nas instâncias
cd ../ansible
./run-ansible.sh

