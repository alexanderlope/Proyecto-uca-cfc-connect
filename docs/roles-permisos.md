# Seguridad, roles y persistencia

La API usa HTTP Basic sobre Spring Security. El usuario es el correo guardado en `usuarios`; la contraseña se guarda con BCrypt. Las cuentas deshabilitadas (`estado=false`) no pueden autenticarse. La documentación OpenAPI queda pública y todos los endpoints de negocio requieren autenticación.

## Matriz de permisos

| Operación | ADMIN | RECEPCIONISTA | CLIENTE | CONTABILIDAD |
|---|---:|---:|---:|---:|
| Crear, consultar, editar y eliminar usuarios y roles | Sí | No | No | No |
| Gestionar clientes | Sí | Sí | No | No |
| Consultar cursos, diplomados, espacios y catering | Sí | Sí | Sí | Sí |
| Administrar cursos, diplomados, espacios y catering | Sí | Sí | No | No |
| Gestionar inscripciones y cotizaciones | Sí | Sí | Solo crear | No |
| Consultar y gestionar pagos | Sí | No | No | Sí |

Las cuentas CLIENTE solo pueden crear inscripciones y cotizaciones asociadas al cliente cuyo correo coincide con el correo de inicio de sesión; el `clienteId` enviado se reemplaza por el vínculo encontrado. Las lecturas de colecciones de inscripciones, cotizaciones y pagos se restringen porque la API aún no implementa filtrado por propietario.

## Primer administrador

Configura `CFC_ADMIN_EMAIL` y `CFC_ADMIN_PASSWORD` en el entorno antes de iniciar la aplicación. La contraseña debe tener al menos 12 caracteres. Si ese correo no existe, la aplicación crea el usuario ADMIN con contraseña BCrypt. Si ya existe, no cambia su contraseña. No guardes estas variables en el repositorio.

Los usuarios creados mediante `POST /usuarios` también reciben contraseña BCrypt. Los registros de usuario existentes que tengan contraseñas en texto plano deben restablecerse antes de usar autenticación; no se aceptan contraseñas sin cifrar.

## Respuestas de error

- `401`: falta autenticación o las credenciales no son válidas.
- `403`: el rol no tiene permiso, o la cuenta CLIENTE no está vinculada a un registro de cliente.
- `400`: Bean Validation rechazó campos del cuerpo.
- `404`: no existe el recurso solicitado.
- `409`: regla de negocio o restricción de integridad/duplicidad de la base de datos.
- `500`: error inesperado; el cuerpo no expone mensajes internos de SQL.

## MySQL

El perfil `mysql` conecta a `uca_cfc_connect` en `localhost:3306` por defecto y crea la base si el usuario de conexión tiene permisos. Configura `DB_URL`, `DB_USERNAME` y `DB_PASSWORD` mediante el entorno. Ejemplo de inicio: `mvn spring-boot:run -Dspring-boot.run.profiles=mysql`. Hibernate mantiene `ddl-auto=update`; `docs/schema.sql` es una referencia del modelo, no un script de migración ejecutado por la aplicación.

Las validaciones de los DTO dan respuestas `400`; las restricciones únicas y foráneas de JPA/MySQL son la última barrera y se traducen a `409` sin filtrar el detalle SQL.
