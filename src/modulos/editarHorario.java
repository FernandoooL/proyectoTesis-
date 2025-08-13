package modulos;

import static com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker.check;
import conexion.ConexionBD;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static sun.nio.ch.IOStatus.check;


public class editarHorario extends javax.swing.JFrame {
        private int idHorario;

    
    public editarHorario() {
        initComponents();
        this.setLocationRelativeTo(null);
        jCheckBox1.addItemListener(e -> actualizarNombreHorario());
    jCheckBox2.addItemListener(e -> actualizarNombreHorario());
    jCheckBox3.addItemListener(e -> actualizarNombreHorario());
    jCheckBox4.addItemListener(e -> actualizarNombreHorario());
    jCheckBox5.addItemListener(e -> actualizarNombreHorario());
    jCheckBox6.addItemListener(e -> actualizarNombreHorario());
    }    
    
private void actualizarNombreHorario() {
    StringBuilder nombre = new StringBuilder();

    if (jCheckBox1.isSelected()) {
        nombre.append("Lun ");
        if (!txtEnLunes.getText().isEmpty() && !txtSaLunes.getText().isEmpty()) {
            nombre.append("(").append(txtEnLunes.getText()).append("-").append(txtSaLunes.getText()).append(") ");
        }
    }

    if (jCheckBox2.isSelected()) {
        nombre.append("Mar ");
        if (!txtEnMartes.getText().isEmpty() && !txtSaMartes.getText().isEmpty()) {
            nombre.append("(").append(txtEnMartes.getText()).append("-").append(txtSaMartes.getText()).append(") ");
        }
    }

    if (jCheckBox3.isSelected()) {
        nombre.append("Mié ");
        if (!txtEnMiercoles.getText().isEmpty() && !txtSaMiercoles.getText().isEmpty()) {
            nombre.append("(").append(txtEnMiercoles.getText()).append("-").append(txtSaMiercoles.getText()).append(") ");
        }
    }

    if (jCheckBox4.isSelected()) {
        nombre.append("Jue ");
        if (!txtEnJueves.getText().isEmpty() && !txtSaJueves.getText().isEmpty()) {
            nombre.append("(").append(txtEnJueves.getText()).append("-").append(txtSaJueves.getText()).append(") ");
        }
    }

    if (jCheckBox5.isSelected()) {
        nombre.append("Vie ");
        if (!txtEnViernes.getText().isEmpty() && !txtSaViernes.getText().isEmpty()) {
            nombre.append("(").append(txtEnViernes.getText()).append("-").append(txtSaViernes.getText()).append(") ");
        }
    }
    txtNombre.setText(nombre.toString().trim());
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblE = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblDia = new javax.swing.JLabel();
        lblLaborable = new javax.swing.JLabel();
        lblEntrada = new javax.swing.JLabel();
        lblSalida = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        lblLunes = new javax.swing.JLabel();
        txtEnLunes = new javax.swing.JTextField();
        txtSaLunes = new javax.swing.JTextField();
        lblMartes = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        txtEnMartes = new javax.swing.JTextField();
        txtSaMartes = new javax.swing.JTextField();
        lblMiercoles = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        txtEnMiercoles = new javax.swing.JTextField();
        txtSaMiercoles = new javax.swing.JTextField();
        lblJueves = new javax.swing.JLabel();
        lblViernes = new javax.swing.JLabel();
        lblSabado = new javax.swing.JLabel();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        txtEnJueves = new javax.swing.JTextField();
        txtSaJueves = new javax.swing.JTextField();
        txtEnViernes = new javax.swing.JTextField();
        txtSaViernes = new javax.swing.JTextField();
        txtEnSabado = new javax.swing.JTextField();
        txtSaSabado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblE.setText("EDITAR HORARIO");

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

        lblDia.setText("DÍA");

        lblLaborable.setText("LABORABLE");

        lblEntrada.setText("ENTRADA");

        lblSalida.setText("SALIDA");

        lblLunes.setText("LUNES");

        txtEnLunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnLunesActionPerformed(evt);
            }
        });

        lblMartes.setText("MARTES");

        lblMiercoles.setText("MIERCOLES");

        lblJueves.setText("JUEVES");

        lblViernes.setText("VIERNES");

        lblSabado.setText("SABADO");

        jLabel1.setText("DIA Y HORA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblLunes))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMiercoles)
                            .addComponent(lblMartes)
                            .addComponent(lblJueves)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblSabado)
                                .addComponent(lblViernes))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDia)
                                .addGap(360, 360, 360)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEnLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEntrada)
                                    .addComponent(txtEnMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEnMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEnJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEnViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEnSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSaLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSalida)
                            .addComponent(txtSaMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSaMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSaJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSaViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSaSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)))
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLaborable)
                    .addComponent(btnRegresar)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
                .addContainerGap(223, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(398, 398, 398)
                .addComponent(lblE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblDia)
                                    .addComponent(lblEntrada)
                                    .addComponent(lblSalida)
                                    .addComponent(lblLaborable))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtEnLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSaLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jCheckBox1))
                                .addGap(3, 3, 3))
                            .addComponent(lblLunes, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAceptar)
                            .addComponent(btnRegresar)
                            .addComponent(jLabel1))))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMartes)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtEnMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtSaMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblMiercoles)
                                        .addGap(9, 9, 9))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEnMiercoles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSaMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblJueves)
                                    .addComponent(txtSaJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtEnJueves, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jCheckBox3))))
                    .addComponent(jCheckBox2))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEnViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblViernes)
                    .addComponent(jCheckBox5))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSabado)
                        .addComponent(txtEnSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSaSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 60, Short.MAX_VALUE))
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
        String nombreHorario = txtNombre.getText().trim();
