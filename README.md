# librarymanagement - Backend API

Esta documentação fornece uma visão da API backend `librarymanagement`, focando em seus recursos, endpoints e configurações.

## Sumário
1. [Visão Geral](#1-visão-geral)
2. [Tecnologias Principais](#2-tecnologias-principais)
3. [Entidades Principais](#3-entidades-principais)
    - [3.1. `Author`](#31-author)
    - [3.2. `Publisher`](#32-publisher)
    - [3.3. `Session`](#33-session)
    - [3.4. `Reader`](#34-reader)
    - [3.5. `Book`](#35-book)
    - [3.6. `BookRental`](#36-bookrental)
4. [Endpoints da API](#4-endpoints-da-api)
    - [4.1. Endpoints de Autores (`/author`)](#41-endpoints-de-autores-author)
    - [4.2. Endpoints de Editoras (`/publisher`)](#42-endpoints-de-editoras-publisher)
    - [4.3. Endpoints de Sessões/Gêneros (`/session`)](#43-endpoints-de-sessõesgêneros-session)
    - [4.4. Endpoints de Leitores (`/reader`)](#44-endpoints-de-leitores-reader)
    - [4.5. Endpoints de Livros (`/book`)](#45-endpoints-de-livros-book)
    - [4.6. Endpoints de Aluguéis de Livros (`/rent`)](#46-endpoints-de-aluguéis-de-livros-rent)
5. [Configurações Essenciais (`application.properties`)](#5-configurações-essenciais-applicationproperties)
6. [Executando Localmente](#6-executando-localmente)

---

### 1. Visão Geral

A aplicação `librarymanagement` é um serviço backend desenvolvido em Spring Boot, com o objetivo de gerenciar o acervo de livros de uma biblioteca, incluindo autores, editoras, leitores, sessões (gêneros), e o controle de aluguéis (empréstimos) de livros.

### 2. Tecnologias Principais

*   **Framework**: Spring Boot 3.x (versão `3.5.6` no `pom.xml`)
*   **Linguagem**: Java 21
*   **Persistência**: Spring Data JPA
*   **Banco de Dados**: H2 Database (em memória para desenvolvimento, configurável para arquivo)
*   **Auxiliares**: Lombok
*   **Build Tool**: Maven

### 3. Entidades Principais

As principais entidades que compõem o sistema são:

#### 3.1. `Author`

Representa um autor de livros.

*   **id**: `Long` (Gerado automaticamente)
*   **fullName**: `String` (Nome completo do autor, obrigatório)
*   **birthday**: `LocalDate` (Data de nascimento, obrigatório)
*   **country**: `String` (País de origem, obrigatório)

#### 3.2. `Publisher`

Representa uma editora de livros.

*   **id**: `Long` (Gerado automaticamente)
*   **name**: `String` (Nome da editora, único, obrigatório)

#### 3.3. `Session`

Representa uma categoria ou gênero de livro.

*   **id**: `Long` (Gerado automaticamente)
*   **gender**: `String` (Nome do gênero/categoria, único, obrigatório)

#### 3.4. `Reader`

Representa um leitor da biblioteca.

*   **id**: `Long` (Gerado automaticamente)
*   **fullName**: `String` (Nome completo do leitor, obrigatório)
*   **age**: `String` (Idade do leitor, obrigatório)

#### 3.5. `Book`

Representa um livro no acervo da biblioteca.

*   **id**: `Long` (Gerado automaticamente)
*   **title**: `String` (Título do livro, obrigatório)
*   **author**: `Author` (Associação Many-to-One com o autor, obrigatório)
*   **publisher**: `Publisher` (Associação Many-to-One com a editora, obrigatório)
*   **ageRestriction**: `Integer` (Restrição de idade, obrigatório)
*   **session**: `Session` (Associação Many-to-One com a sessão/gênero, obrigatório)
*   **releaseDate**: `LocalDate` (Data de lançamento, obrigatório)

#### 3.6. `BookRental`

Representa um registro de aluguel (empréstimo) de um livro por um leitor.

*   **id**: `Long` (Gerado automaticamente)
*   **reader**: `Reader` (Associação Many-to-One com o leitor que alugou, obrigatório)
*   **book**: `Book` (Associação Many-to-One com o livro alugado, obrigatório)
*   **bookRent**: `LocalDateTime` (Timestamp do aluguel, obrigatório)
*   **bookDevolution**: `LocalDateTime` (Timestamp da devolução, "ATENÇÃO" deve manter nulo se o livro ainda não foi devolvido)

### 4. Endpoints da API

A API é organizada por recursos relacionados a autores, editoras, sessões, leitores, livros e aluguéis.

#### 4.1. Endpoints de Autores (`/author`)

Base: `/author`

*   **`POST /author`**
    *   **Descrição**: Cria um novo autor.
    *   **Método**: `POST`
    *   **Corpo da Requisição**: `Author` (JSON)
        ```json
        {
            "fullName": "Machado de Assis",
            "birthday": "1839-06-21",
            "country": "Brasil"
        }
        ```
    *   **Resposta**: `200 OK` com o `Author` criado.

*   **`GET /author`**
    *   **Descrição**: Lista todos os autores registrados.
    *   **Método**: `GET`
    *   **Resposta**: `200 OK` com uma lista de `Author`.

*   **`GET /author/{id}`**
    *   **Descrição**: Busca um autor pelo ID.
    *   **Método**: `GET`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com o `Author` encontrado.

*   **`PUT /author/{id}`**
    *   **Descrição**: Atualiza um autor existente.
    *   **Método**: `PUT`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Corpo da Requisição**: `Author` (JSON)
        ```json
        {
            "fullName": "Joaquim Maria Machado de Assis",
            "country": "Brasil"
        }
        ```
    *   **Resposta**: `200 OK` com o `Author` atualizado.

*   **`DELETE /author/{id}`**
    *   **Descrição**: Exclui um autor pelo ID.
    *   **Método**: `DELETE`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com `true` se a exclusão for bem-sucedida.

#### 4.2. Endpoints de Editoras (`/publisher`)

Base: `/publisher`

*   **`POST /publisher`**
    *   **Descrição**: Cria uma nova editora.
    *   **Método**: `POST`
    *   **Corpo da Requisição**: `Publisher` (JSON)
        ```json
        {
            "name": "Companhia das Letras"
        }
        ```
    *   **Resposta**: `200 OK` com a `Publisher` criada.

*   **`GET /publisher`**
    *   **Descrição**: Lista todas as editoras registradas.
    *   **Método**: `GET`
    *   **Resposta**: `200 OK` com uma lista de `Publisher`.

*   **`GET /publisher/{id}`**
    *   **Descrição**: Busca uma editora pelo ID.
    *   **Método**: `GET`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com a `Publisher` encontrada.

*   **`PUT /publisher/{id}`**
    *   **Descrição**: Atualiza o nome de uma editora existente.
    *   **Método**: `PUT`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Parâmetros de Query**: `name` (String)
        ```http
        PUT /publisher/1?name=Editora Alfa
        ```
    *   **Resposta**: `200 OK` com a `Publisher` atualizada.

*   **`DELETE /publisher/{id}`**
    *   **Descrição**: Exclui uma editora pelo ID.
    *   **Método**: `DELETE`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com `true` se a exclusão for bem-sucedida.

#### 4.3. Endpoints de Sessões/Gêneros (`/session`)

Base: `/session`

*   **`POST /session`**
    *   **Descrição**: Cria uma nova sessão/gênero.
    *   **Método**: `POST`
    *   **Corpo da Requisição**: `Session` (JSON)
        ```json
        {
            "gender": "Ficção Científica"
        }
        ```
    *   **Resposta**: `200 OK` com a `Session` criada.

*   **`GET /session`**
    *   **Descrição**: Lista todas as sessões/gêneros.
    *   **Método**: `GET`
    *   **Resposta**: `200 OK` com uma lista de `Session`.

*   **`GET /session/{id}`**
    *   **Descrição**: Busca uma sessão/gênero pelo ID.
    *   **Método**: `GET`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com a `Session` encontrada.

*   **`PUT /session`**
    *   **Descrição**: Atualiza uma sessão/gênero existente.
    *   **Método**: `PUT`
    *   **Parâmetros de Query**: `id` (Long)
    *   **Corpo da Requisição**: `Session` (JSON)
        ```json
        {
            "gender": "Fantasia"
        }
        ```
    *   **Resposta**: `200 OK` com a `Session` atualizada.

*   **`DELETE /session/{id}`**
    *   **Descrição**: Exclui uma sessão/gênero pelo ID.
    *   **Método**: `DELETE`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com `true` se a exclusão for bem-sucedida.

#### 4.4. Endpoints de Leitores (`/reader`)

Base: `/reader`

*   **`POST /reader`**
    *   **Descrição**: Cria um novo leitor.
    *   **Método**: `POST`
    *   **Corpo da Requisição**: `Reader` (JSON)
        ```json
        {
            "fullName": "Maria Silva",
            "age": "25"
        }
        ```
    *   **Resposta**: `200 OK` com o `Reader` criado.

*   **`GET /reader`**
    *   **Descrição**: Lista todos os leitores registrados.
    *   **Método**: `GET`
    *   **Resposta**: `200 OK` com uma lista de `Reader`.

*   **`GET /reader/{id}`**
    *   **Descrição**: Busca um leitor pelo ID.
    *   **Método**: `GET`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com o `Reader` encontrado.

*   **`PUT /reader`**
    *   **Descrição**: Atualiza um leitor existente.
    *   **Método**: `PUT`
    *   **Parâmetros de Query**: `id` (Long)
    *   **Corpo da Requisição**: `Reader` (JSON)
        ```json
        {
            "fullName": "Maria Augusta Silva",
            "age": "26"
        }
        ```
    *   **Resposta**: `200 OK` com o `Reader` atualizado.

*   **`DELETE /reader/{id}`**
    *   **Descrição**: Exclui um leitor pelo ID.
    *   **Método**: `DELETE`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com `true` se a exclusão for bem-sucedida.

#### 4.5. Endpoints de Livros (`/book`)

Base: `/book`

*   **`POST /book`**
    *   **Descrição**: Cria um novo livro. Requer que `Author`, `Publisher` e `Session` associados já existam.
    *   **Método**: `POST`
    *   **Corpo da Requisição**: `Book` (JSON)
        ```json
        {
            "title": "Dom Casmurro",
            "author": {
                "id": 1
            },
            "publisher": {
                "id": 1
            },
            "ageRestriction": 12,
            "session": {
                "id": 1
            },
            "releaseDate": "1899-05-01"
        }
        ```
    *   **Resposta**: `200 OK` com o `Book` criado.

*   **`GET /book`**
    *   **Descrição**: Lista todos os livros.
    *   **Método**: `GET`
    *   **Resposta**: `200 OK` com uma lista de `Book`.

*   **`GET /book/{id}`**
    *   **Descrição**: Busca um livro pelo ID.
    *   **Método**: `GET`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com o `Book` encontrado.

*   **`PUT /book/{id}`**
    *   **Descrição**: Atualiza um livro existente.
    *   **Método**: `PUT`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Corpo da Requisição**: `Book` (JSON)
        ```json
        {
            "title": "Dom Casmurro (Nova Edição)",
            "publisher": {
                "id": 2
            }
        }
        ```
    *   **Resposta**: `200 OK` com o `Book` atualizado.

*   **`DELETE /book/{id}`**
    *   **Descrição**: Exclui um livro pelo ID.
    *   **Método**: `DELETE`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com `true` se a exclusão for bem-sucedida.

#### 4.6. Endpoints de Aluguéis de Livros (`/rent`)

Base: `/rent`

*   **`POST /rent`**
    *   **Descrição**: Registra um novo aluguel de livro. O `bookRent` é preenchido automaticamente com a data e hora atuais.
    *   **Método**: `POST`
    *   **Corpo da Requisição**: `BookRental` (JSON)
        ```json
        {
            "reader": {
                "id": 1
            },
            "book": {
                "id": 1
            }
        }
        ```
    *   **Resposta**: `200 OK` com o `BookRental` registrado.

*   **`GET /rent`**
    *   **Descrição**: Lista todos os registros de aluguéis.
    *   **Método**: `GET`
    *   **Resposta**: `200 OK` com uma lista de `BookRental`.

*   **`GET /rent/{id}`**
    *   **Descrição**: Busca um registro de aluguel pelo ID.
    *   **Método**: `GET`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com o `BookRental` encontrado.

*   **`PUT /rent/{id}`**
    *   **Descrição**: Registra a devolução de um livro. O `bookDevolution` é preenchido automaticamente com a data e hora atuais.
    *   **Método**: `PUT`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com o `BookRental` atualizado.

*   **`PUT /rent/edit/{id}`**
    *   **Descrição**: Edita um registro de aluguel existente.
    *   **Método**: `PUT`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Corpo da Requisição**: `BookRental` (JSON)
        ```json
        {
            "reader": {
                "id": 2
            }
        }
        ```
    *   **Resposta**: `200 OK` com o `BookRental` atualizado.

*   **`DELETE /rent/{id}`**
    *   **Descrição**: Exclui um registro de aluguel pelo ID.
    *   **Método**: `DELETE`
    *   **Parâmetros de Path**: `id` (Long)
    *   **Resposta**: `200 OK` com `true` se a exclusão for bem-sucedida.

### 5. Configurações Essenciais (`application.properties`)

*   **Nome da Aplicação**:
    *   `spring.application.name=librarymanagement`
*   **Console H2 Database**:
    *   `spring.h2.console.enabled=true`
    *   `spring.h2.console.path=/h2-console`
*   **Configurações de Banco de Dados H2**:
    *   `spring.datasource.driver-class-name=org.h2.Driver`
    *   `spring.datasource.url=jdbc:h2:file:/data/db/testdb` (Banco de dados persistido em arquivo)
    *   `spring.datasource.username=sa`
    *   `spring.datasource.password=` (sem senha)
*   **JPA (atualização do banco de dados)**:
    *   `spring.jpa.hibernate.ddl-auto=update`

### 6. Executando Localmente

A aplicação utiliza Maven para construção e pode ser executada diretamente como uma aplicação Spring Boot.

*   **Pré-requisitos**:
    *   Java 21 instalado
    *   Maven instalado
*   **Passos para Execução**:
    *   Navegue até a raiz do projeto (onde está o arquivo `pom.xml`).
    *   Execute a aplicação usando o comando Maven: `mvn spring-boot:run`
    *   A aplicação estará disponível em `http://localhost:8080` (porta padrão do Spring Boot).
    *   O console do H2 Database estará acessível em `http://localhost:8080/h2-console`.

---