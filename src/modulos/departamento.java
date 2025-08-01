package modulos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Departamento;
import modelo.DepartamentoDao;
import modelo.Empleado;

public class departamento extends javax.swing.JFrame {
    
    Departamento vDepartamento = new Departamento();
    DepartamentoDao vDepartamentoDao = new DepartamentoDao();
    private DefaultTableModel modelo;

    
    public departamento() {
        initComponents();
        this.setLocationRelativeTo(null);
        listarDepartamento();
    }
    
    public void listarDepartamento(){
    List<Departamento> ListarDepa = vDepartamentoDao.ListarDepartamento();
    modelo= (DefaultTableModel)tblDepa.getModel();
    Object [] obj = new Object[2];
    for(int i = 0; i<ListarDepa.size();i++){
        obj[0]= ListarDepa.get(i).getId();
        obj[1]= ListarDepa.get(i).getNombre();
        
        
        modelo.addRow(obj);
        }
    tblDepa.setModel(modelo);
    }
    
     private Departamento SelectedData;
     
     private Departamento ventanaOrigen;
   
   public Departamento getSelectedData(){
       return SelectedData;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDepa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("LISTAR DEPARTAMENTO");

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar.png"))); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(51, 204, 255));
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

        tblDepa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE"
            }
        ));
        tblDepa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDepaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDepa);
        if (tblDepa.getColumnModel().getColumnCount() > 0) {
            tblDepa.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(242, 242, 242))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addGap(1, 1, 1))
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       int fila = tblDepa.getSelectedRow();
        if (fila >= 0 && SelectedData != null) {
            editarDepartamento editarVentana = new editarDepartamento(SelectedData);
            editarVentana.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un departamento para editar.");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    public void cargarDatosTabla() {
    DefaultTableModel modelo = (DefaultTableModel) tblDepa  .getModel();
    modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String url = "jdbc:mysql://localhost:3306/sistemaLectorRFID";
        String user = "root";
        String password = "";

        conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT id, nombre FROM departamentos";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("id");
            String nombre = rs.getString("nombre");

            // Agregar fila a la tabla
            modelo.addRow(new Object[]{id, nombre});
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar departamentos: " + ex.getMessage());
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

    private void tblDepaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDepaMouseClicked
       int fila = tblDepa.getSelectedRow();
    if (fila >= 0) {
        SelectedData = new Departamento();
        SelectedData.setId(Integer.parseInt(tblDepa.getValueAt(fila, 0).toString()));
        SelectedData.setNombre(tblDepa.getValueAt(fila, 1).toString());
    } 
    }//GEN-LAST:event_tblDepaMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       int fila = tblDepa.getSelectedRow();
        if (fila >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar departamento?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(tblDepa.getValueAt(fila, 0).toString());
                if (vDepartamentoDao.eliminarDepartamento(id)) {
                    JOptionPane.showMessageDialog(this, "Departamento eliminado.");
                    modelo.removeRow(fila);
                    SelectedData = null;
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un departamento.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevoDepartamento nD = new nuevoDepartamento(this);
nD.setVisible(true);

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
            java.util.logging.Logger.getLogger(departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new departamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDepa;
    // End of variables declaration//GEN-END:variables
}
