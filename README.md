# DESAFIO TÉCNICO PARA DESENVOLVEDOR BACKEND - NUVEN


O desafio consiste na criação de um CRUD para criar, ler, atualizar e deletar locais de um banco de dados.

## Tecnologias usadas

- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- PostgresSQL
- Lombok
- JUnit
- Mockito

## Pré-Requisitos

- Docker instalado

## Instalação e Configuração

1. Clone o repositório do Github:
```
https://github.com/NayraSousa/nuven-api.git
```
2. Navegue até o diretório do projeto:
``` 
cd nuven-api
```
3. Execute o comando:
```
docker compose up --build
```
4. Para acessar a documentação da API: http://localhost:8080/swagger-ui/index.html

## Rotas da API

## 1. Locais

- **Listar Locais**
    - **Método:** GET
    - **Endpoint:** `/place/read-all`
    - **Descrição:** Retorna a lista de locais ordenados por data de criação.
  
- **Listar Endereços**
    - **Método:** GET
    - **Endpoint:** `/address/read-all`
    - **Descrição:** Retorna a lista de endereços de todos os locais criados.

- **Criar Locais**
    - **Método:** POST
    - **Endpoint:** `/place/create`
    - **Descrição:** Cria um novo local.

- **Detalhes do Local**
    - **Método:** GET
    - **Endpoint:** `/place/read-by-name?name=`
    - **Descrição:** Retorna uma lista de locais a partir do nome correspondente.

    - **Método:** GET
    - **Endpoint:** `/place/read-by-id/{id}`
    - **Descrição:** Retorna um local correspondente ao ID.

- **Atualizar Local**
    - **Método:** PUT
    - **Endpoint:** `/place/update/{id}`
    - **Descrição:** Atualiza as informações de um local específico a partir do ID correspondente.

- **Excluir Todo**
    - **Método:** DELETE
    - **Endpoint:** `/place/delete/{id}`
    - **Descrição:** Exclui um local específico a partir do ID correspondente.



