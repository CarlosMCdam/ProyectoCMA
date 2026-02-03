SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE databaseCMA.portatiles;
TRUNCATE TABLE databaseCMA.pc;
TRUNCATE TABLE databaseCMA.pantallas_tactiles;
TRUNCATE TABLE databaseCMA.armarios_carga;
TRUNCATE TABLE databaseCMA.tablets;
TRUNCATE TABLE databaseCMA.impresoras;
TRUNCATE TABLE databaseCMA.persona_dispositivos;
TRUNCATE TABLE databaseCMA.averia;
TRUNCATE TABLE databaseCMA.dispositivos;
TRUNCATE TABLE databaseCMA.modelo;
TRUNCATE TABLE databaseCMA.persona;


INSERT INTO databaseCMA.persona (gmail, rol)
VALUES
    ('andres.sinche@gmail.com', 'ADMIN'),
    ('marta.villena@gmail.com', 'ADMIN'),
    ('carlos.martinez@gmail.com', 'ADMIN'),
    ('laura.gomez@gmail.com', 'PROFESOR'),
    ('javier.santos@gmail.com', 'ALUMNO'),
    ('patricia.lopez@gmail.com', 'ADMIN'),
    ('sergio.ramirez@gmail.com', 'PROFESOR'),
    ('ana.torres@gmail.com', 'ALUMNO'),
    ('david.morales@gmail.com', 'ADMIN'),
    ('lucia.fernandez@gmail.com', 'PROFESOR'),
    ('roberto.perez@gmail.com', 'ALUMNO'),
    ('ines.castillo@gmail.com', 'ADMIN');

INSERT INTO databaseCMA.modelo (nombre, estado, tipo, descripcion)
VALUES
    ('Modelo A', 'Activo', 'Tablet', 'Tablet para uso interno'),
    ('Modelo B', 'Activo', 'Impresora', 'Impresora láser color'),
    ('Modelo C', 'Inactivo', 'PC', 'PC de escritorio para oficina'),
    ('Modelo D', 'Activo', 'Portátil', 'Portátil para técnicos'),
    ('Modelo E', 'Activo', 'Pantalla táctil', 'Pantalla táctil de control'),
    ('Modelo F', 'Activo', 'Tablet', 'Tablet de alta resistencia'),
    ('Modelo G', 'Activo', 'Impresora', 'Impresora térmica'),
    ('Modelo H', 'Inactivo', 'PC', 'PC antiguo de laboratorio'),
    ('Modelo I', 'Activo', 'Portátil', 'Portátil ultraligero'),
    ('Modelo J', 'Activo', 'Pantalla táctil', 'Pantalla táctil industrial'),
    ('Modelo K', 'Activo', 'Tablet', 'Tablet para alumnos'),
    ('Modelo L', 'Activo', 'Impresora', 'Impresora multifunción'),
    ('Modelo M', 'Activo', 'PC', 'PC de alto rendimiento'),
    ('Modelo N', 'Activo', 'Portátil', 'Portátil para profesores'),
    ('Modelo O', 'Activo', 'Pantalla táctil', 'Pantalla táctil 4K'),
    ('Modelo P', 'Activo', 'Tablet', 'Tablet de inventario'),
    ('Modelo Q', 'Activo', 'Impresora', 'Impresora matricial'),
    ('Modelo R', 'Activo', 'PC', 'PC de administración'),
    ('Modelo S', 'Activo', 'Portátil', 'Portátil gaming'),
    ('Modelo T', 'Activo', 'Pantalla táctil', 'Pantalla táctil educativa');

INSERT INTO databaseCMA.dispositivos (id_modelo, ubicacion)
VALUES
    (1, 'Oficina 1'),
    (2, 'Sala de impresión'),
    (3, 'Oficina 2'),
    (4, 'Oficina 3'),
    (5, 'Recepción'),
    (6, 'Aula 1'),
    (7, 'Almacén'),
    (8, 'Laboratorio'),
    (9, 'Aula 2'),
    (10, 'Sala de control'),
    (11, 'Aula 3'),
    (12, 'Oficina 4'),
    (13, 'Administración'),
    (14, 'Aula 4'),
    (15, 'Hall principal'),
    (16, 'Aula 5'),
    (17, 'Sala de profesores'),
    (18, 'Dirección'),
    (19, 'Aula 6'),
    (20, 'Biblioteca');

