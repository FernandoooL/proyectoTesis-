package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionBD {
    
private static final String DB_URL = "jdbc:mysql://localhost:3306/sistemalectorrfid?serverTimezone=America/Mexico_City";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
         return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
    
    public Connection getConexion(){
        Connection conn = null;
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontró el controlador de la base de datos.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;      
        }
    }