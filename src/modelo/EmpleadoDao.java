package modelo;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



public class EmpleadoDao {
      
    
     public static boolean actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE empleados SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, " +
                     "fechaNacimiento = ?, telefono = ?, curp = ?, domicilio = ?, idDepartamento = ?, " +
                     "idTipoEmpleado = ?, idHorario = ?, idEstatusEmpleado = ?, observaciones = ? " +
                     "WHERE uid = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellidoPaterno());
            stmt.setString(3, empleado.getApellidoMaterno());
            stmt.setDate(4, new Date(empleado.getFechaNacimiento().getTime()));
            stmt.setString(5, empleado.getTelefono());
            stmt.setString(6, empleado.getCurp());
            stmt.setString(7, empleado.getDomicilio());
            stmt.setString(8, empleado.getidDepartamento());
            stmt.setString(9, (String) empleado.getidTipoEmpleado());
            stmt.setString(10, (String) empleado.getidHorario());
            stmt.setString(11, (String) empleado.getidEstatusEmpleado());
            stmt.setString(12, empleado.getObservaciones());
            stmt.setString(13, empleado.getUid()); 

                int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarEmpleado(TipoEmpleado tipoempleadoActual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    


   public boolean eliminarEmpleado(String uid) {
    String sqlEliminarRegistros = "DELETE FROM registros WHERE uid = ?";
    String sqlEliminarEmpleado = "DELETE FROM empleados WHERE uid = ?";
    
    try (Connection con = ConexionBD.getConnection()) {
        con.setAutoCommit(false); // transacción segura

        try (PreparedStatement ps1 = con.prepareStatement(sqlEliminarRegistros);
             PreparedStatement ps2 = con.prepareStatement(sqlEliminarEmpleado)) {

            ps1.setString(1, uid);
            ps1.executeUpdate();

            ps2.setString(1, uid);
            int resultado = ps2.executeUpdate();

            con.commit();
            return resultado > 0;

        } catch (SQLException ex) {
            con.rollback(); // deshacer si falla
            ex.printStackTrace();
            return false;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



    ConexionBD cn = new ConexionBD();
    Connection conn;
    
    ResultSet rs;
    PreparedStatement ps;
    
    public List ListarEmpleado() {
        List<Empleado> ListaEmp = new ArrayList();
        String sql = "SELECT * FROM empleados";
        try {
            conn = cn.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt("id"));
                emp.setUid(rs.getString("uid"));
                emp.setidTipoEmpleado(rs.getString("idTipoEmpleado"));
                emp.setidEstatusEmpleado(rs.getString("idEstatusEmpleado"));
                emp.setidDepartamento(rs.getString("idDepartamento"));
                emp.setidHorario(rs.getString("idHorario"));
                emp.setNombre(rs.getString("nombre"));
                emp.setApellidoPaterno(rs.getString("apellidoPaterno"));
                emp.setApellidoMaterno(rs.getString("apellidoMaterno"));
                emp.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                emp.setDomicilio(rs.getString("domicilio"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setCurp(rs.getString("curp"));
                emp.setObservaciones(rs.getString("observaciones"));
                ListaEmp.add(emp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListaEmp;
    }
}