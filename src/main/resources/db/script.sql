-- Crear usuario APIREST
CREATE USER APIREST IDENTIFIED BY APIREST
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp;

-- Otorgar permisos necesarios al usuario APIREST
GRANT CONNECT, RESOURCE TO APIREST;
GRANT CREATE SESSION TO APIREST;
GRANT CREATE SEQUENCE TO APIREST;
GRANT CREATE TABLE TO APIREST;
GRANT CREATE VIEW TO APIREST;
GRANT DBA TO APIREST;

CONNET APIREST/APIREST;

-- Creacion de la tabla estados
CREATE TABLE estados (
    estado_id VARCHAR(5) PRIMARY KEY,
    estado VARCHAR(50) UNIQUE NOT NULL
);

-- Creacion de la tabla roles
CREATE TABLE roles (
    rol_id NUMBER PRIMARY KEY,
    rol VARCHAR2(50) UNIQUE NOT NULL,
    codigo VARCHAR2(20) UNIQUE NOT NULL,
    estado_id VARCHAR(5) NOT NULL,
    FOREIGN KEY (estado_id) REFERENCES estados(estado_id)
);

-- Creacion de la tabla usuarios
CREATE TABLE usuarios (
    usuario_id NUMBER PRIMARY KEY,
    tipo_identificacion VARCHAR2(5) NOT NULL,
    numero_identificacion VARCHAR2(50) NOT NULL,
    primer_nombre VARCHAR2(50) NOT NULL,
    segundo_nombre VARCHAR2(50),
    primer_apellido VARCHAR2(50) NOT NULL,
    segundo_apellido VARCHAR2(50),
    correo_electronico VARCHAR2(100) UNIQUE NOT NULL,
    telefono VARCHAR2(20),
    nombre_usuario VARCHAR2(50) UNIQUE NOT NULL,
    contrasena VARCHAR2(100) NOT NULL,
    rol_id NUMBER NOT NULL,
    estado_id VARCHAR(5) NOT NULL,
    fecha_creacion DATE NOT NULL, 
	fecha_modificacion DATE,
    FOREIGN KEY (rol_id) REFERENCES roles(rol_id),
    FOREIGN KEY (estado_id) REFERENCES estados(estado_id)
);