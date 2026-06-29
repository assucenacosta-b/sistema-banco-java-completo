# Sistema Bancário em Java

## Descrição

Este projeto consiste no desenvolvimento de um Sistema Bancário Desktop utilizando Java, Programação Orientada a Objetos, Swing para a interface gráfica e PostgreSQL para persistência de dados.

O sistema permite o gerenciamento de usuários, clientes e contas bancárias, além da realização de operações financeiras como depósitos, saques, transferências, emissão de extratos e geração de relatórios.

O projeto foi desenvolvido para aplicar os principais conceitos de Programação Orientada a Objetos, incluindo abstração, encapsulamento, herança, polimorfismo e interfaces, juntamente com acesso a banco de dados utilizando JDBC.

---

## Tecnologias Utilizadas

- Java 21
- Swing
- PostgreSQL
- JDBC
- Git
- GitHub
- IDE: NetBeans (Eclipse/IntelliJ)

---

## Estrutura do Projeto

```
src/
└── banco/
    ├── app/
    │   └── SistemaBanco.java
    │
    ├── dao/
    │   ├── ConexaoDB.java
    │   ├── UsuarioDAO.java
    │   ├── ClienteDAO.java
    │   ├── ContaCorrenteDAO.java
    │   └── ContaPoupancaDAO.java
    │
    ├── interfaces/
    │   └── Operavel.java
    │
    ├── model/
    │   ├── Usuario.java
    │   ├── Cliente.java
    │   ├── ContaBancaria.java
    │   ├── ContaCorrente.java
    │   └── ContaPoupanca.java
    │
    ├── service/
    │   ├── BancoService.java
    │   └── UsuarioService.java
    │
    └── ui/
        ├── TelaLogin.java
        ├── TelaMenuPrincipal.java
        ├── TelaCadastroUsuario.java
        ├── TelaGerenciarUsuarios.java
        ├── TelaCadastroCliente.java
        ├── TelaCadastroContaCorrente.java
        ├── TelaCadastroContaPoupanca.java
        ├── TelaOperacoes.java
        ├── TelaExtrato.java
        └── TelaRelatorio.java
```

---

## Responsabilidade de Cada Pacote

### app

Contém a classe principal responsável por iniciar a aplicação.

### model

Armazena as entidades do sistema, representando usuários, clientes e contas bancárias.

### interfaces

Contém as interfaces utilizadas pelas classes do sistema.

### dao

Responsável pela comunicação com o banco de dados utilizando JDBC.

### service

Implementa as regras de negócio e faz a comunicação entre a interface gráfica e os DAOs.

### ui

Contém todas as telas desenvolvidas em Swing.

---

## Funcionalidades

- Login de usuários
- Cadastro de usuários
- Cadastro de clientes
- Cadastro de contas correntes
- Cadastro de contas poupança
- Depósitos
- Saques
- Transferências
- Consulta de extratos
- Aplicação de rendimento em conta poupança
- Relatório geral do banco
- Controle de acesso por perfil de usuário

---

## Banco de Dados

O sistema utiliza PostgreSQL para armazenar todas as informações.

As principais tabelas são:

- usuarios
- clientes
- contas_correntes
- contas_poupanca
- transacoes

A conexão é realizada utilizando JDBC por meio da classe `ConexaoDB`.

---

## Como Executar

### 1. Clonar o repositório

```bash
git clone https://github.com/assucenacosta-b/sistema-banco-java-completo.git
```

### 2. Criar o banco de dados

Criar um banco chamado:

```
sistema_banco
```

Executar os scripts SQL para criação das tabelas.

### 3. Configurar a conexão

Editar o arquivo:

```
db.properties
```

Exemplo:

```properties
db.url=jdbc:postgresql://localhost:5432/sistema_banco
db.usuario=postgres
db.senha=sua_senha
```

### 4. Executar

Executar a classe:

```
SistemaBanco.java
```

---

## Diagrama da Hierarquia de Classes

```
Operavel
     ▲
     │
ContaBancaria (abstrata)
     ▲
     ├───────────────┐
     │               │
ContaCorrente   ContaPoupanca

Cliente

Usuario
```

---

## Conceitos de POO Aplicados

- Encapsulamento
- Herança
- Polimorfismo
- Abstração
- Interfaces

---

## Segurança

- Senhas armazenadas utilizando hash SHA-256.
- Uso de PreparedStatement para evitar SQL Injection.
- Controle de acesso por perfil de usuário.
- Validação de CPF, login e número de conta.

---

## Autor

Assucena Costa Belarmino dos Reis

2° Periodo Informatica

