package modelo;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class PerfilDao {
    
     public static boolean actualizarPerfil(Perfil PerfilActual) {
        String sql = "UPDATE departamentos SET nombre = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, PerfilActual.getNombre());
            stmt.setString(2, PerfilActual.getDescripcion());
            stmt.setInt(3, PerfilActual.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarPerfil(int id) {
    String sql = "DELETE FROM perfiles WHERE id = ?";
    
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

    public List listarPerfil() {
        List<Perfil> ListarPerf = new ArrayList();
        String sql = "SELECT * FROM perfiles";
        try {
            conn = cn.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Perfil perf = new Perfil();
                perf.setId(rs.getInt("id"));
                perf.setNombre(rs.getString("nombre"));  
                perf.setDescripcion(rs.getString("descripcion")); 
                ListarPerf.add(perf);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListarPerf;
    }
    
}
