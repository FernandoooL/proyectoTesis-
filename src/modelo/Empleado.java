package modelo;

import java.sql.Date;


public class Empleado {

    public static Empleado getSelectedData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int id;
    String uid;
    String idTipoEmpleado;
    String idEstatusEmpleado;
    String idDepartamento;
    String idHorario;
    String nombre;
    String apellidoPaterno;
    String apellidoMaterno;
    Date fechaNacimiento;
    String domicilio;
    String telefono;
    String curp;
    String observaciones;

    public Empleado() {
    }

    public Empleado(int id, String uid, String idTipoEmpleado, String idEstatusEmpleado, String idDepartamento, String idHorario, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String domicilio, String telefono, String curp, String observaciones) {
        this.id = id;
        this.uid = uid;
        this.idTipoEmpleado = idTipoEmpleado;
        this.idEstatusEmpleado = idEstatusEmpleado;
        this.idDepartamento = idDepartamento;
        this.idHorario = idHorario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.curp = curp;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getidTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setidTipoEmpleado(String idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public String getidEstatusEmpleado() {
        return idEstatusEmpleado;
    }

    public void setidEstatusEmpleado(String idEstatusEmpleado) {
        this.idEstatusEmpleado = idEstatusEmpleado;
    }

    public String getidDepartamento() {
        return idDepartamento;
    }

    public void setidDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getidHorario() {
        return idHorario;
    }

    public void setidHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void cargarDatosTabla() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
