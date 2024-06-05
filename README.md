
#  DESAFIO IBM

## 📖  Descrição
O desafio consiste em criar uma aplicação web que simule transações financeiras de um banco.


<br/>

## 🛠️ Funcionalidades

Essa aplicação deve conter os seguintes requisitos:
• Cadastro de clientes com os campos: nome, idade, endereço de email e número da conta.
• Cadastro de Débito e Crédito na conta do cliente.
• Extrato em tela da conta do cliente com saldo total (no rodapé ou no topo da página).
  

<br/>


## 💻 Tecnologies

- Java 17
- Spring Boot 3 
- Maven
- JPA + Hibernate
- MySQL
- Angular v16
- Node 20.13.1


## ⏳ Inicialização

## Configurações do banco de dados:

```
spring.application.name=banco
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/seu-banco?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSl=false
spring.datasource.username=seu usuário
spring.datasource.password=sua senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
server.port=8080
```



### Executando o backend
Você precisa ter o Java e o Maven instalados e configurados localmente.

Abra o projeto crud-spring em seu IDE favorito como um projeto Maven e execute-o como um aplicativo Spring Boot.


### Executando o front-end

Você precisa ter o Node.js/NPM instalado localmente.

1. Instale todas as dependências necessárias:

```
npm install
```


2. Execute o projeto:

```
ng serve

```
![clientesIBM](https://github.com/devsiqueir/testeIBM/assets/50212867/1aae482f-037c-4cd1-b77a-646c97b5ca21)





![clientesIBM2](https://github.com/devsiqueir/testeIBM/assets/50212867/2b35c5ce-416d-413b-92fe-c1e7bceef695)


### Documentação da API
```
http://localhost:8080/swagger-ui/index.html#/
```
