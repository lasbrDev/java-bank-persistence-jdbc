
# Bank Application with JDBC and Docker

Este projeto é uma aplicação bancária simples que utiliza Java, JDBC para interação com um banco de dados MySQL, e Docker para facilitar o ambiente de desenvolvimento.


## Estrutura do Projeto

O projeto segue a seguinte estrutura de pacotes:

```
├───src
│   ├───main
│   │   ├───java
│   │   │   └───br
│   │   │       └───com
│   │   │           └───lasbr
│   │   │               └───bank
│   │   │                   └───domain
│   │   │                       ├───account
│   │   │                       └───costumer
│   │   └───resources
│   └───test
│       └───java
└───target
    ├───classes
    │   └───br
    │       └───com
    │           └───lasbr
    │               └───bank
    │                   └───domain
    │                       ├───account
    │                       └───costumer
    └───generated-sources
        └───annotations

```



## Classes Principais

**Conta**
A classe Conta representa uma conta bancária com número, saldo e titular.

**ContaService**
ContaService é responsável por gerenciar as operações relacionadas a contas, como abertura, encerramento, saque e depósito.

**DadosAberturaConta** e **DadosCadastroCliente**
Essas classes contêm dados necessários para abrir uma conta e cadastrar um cliente, respectivamente.

**Cliente**
Representa um cliente com nome, CPF e email.

**RegraDeNegocioException**
Exceção personalizada para representar violações de regras de negócio.

**BankApplication**
A classe principal contendo o menu interativo para operações bancárias.

**ConnectionFactory**
Factory de conexões JDBC para interação com o banco de dados MySQL.
## Banco de Dados

O banco de dados utilizado é o MySQL, e a configuração está definida no arquivo docker-compose.yml.

```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0.28
    environment:
        MYSQL_ROOT_PASSWORD: root
        MYSQL_DATABASE: my_mysql_db
        MYSQL_PASSWORD: root
    ports:
      - 3307:3306
```

Abaixo, estão os comandos para rodar a instância do MySQL no Docker:

```bash
# Iniciar o banco de dados MySQL
docker-compose up -d

# Parar o banco de dados MySQL
docker-compose down
```



## Acessando o MySQL

### Usando o MySQL Workbench

Você pode usar o MySQL Workbench para conectar à uma instância do MySQL em execução:

* Host: localhost
* Porta: 3307
* Usuário: root
* Senha: root

### Usando o Terminal

Você também pode usar o terminal para se conectar à instância do MySQL:
```bash
# Conectar ao MySQL
mysql -h localhost -P 3307 -u root -p
```


## Boas Práticas em POO

Além de sempre adotar as boas práticas em orientação a objetos, temos outros conceitos importantes como separação das responsabilidades e, sempre que possível, tentar enxergar se o projeto pode melhorar no sentido de abordar outros aspectos como os principios SOLID.

Até o momento, a aplicação adota principalmente o Princípio Aberto/Fechado (Open/Closed Principle) do SOLID. Este princípio permite estender a funcionalidade da aplicação sem modificar suas classes existentes. Novos serviços bancários ou tipos de contas podem ser adicionados sem alterar diretamente as implementações existentes.
## Status do Projeto

Atualmente, a aplicação é capaz apenas de cadastrar uma conta. Os métodos adicionais, incluindo a persistência de dados no banco de dados, estão em desenvolvimento. Futuras atualizações incluirão funcionalidades adicionais, a conclusão das operações bancárias essenciais, testes abrangentes e tratamento de exceções.

Este projeto é uma obra em andamento, e esforços estão sendo feitos para fornecer uma aplicação robusta, segura e de fácil manutenção. Fique atento para futuras atualizações e melhorias!


