# Pessoa API

## Descrição

O **Pessoa API** é uma aplicação backend desenvolvida em **Spring Boot**, que gerencia registros de pessoas e suas respectivas stacks de tecnologia. A API permite realizar operações de CRUD (Create, Read, Update, Delete) para manipular as informações de pessoas e as tecnologias associadas a elas.

## Funcionalidades

1. **Criar Pessoa**  
   Endpoint: `POST /pessoas`
    - Cria uma nova pessoa com apelido, nome, data de nascimento e stacks.
    - Valida se todos os campos obrigatórios estão preenchidos antes de salvar.

2. **Buscar Pessoa por ID**  
   Endpoint: `GET /pessoas/{id}`
    - Retorna as informações de uma pessoa pelo seu ID.
    - Caso a pessoa não seja encontrada, retorna `404 Not Found`.

3. **Atualizar Pessoa**  
   Endpoint: `PUT /pessoas/{id}`
    - Atualiza os dados de uma pessoa existente, incluindo suas stacks.
    - Valida se todos os campos obrigatórios estão preenchidos.

4. **Buscar Pessoas por Termo**  
   Endpoint: `GET /pessoas?t={termo}`
    - Busca pessoas que correspondem ao termo fornecido.
    - Caso o termo não seja passado, retorna um erro `400 Bad Request`.

5. **Excluir Pessoa**  
   Endpoint: `DELETE /pessoas/{id}`
    - Remove uma pessoa do banco de dados.
    - Caso a pessoa não exista, retorna `400 Bad Request`.

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para construção da API.
- **Hibernate**: ORM para gerenciar o mapeamento entre objetos e o banco de dados.
- **MySQL**: Banco de dados utilizado para armazenar os registros de pessoas e stacks.
- **Maven**: Gerenciador de dependências para o projeto.
- **Docker**: Utilizado para criar um container com o banco de dados MySQL e virtualizar o ambiente de desenvolvimento.

## Observações

Este projeto foi desenvolvido como parte de um desafio técnico com prazo de tempo limitado, por esse motivo, pode conter bugs e comportamentos inesperados. Agradeço por contribuições e sugestões de melhorias são bem-vindas.