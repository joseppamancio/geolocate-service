[English](README.md) | [Português](README.pt.md)


[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
[![Spring WebFlux](https://img.shields.io/badge/Spring_WebFlux-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
[![Project Reactor](https://img.shields.io/badge/Project_Reactor-BA0D34?style=for-the-badge&logo=projectreactor&logoColor=white)](https://projectreactor.io/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![WireMock](https://img.shields.io/badge/WireMock-E95420?style=for-the-badge&logo=wireguard&logoColor=white)](http://wiremock.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white)](https://swagger.io/)

# Geolocalización con Spring WebFlux
## 📝 Descripción
Este proyecto es una API Rest Reactiva que utiliza Spring WebFlux y tiene como objetivo devolver la ubicación a través de coordenadas geográficas (latitud y longitud) de una dirección.

WebFlux es un marco de trabajo reactivo que forma parte de Spring 5 y es una alternativa a Spring MVC. Está basado en Project Reactor, que es una implementación reactiva para Java.
Es adecuado para aplicaciones que requieren alta escalabilidad y baja latencia, como aplicaciones de IoT, chat, juegos en línea, entre otros.
Al ser no bloqueante, es capaz de manejar un gran número de solicitudes simultáneas con menos hilos, lo que lo hace más eficiente.

## ⚙️ Tecnologías
- Java 17
- Spring WebFlux
- Project Reactor
- Maven
- MongoDB
- Docker
- WireMock

## ✅ Prerrequisitos

### Docker
Antes de comenzar, necesitarás tener Docker y Docker Compose instalados en tu máquina.
- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/install/)

El proyecto utiliza Docker para ejecutar 3 contenedores:
- MongoDB: base de datos NoSQL utilizada para almacenar datos
- Mongo Express: interfaz gráfica para ver datos de MongoDB
- WireMock: herramienta para simular servicios RESTful, que simulará la API de geolocalización de Google

### Postman
Para probar los Endpoints, importa la Colección y los Ambientes JSON en Postman, que se encuentran en los siguientes directorios:
- 'src/main/resources/docs/collections/geolocatesvc.postman_collection.json'.
- 'src/main/resources/docs/collections/environments/LOCAL.postman_environment.json'.

## 📦 Instalación
1. Clona el repositorio

```bash
git clone https://github.com/joseppamancio/geolocatesvc.git
```

2. Importa el proyecto en tu IDE

3. Ejecuta el proyecto
   Al ejecutar el proyecto, además de iniciar la aplicación, se iniciarán los contenedores de MongoDB, Mongo Express y WireMock.
   WireMock simulará la API de geolocalización de Google, y MongoDB se utilizará para almacenar los datos.

4. Probando los Endpoints
   Con la colección y el ambiente importados en Postman, puedes probar los Endpoints de la API.
   Se han habilitado 3 Endpoints:

### Endpoint para verificar si la aplicación está funcionando
- **GET {{HOSTNAME}}/geolocatesvc/ping**

### Endpoint para verificar si WireMock está funcionando
- **GET {{WIREMOCK}}/maps/api/geocode/json?key=_Ab12Cd34Ef56Gh78Ij90Kl12Mn34Op56-r8St9&latlng=-26.1965843,-52.6890572
  ***Nota 1:*** La GOOGLE_API_KEY es solo un ejemplo, no es una clave válida. Pero sigue el patrón de una clave válida.

Este endpoint devolverá un JSON con la ubicación de la dirección correspondiente a las coordenadas geográficas proporcionadas.
***Nota 2:*** Las coordenadas proporcionadas son solo un ejemplo, se ha simulado una dirección fija para simular la respuesta de la API de Google.

### Endpoint para obtener la ubicación de una dirección a través de coordenadas geográficas
- **GET {{HOSTNAME}}/geolocatesvc/api/v1/address?latitude=-26.1965843&longitude=-52.6890572

Finalmente, este endpoint de nuestra API devolverá la ubicación de la dirección correspondiente a las coordenadas geográficas proporcionadas,
obtendrá información de la API de Google (Wiremock en este caso) y la almacenará en MongoDB.

5. Accediendo a MongoDB
   Después de obtener el valor de latitud y longitud, la aplicación lo almacenará en MongoDB.
   Para acceder a MongoDB, accede a la URL `http://localhost:8081/` y usa las siguientes credenciales:
- **Usuario: admin**
- **Contraseña: notset**
  Tendrás acceso a la base de datos `geolocatesvc` y a la colección `locations` a través de la interfaz gráfica de Mongo Express.

## 🧪 Pruebas de Integración
La aplicación tiene una prueba de extremo a extremo donde, al realizar una solicitud al endpoint de búsqueda de ubicación,
la aplicación recuperará información de WireMock y devolverá la ubicación de la dirección correspondiente a las coordenadas geográficas proporcionadas.

## 📚 Documentación
La documentación de la API se realizó utilizando Swagger y se puede acceder a través de la URL `http://localhost:8080/geolocatesvc/swagger-ui.html`.

# 🎥 Video de Demostración
https://github.com/joseppamancio/geolocatesvc/assets/54041843/acf436a7-e094-4bfb-92ca-ca5bc6c3a648