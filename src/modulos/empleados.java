package modulos;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import modelo.EmpleadoDao;

public class empleados extends javax.swing.JFrame {

    Empleado vEmpleado = new Empleado();
    EmpleadoDao vEmpleadoDao = new EmpleadoDao();
    
    DefaultTableModel modelo = new DefaultTableModel(); //para las tablas
    
    public empleados() {
        initComponents();
        this.setLocationRelativeTo(null);
        listarEmpleado();
    }

   
    public void listarEmpleado(){
    List<Empleado> ListarEmp = vEmpleadoDao.ListarEmpleado();
    modelo= (DefaultTableModel)tblEmpleado.getModel();
    Object [] obj = new Object[14];
    for (int i = 0; i < ListarEmp.size(); i++) {
    obj[0] = ListarEmp.get(i).getId();
    obj[1] = ListarEmp.get(i).getUid(); // ← UID aquí
    obj[2] = ListarEmp.get(i).getidTipoEmpleado();
    obj[3] = ListarEmp.get(i).getidEstatusEmpleado();
    obj[4] = ListarEmp.get(i).getidDepartamento();
    obj[5] = ListarEmp.get(i).getidHorario();
    obj[6] = ListarEmp.get(i).getNombre();
    obj[7] = ListarEmp.get(i).getApellidoPaterno();
    obj[8] = ListarEmp.get(i).getApellidoMaterno();
    obj[9] = ListarEmp.get(i).getFechaNacimiento();
    obj[10] = ListarEmp.get(i).getDomicilio();
    obj[11] = ListarEmp.get(i).getTelefono();
    obj[12] = ListarEmp.get(i).getCurp();
    obj[13] = ListarEmp.get(i).getObservaciones();

    modelo.addRow(obj);
        }
    tblEmpleado.setModel(modelo);
    }
    
    private Empleado SelectedData;
   
   public Empleado getSelectedData(){
       return SelectedData;
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblLis = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnNuevo.setBackground(new java.awt.Color(0, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(153, 255, 102));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar.png"))); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblLis.setText("LISTA DE EMPLEADOS");

        tblEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "UID", "TIPO EMPLEADO", "ESTATUS EMPLEADO", "DEPARTAMENTO", "HORARIO", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "FECHA NACIMIENTO", "DOMICILIO", "TELEFONO", "CURP", "OBSERVACIONES"
            }
        ));
        tblEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleado);
        if (tblEmpleado.getColumnModel().getColumnCount() > 0) {
            tblEmpleado.getColumnModel().getColumn(0).setMinWidth(0);
            tblEmpleado.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblEmpleado.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(356, 356, 356)
                .addComponent(lblLis)
                .addContainerGap(478, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLis)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnEditar)
                    .addComponent(btnNuevo)
                    .addComponent(btnRegresar))
                .addGap(82, 82, 82)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevoEmpleado nEmp = new nuevoEmpleado(this);
    nEmp.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int fila = tblEmpleado.getSelectedRow();
    if (fila >= 0 && SelectedData != null) {
        editarEmpleado editarVentana = new editarEmpleado(SelectedData); // asegúrate de tener este constructor
        editarVentana.setVisible(true);
        this.dispose(); // cerrar la ventana actual si es necesario
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un empleado para editar.");
    }
    }//GEN-LAST:event_btnEditarActionPerformed

    public void cargarDatosTabla() {
//    DefaultTableModel modelo = (DefaultTableModel) tblEmpleado  .getModel();
    modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String url = "jdbc:mysql://localhost:3306/sistemaLectorRFID";
        String user = "root";
        String password = "";

        conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT id, uid, idTipoEmpleado, idEstatusEmpleado, idDepartamento, idHorario, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, domicilio, telefono, curp, observaciones FROM empleados";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Object[] fila = new Object[14];
            fila[0] = rs.getInt("id");
            fila[1] = rs.getString("uid");
            fila[2] = rs.getString("idTipoEmpleado");
            fila[3] = rs.getString("idEstatusEmpleado");
            fila[4] = rs.getString("idDepartamento");
            fila[5] = rs.getString("idHorario");
            fila[6] = rs.getString("nombre");
            fila[7] = rs.getString("apellidoPaterno");
            fila[8] = rs.getString("apellidoMaterno");
            fila[9] = rs.getDate("fechaNacimiento");  // Date directamente
            fila[10] = rs.getString("domicilio");
            fila[11] = rs.getString("telefono");
            fila[12] = rs.getString("curp");
            fila[13] = rs.getString("observaciones");

            // Agregar fila a la tabla
           modelo.addRow(fila);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar empleados: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            // Ignorar errores de cierre
        }
    }
    }
    
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        principal p = new principal();
    p.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void tblEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoMouseClicked
       int fila = tblEmpleado.getSelectedRow();

