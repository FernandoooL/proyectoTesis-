package modulos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.TipoEmpleado;
import modelo.TipoEmpleadoDao;


public class tipoEmpleado extends javax.swing.JFrame {

    TipoEmpleado vtipoEmpleado = new TipoEmpleado();
    TipoEmpleadoDao vtipoEmpleadoDao = new TipoEmpleadoDao();
    private DefaultTableModel modelo;
    
    public tipoEmpleado() {
        initComponents();
        this.setLocationRelativeTo(null);
        listartipoEmpleado();
    }

    public void listartipoEmpleado(){
        List<TipoEmpleado> listaTipoEm = vtipoEmpleadoDao.listartipoEmpleado();
        modelo= (DefaultTableModel)tblTipo.getModel();
    Object[] obj = new Object[2]; // o más, según lo que necesites
    for(int i = 0; i<listaTipoEm.size();i++){
        obj[0]= listaTipoEm.get(i).getId();
        obj[1]= listaTipoEm.get(i).getNombre();
        
        
        modelo.addRow(obj);
        }
    tblTipo.setModel(modelo);
    }
    
    private TipoEmpleado SelectedData;
    
    private tipoEmpleado ventanaOrigen;
   
   public TipoEmpleado getSelectedData(){
        return SelectedData;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLis = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipo = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblLis.setText("LISTADO DE TIPO EMPLEADO");

        tblTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE"
            }
        ));
        tblTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTipoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTipo);

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar.png"))); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(102, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(153, 255, 51));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(lblLis)))
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblLis)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int fila = tblTipo.getSelectedRow();
    if (fila >= 0 && SelectedData != null) {
        editarTipoEmpleado editarVentana = new editarTipoEmpleado(SelectedData); // asegúrate de tener este constructor
        editarVentana.setVisible(true);
        this.dispose(); // cerrar la ventana actual si es necesario
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un empleado para editar.");
    }
    }//GEN-LAST:event_btnEditarActionPerformed

    public void cargarDatosTabla() {
    DefaultTableModel modelo = (DefaultTableModel) tblTipo.getModel();
    modelo.setRowCount(0); // Limpiar tabla

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemaLectorRFID", "root", "");
         PreparedStatement ps = conn.prepareStatement("SELECT id, nombre FROM tiposempleados");
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String id = rs.getString("id");
            String nombre = rs.getString("nombre");
            modelo.addRow(new Object[]{id, nombre});
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void tblTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoMouseClicked
        int fila = tblTipo.getSelectedRow();
    if (fila >= 0) {
        SelectedData = new TipoEmpleado(); {
        SelectedData = new TipoEmpleado();
        SelectedData.setId((Integer) tblTipo.getValueAt(fila, 0));
        SelectedData.setNombre(tblTipo.getValueAt(fila, 1).toString()); 
    }//GEN-LAST:event_tblTipoMouseClicked
    }
    }
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        principal p = new principal();
        p.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblTipo.getSelectedRow();

    if (fila >= 0) {
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de eliminar este empleado?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Obtener ID desde la tabla
            int idEmpleado = Integer.parseInt(tblTipo.getValueAt(fila, 0).toString());

            boolean eliminado = vtipoEmpleadoDao.eliminarEmpleado(idEmpleado); // Llama al método del DAO

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.");
                modelo.removeRow(fila); // Eliminar de la tabla directamente
                SelectedData = null; // Limpiar selección
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el empleado.");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla.");
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevoTipoEmpleado nTipoEmp = new nuevoTipoEmpleado(this);
nTipoEmp.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    
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
            java.util.logging.Logger.getLogger(tipoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tipoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tipoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tipoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tipoEmpleado().setVisible(true);
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
    private javax.swing.JTable tblTipo;
    // End of variables declaration//GEN-END:variables

    
}
