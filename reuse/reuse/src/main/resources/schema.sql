CREATE TABLE IF NOT EXISTS rol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    activo BOOLEAN,
    rol_id BIGINT,
    FOREIGN KEY (rol_id) REFERENCES rol(id)
);

CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    documento VARCHAR(20),
    telefono VARCHAR(20),
    activo BOOLEAN
);

CREATE TABLE IF NOT EXISTS categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    activo BOOLEAN
);

CREATE TABLE IF NOT EXISTS producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(255),
    precio DOUBLE,
    stock INT,
    activo BOOLEAN,
    categoria_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE IF NOT EXISTS pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME,
    total DOUBLE,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS detalle_pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT,
    precio DOUBLE,
    pedido_id BIGINT,
    producto_id BIGINT,
    FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);
