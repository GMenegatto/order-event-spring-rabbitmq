
# Sistema de Processamento de Pedidos

## Descrição do Projeto

Este projeto é um estudo para desenvolver uma aplicação que processa pedidos e gera relatórios com base em mensagens consumidas de uma fila RabbitMQ. A aplicação foi construída utilizando **Spring Boot**, **MongoDB** e **Docker** para facilitar a implantação e a execução.

## Funcionalidades

- Consumir dados de pedidos de uma fila RabbitMQ.
- Armazenar informações dos pedidos em um banco de dados MongoDB.
- Gerar relatórios sobre os pedidos, incluindo:
  - Valor total do pedido.
  - Quantidade de pedidos por cliente.
  - Lista de pedidos realizados por cliente.

## Tecnologias Utilizadas

- **Java** com **Spring Boot**: Backend da aplicação e gerenciamento de serviços.
- **MongoDB**: Banco de dados NoSQL para armazenar os pedidos.
- **RabbitMQ**: Mensageria para o consumo de pedidos.
- **Docker**: Utilizado para containerizar a aplicação e garantir uma fácil execução.

## Estrutura da Mensagem

As mensagens consumidas da fila RabbitMQ devem seguir o seguinte formato:

```json
{
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "lápis",
      "quantidade": 100,
      "preco": 1.10
    },
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 1.00
    }
  ]
}
```

## Como Executar o Projeto

1. Certifique-se de ter o **Docker** instalado na sua máquina.
2. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
3. Navegue até a pasta do projeto:
   ```bash
   cd nome-do-projeto
   ```
4. Execute o comando para iniciar os containers Docker:
   ```bash
   docker-compose up
   ```
5. A aplicação estará disponível e rodando com todos os serviços necessários (MongoDB, RabbitMQ, aplicação Spring Boot).

## Relatórios

Os relatórios gerados pela aplicação podem ser acessados por meio de endpoints REST:

- **GET /customers/{customerId}/orders**: Retorna as orders de um cliente.
