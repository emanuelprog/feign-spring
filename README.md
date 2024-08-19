# Feign-Spring Project

Este projeto foi desenvolvido para testar a integração de Feign e Kafka em um ambiente de microserviços, utilizando Swagger para documentação das APIs. Ele consiste em dois microserviços: `user-service` e `order-service`.

## Descrição

- **User-Service**: Responsável por operações de gerenciamento de usuários (CRUD) e consumo de mensagens Kafka enviadas pelo `order-service`.
- **Order-Service**: Responsável por operações de gerenciamento de pedidos (CRUD) e produção de mensagens Kafka que são consumidas pelo `user-service`.

### Tecnologias Utilizadas

- **Spring Boot**
- **Spring Cloud OpenFeign**
- **Apache Kafka**
- **Swagger/OpenAPI**

## Endpoints

### User-Service

#### Listar todos os usuários
`GET /users`
- **Descrição**: Retorna uma lista de todos os usuários registrados.
- **Resposta**: `{"msg": "List loaded successfully!", "status": 200, "data": [...]}`

#### Criar um novo usuário
`POST /users`
- **Descrição**: Adiciona um novo usuário ao sistema.
- **Corpo da Requisição**: `{"name": "Nome do Usuário", "email": "email@exemplo.com"}`
- **Resposta**: `{"msg": "User created successfully!", "status": 201, "data": {...}}`

#### Buscar usuário por ID
`GET /users/{id}`
- **Descrição**: Retorna as informações de um usuário baseado no ID fornecido.
- **Resposta**: `{"msg": "User found successfully!", "status": 200, "data": {...}}`

#### Atualizar um usuário
`PUT /users/{id}`
- **Descrição**: Modifica as informações de um usuário existente no sistema baseado no ID fornecido.
- **Corpo da Requisição**: `{"name": "Nome Atualizado", "email": "email@exemplo.com"}`
- **Resposta**: `{"msg": "User updated successfully!", "status": 200, "data": {...}}`

#### Deletar um usuário
`DELETE /users/{id}`
- **Descrição**: Remove um usuário existente do sistema baseado no ID fornecido.
- **Resposta**: `{"msg": "User deleted successfully!", "status": 200}`

### Order-Service

#### Listar todos os pedidos
`GET /orders`
- **Descrição**: Retorna uma lista de todos os pedidos registrados.
- **Resposta**: `{"msg": "List loaded successfully!", "status": 200, "data": [...]}`

#### Criar um novo pedido
`POST /orders`
- **Descrição**: Adiciona um novo pedido ao sistema.
- **Corpo da Requisição**: `{"userId": 1, "product": "Nome do Produto"}`
- **Resposta**: `{"msg": "Order created successfully!", "status": 201, "data": {...}}`

#### Buscar pedido por ID
`GET /orders/{id}`
- **Descrição**: Retorna as informações de um pedido baseado no ID fornecido.
- **Resposta**: `{"msg": "Order found successfully!", "status": 200, "data": {...}}`

#### Atualizar um pedido
`PUT /orders/{id}`
- **Descrição**: Modifica as informações de um pedido existente no sistema baseado no ID fornecido.
- **Corpo da Requisição**: `{"userId": 1, "product": "Produto Atualizado"}`
- **Resposta**: `{"msg": "Order updated successfully!", "status": 200, "data": {...}}`

#### Deletar um pedido
`DELETE /orders/{id}`
- **Descrição**: Remove um pedido existente do sistema baseado no ID fornecido.
- **Resposta**: `{"msg": "Order deleted successfully!", "status": 200}`

## Kafka

- O `user-service` consome mensagens do tópico `user-topico`, que são produzidas pelo `order-service` quando um novo pedido é criado.

## Comunicação entre Microserviços

- O `order-service` utiliza Feign para se comunicar com o `user-service` e verificar se um usuário existe antes de criar um pedido.

## Inicializar o Kafka e Zookeeper

1. Entrar na pasta onde o Kafka foi instalado.
2. Executar o Zookeeper:

   ```cmd
   bin/zookeeper-server-start.sh config/zookeeper.properties

3. Iniciar o Kafka:
   ```cmd
   bin/kafka-server-start.sh config/server.properties

## Configurar o Kafka Tool

1. Abra o Kafka Tool.
  - Vá em File > Add Cluster.
  - Preencha os detalhes do cluster:
  - Cluster Name: Escolha um nome descritivo, como "MeuClusterKafka".
  - Bootstrap Servers: Insira localhost:9092 (ou o endereço do seu servidor Kafka).
  - Kafka Cluster Version: Selecione a versão correspondente à sua instalação do Kafka.
  - Zookeeper: Insira localhost:2181 (ou o endereço do seu servidor Zookeeper).

## Como Rodar o Projeto

1. **Clone o Repositório**:
   ```sh
   git clone <URL-do-repositório>
   
2. **Navegue até as Pastas dos Serviços e Rode os Microserviços**:
   cd user-service
   ./mvnw spring-boot:run
   
   2.1 **Em outro terminal**:
    cd order-service
    ./mvnw spring-boot:run

3. **Acesse o Swagger**:
   - Para user-service: http://localhost:8081/swagger-ui.html
   - Para order-service: http://localhost:8080/swagger-ui.html

