CREATE DEFINER=`root`@`localhost` PROCEDURE `REPORTE_EMPLEADOS`(
    IN TIPO_EMPLEADO VARCHAR(100),
    IN DEPARTAMENTO VARCHAR(100),
    IN ESTATUS_EMPLEADO VARCHAR(100)
)
BEGIN
    SELECT 
        e.id,
        e.nombre,
        e.apellidoPaterno,
        e.apellidoMaterno,
        te.nombre AS tipoEmpleado,
        ee.nombre AS estatusEmpleado,
        d.nombre AS departamento,
        e.fechaNacimiento,
        e.domicilio,
        e.telefono,
        e.curp,
        e.observaciones
    FROM empleados e
    INNER JOIN tiposempleados te ON e.idTipoEmpleado = te.id
    INNER JOIN estatusempleados ee ON e.idEstatusEmpleado = ee.id
    INNER JOIN departamentos d ON e.idDepartamento = d.id
    WHERE (TIPO_EMPLEADO = 'TODOS' OR te.nombre = TIPO_EMPLEADO)
      AND (DEPARTAMENTO = 'TODOS' OR d.nombre = DEPARTAMENTO)
      AND (ESTATUS_EMPLEADO = 'TODOS' OR ee.nombre = ESTATUS_EMPLEADO);
END