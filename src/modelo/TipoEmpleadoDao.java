package modelo;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoEmpleadoDao {

    public static boolean actualizarTipoEmpleado(TipoEmpleado tipo) {
        String sql = "UPDATE tiposempleados SET nombre = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo.getNombre());
            stmt.setInt(2, tipo.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarTipoEmpleado(Departamento departamentoActual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean eliminarEmpleado(int id) {
    String sql = "DELETE FROM tiposempleados WHERE id = ?";
    
    try (Connection con = ConexionBD.getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, id);
        int resultado = ps.executeUpdate();
        return resultado > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    
     ConexionBD cn = new ConexionBD();
    Connection conn;
    
    ResultSet rs;
    PreparedStatement ps;
    
        public List<TipoEmpleado> listartipoEmpleado() {
        List<TipoEmpleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM tiposempleados";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoEmpleado tipo = new TipoEmpleado();
                tipo.setId(rs.getInt("id"));
                tipo.setNombre(rs.getString("nombre"));
                lista.add(tipo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }       
        
        public static List<String> obtenerNombresTipos() {
    List<String> lista = new ArrayList<>();
    String sql = "SELECT id FROM tiposempleados"; // Ajusta el nombre de tabla y columna
    try (Connection con = ConexionBD.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            lista.add(rs.getString("id"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}
    }
