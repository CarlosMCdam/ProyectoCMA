INSERT INTO databaseCMA.persona (gmail, rol)
VALUES ('andres.sinche@gmail.com', 'ADMIN'),
       ('marta.villena@gmail.com', 'ADMIN'),
       ('carlos.martinez@gmail.com', 'ADMIN');

INSERT INTO databaseCMA.modelo (nombre, estado, tipo, descripcion)
VALUES ('Modelo A', 'Activo', 'Tablet', 'Tablet para uso interno'),
       ('Modelo B', 'Activo', 'Impresora', 'Impresora láser color'),
       ('Modelo C', 'Inactivo', 'PC', 'PC de escritorio para oficina'),
       ('Modelo D', 'Activo', 'Portátil', 'Portátil para técnicos'),
       ('Modelo E', 'Activo', 'Pantalla táctil', 'Pantalla táctil de control');

INSERT INTO databaseCMA.dispositivos (id_modelo, ubicacion)
VALUES (1, 'Oficina 1'),
       (2, 'Sala de impresión'),
       (3, 'Oficina 2'),
       (4, 'Oficina 3'),
       (5, 'Recepción');

INSERT INTO databaseCMA.persona_dispositivos (id_persona, id_dispositivo)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (1, 4),
       (2, 5),
       (1, 5);

INSERT INTO databaseCMA.averia (id_averia, descripcion, estado, solucion, fecha_inicial, fecha_final, id_dispositivo, id_persona)
VALUES (1, 'Pantalla rota', 'Abierta', NULL, '2025-12-01', NULL, 5, 1),
       (2, 'Impresora sin toner', 'Resuelta', 'Cambio de toner', '2025-11-28', '2025-11-29', 2, 2),
       (3, 'Tablet no enciende', 'En progreso', NULL, '2025-12-10', NULL, 1, 3),
       (4, 'PC lento', 'Resuelta', 'Instalación de RAM adicional', '2025-12-05', '2025-12-06', 3, 1);

INSERT INTO databaseCMA.impresoras (id_dispositivo, tipo_impresion, color)
VALUES (2, 'Laser', TRUE);

INSERT INTO databaseCMA.tablets (id_dispositivo, sistema_operativo, pulgadas)
VALUES (1, 'Android', 10.1);

INSERT INTO databaseCMA.armarios_carga (id_dispositivo, num_puertos, ventilado)
VALUES (4, 8, TRUE);

INSERT INTO databaseCMA.pantallas_tactiles (id_dispositivo, pulgadas, resolucion)
VALUES (5, 15.6, '1920x1080');

INSERT INTO databaseCMA.pc (id_dispositivo, tipo_disco, ram_gb)
VALUES (3, 'SSD', 16);

INSERT INTO databaseCMA.portatiles (id_pc, pulgadas)
VALUES (3, 15.6);

