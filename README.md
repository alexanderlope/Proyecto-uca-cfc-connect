# UCA-CFC Connect

## Descripción del proyecto

**UCA-CFC Connect** es una aplicación web empresarial desarrollada para el Centro de Formación Continua de la UCA (CFC). El sistema busca automatizar y centralizar procesos académicos y administrativos como la gestión de cursos y diplomados, inscripción de participantes, administración de clientes, cotizaciones, alquiler de espacios, solicitudes de catering, pagos y control de usuarios y roles.

El proyecto está planteado con una arquitectura MVC y servicios REST, utilizando Spring Boot como plataforma principal.

## Integrantes del equipo

| Integrante | Responsabilidad |
|---|---|
| Integrante 1 | Por asignar |
| Integrante 2 | Por asignar |
| Integrante 3 | Por asignar |
| Integrante 4 | Por asignar |
| Integrante 5 | Por asignar |

> **Nota:** Reemplace los nombres y responsabilidades anteriores por los datos reales del equipo.

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot**
- **Spring MVC**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA**
- **Hibernate**
- **MySQL o PostgreSQL**
- **Swagger / OpenAPI**
- **Maven**
- **Git y GitHub**

## Requisitos previos

Antes de ejecutar el proyecto localmente, se recomienda tener instalado:

- Java 21
- Maven
- MySQL o PostgreSQL
- Git

Puede comprobar las versiones mediante:

```bash
java -version
mvn -version
git --version
```

## Configuración de la base de datos

Cree una base de datos para el proyecto. Por ejemplo, utilizando MySQL:

```sql
CREATE DATABASE uca_cfc_connect;
```

Luego configure las variables de entorno correspondientes.

### Variables de entorno

Ejemplo:

```text
DB_URL=jdbc:mysql://localhost:3306/uca_cfc_connect
DB_USERNAME=root
DB_PASSWORD=tu_password
JWT_SECRET=tu_clave_secreta
```

En caso de utilizar PostgreSQL, adapte `DB_URL` al motor correspondiente:

```text
DB_URL=jdbc:postgresql://localhost:5432/uca_cfc_connect
DB_USERNAME=postgres
DB_PASSWORD=tu_password
JWT_SECRET=tu_clave_secreta
```

> No incluya contraseñas, claves JWT reales ni otras credenciales sensibles directamente en el repositorio de GitHub.

## Configuración de Spring Boot

Las variables anteriores pueden utilizarse desde `application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=${JWT_SECRET}
```

La configuración concreta puede variar según el motor de base de datos utilizado por el equipo.

## Instalación y ejecución local

### 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd uca-cfc-connect
```

### 2. Configurar las variables de entorno

Configure:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
```

### 3. Compilar el proyecto

```bash
mvn clean install
```

### 4. Ejecutar las pruebas

```bash
mvn test
```

### 5. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

También puede ejecutar el proyecto mediante el IDE utilizando la clase principal de Spring Boot.

Por defecto, la aplicación estará disponible en:

```text
http://localhost:8080
```

## Documentación Swagger / OpenAPI

Una vez que la aplicación esté ejecutándose, la documentación Swagger podrá consultarse mediante:

```text
http://localhost:8080/swagger-ui.html
```

o, dependiendo de la versión de Springdoc utilizada:

```text
http://localhost:8080/swagger-ui/index.html
```

### Swagger desplegado

Cuando el proyecto se encuentre desplegado, coloque aquí el enlace definitivo:

```text
https://<DOMINIO_DEL_PROYECTO>/swagger-ui/index.html
```

> **Pendiente:** sustituir el enlace anterior por la URL real una vez desplegada la aplicación.

## Estructura de carpetas

La estructura propuesta para el backend es:

```text
uca-cfc-connect/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── sv/
│   │   │       └── edu/
│   │   │           └── udb/
│   │   │               └── cfconnect/
│   │   │                   ├── controller/
│   │   │                   ├── service/
│   │   │                   │   └── implementation/
│   │   │                   ├── repository/
│   │   │                   │   └── domain/
│   │   │                   ├── dto/
│   │   │                   ├── exception/
│   │   │                   ├── security/
│   │   │                   └── config/
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │
│   └── test/
│       └── java/
│
├── pom.xml
├── README.md
└── .gitignore
```

### Descripción de las carpetas

| Carpeta | Descripción |
|---|---|
| `controller` | Expone los servicios REST y recibe las solicitudes HTTP. |
| `service` | Contiene la lógica de negocio. |
| `service/implementation` | Implementaciones de las interfaces de servicio. |
| `repository` | Acceso y persistencia de datos mediante Spring Data JPA. |
| `repository/domain` | Entidades/modelos principales del sistema. |
| `dto` | Objetos utilizados para entrada y salida de información mediante la API. |
| `exception` | Excepciones personalizadas y manejo centralizado de errores. |
| `security` | Configuración de Spring Security y JWT. |
| `config` | Configuraciones generales de Spring Boot y Swagger/OpenAPI. |
| `test` | Pruebas unitarias y de integración. |

## Módulos principales

El sistema contempla los siguientes módulos:

1. Gestión académica
2. Gestión de clientes
3. Inscripciones
4. Cotizaciones
5. Alquiler de espacios
6. Catering
7. Agenda institucional
8. Pagos
9. Seguridad, usuarios y roles

## Roles del sistema

- **ADMIN**
- **RECEPCIONISTA**
- **CLIENTE**
- **CONTABILIDAD**

## API REST

Los módulos deberán exponer servicios REST utilizando operaciones como:

```text
GET
POST
PUT
DELETE
```

Además, se contempla implementar búsquedas mediante filtros, paginación y ordenamiento.

## Control de versiones

El proyecto utiliza Git y GitHub para el control de versiones.

Se recomienda trabajar mediante ramas por funcionalidad:

```bash
git checkout -b feature/clientes
git checkout -b feature/cursos
git checkout -b feature/inscripciones
git checkout -b feature/cotizaciones
git checkout -b feature/pagos
```

Las funcionalidades deberán integrarse mediante Pull Requests.

## Estado del proyecto

**Fase actual:** Fase 1 - Planificación y Diseño.

La primera fase contempla el análisis y diseño del sistema, incluyendo el modelo de dominio, modelo de base de datos, definición de endpoints REST, mockups, flujos de procesos, plan de trabajo y estructura inicial del repositorio.

## Licencia

Proyecto académico desarrollado para la asignatura **Desarrollo de Software Empresarial**, Escuela de Computación, Facultad de Ingeniería.
