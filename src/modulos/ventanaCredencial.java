package modulos;

import conexion.ConexionBD;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.text.html.HTML.Tag.SELECT;
import modulos.nuevoEmpleado.ItemCombo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ventanaCredencial extends javax.swing.JFrame {
  
  ConexionBD cn = new ConexionBD();
   Connection con;
    
    ResultSet rs;
    PreparedStatement ps;
    
    public ventanaCredencial() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarEmpleados();
    }

    
    private void cargarEmpleados() {
    try (Connection con = ConexionBD.getConnection()) {
        String sql = "SELECT id, nombre, apellidoPaterno, apellidoMaterno FROM empleados";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        jcbNombre.removeAllItems();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellidoP = rs.getString("apellidoPaterno");
            String apellidoM = rs.getString("apellidoMaterno");
            String nombreCompleto = nombre + " " + apellidoP + " " + apellidoM;

            jcbNombre.addItem(new ItemCombo(id, nombreCompleto));
        }

        rs.close();
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar nombres: " + e.getMessage());
    }
}



    public void generarCredencial(int idEmpleado) {
        try (Connection conexion = ConexionBD.getConnection()) {
            if (conexion == null) {
                mostrarError("No se pudo conectar a la base de datos");
                return;
            }

            // 1. Obtener imágenes de frente (id=1) y atrás (id=2)
            InputStream isFrente = obtenerImagenDeTabla(conexion, 1);
            InputStream isAtras = obtenerImagenDeTabla(conexion, 2);

            BufferedImage frente = ImageIO.read(isFrente);
            BufferedImage atras = ImageIO.read(isAtras);

            
            if (frente == null || atras == null) {
                mostrarError("No se encontraron las imágenes de fondo en la tabla 'imagen'");
                return;
            }

            // 2. Preparar parámetros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ID", idEmpleado);
            parametros.put("FONDO_FRENTE", frente);
            parametros.put("FONDO_REVERSO", atras);

            // 3. Generar reporte
            InputStream reporteStream = getClass().getResourceAsStream("/reportes/reporteCredenciales.jasper");
            if (reporteStream == null) {
                mostrarError("No se encontró el archivo del reporte");
                return;
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteStream, parametros, conexion);
            
            // 4. Mostrar resultado
            mostrarReporte(jasperPrint);

        } catch (Exception e) {
            mostrarError("Error al generar credencial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private InputStream obtenerImagenDeTabla(Connection conexion, int idImagen) throws SQLException {
        String sql = "SELECT imagen FROM imagen WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idImagen);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    byte[] bytes = rs.getBytes("imagen");
                    return bytes != null ? new ByteArrayInputStream(bytes) : null;
                }
            }
        }
        return null;
    }

    private void mostrarReporte(JasperPrint jasperPrint) {
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setTitle("Credencial de Empleado");
        viewer.setVisible(true);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, 
            "❌ " + mensaje, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    
    
   public class ItemCombo {
    private int id;
    private String nombre;

    public ItemCombo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre; // Esto es lo que se muestra en el JComboBox
    }
}


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbNombre = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("IMPRIMIR CREDENCIALES");

        jLabel2.setText("NOMBRE: ");

        jcbNombre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(102, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/credencial(1).png"))); // NOI18N
        jButton1.setText("IMPRIMIR CREDENCIALES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 255, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar.png"))); // NOI18N
        jButton2.setText("REGRESAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jButton2)))
                .addContainerGap(140, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(200, 200, 200))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   ItemCombo seleccionado = (ItemCombo) jcbNombre.getSelectedItem();

    if (seleccionado == null || seleccionado.getId() == -1) {
        JOptionPane.showMessageDialog(this, "⚠️ Selecciona un empleado válido.");
        return;
    }

    generarCredencial(seleccionado.getId());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose(); // Cierra la ventana actual

    for (Frame f : Frame.getFrames()) {
        if (f instanceof principal) {
            f.setVisible(true); // Muestra la existente si ya está creada
            return;
        }
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    
    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaCredencial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaCredencial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaCredencial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaCredencial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaCredencial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox jcbNombre;
    // End of variables declaration//GEN-END:variables
    
//    private Connection ConexionBD() {
//          try {
//        Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de tener el driver en tu proyecto
//        return DriverManager.getConnection(
//            "jdbc:mysql://localhost:3306/sistemaLectorRFID", // Cambia "mi_basedatos" por el nombre de tu base de datos
//            "root",                                  // Cambia "usuario" por tu nombre de usuario
//            ""                                // Cambia "contraseña" por tu contraseña
//        );
//    } catch (Exception e) {
//        JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage());
//        return null;
//    }
//    }
}
