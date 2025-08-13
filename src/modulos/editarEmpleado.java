package modulos;

import java.awt.Frame;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Departamento;
import modelo.Empleado;
import modelo.EmpleadoDao;



public class editarEmpleado extends javax.swing.JFrame {

    private Empleado empleadoActual;
   
    public editarEmpleado(Empleado empleado) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.empleadoActual = empleado;        
        cargarCombos();  // <--- AQUI
        cargarDatosEmpleado();
        
        // Asignar valores seleccionados en los combos según el empleado
    cbxDepa.setSelectedItem(empleado.getidDepartamento());
    cbxTipo.setSelectedItem(empleado.getidTipoEmpleado());
    cbxEstatus.setSelectedItem(empleado.getidEstatusEmpleado());
        
        // Rellenar los campos con los datos del empleado
    txtNombre.setText(empleado.getNombre());
    txtAp.setText(empleado.getApellidoPaterno());
    txtAp2.setText(empleado.getApellidoMaterno());
    jdcFecha.setDate(empleado.getFechaNacimiento());
    txtTelefono.setText(empleado.getTelefono());
    txtCurp.setText(empleado.getCurp());
    txtDomicilio.setText(empleado.getDomicilio());
    cbxDepa.setSelectedItem(empleado.getidDepartamento());
    cbxTipo.setSelectedItem(empleado.getidTipoEmpleado());
    txtHorario.setText((String) empleado.getidHorario());
    cbxEstatus.setSelectedItem(empleado.getidEstatusEmpleado());
    txtObservacion.setText(empleado.getObservaciones());
        
    }

    private editarEmpleado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    editarEmpleado(Departamento SelectedData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEditar = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblAp = new javax.swing.JLabel();
        txtAp = new javax.swing.JTextField();
        lblAp2 = new javax.swing.JLabel();
        txtAp2 = new javax.swing.JTextField();
        lblFechaNac = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblCurp = new javax.swing.JLabel();
        txtCurp = new javax.swing.JTextField();
        lblDomicilio = new javax.swing.JLabel();
        lblDepartamento = new javax.swing.JLabel();
        txtDomicilio = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        cbxDepa = new javax.swing.JComboBox();
        cbxTipo = new javax.swing.JComboBox();
        lblEstatus = new javax.swing.JLabel();
        cbxEstatus = new javax.swing.JComboBox();
        lblObservacion = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();
        txtHorario = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jdcFecha = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblEditar.setText("EDITAR EMPLEADO");

        lblNombre.setText("NOMBRE:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        lblAp.setText("APELLIDO PATERNO:");

        lblAp2.setText("APELLIDO MATERNO:");

        lblFechaNac.setText("FECHA DE NACIMIENTO: ");

        lblTelefono.setText("TELEFONO:");

        lblCurp.setText("CURP:");

        lblDomicilio.setText("DOMICILIO: ");

        lblDepartamento.setText("DEPARTAMENTO: ");

        lblTipo.setText("TIPO:");

        lblHorario.setText("HORARIO:");

        cbxDepa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblEstatus.setText("ESTATUS:");

        cbxEstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblObservacion.setText("OBSERVACIONES:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblObservacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtObservacion))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEstatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDepartamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDepa, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDomicilio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDomicilio))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFechaNac)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblEditar)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblNombre)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCurp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCurp))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTelefono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAp2)
                            .addComponent(lblAp, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAp, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblHorario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHorario))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEditar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAp)
                    .addComponent(txtAp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAp2)
                    .addComponent(txtAp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaNac)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurp)
                    .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDomicilio)
                    .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartamento)
                    .addComponent(cbxDepa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHorario)
                    .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstatus)
                    .addComponent(cbxEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObservacion)
                    .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
        // Obtener los datos del formulario
    String nombre = txtNombre.getText();
    String apellidoPaterno = txtAp.getText();
    String apellidoMaterno = txtAp2.getText();
    Date fechaNacimiento = jdcFecha.getDate();
    String telefono = txtTelefono.getText();
    String curp = txtCurp.getText();
    String domicilio = txtDomicilio.getText();
    String departamento = (String) cbxDepa.getSelectedItem();
    String tipoEmpleado = (String) cbxTipo.getSelectedItem();
    String horario = txtHorario.getText();
    String estatus = (String) cbxEstatus.getSelectedItem();
    String observaciones = txtObservacion.getText();

    // Actualizar el objeto empleado
    empleadoActual.setNombre(nombre);
    empleadoActual.setApellidoPaterno(apellidoPaterno);
    empleadoActual.setApellidoMaterno(apellidoMaterno);
    if (fechaNacimiento != null) {
        empleadoActual.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime()));
    }
    empleadoActual.setTelefono(telefono);
    empleadoActual.setCurp(curp);
    empleadoActual.setDomicilio(domicilio);
    empleadoActual.setidDepartamento(departamento);
    empleadoActual.setidTipoEmpleado(tipoEmpleado);
    empleadoActual.setidHorario(horario);
    empleadoActual.setidEstatusEmpleado(estatus);
    empleadoActual.setObservaciones(observaciones);

    // Guardar en la base de datos
    boolean exito = modelo.EmpleadoDao.actualizarEmpleado(empleadoActual);
    if (exito) {
        JOptionPane.showMessageDialog(this, "Empleado actualizado correctamente.");
        this.dispose(); // Cierra la ventana
    } else {
        JOptionPane.showMessageDialog(this, "Error al actualizar el empleado.");
    }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    
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
            java.util.logging.Logger.getLogger(editarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editarEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox cbxDepa;
    private javax.swing.JComboBox cbxEstatus;
    private javax.swing.JComboBox cbxTipo;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JLabel lblAp;
    private javax.swing.JLabel lblAp2;
    private javax.swing.JLabel lblCurp;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDomicilio;
    private javax.swing.JLabel lblEditar;
    private javax.swing.JLabel lblEstatus;
    private javax.swing.JLabel lblFechaNac;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblObservacion;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txtAp;
    private javax.swing.JTextField txtAp2;
    private javax.swing.JTextField txtCurp;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtObservacion;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    private void cargarDatosEmpleado() {
        txtNombre.setText(empleadoActual.getNombre());
    }
    private void cargarCombos() {
    try {
        // Limpia los combos
        cbxDepa.removeAllItems();
        cbxTipo.removeAllItems();
        cbxEstatus.removeAllItems();

        // Suponiendo que tienes estos métodos en tus DAOs que devuelven List<String>
        java.util.List<String> departamentos = modelo.DepartamentoDao.obtenerNombresDepartamentos();
        java.util.List<String> tiposEmpleado = modelo.TipoEmpleadoDao.obtenerNombresTipos();
        java.util.List<String> estatusEmpleado = modelo.EstatusEmpleadoDao.obtenerNombresEstatus();

        // Llenar cbxDepa
        for (String d : departamentos) {
            cbxDepa.addItem(d);
        }

        // Llenar cbxTipo
        for (String t : tiposEmpleado) {
            cbxTipo.addItem(t);
        }

        // Llenar cbxEstatus
        for (String e : estatusEmpleado) {
            cbxEstatus.addItem(e);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar combos: " + e.getMessage());
    }
}

}
