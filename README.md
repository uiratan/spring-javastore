# Spring Boot na AWS
Projeto para estudo de desenvolvimento de API REST com Spring Boot, deploy na clound com
AWS e usando práticas DevOps.


## API REST
### Spring Boot
Aplicação criada com o [Spring Initializr](https://start.spring.io/) em **Java 17** com **Spring Boot** do 
tipo **Gradle** Project para gerenciamento das dependências e build do projeto

#### Dependências
* Spring Web
* Spring Actuator
* Spring Data Jpa
* PostgreSQL
* DevTools
* Lombok
* Bean Validation

### Database
* Postgres via Docker 

`docker run -p 5432:5432 --name postgres -e POSTGRES_USER=beerstore -e POSTGRES_PASSWORD=beerstore 
-e POSTGRES_DB=beerstore -d postgres:15-alpine`

### Tratamento de erros centralizado com ControllerAdvice
Não trata apenas mensagens de validação, vamos já
prepara a aplicação para retornar mensagens de erro mais amigáveis em diversos cenários.

Utilização do padrão Resource Based Error Codes, que
consiste basicamente em criar códigos de erro por recurso. As mensagens são cadastradas em um arquivo de propriedades
à parte, podendo também estar em vários idiomas.



### TDD e Exceptions


## DevOps

### AWS

VPC - Virtual Private Cloud

EC2 - Elastic Cloud Computing

RDS - Relational Database Service

ALB - Application Load Balancer

IAM - Identity & Access Management

CloudWatch - Billing

### Terraform

### Docker e Docker Swarm

### Ansible

### Deploy em produção





### Reference Documentation

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

