[English](README.md) | [Español](README.es.md)

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
[![Spring WebFlux](https://img.shields.io/badge/Spring_WebFlux-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
[![Project Reactor](https://img.shields.io/badge/Project_Reactor-BA0D34?style=for-the-badge&logo=projectreactor&logoColor=white)](https://projectreactor.io/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![WireMock](https://img.shields.io/badge/WireMock-E95420?style=for-the-badge&logo=wireguard&logoColor=white)](http://wiremock.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white)](https://swagger.io/)

# Geolocalização com Spring WebFlux
## 📝 Descrição
Este projeto é uma API Rest reativa utilizando Spring WebFlux que tem como objetivo retornar a localização através de coordenadas geográficas (latitude e longitude) de um endereço.

O WebFlux é um framework reativo que faz parte do Spring 5 e é uma alternativa ao Spring MVC. Ele é baseado no Project Reactor, que é uma implementação reativa para Java.
Ele é indicado para aplicações que precisam de alta escalabilidade e baixa latência, como aplicações de IoT, chat, jogos online, entre outros.
Por ser não bloqueante, ele é capaz de lidar com um grande número de requisições simultâneas com um número menor de threads, o que o torna mais eficiente.

## ⚙️ Tecnologias
- Java 17
- Spring WebFlux
- Project Reactor
- Maven
- MongoDB
- Docker
- WireMock

## ✅ Pré-requisitos

### Docker
Antes de começar, você vai precisar ter instalado em sua máquina o docker e o docker-compose.
- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/install/)

O projeto utiliza o Docker para subir 3 containers
- MongoDB: Banco de dados NoSQL utilizado para armazenar os dados
- Mongo Express: Interface gráfica para visualizar os dados do MongoDB
- WireMock: Ferramenta para simular serviços RESTful, que simulará a API de geolocalização do Google

### Postman
Para testar os Endpoints, importe a Collection e as Environments JSON para o Postman que se encontra nos seguintes diretórios:
- 'src/main/resources/docs/collections/geolocatesvc.postman_collection.json'.
- 'src/main/resources/docs/collections/environments/LOCAL.postman_environment.json'.

## 📦 Instalação
1. Clone o repositório

```bash
  git clone https://github.com/joseppamancio/geolocatesvc.git
```

2. Importe o projeto em sua IDE

3. Execute o projeto
Ao executar o projeto, além de subir a aplicação, os containers do MongoDB, Mongo Express e WireMock serão iniciados.
O wiremock irá simular a API de geolocalização do Google e o MongoDB será utilizado para armazenar os dados.

4. Testando os Endpoints
Com a collection e o environment importados no Postman, você poderá testar os Endpoints da API.
Foram disponibilizados 3 Endpoints:

### Endpoint para verificar se a aplicação está no ar
- **GET {{HOSTNAME}}/geolocatesvc/ping**

### Endpoint para verificar se o wiremock está no ar
- **GET {{WIREMOCK}}/maps/api/geocode/json?key=_Ab12Cd34Ef56Gh78Ij90Kl12Mn34Op56-r8St9&latlng=-26.1965843,-52.6890572
***OBS 1:*** A GOOGLE_API_KEY é apenas um exemplo, não é uma key válida. Mas segue o padrão de uma chave válida.

Este endpoint irá retornar um JSON com a localização do endereço correspondente às coordenadas geográficas informadas.
***OBS 2:*** As coordenadas informadas são apenas um exemplo, foi moccado um endereço fixo para simular a resposta da API do Google.

### Endpoint para buscar a localização de um endereço através de coordenadas geográficas
- **GET {{HOSTNAME}}/geolocatesvc/api/v1/address?latitude=-26.1965843&longitude=-52.6890572

Por fim, este endpoint da nossa API irá retornar a localização do endereço correspondente às coordenadas geográficas informadas,
ele irá buscar informações no Google API(Wiremock neste caso) e armazenar no MongoDB.

5. Acessando o MongoDB
Após conseguir o valor de latitude e longitude a aplicação irá armazenar no MongoDB. 
Para acessar o MongoDB, acesse a URL `http://localhost:8081/` e utilize as seguintes credenciais:
- **Username: admin**
- **Password: notset**
Você terá acesso ao banco de dados `geolocatesvc` e a coleção `locations`, através da interface gráfica do Mongo Express.

## 🧪 Testes End-to-End
A aplicação possui um teste end-to-end onde ao realizar uma requisição para o endpoint de busca de localização, 
a aplicação irá buscar as informações no WireMock e retornar a localização do endereço correspondente às coordenadas geográficas informada.

## 📚 Documentação
A documentação da API foi feita utilizando o Swagger, e pode ser acessada através da URL `http://localhost:8080/geolocatesvc/swagger-ui.html`.

# 🎥 Vídeo de demonstração
https://github.com/joseppamancio/geolocatesvc/assets/54041843/acf436a7-e094-4bfb-92ca-ca5bc6c3a648