CREATE DEFINER=`root`@`localhost` PROCEDURE `REPORTE_ASISTENCIAS`(
    IN DEPARTAMENTO VARCHAR(100),
    IN DESDE DATE,
    IN HASTA DATE
)
BEGIN
    SELECT 
    e.id AS idEmpleado,
    e.nombre,
    e.apellidoPaterno,
    e.apellidoMaterno,
    d.nombre AS departamento,
    es.tipo,
    es.fecha,
    es.hora
FROM entrada_salida es
INNER JOIN empleados e ON es.nombre = e.nombre
INNER JOIN departamentos d ON e.idDepartamento = d.id
ORDER BY e.id, es.fecha, es.hora;
END