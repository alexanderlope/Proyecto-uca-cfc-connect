-- =====================================================================
-- UCA-CFC Connect — 
-- =====================================================================

CREATE DATABASE IF NOT EXISTS uca_cfc_connect
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE uca_cfc_connect;

-- ---------------------------------------------------------------------
-- Seguridad
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS rol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    rol_id BIGINT NOT NULL,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES rol(id)
);

-- ---------------------------------------------------------------------
-- Clientes
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dui_nit VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    empresa VARCHAR(100),
    correo VARCHAR(100),
    telefono VARCHAR(30),
    direccion VARCHAR(255),
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

-- ---------------------------------------------------------------------
-- Catálogos de gestión académica
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS modalidad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS docente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100),
    correo VARCHAR(100),
    telefono VARCHAR(30)
);

-- oferta_academica: tabla única para Curso y Diplomado (herencia SINGLE_TABLE)
CREATE TABLE IF NOT EXISTS oferta_academica (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL,              -- discriminador: CURSO / DIPLOMADO
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(255),
    categoria_id BIGINT NOT NULL,
    modalidad_id BIGINT NOT NULL,
    docente_id BIGINT NOT NULL,
    cupo_maximo INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    horario VARCHAR(100),
    costo DECIMAL(10,2) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    duracion INT NULL,                      -- solo aplicara cuando tipo sea = DIPLOMADO
    CONSTRAINT fk_oferta_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    CONSTRAINT fk_oferta_modalidad FOREIGN KEY (modalidad_id) REFERENCES modalidad(id),
    CONSTRAINT fk_oferta_docente FOREIGN KEY (docente_id) REFERENCES docente(id)
);

-- ---------------------------------------------------------------------
-- Inscripciones
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS inscripcion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    oferta_academica_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE', -- PENDIENTE, CONFIRMADA, CANCELADA, FINALIZADA
    CONSTRAINT fk_inscripcion_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_inscripcion_oferta FOREIGN KEY (oferta_academica_id) REFERENCES oferta_academica(id)
);

-- ---------------------------------------------------------------------
-- Cotizaciones
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS cotizacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    tipo VARCHAR(20) NOT NULL,   -- CURSO, DIPLOMADO, ESPACIO, CATERING, COMBINADO
    fecha DATE NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE', -- PENDIENTE, EN_PROCESO, APROBADA, RECHAZADA
    total DECIMAL(10,2) NOT NULL DEFAULT 0,
    CONSTRAINT fk_cotizacion_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS detalle_cotizacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cotizacion_id BIGINT NOT NULL,
    item_tipo VARCHAR(20) NOT NULL,  -- CURSO, DIPLOMADO, ESPACIO, CATERING
    item_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_detalle_cotizacion FOREIGN KEY (cotizacion_id) REFERENCES cotizacion(id)
        ON DELETE CASCADE
);

-- ---------------------------------------------------------------------
-- Alquiler de espacios
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS espacio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(30) NOT NULL, -- AUDITORIO, SALA_REUNIONES, LABORATORIO, AULA, SALA_MULTIMEDIA
    capacidad INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    equipamiento VARCHAR(255),
    disponible BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS solicitud_alquiler (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    espacio_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    CONSTRAINT fk_alquiler_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_alquiler_espacio FOREIGN KEY (espacio_id) REFERENCES espacio(id)
);

-- ---------------------------------------------------------------------
-- Catering
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS servicio_catering (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,  -- Coffee Break, Desayuno, Almuerzo, Cena, Refrigerio
    descripcion VARCHAR(255),
    precio_por_persona DECIMAL(10,2) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS solicitud_catering (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    servicio_id BIGINT NOT NULL,
    num_asistentes INT NOT NULL,
    menu VARCHAR(255),
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    lugar VARCHAR(150),
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    CONSTRAINT fk_catering_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_catering_servicio FOREIGN KEY (servicio_id) REFERENCES servicio_catering(id)
);

-- ---------------------------------------------------------------------
-- Agenda institucional 
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS actividad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL,       -- CURSO, DIPLOMADO, EVENTO, ALQUILER, CATERING
    referencia_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    lugar VARCHAR(150)
);

-- ---------------------------------------------------------------------
-- Pagos
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS pago (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    tipo_referencia VARCHAR(20) NOT NULL, -- INSCRIPCION, COTIZACION, ALQUILER, CATERING
    referencia_id BIGINT NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    metodo VARCHAR(20) NOT NULL,   -- EFECTIVO, TARJETA, TRANSFERENCIA, DEPOSITO
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE', -- PENDIENTE, PARCIAL, PAGADO
    fecha DATE NOT NULL,
    CONSTRAINT fk_pago_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- ---------------------------------------------------------------------
-- Índices útiles para búsquedas y filtros frecuentes
-- ---------------------------------------------------------------------
CREATE INDEX idx_oferta_categoria ON oferta_academica(categoria_id);
CREATE INDEX idx_oferta_modalidad ON oferta_academica(modalidad_id);
CREATE INDEX idx_inscripcion_estado ON inscripcion(estado);
CREATE INDEX idx_cotizacion_estado ON cotizacion(estado);
CREATE INDEX idx_alquiler_fecha ON solicitud_alquiler(fecha);
CREATE INDEX idx_catering_fecha ON solicitud_catering(fecha);
CREATE INDEX idx_pago_estado ON pago(estado);

-- ---------------------------------------------------------------------
-- Datos semilla mínimos (roles del sistema)
-- ---------------------------------------------------------------------
INSERT INTO rol (nombre, descripcion) VALUES
    ('ADMIN', 'Administra usuarios, roles, catálogos y aprueba cotizaciones'),
    ('RECEPCIONISTA', 'Usuario operativo: registra clientes, inscripciones y solicitudes'),
    ('CLIENTE', 'Consulta oferta académica y solicita servicios'),
    ('CONTABILIDAD', 'Registra y valida pagos, emite comprobantes')
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);
