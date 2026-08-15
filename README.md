# Proyecto UCA-CFC-Connect

Sistema web para la gestión de **cursos, diplomados, inscripciones, cotizaciones y servicios complementarios** de un centro de formación.

El proyecto está desarrollado con **Java y Spring Boot**, utilizando una arquitectura por capas basada en **Controller, Service y Repository**, con el objetivo de facilitar el mantenimiento, escalabilidad y futura integración con una base de datos relacional.

---

## 📌 Descripción del proyecto

**CFC Connect** es una aplicación web orientada a centralizar y optimizar la gestión de los servicios académicos y administrativos de un centro de formación.

El sistema busca permitir la administración de:

* Clientes.
* Cursos.
* Diplomados.
* Inscripciones.
* Cotizaciones.
* Espacios.
* Servicios de catering.
* Pagos.
* Usuarios.
* Roles y permisos.

La primera fase del proyecto se enfoca principalmente en el **análisis, diseño y construcción de la arquitectura base**, dejando preparada la aplicación para incorporar posteriormente persistencia mediante JPA, seguridad, validaciones y funcionalidades CRUD completas.

---

## 🎯 Objetivo general

Desarrollar una aplicación web que permita gestionar de forma centralizada los procesos académicos y administrativos de un centro de formación, facilitando el registro de clientes, cursos, diplomados, inscripciones, cotizaciones, espacios, catering y pagos.

---

## 🎯 Objetivos específicos

* Diseñar una arquitectura organizada y escalable.
* Implementar una API REST utilizando Spring Boot.
* Gestionar clientes y cursos mediante endpoints REST.
* Separar las responsabilidades mediante capas.
* Definir el modelo de dominio del sistema.
* Preparar la aplicación para utilizar persistencia con JPA.
* Implementar pruebas unitarias básicas.
* Facilitar el trabajo colaborativo mediante Git y GitHub.
* Preparar la estructura necesaria para las siguientes fases del proyecto.

---

## 🛠️ Tecnologías utilizadas

| Tecnología        | Uso                        |
| ----------------- | -------------------------- |
| Java              | Lenguaje principal         |
| Spring Boot       | Framework backend          |
| Spring Web        | Desarrollo de API REST     |
| Maven             | Gestión de dependencias    |
| JUnit             | Pruebas unitarias          |
| Git               | Control de versiones       |
| GitHub            | Repositorio y colaboración |
| JPA / Hibernate   | Persistencia planificada   |
| MySQL             | Base de datos planificada  |
| Swagger / OpenAPI | Documentación planificada  |

---

## 🏗️ Arquitectura

El proyecto utiliza una arquitectura por capas:

```text
Cliente
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Base de datos
```

### Controller

Recibe las solicitudes HTTP y expone los endpoints REST de la aplicación.

### Service

Contiene la lógica de negocio y funciona como intermediario entre los controladores y los repositorios.

### Repository

Se encarga del acceso a los datos.

En la primera fase se utiliza almacenamiento temporal en memoria como preparación para la posterior implementación de JPA.

### Domain

Contiene las clases que representan las entidades principales del sistema.

---

## 📂 Estructura del proyecto

```text
src/
└── main/
    └── java/
        └── sv/
            └── edu/
                └── udb/
                    └── cfcconnect/
                        │
                        ├── controller/
                        │   ├── ClienteController.java
                        │   └── CursoController.java
                        │
                        ├── service/
                        │   ├── ClienteService.java
                        │   └── CursoService.java
                        │
                        ├── service/
                        │   └── implementation/
                        │       ├── ClienteServiceImpl.java
                        │       └── CursoServiceImpl.java
                        │
                        ├── repository/
                        │   ├── ClienteRepository.java
                        │   └── CursoRepository.java
                        │
                        ├── repository/
                        │   └── domain/
                        │       ├── Cliente.java
                        │       └── Curso.java
                        │
                        └── exception/
                            └── ResourceNotFoundException.java
```

---

## 📋 Módulos del sistema

El proyecto contempla los siguientes módulos:

| Módulo        | Descripción                                 |
| ------------- | ------------------------------------------- |
| Clientes      | Administración de clientes                  |
| Cursos        | Gestión de cursos disponibles               |
| Diplomados    | Gestión de diplomados                       |
| Inscripciones | Registro de clientes en cursos y diplomados |
| Cotizaciones  | Elaboración y gestión de cotizaciones       |
| Espacios      | Administración de espacios disponibles      |
| Catering      | Gestión de servicios de alimentación        |
| Pagos         | Registro y control de pagos                 |
| Usuarios      | Administración de usuarios                  |
| Roles         | Gestión de permisos y accesos               |

---

# 🚀 Fase 1

La primera fase está enfocada en el análisis y diseño del sistema.

### Actividades realizadas

* [x] Definición del proyecto.
* [x] Identificación de los módulos principales.
* [x] Definición inicial del modelo de dominio.
* [x] Diseño de la arquitectura.
* [x] Creación del proyecto Spring Boot.
* [x] Organización de paquetes.
* [x] Creación de modelos iniciales.
* [x] Implementación de Repository.
* [x] Implementación de Service.
* [x] Implementación de Controller.
* [x] Creación de endpoints GET.
* [x] Pruebas unitarias básicas.
* [ ] Persistencia completa con JPA.
* [ ] CRUD completo.
* [ ] Seguridad y autenticación.
* [ ] Validaciones avanzadas.
* [ ] Documentación Swagger completa.

