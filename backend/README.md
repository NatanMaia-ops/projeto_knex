# Projeto Deputados - Backend

Este projeto é uma API REST desenvolvida em Java com Spring Boot e PostgreSQL para gerenciamento de deputados e suas despesas, incluindo importação de dados via arquivo CSV.

## Sumário

- [Descrição](#descrição)
- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Como executar](#como-executar)
- [Endpoints principais](#endpoints-principais)
- [Licença](#licença)

---

## Descrição

A API permite importar dados de deputados e despesas via CSV, consultar deputados por UF, listar despesas, somar gastos e muito mais.

## Requisitos

- Java 23
- PostgreSQL
- Maven

## Instalação

Clone o repositório:
```sh
git clone https://github.com/NatanMaia-ops/projeto_knex.git
```

Acesse a pasta do backend:
```sh
cd projeto_knex/backend
```

## Configuração

Configure o acesso ao banco de dados no arquivo:
```
src/main/resources/application.properties
```
Exemplo:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## Como executar

No terminal, execute:
```sh
./mvnw spring-boot:run
```
No Windows:
```sh
mvnw.cmd spring-boot:run
```

A API estará disponível em: [http://localhost:3000](http://localhost:3000)

## Endpoints principais

- **Importar CSV de deputados e despesas**
  - `POST /api/deputado/upload`
  - Formato: multipart/form-data, campo `file`

- **Listar deputados por UF**
  - `GET /api/deputado/findByUf?uf=SP`

- **Somatório de despesas por deputado**
  - `GET /api/deputado/deputados/{id}/total-expenses`

- **Somatório total de despesas**
  - `GET /api/deputado/sum-total-expenses`

- **Listar despesas de um deputado**
  - `GET /api/deputado/deputados/{id}/despesas`


```

## Licença

Este projeto é apenas para fins de estudo e