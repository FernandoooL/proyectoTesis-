package modelo;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class DepartamentoDao { 
    
    public static boolean actualizarDepartamento(Departamento departamentoActual) {
        String sql = "UPDATE departamentos SET nombre = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, departamentoActual.getNombre());
            stmt.setInt(2, departamentoActual.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarDepartamento(int id) {
    String sql = "DELETE FROM departamentos WHERE id = ?";
    
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
    
    
    public List ListarDepartamento() {
        List<Departamento> ListaDep = new ArrayList();
        String sql = "SELECT * FROM departamentos";
        try {
            conn = cn.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Departamento depa = new Departamento();
                depa.setId(rs.getInt("id"));
                depa.setNombre(rs.getString("nombre"));                 
                ListaDep.add(depa);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListaDep;
    }
    
    // En DepartamentoDao.java
public static List<String> obtenerNombresDepartamentos() {
    List<String> lista = new ArrayList<>();
    String sql = "SELECT id FROM departamentos"; // Ajusta el nombre de tabla y columna
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
