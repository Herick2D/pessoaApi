# Documentação Pessoa API

O Pessoa API é uma aplicação backend desenvolvida em Spring Boot, que gerencia registros de pessoas e suas respectivas stacks de tecnologia. A API permite realizar operações de CRUD (Create, Read, Update, Delete) para manipular as informações de pessoas e as tecnologias associadas a elas.

## Tabela de Conteúdos

- [Visão Geral](#)
- [Primeiros Passos](#primeiros-passos)
    - [Pré-requisitos](#pré-requisitos)
    - [Instalação](#instalação)
- [Uso](#uso)
    - [Endpoints da API](#endpoints-da-api)
- [Observações](#observações)

## Primeiros Passos

### Pré-requisitos

Certifique-se de ter o seguinte instalado no seu sistema:

- Java 17 ou superior
- MySQL 8.0 ou superior
- Ferramentas de testes de API de sua preferencia (Postman, insomnia, etc)

## Instalação

1. Clone o repositório:

```bash
git clone git@github.com:Herick2D/pessoaApi.git
```

2. Atualize o arquivo application.properties com as credenciais do seu banco de dados MySql.

3. Inicie o projeto.

## Uso

Ao executar a aplicação será iniciada e estará acessível em http://localhost:8080.

### Endpoints da API

### Criação de pessoas

- URL: /pessoas
- Método: POST
- Corpo da Requisição:

```
{
    "apelido" : "josé",
    "nome" : "José Roberto",
    "nascimento" : "2000-10-01",
    "stack" : ["C#", "Node", "Oracle"]
}
```

- Resposta: 201 - Created.

```
{
    "id": 1,
    "apelido": "josé",
    "nome": "José Roberto",
    "nascimento": "2000-10-01",
    "stack": ["C#", "Node", "Oracle"]
}
```


### Detalhe de uma pessoa

- URL: /pessoas/{id}
- Método: GET
- Resposta: 200 - Ok.

```
{
    "id": 1,
    "apelido": "josé",
    "nome": "José Roberto",
    "nascimento": "2000-10-01",
    "stack": ["C#", "Node", "Oracle"]
}
```

### Buscar por termos específicos
_Estarei levando em consideração a busca por termo "berto";_

- URL: /pessoas?t={termo}
- Método: GET
- Resposta: 200 - Ok.

```
[
    {
    "apelido" : "josé",
    "nome" : "José Roberto",
    "nascimento" : "2000-10-01",
    "stack" : ["C#", "Node", "Oracle"]
}
]
```

### Alterar Pessoas
_Estarei levando em consideração o id 1, e a alteração no apelido e stack do mesmo;_

- URL: /pessoas/{id}
- Método: PUT
- Corpo da Requisição:

```
{
    "apelido" : "Zé",
    "nome" : "José Roberto",
    "nascimento" : "2000-10-01",
    "stack" : ["C++", "Java", "AWS"]
}
```

- Resposta:

```
{
    "id": 1,
    "apelido": "Zé",
    "nome": "José Roberto",
    "nascimento": "2000-10-01",
    "stack": ["Java", "C++", "AWS"]
}
```

Excluir Pessoa

- URL:  /pessoas/{id}
- Método: Delete
- Resposta: 204 - No Content

## Observações
Este projeto foi desenvolvido como parte de um desafio técnico com prazo de tempo limitado, por esse motivo, pode conter bugs e comportamentos inesperados. Agradeço por contribuições e sugestões de melhorias são bem-vindas.