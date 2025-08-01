package proyectotesis;

import conexion.ConexionBD;
import modulos.principal;

public class ProyectoTesis {

    public static void main(String[] args) {
       ConexionBD b= new ConexionBD();
       
       b.getConexion();
    principal p = new principal();
    p.setVisible(true);
    }
    
}
