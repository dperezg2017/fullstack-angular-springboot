INSERT INTO regiones (id, nombre) VALUES (1,'Sudámerica');
INSERT INTO regiones (id, nombre) VALUES (2,'Centroamerica');
INSERT INTO regiones (id, nombre) VALUES (3,'Norteamérica');
INSERT INTO regiones (id, nombre) VALUES (4,'Europa');
INSERT INTO regiones (id, nombre) VALUES (5,'Asia');
INSERT INTO regiones (id, nombre) VALUES (6,'Africa');
INSERT INTO regiones (id, nombre) VALUES (7,'Oceanía');
INSERT INTO regiones (id, nombre) VALUES (8,'Antártida');

INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(1,'Deyviz','Perez','dperezg2017@gmail.com','2019-01-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(2,'Lesly','Luciel','luciel@gmail.com','2019-02-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(3,'Leon','San Bernardo','lbernardo@gmail.com','2019-03-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(4,'Camila','De la cruz','ccruz@gmail.com','2019-04-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(5,'Giuseppi','De los pinos','gpinos@gmail.com','2019-05-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(6,'Bartolome','Santarmel','bsantarmel@gmail.com','2019-06-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(7,'Sergio','Cuzco','scuzco@gmail.com','2019-07-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(8,'Amador','Nazaret','anazaret@gmail.com','2019-08-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(1,'Betinna','Paypal','bpaypal@gmail.com','2019-09-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(2,'Maribel','Ramos','bramos@gmail.com','2019-10-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(3,'Rosalia','Da silva','rsilva@gmail.com','2019-11-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(4,'Rosita','Garcia','rgarcia@gmail.com','2019-12-10');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES(5,'Lorena','Leonidas','llorena@gmail.com','2019-01-10');

INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('andres','$2a$10$giC/j4wrAEM7OpmRsxn.jeRLAar7QFDUow.KwSc5/NT3XdsDtptMK','1','Andres','Guzamn','profesor@bolsadeideas.com');
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('admin','$2a$10$DzhKJmOZkWiBepqDo60iouU8Zk64sQ9vx1xsZ9hC0pzRKtIoyUvaK','1','Deyviz','Doe','profesor@bolsadeideas.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,1);

INSERT INTO productos (nombre,precio,create_at) VALUES ('Platano Bellaco', 259990, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Platano Isla', 259991, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Platano Seda', 259992, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Naranja Huando', 259993, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Manzana Chilena', 259994, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Anis caja', 259995, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Anis c/u', 259996, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Manzanilla Pack', 259997, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Fideo Donvictorio', 259998, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Fideo lavaggi', 259999, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Fideo Alianza', 259910, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Tallarines lavaggi', 259920, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Tallarines Alianaz', 259930, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Tallarines Don Victorio', 259940, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Lechuga ', 259950, NOW());


INSERT INTO facturas (descripcion,observacion,cliente_id,create_at) VALUES('Factura equipos de oficina',null,1,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id)VALUES(1,1,1);
INSERT INTO facturas_items(cantidad,factura_id,producto_id)VALUES(2,1,4);
INSERT INTO facturas_items(cantidad,factura_id,producto_id)VALUES(1,1,5);
INSERT INTO facturas_items(cantidad,factura_id,producto_id)VALUES(1,1,7);

INSERT INTO facturas (descripcion,observacion,cliente_id,create_at) VALUES('Factura Bicicleta','Alguna nota importante!',1,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id)VALUES(3,2,6);