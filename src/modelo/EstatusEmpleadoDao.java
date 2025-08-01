package modelo;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EstatusEmpleadoDao {
    public static List<String> obtenerNombresEstatus() {
    List<String> lista = new ArrayList<>();
    String sql = "SELECT id FROM estatusempleados"; // Ajusta el nombre de tabla y columna
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
