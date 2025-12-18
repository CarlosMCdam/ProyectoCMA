DROP TABLE IF EXISTS Portatiles;
DROP TABLE IF EXISTS PC;
DROP TABLE IF EXISTS Pantallas_Tactiles;
DROP TABLE IF EXISTS Armarios_Carga;
DROP TABLE IF EXISTS Tablets;
DROP TABLE IF EXISTS Impresoras;
DROP TABLE IF EXISTS Persona_Dispositivos;
DROP TABLE IF EXISTS Averia;
DROP TABLE IF EXISTS Dispositivos;
DROP TABLE IF EXISTS Modelo;
DROP TABLE IF EXISTS Persona;

CREATE TABLE Persona
(
    id_persona INT AUTO_INCREMENT PRIMARY KEY,
    gmail      VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Modelo
(
    id_modelo   INT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    estado      VARCHAR(50),
    tipo        VARCHAR(50),
    descripcion VARCHAR(255)
);

CREATE TABLE Dispositivos
(
    id_dispositivo INT AUTO_INCREMENT PRIMARY KEY,
    id_modelo      INT          NOT NULL,
    ubicacion      VARCHAR(100) NOT NULL,
    CONSTRAINT fk_dispositivo_modelo
        FOREIGN KEY (id_modelo) REFERENCES Modelo (id_modelo)
);

CREATE TABLE Averia
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
        FOREIGN KEY (id_dispositivo) REFERENCES Dispositivos (id_dispositivo),
    CONSTRAINT fk_averia_persona
        FOREIGN KEY (id_persona) REFERENCES Persona (id_persona)
);

CREATE TABLE Persona_Dispositivos
(
    id_persona     INT NOT NULL,
    id_dispositivo INT NOT NULL,
    PRIMARY KEY (id_persona, id_dispositivo),
    CONSTRAINT fk_pd_persona
        FOREIGN KEY (id_persona) REFERENCES Persona (id_persona),
    CONSTRAINT fk_pd_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES Dispositivos (id_dispositivo)
);

CREATE TABLE Impresoras
(
    id_dispositivo INT PRIMARY KEY,
    tipo_impresion VARCHAR(50),
    color          BOOLEAN,
    CONSTRAINT fk_impresoras_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES Dispositivos (id_dispositivo)
);

CREATE TABLE Tablets
(
    id_dispositivo    INT PRIMARY KEY,
    sistema_operativo VARCHAR(50),
    pulgadas          DECIMAL(4, 1),
    CONSTRAINT fk_tablets_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES Dispositivos (id_dispositivo)
);

CREATE TABLE Armarios_Carga
(
    id_dispositivo INT PRIMARY KEY,
    num_puertos    INT,
    ventilado      BOOLEAN,
    CONSTRAINT fk_armarios_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES Dispositivos (id_dispositivo)
);

CREATE TABLE Pantallas_Tactiles
(
    id_dispositivo INT PRIMARY KEY,
    pulgadas       DECIMAL(4, 1),
    resolucion     VARCHAR(50),
    CONSTRAINT fk_pantallas_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES Dispositivos (id_dispositivo)
);

CREATE TABLE PC
(
    id_dispositivo INT PRIMARY KEY,
    tipo_disco     VARCHAR(30),
    ram_gb         INT,
    CONSTRAINT fk_pc_dispositivo
        FOREIGN KEY (id_dispositivo) REFERENCES Dispositivos (id_dispositivo)
);

CREATE TABLE Portatiles
(
    id_pc    INT PRIMARY KEY,
    pulgadas DECIMAL(4, 1),
    CONSTRAINT fk_portatiles_pc
        FOREIGN KEY (id_pc) REFERENCES PC (id_dispositivo)
);
