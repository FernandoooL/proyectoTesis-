package modulos;

import conexion.ConexionBD;
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
  
    public ventanaCredencial() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarEmpleados();
    }

    
    private void cargarEmpleados() {
    try (Connection conn = ConexionBD()) {
        String sql = "SELECT id, nombre, apellidoPaterno, apellidoMaterno FROM empleados";
        PreparedStatement ps = conn.prepareStatement(sql);
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

    private void generarReporte(int ID) {
         try (Connection con = ConexionBD()) {
        if (con == null) {
            JOptionPane.showMessageDialog(this, "❌ No se pudo conectar a la base de datos.");
            return;
        }

        BufferedImage imagen = null;
        String sql = "SELECT fotografia FROM empleados WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    byte[] bytes = rs.getBytes("fotografia");
                    if (bytes != null) {
                        try (InputStream is = new ByteArrayInputStream(bytes)) {
                            imagen = ImageIO.read(is);
                        }
                    }
                }
            }
        }

        if (imagen == null) {
            JOptionPane.showMessageDialog(this, "❌ No se encontró la imagen del empleado.");
            return;
        }

        // Mostrar la imagen en un diálogo para validar
//        JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(imagen)), "Foto del empleado", JOptionPane.PLAIN_MESSAGE);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ID", ID);
        parametros.put("FOTOGRAFIA", imagen);

        // Cargar reporte desde recurso (asegúrate que el .jasper está en la ruta correcta)
        InputStream reporteStream = getClass().getResourceAsStream("/reportes/reporteCredenciales.jasper");
        if (reporteStream == null) {
            JOptionPane.showMessageDialog(this, "❌ No se encontró el archivo reporteCredenciales.jasper");
            return;
        }

        JasperPrint print = JasperFillManager.fillReport(reporteStream, parametros, con);
        JasperViewer.viewReport(print, false);

    } catch (JRException | SQLException | IOException e) {
        JOptionPane.showMessageDialog(null, "❌ Error al generar el reporte: " + e.getMessage());
        e.printStackTrace();
    }
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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/credencial(1).png"))); // NOI18N
        jButton1.setText("IMPRIMIR CREDENCIALES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar.png"))); // NOI18N
        jButton2.setText("REGRESAR");

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

    generarReporte(seleccionado.getId());
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
    
    private Connection ConexionBD() {
          try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de tener el driver en tu proyecto
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/sistemaLectorRFID", // Cambia "mi_basedatos" por el nombre de tu base de datos
            "root",                                  // Cambia "usuario" por tu nombre de usuario
            ""                                // Cambia "contraseña" por tu contraseña
        );
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage());
        return null;
    }
    }
}
