package modulos;

import conexion.ConexionBD;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class reporteRegistro extends javax.swing.JFrame {

      ConexionBD cn = new ConexionBD();
   Connection con;
    
    ResultSet rs;
    PreparedStatement ps;
    
    public reporteRegistro() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarNombres();  
    }

  private void cargarNombres() {


String sql = "SELECT DISTINCT CONCAT(e.nombre, ' ', e.apellidoPaterno, ' ', e.apellidoMaterno) AS nombreCompleto " +
             "FROM registros r " +
             "JOIN empleados e ON r.uid = e.uid " +
             "ORDER BY nombreCompleto ASC";

try (Connection con = ConexionBD.getConnection();
     PreparedStatement stmt = con.prepareStatement(sql);
     ResultSet rs = stmt.executeQuery()) {

    jcbNombre.removeAllItems();
    jcbNombre.addItem("TODOS");

    while (rs.next()) {
        String nombreCompleto = rs.getString("nombreCompleto");
        if (nombreCompleto != null) {
            jcbNombre.addItem(nombreCompleto.trim());
        }
    }

} catch (SQLException e) {
    System.err.println("Error al cargar nombres completos:");
    e.printStackTrace();
}
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRegistro = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jcbNombre = new javax.swing.JComboBox();
        btnReporte = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblRegistro.setText("REPORTE DE REGISTRO");

        lblNombre.setText("NOMBRE:");

        jcbNombre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNombreActionPerformed(evt);
            }
        });

        btnReporte.setBackground(new java.awt.Color(0, 255, 255));
        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        btnReporte.setText("GENERAR REPORTE PDF");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(204, 255, 0));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar.png"))); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(lblRegistro))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(btnRegresar)))
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnReporte)
                .addGap(173, 173, 173))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblRegistro)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(jcbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnReporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        try {
            generarReporte(jcbNombre.getSelectedItem().toString());
            
           
        } catch (SQLException ex) {
            Logger.getLogger(reporteRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void jcbNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbNombreActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
            this.dispose(); // Cierra la ventana actual
    for (Frame f : Frame.getFrames()) {
        if (f instanceof principal) {
            f.setVisible(true); // Muestra la existente si ya está creada
            return;
        }
    }
    }//GEN-LAST:event_btnRegresarActionPerformed

   
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
            java.util.logging.Logger.getLogger(reporteRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reporteRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reporteRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reporteRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reporteRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JComboBox jcbNombre;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRegistro;
    // End of variables declaration//GEN-END:variables

    private void generarReporte(String NOMBRE) throws SQLException {
    ConexionBD cn = new ConexionBD(); 
    Connection con = null;

    try {
        con = cn.getConnection();

        if (con == null) {
            JOptionPane.showMessageDialog(null, "❌ No se pudo conectar a la base de datos.");
            return;
        }

        String dir_current = System.getProperty("user.dir");
        String fileName = dir_current + "\\src\\reportes\\reporteRegistro.jasper";

        // Verifica si el archivo existe
        java.io.File archivo = new java.io.File(fileName);
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "❌ No se encontró el archivo del reporte:\n" + fileName);
            return;
        }

        // Preparar parámetros para el reporte
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("NOMBRE", NOMBRE);
        // Si el usuario seleccionó "TODOS", pasamos null para no filtrar
//        if (NOMBRE.equalsIgnoreCase("TODOS")) {
//            parametros.put("NOMBRE", "TODOS");
//        } else {
//            parametros.put("NOMBRE", NOMBRE);
//        }
        
        // Cargar el reporte con parámetros
        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(fileName);
        JasperPrint impreso = JasperFillManager.fillReport(reporte, parametros, con);

        // Mostrar el visor del reporte
        JasperViewer viewer = new JasperViewer(impreso, false);
        viewer.setTitle("REPORTE DE ENTRADA Y SALIDA");
        viewer.setVisible(true);

    } catch (JRException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "❌ Error al generar el reporte:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {  
        try {
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "❌ Error al cerrar la conexión:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

}