INSERT INTO databaseCMA.persona_dispositivos (id_persona, id_dispositivo)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (1, 4),
    (2, 5),
    (1, 5),
    (4, 6),
    (5, 7),
    (6, 8),
    (7, 9),
    (8, 10),
    (9, 11),
    (10, 12),
    (11, 13),
    (12, 14),
    (4, 15),
    (5, 16),
    (6, 17),
    (7, 18),
    (8, 19),
    (9, 20),
    (10, 6),
    (11, 7),
    (12, 8);

INSERT INTO databaseCMA.averia (id_averia, descripcion, estado, solucion, fecha_inicial, fecha_final, id_dispositivo, id_persona)
VALUES
    (1, 'Pantalla rota', 'Abierta', NULL, '2025-12-01', NULL, 5, 1),
    (2, 'Impresora sin toner', 'Resuelta', 'Cambio de toner', '2025-11-28', '2025-11-29', 2, 2),
    (3, 'Tablet no enciende', 'En progreso', NULL, '2025-12-10', NULL, 1, 3),
    (4, 'PC lento', 'Resuelta', 'Instalación de RAM adicional', '2025-12-05', '2025-12-06', 3, 1),
    (5, 'Tablet congelada', 'Abierta', NULL, '2025-12-12', NULL, 6, 4),
    (6, 'Impresora atascada', 'Resuelta', 'Limpieza interna', '2025-12-02', '2025-12-03', 7, 5),
    (7, 'PC no arranca', 'En progreso', NULL, '2025-12-11', NULL, 8, 6),
    (8, 'Portátil sobrecalentado', 'Abierta', NULL, '2025-12-09', NULL, 9, 7),
    (9, 'Pantalla táctil sin respuesta', 'Abierta', NULL, '2025-12-08', NULL, 10, 8),
    (10, 'Tablet sin batería', 'Resuelta', 'Cambio de batería', '2025-12-04', '2025-12-05', 11, 9),
    (11, 'Impresora imprime borroso', 'En progreso', NULL, '2025-12-07', NULL, 12, 10),
    (12, 'PC con virus', 'Resuelta', 'Formateo y reinstalación', '2025-12-01', '2025-12-02', 13, 11),
    (13, 'Portátil teclado roto', 'Abierta', NULL, '2025-12-10', NULL, 14, 12),
    (14, 'Pantalla táctil parpadea', 'En progreso', NULL, '2025-12-06', NULL, 15, 4),
    (15, 'Tablet sin WiFi', 'Resuelta', 'Reconfiguración de red', '2025-12-03', '2025-12-03', 16, 5),
    (16, 'PC ventilador ruidoso', 'Abierta', NULL, '2025-12-11', NULL, 17, 6);

INSERT INTO databaseCMA.impresoras (id_dispositivo, tipo_impresion, color)
VALUES
    (2, 'Laser', TRUE),
    (7, 'Termica', FALSE),
    (12, 'Multifuncion', TRUE),
    (17, 'Laser', TRUE),
    (20, 'Matricial', FALSE);

INSERT INTO databaseCMA.tablets (id_dispositivo, sistema_operativo, pulgadas)
VALUES
    (1, 'Android', 10.1),
    (6, 'Android', 10.5),
    (11, 'iOS', 11.0),
    (16, 'Android', 9.7),
    (19, 'Android', 10.8);

INSERT INTO databaseCMA.armarios_carga (id_dispositivo, num_puertos, ventilado)
VALUES
    (4, 8, TRUE),
    (8, 12, TRUE),
    (13, 6, FALSE),
    (18, 10, TRUE);

INSERT INTO databaseCMA.pantallas_tactiles (id_dispositivo, pulgadas, resolucion)
VALUES
    (5, 15.6, '1920x1080'),
    (10, 17.0, '1920x1080'),
    (15, 21.5, '2560x1440'),
    (18, 24.0, '3840x2160'),
    (20, 27.0, '1920x1080');

INSERT INTO databaseCMA.pc (id_dispositivo, tipo_disco, ram_gb)
VALUES
    (3, 'SSD', 16),
    (8, 'HDD', 8),
    (13, 'SSD', 32),
    (17, 'SSD', 16),
    (18, 'HDD', 4);

INSERT INTO databaseCMA.portatiles (id_pc, pulgadas)
VALUES
    (3, 15.6),
    (8, 14.0),
    (13, 15.6),
    (17, 13.3);


