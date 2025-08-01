CREATE DEFINER=`root`@`localhost` PROCEDURE `REGISTROS EMPLEADOS`(
    IN nombreCompleto VARCHAR(100)
)
BEGIN
    IF nombreCompleto = 'TODOS' THEN
        -- Mostrar todos los registros
        SELECT 
            registros.id AS id_registro,
            registros.uid,
            empleados.nombre AS nombre_empleado,
            empleados.apellidoPaterno,
            empleados.apellidoMaterno,
            registros.fecha_entrada,
            registros.fecha_salida
        FROM registros
        INNER JOIN empleados ON registros.uid = empleados.uid;
    ELSE
        -- Filtrar por nombre completo
        SELECT 
            registros.id AS id_registro,
            registros.uid,
            empleados.nombre AS nombre_empleado,
            empleados.apellidoPaterno,
            empleados.apellidoMaterno,
            registros.fecha_entrada,
            registros.fecha_salida
        FROM registros
        INNER JOIN empleados ON registros.uid = empleados.uid
        WHERE CONCAT(empleados.nombre, ' ', empleados.apellidoPaterno, ' ', empleados.apellidoMaterno) = nombreCompleto;
    END IF;
END