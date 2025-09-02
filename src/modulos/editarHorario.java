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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import static sun.nio.ch.IOStatus.check;


public class editarHorario extends javax.swing.JFrame {
        private int idHorario;

    
    public editarHorario() {
        initComponents();
        this.setLocationRelativeTo(null);
        configurarSpinnerHora(jSpinnerHora);
        configurarSpinnerHora(jSpinnerHora1);
        configurarSpinnerHora(jSpinnerHora2);
        configurarSpinnerHora(jSpinnerHora3);
        configurarSpinnerHora(jSpinnerHora4);
        configurarSpinnerHora(jSpinnerHora5);
        configurarSpinnerHora(jSpinnerHora6);
        configurarSpinnerHora(jSpinnerHora7);
        configurarSpinnerHora(jSpinnerHora8);
        configurarSpinnerHora(jSpinnerHora9);
        actualizarNombreHorario();
        
        jCheckBox1.addActionListener(e -> actualizarNombreHorario());
        jCheckBox2.addActionListener(e -> actualizarNombreHorario());
        jCheckBox3.addActionListener(e -> actualizarNombreHorario());
        jCheckBox4.addActionListener(e -> actualizarNombreHorario());
        jCheckBox5.addActionListener(e -> actualizarNombreHorario());
    }    
    
private void actualizarNombreHorario() {
    StringBuilder nombre = new StringBuilder();
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm");

    // Ejemplo para 3 horarios (puedes adaptarlo a 9)
    if (jCheckBox1.isSelected()) {
        nombre.append("LUNES: ").append(formato.format(jSpinnerHora2.getValue()))
              .append(" LUNES: ").append(formato.format(jSpinnerHora.getValue()))
              .append(" | ");
    }
    if (jCheckBox2.isSelected()) {
        nombre.append("MARTES: ").append(formato.format(jSpinnerHora6.getValue()))
              .append(" MARTES: ").append(formato.format(jSpinnerHora1.getValue()))
              .append(" | ");
    }
    if (jCheckBox3.isSelected()) {
        nombre.append("MIERCOLES: ").append(formato.format(jSpinnerHora3.getValue()))
              .append(" MIERCOLES: ").append(formato.format(jSpinnerHora4.getValue())).append(" | ");
    }
    if (jCheckBox4.isSelected()) {
        nombre.append("JUEVES: ").append(formato.format(jSpinnerHora5.getValue()))
              .append(" JUEVES: ").append(formato.format(jSpinnerHora7.getValue())).append(" | ");
    }
    if (jCheckBox5.isSelected()) {
        nombre.append("VIERNES: ").append(formato.format(jSpinnerHora8.getValue()))
              .append(" VIERNES: ").append(formato.format(jSpinnerHora9.getValue())).append(" | ");
    }
    // Eliminar el último " | " si hay texto
    if (nombre.length() > 0) {
        nombre.setLength(nombre.length() - 3);
    }

    txtNombre.setText(nombre.toString());
}



private void configurarSpinnerHora(JSpinner spinner) {
    // Inicializar hora en 00:00
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    Date horaCero = calendar.getTime();

    // Asignar modelo al spinner
    SpinnerDateModel modelo = new SpinnerDateModel(horaCero, null, null, Calendar.MINUTE);
    spinner.setModel(modelo);

    // Configurar editor para mostrar HH:mm
    JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
    spinner.setEditor(editor);
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombre = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblDia = new javax.swing.JLabel();
        lblLaborable = new javax.swing.JLabel();
        lblEntrada = new javax.swing.JLabel();
        lblSalida = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        lblLunes = new javax.swing.JLabel();
        lblMartes = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        lblMiercoles = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        lblJueves = new javax.swing.JLabel();
        lblViernes = new javax.swing.JLabel();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSpinnerHora = new javax.swing.JSpinner();
        jSpinnerHora1 = new javax.swing.JSpinner();
        jSpinnerHora2 = new javax.swing.JSpinner();
        jSpinnerHora3 = new javax.swing.JSpinner();
        jSpinnerHora4 = new javax.swing.JSpinner();
        jSpinnerHora5 = new javax.swing.JSpinner();
        jSpinnerHora6 = new javax.swing.JSpinner();
        jSpinnerHora7 = new javax.swing.JSpinner();
        jSpinnerHora8 = new javax.swing.JSpinner();
        jSpinnerHora9 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        lblDia.setText("DÍA DE LA SEMANA: ");

        lblLaborable.setText("¿SE TRABAJA ESTE DÍA?");

        lblEntrada.setText("ENTRADA");

        lblSalida.setText("SALIDA");

        lblLunes.setText("LUNES");

        lblMartes.setText("MARTES");

        lblMiercoles.setText("MIERCOLES");

        lblJueves.setText("JUEVES");

        lblViernes.setText("VIERNES");

        jLabel1.setText("NOMBRE DEL HORARIO GENERADO: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel3.setText("GESTIÓN DE HORARIO LABORAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDia)
                            .addComponent(lblMiercoles)
                            .addComponent(lblJueves)
                            .addComponent(lblMartes)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblViernes))
                            .addComponent(lblLunes))
                        .addGap(360, 360, 360)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEntrada)
                            .addComponent(jSpinnerHora2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jSpinnerHora3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSpinnerHora5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSpinnerHora6)
                            .addComponent(jSpinnerHora8))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSalida)
                            .addComponent(jSpinnerHora, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinnerHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinnerHora4)
                            .addComponent(jSpinnerHora7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSpinnerHora9, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLaborable)
                    .addComponent(btnRegresar)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(365, 365, 365))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSpinnerHora9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblLunes)
                                        .addComponent(jSpinnerHora2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblDia)
                                            .addComponent(lblEntrada)
                                            .addComponent(lblSalida)
                                            .addComponent(lblLaborable))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jCheckBox1))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jSpinnerHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAceptar)
                                    .addComponent(btnRegresar)
                                    .addComponent(jLabel1))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblMartes)
                                        .addComponent(jSpinnerHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jCheckBox2))
                                .addGap(35, 35, 35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerHora6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMiercoles)
                                    .addComponent(jSpinnerHora3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinnerHora4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblJueves)
                                    .addComponent(jSpinnerHora5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinnerHora7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox4)))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblViernes)
                                .addComponent(jSpinnerHora8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox5))))
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

    JOptionPane.showMessageDialog(this, "✅ Horario creado exitosamente.");
    this.dispose();

} catch (SQLException e) {
    JOptionPane.showMessageDialog(this, "❌ Error al guardar: " + e.getMessage());
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSpinner jSpinnerHora;
    private javax.swing.JSpinner jSpinnerHora1;
    private javax.swing.JSpinner jSpinnerHora2;
    private javax.swing.JSpinner jSpinnerHora3;
    private javax.swing.JSpinner jSpinnerHora4;
    private javax.swing.JSpinner jSpinnerHora5;
    private javax.swing.JSpinner jSpinnerHora6;
    private javax.swing.JSpinner jSpinnerHora7;
    private javax.swing.JSpinner jSpinnerHora8;
    private javax.swing.JSpinner jSpinnerHora9;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblEntrada;
    private javax.swing.JLabel lblJueves;
    private javax.swing.JLabel lblLaborable;
    private javax.swing.JLabel lblLunes;
    private javax.swing.JLabel lblMartes;
    private javax.swing.JLabel lblMiercoles;
    private javax.swing.JLabel lblSalida;
    private javax.swing.JLabel lblViernes;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables


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
