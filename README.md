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
- Junit 5
- Mockito


## Exemplos de Requisições

### EndPoint de Estados/

- **POST** `/geo/states/{stateId}`: Adiciona um novo estado no sistema.
- **GET** `/geo/states/{stateId}`: Retorna a lista de estados ativos no sistema.
- **GET** `/geo/states/id/{stateId}`: Retorna as informações de um estado específico por id.
- **GET** `/geo/states/acronym/{acronym}`: Retorna um estado especifico de acordo com a sigla.
- **PUT** `/geo/states/update`: Atualiza as informações de um estado existente.
- **DELETE** `/geo/states/totalDelete/{stateId}`: Remove totalmente um estado do sistema.
- **DELETE** `/geo/states/logicalDelete/{stateId}`: Remove logicamente um estado do sistema.

### EndPoint de Regiões/

- **POST** `/geo/regions/{stateId}`: Adiciona uma nova Região no sistema.
- **GET** `/geo/regions/{stateId}`: Retorna a lista de Regiões ativas no sistema.
- **GET** `/geo/regions/id/{stateId}`: Retorna as informações de uma Região específica por id.
- **GET** `/geo/regions/showStates/{id}`: Retorna a lista de estados de uma Região especifica por ID.
- **PUT** `/geo/regions/update`: Atualiza as informações de um estado existente.
- **PUT** `/geo/regions/insertState/{regionId}/{stateId}`: Insere um estado ativo a uma Região especifica pelo Id.
- **DELETE** `/geo/regions/totalDelete/{regionId}`: Remove totalmente uma Região do sistema.
- **DELETE** `/geo/regions/logicalDelete/{regionId}`: Remove logicamente uma Região do sistema.

### EndPoint de País/

- **POST** `/geo/coutries/{countryId}`: Adiciona um novo País o no sistema.
- **GET** `/geo/countries/{stateId}`: Retorna a lista de Países ativos no sistema.
- **GET** `/geo/countries/id/{stateId}`: Retorna as informações de uma País específica por id.
- **GET** `/geo/countries/showRegions/{id}`: Retorna a lista de regióes de uma país especifico por ID.
- **PUT** `/geo/countries/update`: Atualiza as informações de um País existente.
- **PUT** `/geo/countries/reloadAll` : Atualiza dinamincamente os dados metricos entre as entindades do sistema ativas.
- **POST** `geo/countries/insertRegion/{countryId}/{regionId}"`: Insere uma região ativa dentro de um País especifico pelo Id.