if (nombreHorario.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Debe ingresar un nombre para el horario.");
    return;
}

try (Connection cn = ConexionBD.getConnection()) {
    
    // Insertar en tabla horarios y obtener el ID generado
    int idHorario = -1;
    String sqlInsertHorario = "INSERT INTO horarios (nombre) VALUES (?)";
    try (PreparedStatement pst = cn.prepareStatement(sqlInsertHorario, Statement.RETURN_GENERATED_KEYS)) {
        pst.setString(1, nombreHorario);
        pst.executeUpdate();

        try (ResultSet rs = pst.getGeneratedKeys()) {
            if (rs.next()) {
                idHorario = rs.getInt(1); // Aquí obtenemos el ID autogenerado
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo obtener el ID del nuevo horario.");
                return;
            }
        }
    }

            // Insertar días seleccionados
    insertarDia(cn, idHorario, "Lunes", jCheckBox1, txtEnLunes, txtSaLunes);
    insertarDia(cn, idHorario, "Martes", jCheckBox2, txtEnMartes, txtSaMartes);
    insertarDia(cn, idHorario, "Miércoles", jCheckBox3, txtEnMiercoles, txtSaMiercoles);
    insertarDia(cn, idHorario, "Jueves", jCheckBox4, txtEnJueves, txtSaJueves);
    insertarDia(cn, idHorario, "Viernes", jCheckBox5, txtEnViernes, txtSaViernes);
//    insertarDia(cn, idHorario, "Sáb", jCheckBox6, txtEnSabado, txtSaSabado);

    JOptionPane.showMessageDialog(this, "✅ Horario creado exitosamente.");
    this.dispose();

} catch (SQLException e) {
    JOptionPane.showMessageDialog(this, "❌ Error al guardar: " + e.getMessage());
}
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtEnLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnLunesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnLunesActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(editarHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editarHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editarHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editarHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editarHorario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblE;
    private javax.swing.JLabel lblEntrada;
    private javax.swing.JLabel lblJueves;
    private javax.swing.JLabel lblLaborable;
    private javax.swing.JLabel lblLunes;
    private javax.swing.JLabel lblMartes;
    private javax.swing.JLabel lblMiercoles;
    private javax.swing.JLabel lblSabado;
    private javax.swing.JLabel lblSalida;
    private javax.swing.JLabel lblViernes;
    private javax.swing.JTextField txtEnJueves;
    private javax.swing.JTextField txtEnLunes;
    private javax.swing.JTextField txtEnMartes;
    private javax.swing.JTextField txtEnMiercoles;
    private javax.swing.JTextField txtEnSabado;
    private javax.swing.JTextField txtEnViernes;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSaJueves;
    private javax.swing.JTextField txtSaLunes;
    private javax.swing.JTextField txtSaMartes;
    private javax.swing.JTextField txtSaMiercoles;
    private javax.swing.JTextField txtSaSabado;
    private javax.swing.JTextField txtSaViernes;
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

    private void insertarDia(Connection cn, int idHorario, String dia, JCheckBox check, JTextField entrada, JTextField salida) throws SQLException {
   if (!check.isSelected()) return;
    
    String horaEntrada = entrada.getText().trim();
    String horaSalida = salida.getText().trim();

    if (horaEntrada.isEmpty() || horaSalida.isEmpty()) return;

    try (PreparedStatement pst = cn.prepareStatement(
        "INSERT INTO detalle_horarios (horario_id, dia, laborable, entrada, salida) VALUES (?, ?, ?, ?, ?)")) {

        pst.setInt(1, idHorario);
        pst.setString(2, dia);
        pst.setBoolean(3, true); // Si está seleccionado, es laborable
        pst.setString(4, horaEntrada);
        pst.setString(5, horaSalida);
        pst.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al insertar el día " + dia + ": " + e.getMessage());
    }
    }
}
