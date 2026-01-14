DROP TABLE IF EXISTS portatiles;
DROP TABLE IF EXISTS pc;
DROP TABLE IF EXISTS pantallas_tactiles;
DROP TABLE IF EXISTS armarios_carga;
DROP TABLE IF EXISTS tablets;
DROP TABLE IF EXISTS impresoras;
DROP TABLE IF EXISTS persona_dispositivos;
DROP TABLE IF EXISTS averia;
DROP TABLE IF EXISTS dispositivos;
DROP TABLE IF EXISTS modelo;
DROP TABLE IF EXISTS persona;

CREATE TABLE persona
(
    id_persona INT AUTO_INCREMENT PRIMARY KEY,
    gmail      VARCHAR(100) NOT NULL UNIQUE,
    rol        ENUM('ADMIN', 'PROFESOR', 'ALUMNO') NOT NULL
);

CREATE TABLE modelo
(
    id_modelo   INT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    estado      VARCHAR(50),
    tipo        VARCHAR(50),
    descripcion VARCHAR(255)
);

CREATE TABLE dispositivos
(
    id_dispositivo INT AUTO_INCREMENT PRIMARY KEY,
    id_modelo      INT          NOT NULL,
    ubicacion      VARCHAR(100) NOT NULL,
    CONSTRAINT fk_dispositivo_modelo
        FOREIGN KEY (id_modelo) REFERENCES modelo (id_modelo)
);

CREATE TABLE averia
(
    id_averia      INT PRIMARY KEY,
    descripcion    VARCHAR(255) NOT NULL,
    estado         VARCHAR(50)  NOT NULL,
    solucion       VARCHAR(255),
    fecha_inicial  DATE         NOT NULL,
    fecha_final    DATE,
    id_dispositivo INT          NOT NULL,
    id_persona     INT          NOT NULL,
    CONSTRAINT fk_averia_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES dispositivos (id_dispositivo),
    CONSTRAINT fk_averia_persona
        FOREIGN KEY (id_persona) REFERENCES persona (id_persona)
);

CREATE TABLE persona_dispositivos
(
    id_persona     INT NOT NULL,
    id_dispositivo INT NOT NULL,
    PRIMARY KEY (id_persona, id_dispositivo),
    CONSTRAINT fk_pd_persona
        FOREIGN KEY (id_persona) REFERENCES persona (id_persona),
    CONSTRAINT fk_pd_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES dispositivos (id_dispositivo)
);

CREATE TABLE impresoras
(
    id_dispositivo INT PRIMARY KEY,
    tipo_impresion VARCHAR(50),
    color          BOOLEAN,
    CONSTRAINT fk_impresoras_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES dispositivos (id_dispositivo)
);

CREATE TABLE tablets
(
    id_dispositivo    INT PRIMARY KEY,
    sistema_operativo VARCHAR(50),
    pulgadas          DECIMAL(4, 1),
    CONSTRAINT fk_tablets_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES dispositivos (id_dispositivo)
);

CREATE TABLE armarios_carga
(
    id_dispositivo INT PRIMARY KEY,
    num_puertos    INT,
    ventilado      BOOLEAN,
    CONSTRAINT fk_armarios_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES dispositivos (id_dispositivo)
);

CREATE TABLE pantallas_tactiles
(
    id_dispositivo INT PRIMARY KEY,
    pulgadas       DECIMAL(4, 1),
    resolucion     VARCHAR(50),
    CONSTRAINT fk_pantallas_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES dispositivos (id_dispositivo)
);

CREATE TABLE pc
(
    id_dispositivo INT PRIMARY KEY,
    tipo_disco     VARCHAR(30),
    ram_gb         INT,
    CONSTRAINT fk_pc_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES dispositivos (id_dispositivo)
);

CREATE TABLE portatiles
(
    id_pc    INT PRIMARY KEY,
    pulgadas DECIMAL(4, 1),
    CONSTRAINT fk_portatiles_pc
        FOREIGN KEY (id_pc) REFERENCES pc (id_dispositivo)
);
