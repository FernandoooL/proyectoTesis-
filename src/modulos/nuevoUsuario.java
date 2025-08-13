package modulos;

import conexion.ConexionBD;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class nuevoUsuario extends javax.swing.JFrame {
ConexionBD cn = new ConexionBD();
   Connection con;
    
    ResultSet rs;
PreparedStatement ps;
    
    public nuevoUsuario() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarPerfil();
        cargarEstatus();
    }

    private void cargarPerfil() {
    try (Connection con = ConexionBD.getConnection()) {
        String sql = "SELECT nombre FROM perfiles";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        jbcPerfil.removeAllItems();
        while (rs.next()) {
            jbcPerfil.addItem(rs.getString("nombre"));
        }

        rs.close();
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar nombres: " + e.getMessage());
    }
}
    
    private void cargarEstatus() {
    try (Connection con = ConexionBD.getConnection()) {
        String sql = "SELECT nombre FROM estatusempleados";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        jbcEstatus.removeAllItems();
        while (rs.next()) {
            jbcEstatus.addItem(rs.getString("nombre"));
        }

        rs.close();
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar justificacion: " + e.getMessage());
    }
}
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblR = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblPerfil = new javax.swing.JLabel();
        jbcPerfil = new javax.swing.JComboBox();
        lblEstatus = new javax.swing.JLabel();
        jbcEstatus = new javax.swing.JComboBox();
        lblContra = new javax.swing.JLabel();
        jpContra = new javax.swing.JPasswordField();
        lblConfirmarC = new javax.swing.JLabel();
        jpConfirmarC = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblR.setText("REGISTRAR NUEVO USUARIO");

        lblNombre.setText("NOMBRE:");

        lblPerfil.setText("PERFIL:");

        jbcPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblEstatus.setText("ESTATUS:");

        jbcEstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblContra.setText("CONTRASEÑA:");

        lblConfirmarC.setText("CONFIRMAR CONTRASEÑA:");

        btnAceptar.setBackground(new java.awt.Color(51, 255, 255));
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disquete.png"))); // NOI18N
        btnAceptar.setText("GUARDAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(204, 255, 51));
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
                        .addGap(183, 183, 183)
                        .addComponent(lblR))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblContra)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jpContra))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNombre)
                                            .addComponent(lblPerfil))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jbcPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblEstatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbcEstatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAceptar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegresar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(lblConfirmarC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpConfirmarC)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblR)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPerfil)
                    .addComponent(jbcPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEstatus)
                    .addComponent(jbcEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContra)
                    .addComponent(jpContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfirmarC)
                    .addComponent(jpConfirmarC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnRegresar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
       String nombre = txtNombre.getText().trim();
    String contra = new String(jpContra.getPassword());
    String confirmarContra = new String(jpConfirmarC.getPassword());
    String perfilNombre = (String) jbcPerfil.getSelectedItem();
    String estatusNombre = (String) jbcEstatus.getSelectedItem();

    if (nombre.isEmpty() || contra.isEmpty() || confirmarContra.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
        return;
    }

    if (!contra.equals(confirmarContra)) {
        JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
        return;
    }

    try (Connection con = ConexionBD.getConnection()) {
        // Obtener ID del perfil
        String sqlPerfil = "SELECT id FROM perfiles WHERE nombre = ?";
        PreparedStatement psPerfil = con.prepareStatement(sqlPerfil);
        psPerfil.setString(1, perfilNombre);
        ResultSet rsPerfil = psPerfil.executeQuery();
        int idPerfil = rsPerfil.next() ? rsPerfil.getInt("id") : -1;

        // Obtener ID del estatus
        String sqlEstatus = "SELECT id FROM estatusempleados WHERE nombre = ?";
        PreparedStatement psEstatus = con.prepareStatement(sqlEstatus);
        psEstatus.setString(1, estatusNombre);
        ResultSet rsEstatus = psEstatus.executeQuery();
        int idEstatus = rsEstatus.next() ? rsEstatus.getInt("id") : -1;

        if (idPerfil == -1 || idEstatus == -1) {
            JOptionPane.showMessageDialog(this, "Perfil o estatus inválido.");
            return;
        }

        // Insertar el nuevo usuario
        String sqlInsert = "INSERT INTO usuarios (nombre, contrasenia, idPerfil, activo) VALUES (?, ?, ?, ?)";
        PreparedStatement psInsert = con.prepareStatement(sqlInsert);
        psInsert.setString(1, nombre);
        psInsert.setString(2, contra); // En producción, deberías cifrar la contraseña.
        psInsert.setInt(3, idPerfil);
        psInsert.setInt(4, idEstatus);

        int rowsAffected = psInsert.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario.");
        }

        rsPerfil.close();
        psPerfil.close();
        rsEstatus.close();
        psEstatus.close();
        psInsert.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + e.getMessage());
    }
    }//GEN-LAST:event_btnAceptarActionPerformed

    
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
            java.util.logging.Logger.getLogger(nuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nuevoUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox jbcEstatus;
    private javax.swing.JComboBox jbcPerfil;
    private javax.swing.JPasswordField jpConfirmarC;
    private javax.swing.JPasswordField jpContra;
    private javax.swing.JLabel lblConfirmarC;
    private javax.swing.JLabel lblContra;
    private javax.swing.JLabel lblEstatus;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblR;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables


}
