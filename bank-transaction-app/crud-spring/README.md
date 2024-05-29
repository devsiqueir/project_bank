# Banco - Simulador de Transações Bancárias

Este é um projeto  que utiliza Java 17, Spring Framework e MySQL. Este guia irá ajudá-lo a configurar e executar o projeto localmente em sua máquina.




## Pré-requisitos

Certifique-se de ter o seguinte instalado e configurado em sua máquina:

- Java 17
- MySQL
- Maven

## Configuração do Projeto

   1. Clone este repositório em sua máquina local:
      ```bash
      git clone https://github.com/renato-novais/banco-spring.git

   2. Crie um banco de dados MySQL para o projeto e anote suas credenciais (nome do banco de dados, usuário e senha).

   3. Configure o arquivo application.properties localizado em src/main/resources com as credenciais do banco de dados MySQL:

      ```application.properties
      spring.datasource.username=seu-usuario
      spring.datasource.password=sua-senha


## Compilação e Execução do Projeto Backend
1. Navegue até o diretório raiz do projeto:

   ```bash
   cd banco-spring

2. Compile o projeto usando Maven:

   ```bash
   mvn clean install

3. Execute o projeto:

   ```bash
   mvn spring-boot:run

4. Certifique-se de que o MySQL esteja em execução e que as credenciais do banco de dados estejam corretamente configuradas para que o projeto se conecte ao banco de dados.
## Documentação da API

Após o projeto executar, acesse no seu navegador:

```http
  http://localhost:8080/swagger-ui/index.html#/
```


## Funcionalidades

- CRUD Completo de Cliente
- Realizar transações bancárias entre contas
- Extrato da conta
- Relatório de todas as transações bancárias do sistema 

