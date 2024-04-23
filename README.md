# API de Consulta de Precios

Esta API proporciona un servicio REST para consultar los precios de productos en un sistema de comercio electrónico. Utiliza Spring Boot y una base de datos H2 en memoria para almacenar y recuperar los datos de precios.

## Requisitos del sistema

- Java 17
- Spring Boot 3.1.12
- H2 Database
- Maven (o cualquier otra herramienta de gestión de dependencias)

## Configuración del proyecto

1. Clona este repositorio a tu máquina local.
2. Asegúrate de tener Java 17 instalado.
3. El proyecto utiliza Maven para gestionar las dependencias. Puedes importar el proyecto en tu IDE favorito como un proyecto Maven existente.

## Configuración de la base de datos

El proyecto utiliza una base de datos H2 en memoria. La configuración de la base de datos se encuentra en el archivo `application.properties`. Asegúrate de que la URL de la base de datos esté configurada correctamente y de que el modo de inicialización esté establecido en `always` para cargar los datos de ejemplo al iniciar la aplicación.

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.datasource.initialization-mode=always
```
## Descripción de la estructura del proyecto

- `src/main/java`: Contiene el código fuente de la aplicación.
  - `controller`: Contiene los controladores REST.
  - `service`: Contiene la lógica de negocio de la aplicación.
  - `repository`: Contiene los repositorios JPA para acceder a la base de datos.
  - `model`: Contiene las entidades de datos de la aplicación.
  - `utils`: Contiene el manejo de errores de la aplicacion.
- `src/main/resources`: Contiene archivos de recursos, como configuraciones y scripts SQL de inicialización de la base de datos.
- `src/test/java`: Contiene pruebas unitarias para la aplicación.

## Ejecución de pruebas

Para ejecutar las pruebas unitarias, puedes utilizar tu IDE o ejecutar el siguiente comando desde la raíz del proyecto:

```bash
mvn test
```
## Ejecución de la aplicación

Puedes ejecutar la aplicación desde tu IDE o utilizando Maven. Desde la raíz del proyecto, ejecuta el siguiente comando:

```bash
mvn spring-boot:run
```
Esto iniciará la aplicación en http://localhost:8080.

## Endpoints disponibles

### Consulta de precio
- **URL**: `/prices`
- **Método HTTP**: `GET`
- **Parámetros de consulta**:
  - `date`: Fecha de aplicación (formato ISO)
  - `productId`: Identificador del producto
  - `brandId`: Identificador de la cadena
- **Respuesta exitosa**:
  - Código de estado: `200 OK`
  - Cuerpo de respuesta: Objeto JSON con los detalles del precio
- **Respuesta de error**:
  - Código de estado: `404 Not Found`
  - Cuerpo de respuesta: Mensaje de error

Ejemplo de solicitud:

```bash
GET /prices?date=2020-06-14T10:00:00&productId=35455&brandId=1
```