if (fila >= 0) {
    try {
        SelectedData = new Empleado();

        // UID como VARCHAR
        String uid = tblEmpleado.getValueAt(fila, 1).toString().trim();
        if (uid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El UID no puede estar vacío.");
            return;
        }
        SelectedData.setUid(uid);

        // Resto de datos
        SelectedData.setId(Integer.parseInt(tblEmpleado.getValueAt(fila, 0).toString()));
        SelectedData.setidTipoEmpleado(tblEmpleado.getValueAt(fila, 2).toString());
        SelectedData.setidEstatusEmpleado(tblEmpleado.getValueAt(fila, 3).toString());
        SelectedData.setidDepartamento(tblEmpleado.getValueAt(fila, 4).toString());
        SelectedData.setidHorario(tblEmpleado.getValueAt(fila, 5).toString());
        SelectedData.setNombre(tblEmpleado.getValueAt(fila, 6).toString());
        SelectedData.setApellidoPaterno(tblEmpleado.getValueAt(fila, 7).toString());
        SelectedData.setApellidoMaterno(tblEmpleado.getValueAt(fila, 8).toString());

        // Fecha de nacimiento
        String fechaTexto = tblEmpleado.getValueAt(fila, 9).toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(fechaTexto, formatter);
        SelectedData.setFechaNacimiento(Date.valueOf(fechaNacimiento));

        SelectedData.setDomicilio(tblEmpleado.getValueAt(fila, 10).toString());
        SelectedData.setTelefono(tblEmpleado.getValueAt(fila, 11).toString());
        SelectedData.setCurp(tblEmpleado.getValueAt(fila, 12).toString());
        SelectedData.setObservaciones(tblEmpleado.getValueAt(fila, 13).toString());

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar datos del empleado: " + ex.getMessage());
    }
} else {
    JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla.");
}
    }//GEN-LAST:event_tblEmpleadoMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    int fila = tblEmpleado.getSelectedRow();

if (fila == -1) {
    JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla.");
    return;
}

int confirmacion = JOptionPane.showConfirmDialog(this,
        "¿Estás seguro de eliminar este empleado?",
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION);

if (confirmacion == JOptionPane.YES_OPTION) {
    try {
        // Obtener UID desde la columna 1 (índice 1)
        Object valorUid = tblEmpleado.getValueAt(fila, 1);

        if (valorUid == null || valorUid.toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El UID del empleado no es válido.");
            return;
        }

        String uidEmpleado = valorUid.toString();

        // Eliminar usando UID
        boolean eliminado = vEmpleadoDao.eliminarEmpleado(uidEmpleado);

        if (eliminado) {
            DefaultTableModel modelo = (DefaultTableModel) tblEmpleado.getModel();
            modelo.removeRow(fila);
            SelectedData = null; // Limpiar selección

            JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el empleado desde la base de datos.");
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage());
    }
}
    }//GEN-LAST:event_btnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLis;
    private javax.swing.JTable tblEmpleado;
    // End of variables declaration//GEN-END:variables

  
}
