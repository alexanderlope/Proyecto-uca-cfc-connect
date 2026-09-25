# Guía de ejecución y capturas para CFC Connect

## Antes de iniciar

En la copia revisada, `pom.xml` requiere Java 21. El `java` activo en la terminal es Java 17 y el archivo `.idea/misc.xml` hace referencia a un SDK llamado `openjdk-26`; confirma en IntelliJ que exista y selecciona preferiblemente un JDK 21. Maven Wrapper no está incluido.

En IntelliJ:

1. Abre la carpeta del proyecto y espera la importación de Maven.
2. En **File → Project Structure → Project SDK**, elige un JDK 21.
3. En los ajustes de Maven, elige también un JRE compatible (JDK 21).
4. Ejecuta `CfcConnectApplication`.
5. Espera el mensaje de Spring Boot que confirma el arranque.

O en PowerShell, desde la raíz del repositorio:

```powershell
mvn clean test
mvn spring-boot:run
```

La app usa H2 en memoria y `DataSeeder` agrega registros de prueba. No requiere instalar MySQL para la demostración predeterminada.

## Capturas recomendadas

Guarda cada captura con un nombre claro, por ejemplo `01-arranque.png`. Asegúrate de que se vea la URL/operación y el resultado HTTP cuando corresponda.

1. **Arranque:** consola de IntelliJ con la aplicación iniciada.
2. **Panel web:** abre `http://localhost:8080/`. Captura el resumen y, al entrar a cada módulo, las tablas y formularios para crear, buscar, editar y eliminar registros.
3. **Swagger:** abre `http://localhost:8080/swagger-ui.html` y captura la lista de controladores y rutas.
4. **Base de datos:** abre `http://localhost:8080/h2-console`; conecta con JDBC URL `jdbc:h2:mem:cfcconnect`, usuario `sa`, sin contraseña. Captura las tablas o una consulta SQL. El esquema de referencia está en `docs/schema.sql`.
5. **CRUD:** en el panel web, por cada módulo que el equipo presenta, captura crear, listar/buscar, editar y eliminar. Conserva el ID devuelto por la creación para consultar el registro. Swagger permite mostrar los códigos HTTP correspondientes.
6. **Validación:** envía un POST con un campo obligatorio ausente o inválido; captura la respuesta 400 y el detalle JSON.
7. **Error:** solicita un ID que no existe; captura la respuesta 404.
8. **Búsqueda personalizada:** usa `GET /espacios?soloDisponibles=true` y captura la consulta junto con la respuesta. Las funciones de búsqueda de repositorio que no tienen ruta HTTP no se pueden evidenciar desde Swagger.
9. **Módulos:** captura las operaciones específicas disponibles: inscribir participante en curso/diplomado, aprobar/rechazar cotización, reservar/liberar espacio, cambiar estado de inscripción y confirmar pago.
10. **Git:** en la terminal ejecuta `git branch --all` y `git log --oneline --decorate --all`; captura ambas salidas.

## Estado de verificación

Durante esta revisión solo se inspeccionó el código y la configuración. No se ejecutaron las pruebas ni se inició la aplicación. Java 17 está activo, mientras que el proyecto pide Java 21; corrige esa diferencia en IntelliJ antes de generar capturas de funcionamiento. `src/test` no contiene pruebas y no se encontraron reportes Surefire en `target`, así que no presentes resultados automatizados sin ejecutar y documentar pruebas reales.
