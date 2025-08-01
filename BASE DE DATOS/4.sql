CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerEmpleadosResumen`()
BEGIN
 SELECT 
        e.uid,
        CONCAT(e.nombre, ' ', e.apellidoPaterno, ' ', e.apellidoMaterno) AS nombreCompleto,
        te.nombre AS tipoEmpleado,
        e.fotografia
    FROM empleados e
    INNER JOIN tiposempleados te ON e.idTipoEmpleado = te.id;
END