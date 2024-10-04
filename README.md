
# Sistema de Gestión de Contenedores y Logística

Este proyecto contiene los scripts SQL y el esquema de base de datos para el **Sistema de Gestión de Contenedores y Logística (SGCL)**. El proyecto incluye los comandos SQL necesarios para crear la base de datos, insertar datos iniciales y realizar consultas.

## Descripción de la Base de Datos

La base de datos consiste en las siguientes entidades principales:

- **Operador**: Contiene información sobre los operadores en el sistema.
- **Rol**: Define los roles asignados a los operadores.
- **Permiso**: Especifica los permisos otorgados a los roles.
- **Ubicación**: Gestiona las ubicaciones de los contenedores.
- **Contenedor**: Almacena información sobre los contenedores.
- **Módulo**: Define los módulos dentro de los contenedores.
- **Caja**: Almacena información sobre las cajas dentro de los módulos.
- **Ruta**: Define las rutas para los movimientos de contenedores.
- **Historial Movimiento**: Rastrea los movimientos de contenedores y cajas.

## Creación del Esquema y Tablas

A continuación se encuentran los comandos SQL necesarios para la creación de la base de datos y las tablas:

```sql
CREATE DATABASE sgcl_db;
USE sgcl_db;

-- Creación de tablas

CREATE TABLE operador (
    id INT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100)
);

CREATE TABLE rol (
    id INT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE permiso (
    id INT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE operador_rol (
    operador_id INT,
    rol_id INT,
    PRIMARY KEY (operador_id, rol_id),
    FOREIGN KEY (operador_id) REFERENCES operador(id),
    FOREIGN KEY (rol_id) REFERENCES rol(id)
);

CREATE TABLE rol_permiso (
    rol_id INT,
    permiso_id INT,
    PRIMARY KEY (rol_id, permiso_id),
    FOREIGN KEY (rol_id) REFERENCES rol(id),
    FOREIGN KEY (permiso_id) REFERENCES permiso(id)
);

CREATE TABLE ubicacion (
    id INT PRIMARY KEY,
    nombre VARCHAR(100),
    direccion VARCHAR(200)
);

CREATE TABLE contenedor (
    id INT PRIMARY KEY,
    ubicacion_id INT,
    codigo VARCHAR(100),
    estado VARCHAR(50),
    FOREIGN KEY (ubicacion_id) REFERENCES ubicacion(id)
);

CREATE TABLE modulo (
    id INT PRIMARY KEY,
    contenedor_id INT,
    nombre VARCHAR(100),
    FOREIGN KEY (contenedor_id) REFERENCES contenedor(id)
);

CREATE TABLE caja (
    id INT PRIMARY KEY,
    modulo_id INT,
    nombre VARCHAR(100),
    FOREIGN KEY (modulo_id) REFERENCES modulo(id)
);

CREATE TABLE ruta (
    id INT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE historial_movimiento (
    id INT PRIMARY KEY,
    caja_id INT,
    operador_id INT,
    ubicacion_id INT,
    ruta_id INT,
    fecha_movimiento DATE,
    FOREIGN KEY (caja_id) REFERENCES caja(id),
    FOREIGN KEY (operador_id) REFERENCES operador(id),
    FOREIGN KEY (ubicacion_id) REFERENCES ubicacion(id),
    FOREIGN KEY (ruta_id) REFERENCES ruta(id)
);
```

## Inserción de Datos

Los siguientes comandos SQL insertan los datos iniciales en las tablas:

```sql
INSERT INTO operador (id, nombre, apellido) VALUES (1, 'Juan', 'Perez');
INSERT INTO operador (id, nombre, apellido) VALUES (2, 'Maria', 'Lopez');

INSERT INTO rol (id, nombre) VALUES (1, 'Admin');
INSERT INTO rol (id, nombre) VALUES (2, 'Operador');

INSERT INTO permiso (id, nombre) VALUES (1, 'Ver');
INSERT INTO permiso (id, nombre) VALUES (2, 'Editar');

INSERT INTO operador_rol (operador_id, rol_id) VALUES (1, 1);
INSERT INTO operador_rol (operador_id, rol_id) VALUES (2, 2);

INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (1, 1);
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (1, 2);
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (2, 1);

INSERT INTO ubicacion (id, nombre, direccion) VALUES (1, 'Deposito A', 'Calle Falsa 123');
INSERT INTO ubicacion (id, nombre, direccion) VALUES (2, 'Deposito B', 'Avenida Siempre Viva 742');

INSERT INTO contenedor (id, ubicacion_id, codigo, estado) VALUES (1, 1, 'CONT-001', 'Activo');
INSERT INTO contenedor (id, ubicacion_id, codigo, estado) VALUES (2, 2, 'CONT-002', 'Inactivo');

INSERT INTO modulo (id, contenedor_id, nombre) VALUES (1, 1, 'Modulo 1');
INSERT INTO modulo (id, contenedor_id, nombre) VALUES (2, 2, 'Modulo 2');

INSERT INTO caja (id, modulo_id, nombre) VALUES (1, 1, 'Caja 1');
INSERT INTO caja (id, modulo_id, nombre) VALUES (2, 2, 'Caja 2');

INSERT INTO ruta (id, nombre) VALUES (1, 'Ruta Norte');
INSERT INTO ruta (id, nombre) VALUES (2, 'Ruta Sur');

INSERT INTO historial_movimiento (id, caja_id, operador_id, ubicacion_id, ruta_id, fecha_movimiento)
VALUES (1, 1, 1, 1, 1, '2024-09-26');
INSERT INTO historial_movimiento (id, caja_id, operador_id, ubicacion_id, ruta_id, fecha_movimiento)
VALUES (2, 2, 2, 2, 2, '2024-09-26');
```

## Consultas SQL de Ejemplo

1. Consultar todos los movimientos de una caja específica:

```sql
SELECT hm.id, hm.fecha_movimiento, o.nombre AS operador, u.nombre AS ubicacion, r.nombre AS ruta
FROM historial_movimiento hm
JOIN operador o ON hm.operador_id = o.id
JOIN ubicacion u ON hm.ubicacion_id = u.id
JOIN ruta r ON hm.ruta_id = r.id
WHERE hm.caja_id = 1;
```

2. Consultar permisos asociados a un rol:

```sql
SELECT r.nombre AS rol, p.nombre AS permiso
FROM rol r
JOIN rol_permiso rp ON r.id = rp.rol_id
JOIN permiso p ON rp.permiso_id = p.id
WHERE r.id = 1;
```

3. Consultar cajas en un contenedor específico:

```sql
SELECT c.nombre AS caja, m.nombre AS modulo, cont.codigo AS contenedor
FROM caja c
JOIN modulo m ON c.modulo_id = m.id
JOIN contenedor cont ON m.contenedor_id = cont.id
WHERE cont.id = 1;
```

## Capturas de Pantalla

Aquí están algunas capturas de pantalla de la base de datos en acción:

![SGCL Base de Datos - Captura 1](./images/sgcl_db_image1.png)

![SGCL Base de Datos - Captura 2](./images/sgcl_db_image2.png)

![SGCL Base de Datos - Captura 3](./images/sgcl_db_image3.png)


## Uso

1. Clona este repositorio.
2. Ejecuta el script SQL proporcionado (`sgcl_db_script.sql`) para crear la base de datos y las tablas.
3. Inserta los datos de ejemplo y ejecuta las consultas según sea necesario.

Siente la libertad de explorar el esquema y extenderlo para más funcionalidades.
