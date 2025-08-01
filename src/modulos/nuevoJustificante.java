package modulos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class nuevoJustificante extends javax.swing.JFrame {

    
    public nuevoJustificante() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarNombres();
        cargarTipoIncidencias();
    }

    private void cargarNombres() {
    try (Connection conn = ConexionBD()) {
        String sql = "SELECT nombre FROM empleados";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        jbcNombre.removeAllItems();
        while (rs.next()) {
            jbcNombre.addItem(rs.getString("nombre"));
        }

        rs.close();
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar nombres: " + e.getMessage());
    }
}
    
    
      private void cargarTipoIncidencias() {
    try (Connection conn = ConexionBD()) {
        String sql = "SELECT nombre FROM tiposincidencias";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        jbcTipo.removeAllItems();
        while (rs.next()) {
            jbcTipo.addItem(rs.getString("nombre"));
        }

        rs.close();
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar tipo incidencias: " + e.getMessage());
    }
}
    
    private int obtenerIdEmpleadoPorNombre(Connection conn, String nombre) {
    int id = -1;
    String sql = "SELECT id FROM empleados WHERE nombre = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al obtener ID del empleado: " + e.getMessage());
    }
    return id;
}

private int obtenerIdJustificacionPorNombre(Connection conn, String nombre) {
    int id = -1;
    String sql = "SELECT id FROM justificacion WHERE nombre = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al obtener ID de justificación: " + e.getMessage());
    }
    return id;
}

    private int obtenerIdTipoIncidenciaPorNombre(Connection conn, String nombre) {
    int id = -1;
    String sql = "SELECT id FROM tiposincidencias WHERE nombre = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al obtener ID del tipo de incidencia: " + e.getMessage());
    }
    return id;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblR = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jbcNombre = new javax.swing.JComboBox();
        lblDia = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        lblFecha = new javax.swing.JLabel();
        lblHasta = new javax.swing.JLabel();
        lblObservaciones = new javax.swing.JLabel();
        txtObservaciones = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jdcFechaI = new com.toedter.calendar.JDateChooser();
        jdcFechaF = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jbcTipo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblR.setText("REGISTRAR NUEVO JUSTIFICANTE");

        lblNombre.setText("NOMBRE:");

        jbcNombre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jbcNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcNombreActionPerformed(evt);
            }
        });

        lblDia.setText("UN SOLO DIA:");

        lblFecha.setText("FECHA:");

        lblHasta.setText("HASTA:");

        lblObservaciones.setText("MOTIVO:");

        btnAceptar.setBackground(new java.awt.Color(0, 255, 255));
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disquete.png"))); // NOI18N
        btnAceptar.setText("GUARDAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
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

        jLabel1.setText("TIPO INCIDENCIA:");

        jbcTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblR)
                .addGap(304, 304, 304))
            .addGroup(layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbcNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(152, 152, 152)
                        .addComponent(btnRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbcTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(jdcFechaI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jdcFechaF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 145, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblR)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(jbcNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jbcTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDia)
                    .addComponent(jCheckBox1))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha)
                    .addComponent(jdcFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHasta)
                    .addComponent(jdcFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObservaciones)
                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnRegresar))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        principal p = new principal();
    p.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
    String nombre = (String) jbcNombre.getSelectedItem();
boolean unDia = jCheckBox1.isSelected();
java.util.Date fechaInicio = jdcFechaI.getDate();
java.util.Date fechaFin = jdcFechaF.getDate();
String observaciones = txtObservaciones.getText();

if (unDia) {
    fechaFin = fechaInicio;
}

java.sql.Date sqlFechaInicio = new java.sql.Date(fechaInicio.getTime());
java.sql.Date sqlFechaFin = new java.sql.Date(fechaFin.getTime());

try (Connection conn = ConexionBD()) {
    // Obtener los IDs reales
    int idEmpleado = obtenerIdEmpleadoPorNombre(conn, nombre);
    int idTipoIncidencia = 1; // Puedes cambiar esto si usas un JComboBox para tipos de incidencia

    String sql = "INSERT INTO incidencias (idEmpleado, idTipoIncidencia, fechaHoraInicio, fechaHoraFin, observaciones) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setInt(1, idEmpleado);
    ps.setInt(2, idTipoIncidencia);
    ps.setDate(3, sqlFechaInicio);
    ps.setDate(4, sqlFechaFin);
    ps.setString(5, observaciones);

    int filasAfectadas = ps.executeUpdate();

    if (filasAfectadas > 0) {
        JOptionPane.showMessageDialog(this, "Justificante guardado correctamente.");
        limpiarCampos();
    } else {
        JOptionPane.showMessageDialog(this, "No se pudo guardar el justificante.");
    }

    ps.close();
} catch (SQLException e) {
    JOptionPane.showMessageDialog(this, "Error al guardar justificante: " + e.getMessage());
}
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void jbcNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbcNombreActionPerformed

    private void limpiarCampos() {
    jbcNombre.setSelectedIndex(0);
    jdcFechaI.setDate(null);
    jdcFechaF.setDate(null);
    txtObservaciones.setText("");
}
    
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
            java.util.logging.Logger.getLogger(nuevoJustificante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nuevoJustificante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nuevoJustificante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nuevoJustificante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nuevoJustificante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox jbcNombre;
    private javax.swing.JComboBox jbcTipo;
    private com.toedter.calendar.JDateChooser jdcFechaF;
    private com.toedter.calendar.JDateChooser jdcFechaI;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblR;
    private javax.swing.JTextField txtObservaciones;
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
