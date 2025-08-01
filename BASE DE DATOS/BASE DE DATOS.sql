-- Crear la base de datos
drop database if exists sistemaLectorRFID;
CREATE DATABASE sistemaLectorRFID;
USE sistemaLectorRFID;
-- Tabla de perfiles
DROP TABLE IF EXISTS perfiles;
CREATE TABLE perfiles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(200) NOT NULL
);
-- Tabla de usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idPerfil INT NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    contrasenia VARCHAR(60) NOT NULL,
    activo TINYINT(1),
    FOREIGN KEY (idPerfil) REFERENCES perfiles(id)
);
-- Tabla de estatus de empleados
CREATE TABLE estatusempleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL
);
-- Tabla de tipos de empleados
CREATE TABLE tiposempleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL
);
-- Tabla de departamentos
CREATE TABLE departamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL
);
-- tabla de horarios
DROP TABLE IF EXISTS horarios; 
CREATE TABLE horarios (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);
DROP TABLE IF EXISTS detalle_horarios;
CREATE TABLE detalle_horarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    horario_id INT NOT NULL,
    dia ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes') NOT NULL,
    laborable BOOLEAN NOT NULL,
    entrada TIME,
    salida TIME,
    FOREIGN KEY (horario_id) REFERENCES horarios(id) ON DELETE CASCADE
);
-- Tabla de empleados
DROP TABLE IF EXISTS empleados;
CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    uid VARCHAR(100) UNIQUE,
    idTipoEmpleado INT NOT NULL,
    idEstatusEmpleado INT NOT NULL,
    idDepartamento INT NOT NULL,
    idHorario INT NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    apellidoPaterno VARCHAR(30) NOT NULL,
    apellidoMaterno VARCHAR(30) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    domicilio VARCHAR(200) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    curp VARCHAR(18) NOT NULL,
    observaciones VARCHAR(200) NOT NULL,
    fotografia BLOB NOT NULL, 
    FOREIGN KEY (idTipoEmpleado) REFERENCES tiposempleados(id),
    FOREIGN KEY (idEstatusEmpleado) REFERENCES estatusempleados(id),
    FOREIGN KEY (idDepartamento) REFERENCES departamentos(id)
);

CREATE TABLE tiposincidencias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL
);

CREATE TABLE incidencias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idEmpleado INT NOT NULL,
    idTipoIncidencia INT NOT NULL,
    fechaHoraInicio DATE NOT NULL,
    fechaHoraFin DATE NOT NULL,
    observaciones VARCHAR(200) NOT NULL,
    FOREIGN KEY (idEmpleado) REFERENCES empleados(id),
    FOREIGN KEY (idTipoIncidencia) REFERENCES tiposincidencias(id)
);
CREATE TABLE registros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    uid VARCHAR(100),
    nombre VARCHAR(100),
    fecha_entrada DATETIME,
    fecha_salida DATETIME,
    FOREIGN KEY (uid) REFERENCES empleados(uid)
);
DROP TABLE IF EXISTS imagen;
CREATE TABLE imagen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    imagen BLOB NOT NULL,
    descripcion VARCHAR(200) NOT NULL
);