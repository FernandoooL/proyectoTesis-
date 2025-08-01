package modelo;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class JustificacionDao {
    
    public static boolean actualizarJustificacion(Justificacion justificacionActual) {
        String sql = "UPDATE justificacion SET nombre = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, justificacionActual.getNombre());
            stmt.setInt(2, justificacionActual  .getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarJustificacion(int id) {
    String sql = "DELETE FROM justificacion WHERE id = ?";
    
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
    
    public List listarJustificacion() {
        List<Justificacion> ListaJus = new ArrayList();
        String sql = "SELECT * FROM justificacion";
        try {
            conn = cn.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Justificacion jus = new Justificacion();
                jus.setId(rs.getInt("id"));
                jus.setNombre(rs.getString("nombre"));                 
                ListaJus.add(jus);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListaJus;
    }
}