---

# 🔌 Endpoints disponibles

## Clientes

### Obtener todos los clientes

```http
GET /clientes
```

Ejemplo:

```http
GET http://localhost:8080/clientes
```

Respuesta:

```json
[
    {
        "id": 1,
        "nombre": "Juan Pérez",
        "correo": "juan@gmail.com",
        "telefono": "7777-1111"
    },
    {
        "id": 2,
        "nombre": "Ana López",
        "correo": "ana@gmail.com",
        "telefono": "7777-2222"
    }
]
```

### Obtener un cliente

```http
GET /clientes/{id}
```

Ejemplo:

```http
GET http://localhost:8080/clientes/1
```

---

## Cursos

### Obtener todos los cursos

```http
GET /cursos
```

Ejemplo:

```http
GET http://localhost:8080/cursos
```

Respuesta:

```json
[
    {
        "id": 1,
        "nombre": "Spring Boot",
        "precio": 120.0,
        "cupo": 30
    },
    {
        "id": 2,
        "nombre": "React",
        "precio": 150.0,
        "cupo": 25
    }
]
```

### Obtener un curso

```http
GET /cursos/{id}
```

Ejemplo:

```http
GET http://localhost:8080/cursos/1
```

---

# ⚙️ Requisitos

Para ejecutar el proyecto se recomienda tener instalado:

* Java JDK 17 o superior.
* Maven.
* Git.
* IntelliJ IDEA, Eclipse o Visual Studio Code.
* Postman o una herramienta similar para probar la API.

---

# 📥 Instalación

Clonar el repositorio:

```bash
git clone https://github.com/USUARIO/cfc-connect.git
```

Ingresar al proyecto:

```bash
cd cfc-connect
```

Compilar el proyecto:

```bash
mvn clean install
```

---

# ▶️ Ejecución

Para ejecutar la aplicación mediante Maven:

```bash
mvn spring-boot:run
```

También puede ejecutarse desde el IDE utilizando la clase principal de Spring Boot.

La aplicación estará disponible en:

```text
http://localhost:8080
```

---

# 🧪 Pruebas

Para ejecutar las pruebas unitarias:

```bash
mvn test
```

Actualmente se incluyen pruebas básicas para verificar el funcionamiento de la capa de servicios.

---

# 🌱 Ramas de Git

Para facilitar el trabajo colaborativo se recomienda utilizar ramas separadas por funcionalidad.

Ejemplo:

```text
main
│
├── develop
│
├── feature/clientes
├── feature/cursos
├── feature/diplomados
├── feature/inscripciones
├── feature/cotizaciones
├── feature/espacios
├── feature/catering
├── feature/pagos
└── feature/seguridad
```

### Ejemplo de creación de una rama

```bash
git checkout -b feature/clientes
```

Agregar los cambios:

```bash
git add .
```

Crear un commit:

```bash
git commit -m "feat: implementar modulo de clientes"
```

Subir la rama:

```bash
git push origin feature/clientes
```

---

# 📝 Convención de commits

Se recomienda utilizar commits descriptivos siguiendo una estructura similar a:

```text
feat: nueva funcionalidad
fix: corrección de error
refactor: modificación de código
test: creación o modificación de pruebas
docs: actualización de documentación
style: cambios de formato
chore: tareas de mantenimiento
```

Ejemplos:

```bash
git commit -m "feat: agregar endpoint de clientes"
```

```bash
git commit -m "test: agregar pruebas de ClienteService"
```

```bash
git commit -m "docs: actualizar README"
```

---

# 🔮 Próximas fases

## Fase 2 — Persistencia y CRUD

Se incorporará:

* JPA.
* Hibernate.
* MySQL.
* Entidades persistentes.
* Relaciones entre entidades.
* CRUD completo.
* DTOs.
* Validaciones.
* Manejo de excepciones.
* Paginación y filtros.

## Fase 3 — Seguridad

Se contempla:

* Spring Security.
* Autenticación.
* Autorización.
* Roles.
* Protección de endpoints.
* Gestión de usuarios.

## Fase 4 — Documentación y pruebas

Se incorporará:

* Swagger/OpenAPI.
* Pruebas unitarias.
* Pruebas de integración.
* Documentación de endpoints.
* Mejoras en el manejo de errores.

## Fase 5 — Interfaz y despliegue

Se contempla:

* Interfaz web.
* Integración frontend/backend.
* Configuración para producción.
* Despliegue.
* Optimización.

---

# 👥 Equipo de desarrollo

Proyecto académico desarrollado para la gestión de servicios de un centro de formación.

Los integrantes del equipo pueden agregarse en esta sección:

```text
* Enrique Alexander Solano Lopez SL223188
* Adrián Alejandro Jiménez Mena JM242020
* Mario Antonio Rivera Hernandez RH242680
* Sergio Enrique Valencia Rosales VR242686
* Lazaro Moises Vargas Granados VG210810
```

---

# 📄 Estado del proyecto

**Estado:** En desarrollo 🚧

**Fase actual:** Fase 1 — Análisis, diseño y arquitectura.

El proyecto cuenta actualmente con una estructura inicial funcional basada en Spring Boot y arquitectura por capas. Las funcionalidades restantes serán incorporadas progresivamente durante las siguientes fases.

---

# 📜 Licencia

Este proyecto fue desarrollado con fines académicos.

© 2026 CFC Connect. Todos los derechos reservados.
