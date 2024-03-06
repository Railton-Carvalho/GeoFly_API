# GeoFly - Geographical Information API

GeoFly é uma API desenvolvida com Spring Boot que visa auxiliar geógrafos e profissionais relacionados a cadastrar informações sobre países, regiões e estados. A API oferece endpoints para gerenciar dados geográficos de forma eficiente e simplificada.

## Recursos

- Cadastro de países com informações básicas como nome, continente, IDH etc.
- Gerenciamento Dinâmico de regiões dentro de um país, permitindo a adição de estados.
- Possibilidade de cadastrar estados dentro de uma região com informações detalhadas como nome, PIB, população, etc.
- Endpoints para consultar, atualizar, incluir e excluir informações sobre países, regiões e estados do banco de dados.

## Tecnologias Utilizadas

- Spring Boot
- FlyAway Migration
- Hibernate
- Spring Data JPA
- MySQL - WorkBench 8.0
- Maven

## Exemplos de Requisições

### Cadastro de Estados/

- **POST** `/geo/states/{stateId}`: Adiciona um novo estado no sistema.
- **GET** `/geo/states/{stateId}`: Retorna a lista de estados cadastrados no sistema.
- **GET** `/geo/states/id/{stateId}`: Retorna as informações de um estado específico por id.
- **PUT** `/geo/states/update`: Atualiza as informações de um estado existente.
- **DELETE** `/geo/states/{stateId}`: Remove um estado do sistema.