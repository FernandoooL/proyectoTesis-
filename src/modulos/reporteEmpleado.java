package modulos;

import conexion.ConexionBD;
import java.io.File;
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


public class reporteEmpleado extends javax.swing.JFrame {

    
    public reporteEmpleado() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarDepartamentos();
        cargarTiposEmpleado();
        cargarEstatus();
    }

    private void cargarDepartamentos() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sistemaLectorRFID", "root", "")) {
            String sql = "SELECT nombre FROM departamentos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            jcbDepartamento.removeAllItems();
            jcbDepartamento.addItem("TODOS");
            while (rs.next()) {
                jcbDepartamento.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarTiposEmpleado() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sistemaLectorRFID", "root", "")) {
            String sql = "SELECT nombre FROM tiposempleados";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            jcbTipo.removeAllItems();
            jcbTipo.addItem("TODOS");
            while (rs.next()) {
                jcbTipo.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarEstatus() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sistemaLectorRFID", "root", "")) {
            String sql = "SELECT nombre FROM estatusempleados";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            jcbEstatus.removeAllItems();
            jcbEstatus.addItem("TODOS");
            while (rs.next()) {
                jcbEstatus.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRep = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        jcbTipo = new javax.swing.JComboBox();
        lblDepartamento = new javax.swing.JLabel();
        jcbDepartamento = new javax.swing.JComboBox();
        lblEstatus = new javax.swing.JLabel();
        jcbEstatus = new javax.swing.JComboBox();
        btnReporte = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblRep.setText("REPORTE DE EMPLEADO");

        lblTipo.setText("TIPO DE EMPLEADO:");

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoActionPerformed(evt);
            }
        });

        lblDepartamento.setText("DEPARTAMENTO:");

        jcbDepartamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblEstatus.setText("ESTATUS:");

        jcbEstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                        .addGap(366, 366, 366)
                        .addComponent(lblRep))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEstatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbEstatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDepartamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbDepartamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(219, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnReporte)
                        .addGap(267, 267, 267))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRegresar)
                        .addGap(318, 318, 318))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblRep)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartamento)
                    .addComponent(jcbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstatus)
                    .addComponent(jcbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(btnReporte)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoActionPerformed
       
    }//GEN-LAST:event_jcbTipoActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        principal p = new principal();
    p.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        try {
            generarReporte(jcbTipo.getSelectedItem().toString(), jcbDepartamento.getSelectedItem().toString(), jcbEstatus.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(reporteEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(reporteEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reporteEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reporteEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reporteEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reporteEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JComboBox jcbDepartamento;
    private javax.swing.JComboBox jcbEstatus;
    private javax.swing.JComboBox jcbTipo;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblEstatus;
    private javax.swing.JLabel lblRep;
    private javax.swing.JLabel lblTipo;
    // End of variables declaration//GEN-END:variables
public void generarReporte(String TIPO_EMPLEADO, String DEPARTAMENTO, String ESTATUS_EMPLEADO) throws SQLException {
    ConexionBD cn = new ConexionBD();
    Connection con = null;

    try {
        con = cn.getConnection();
        String dir_current = System.getProperty("user.dir");
        String fileName = dir_current + "\\src\\reportes\\reporteEmpleado.jasper";

        // Crear un mapa de parámetros para enviar al reporte
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("TIPO_EMPLEADO", TIPO_EMPLEADO);
        parametros.put("DEPARTAMENTO", DEPARTAMENTO);
        parametros.put("ESTATUS_EMPLEADO", ESTATUS_EMPLEADO);

        // Cargar y llenar el reporte
        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(fileName);
        JasperPrint impreso = JasperFillManager.fillReport(reporte, parametros, con);

        // Mostrar el visor del reporte
        JasperViewer viewer = new JasperViewer(impreso, false);
        viewer.setTitle("REPORTE DE EMPLEADO");
        viewer.setVisible(true);

    } catch (JRException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
}
