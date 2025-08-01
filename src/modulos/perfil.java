package modulos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Perfil;
import modelo.PerfilDao;

public class perfil extends javax.swing.JFrame {

    Perfil vPerfil = new Perfil();
    PerfilDao vPerfilDao = new PerfilDao();
    private DefaultTableModel modelo;
    
    public perfil() {
        initComponents();
        this.setLocationRelativeTo(null);
        listarPerfil();
    }

     public void listarPerfil(){
    List<Perfil> ListarPerf = vPerfilDao.listarPerfil();
    modelo= (DefaultTableModel)tblPerfil.getModel();
    Object [] obj = new Object[3];
    for(int i = 0; i<ListarPerf.size();i++){
        obj[0]= ListarPerf.get(i).getId();
        obj[1]= ListarPerf.get(i).getNombre();
        obj[2]= ListarPerf.get(i).getDescripcion();
        
        
        modelo.addRow(obj);
        }
    tblPerfil.setModel(modelo);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLis = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPerfil = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblLis.setText("LISTA DE PERFIL");

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar.png"))); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(204, 255, 0));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 0, 51));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tblPerfil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "DESCRIPCION"
            }
        ));
        tblPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPerfilMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPerfil);

        btnNuevo.setBackground(new java.awt.Color(0, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 59, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblLis)
                                .addGap(332, 332, 332))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblLis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar)
                    .addComponent(btnNuevo))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPerfilMouseClicked
       int fila = tblPerfil.getSelectedRow();
    if (fila >= 0) {
           Perfil SelectedData = new Perfil();
        SelectedData.setId(Integer.parseInt(tblPerfil.getValueAt(fila, 0).toString()));
        SelectedData.setNombre(tblPerfil.getValueAt(fila, 1).toString());
        SelectedData.setDescripcion(tblPerfil.getValueAt(fila, 1).toString());        
    } 
    }//GEN-LAST:event_tblPerfilMouseClicked

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        principal p = new principal();
        p.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       Perfil SelectedData = null;
        int fila = tblPerfil.getSelectedRow();
if (fila >= 0) {
    SelectedData = new Perfil();
    SelectedData.setId(Integer.parseInt(tblPerfil.getValueAt(fila, 0).toString()));
    SelectedData.setNombre(tblPerfil.getValueAt(fila, 1).toString());
    SelectedData.setDescripcion(tblPerfil.getValueAt(fila, 2).toString()); // estaba mal: usabas 1 dos veces

    editarPerfil editar = new editarPerfil(SelectedData);
    editar.setVisible(true);
    this.dispose();
} else {
    JOptionPane.showMessageDialog(this, "Por favor, seleccione un perfil para editar.");
}
    }//GEN-LAST:event_btnEditarActionPerformed

    public void cargarDatosTabla() {
    DefaultTableModel modelo = (DefaultTableModel) tblPerfil  .getModel();
    modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String url = "jdbc:mysql://localhost:3306/sistemaLectorRFID";
        String user = "root";
        String password = "";

        conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT id, nombre, descripcion FROM perfiles";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("id");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");

            // Agregar fila a la tabla
            modelo.addRow(new Object[]{id, nombre, descripcion});
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar perfil: " + ex.getMessage());
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
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblPerfil.getSelectedRow();
        if (fila >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar perfil?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(tblPerfil.getValueAt(fila, 0).toString());
                if (vPerfilDao.eliminarPerfil(id)) {
                    JOptionPane.showMessageDialog(this, "Perfil eliminado.");
                    modelo.removeRow(fila);
                    Object SelectedData = null;
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un perfil.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevoPerfil ventana = new nuevoPerfil(this);
ventana.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    
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
            java.util.logging.Logger.getLogger(perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new perfil().setVisible(true);
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
    private javax.swing.JTable tblPerfil;
    // End of variables declaration//GEN-END:variables

    
}
