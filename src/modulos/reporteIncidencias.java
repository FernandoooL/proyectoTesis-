package modulos;

import conexion.ConexionBD;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class reporteIncidencias extends javax.swing.JFrame {

    public reporteIncidencias() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarNombres();
    }

   private void cargarNombres() {
    String sql = "SELECT DISTINCT CONCAT(e.nombre, ' ', e.apellidoPaterno, ' ', e.apellidoMaterno) AS nombreCompleto " +
                 "FROM empleados e " +
                 "ORDER BY nombreCompleto ASC";

    try (Connection con = ConexionBD.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        jcbNombre.removeAllItems();
        jcbNombre.addItem("TODOS"); // opción para mostrar todos

        while (rs.next()) {
            String nombreCompleto = rs.getString("nombreCompleto");
            if (nombreCompleto != null && !nombreCompleto.isEmpty()) {
                jcbNombre.addItem(nombreCompleto.trim());
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar nombres completos:\n" + e.getMessage());
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jcbNombre = new javax.swing.JComboBox();
        btnReporte = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("REPORTE DE INCIDENCIAS");

        lblNombre.setText("NOMBRE: ");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbNombre, 0, 197, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(168, 168, 168))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnReporte)
                        .addGap(160, 160, 160))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRegresar)
                        .addGap(205, 205, 205))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(jcbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegresar)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
             this.dispose(); // Cierra la ventana actual
    for (Frame f : Frame.getFrames()) {
        if (f instanceof principal) {
            f.setVisible(true); // Muestra la existente si ya está creada
            return;
        }
    }
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        String nombreSeleccionado = jcbNombre.getSelectedItem() != null ?
                jcbNombre.getSelectedItem().toString().trim() : "TODOS";
        generarReporte(nombreSeleccionado);
    }//GEN-LAST:event_btnReporteActionPerformed

    private void jcbNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbNombreActionPerformed

   
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
            java.util.logging.Logger.getLogger(reporteIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reporteIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reporteIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reporteIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reporteIncidencias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox jcbNombre;
    private javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
    
    private void generarReporte(String nombreCompleto) {
    ConexionBD cn = new ConexionBD();
    try (Connection con = cn.getConnection()) {

        if (con == null) {
            JOptionPane.showMessageDialog(null, "❌ No se pudo conectar a la base de datos.");
            return;
        }

        // Ruta del archivo .jasper
        String dir_current = System.getProperty("user.dir");
        String fileName = dir_current + "\\src\\reportes\\reporteIncidencias.jasper";

        java.io.File archivo = new java.io.File(fileName);
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "❌ No se encontró el archivo del reporte:\n" + fileName);
            return;
        }

        // Preparar parámetros
        Map<String, Object> parametros = new HashMap<>();
        if ("Todos".equalsIgnoreCase(nombreCompleto)) {
            parametros.put("nombreCompleto", "TODOS"); // null indica que Jasper debe traer todos
        } else {
            parametros.put("nombreCompleto", nombreCompleto); // Filtra por el nombre seleccionado
        }

        // Cargar y llenar el reporte
        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(fileName);
        JasperPrint impreso = JasperFillManager.fillReport(reporte, parametros, con);

        // Mostrar visor del reporte
        JasperViewer viewer = new JasperViewer(impreso, false);
        viewer.setTitle("REPORTE DE INCIDENCIAS");
        viewer.setVisible(true);

    } catch (JRException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "❌ Error al generar el reporte:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "❌ Error de base de datos:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}
