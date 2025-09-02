package modulos;

import conexion.ConexionBD;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class incidencias extends javax.swing.JFrame {

   
    public incidencias() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarUsuarios();
    }

    
    private void cargarUsuarios() {
    String sql = "SELECT e.id, CONCAT(e.nombre, ' ', e.apellidoPaterno, ' ', e.apellidoMaterno) AS nombreCompleto " +
                 "FROM empleados e " +
                 "ORDER BY nombreCompleto ASC";

    try (Connection con = ConexionBD.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        jComboBox1.removeAllItems();
        jComboBox1.addItem(new EmpleadoItem(0, "Todos")); // Opción general

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombreCompleto = rs.getString("nombreCompleto");

            if (nombreCompleto != null && !nombreCompleto.trim().isEmpty()) {
                jComboBox1.addItem(new EmpleadoItem(id, nombreCompleto.trim()));
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar empleados: " + e.getMessage());
    }
}

   private void seleccionarEmpleadoPorId(int idBuscado) {
    for (int i = 0; i < jComboBox1.getItemCount(); i++) {
        EmpleadoItem item = (EmpleadoItem) jComboBox1.getItemAt(i);
        if (item.getId() == idBuscado) {
            jComboBox1.setSelectedIndex(i); // ✅ selecciona el índice correcto
            return;
        }
    }
    // si no encuentra, opcional: seleccionar "Todos"
    jComboBox1.setSelectedIndex(0);
}


public class EmpleadoItem {
    private int id;
    private String nombreCompleto;

    public EmpleadoItem(int id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    @Override
    public String toString() {
        return nombreCompleto; // lo que se verá en el JComboBox
    }
}

    
    private void filtrarEntrada() {
    DefaultTableModel modelo = (DefaultTableModel) tblIncidencias.getModel();
    modelo.setRowCount(0);

    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); // Solo fecha

    try (Connection con = ConexionBD.getConnection()) {
        String usuarioSeleccionado = jComboBox1.getSelectedItem().toString();
        java.util.Date desde = jdcD.getDate();
        java.util.Date hasta = jdcH.getDate();

        if (desde == null || hasta == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un rango de fechas.");
            return;
        }

        java.sql.Date fechaInicio = new java.sql.Date(desde.getTime());
        java.sql.Date fechaFin = new java.sql.Date(hasta.getTime());

        StringBuilder sql = new StringBuilder(
            "SELECT i.id, i.idEmpleado, i.idTipoIncidencia, i.fechaHoraInicio, i.fechaHoraFin, i.observaciones " +
            "FROM incidencias i " +
            "INNER JOIN empleados e ON i.idEmpleado = e.id " +
            "WHERE DATE(i.fechaHoraInicio) BETWEEN ? AND ?"
        );

        List<Object> parametros = new ArrayList<>();
        parametros.add(fechaInicio);
        parametros.add(fechaFin);

        // Si no es "Todos", agregamos filtro por empleado
        if (!usuarioSeleccionado.equals("Todos")) {
            // Obtenemos el id real del empleado según el nombre completo
            String sqlEmpleado = "SELECT id FROM empleados WHERE CONCAT(nombre,' ',apellidoPaterno,' ',apellidoMaterno) = ?";
            int idEmpleado = -1;

            try (PreparedStatement psEmpleado = con.prepareStatement(sqlEmpleado)) {
                psEmpleado.setString(1, usuarioSeleccionado);
                try (ResultSet rsEmpleado = psEmpleado.executeQuery()) {
                    if (rsEmpleado.next()) {
                        idEmpleado = rsEmpleado.getInt("id");
                    } else {
                        JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
                        return;
                    }
                }
            }

            sql.append(" AND i.idEmpleado = ?");
            parametros.add(idEmpleado);
        }

        sql.append(" ORDER BY i.fechaHoraInicio ASC");

        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                Object param = parametros.get(i);
                if (param instanceof java.sql.Date) {
                    ps.setDate(i + 1, (java.sql.Date) param);
                } else if (param instanceof Integer) {
                    ps.setInt(i + 1, (Integer) param);
                }
            }

            try (ResultSet rs = ps.executeQuery()) {
                int contador = 0;
                while (rs.next()) {
                    String fechaInicioStr = formatoFecha.format(rs.getTimestamp("fechaHoraInicio"));
                    String fechaFinStr = rs.getTimestamp("fechaHoraFin") != null ?
                            formatoFecha.format(rs.getTimestamp("fechaHoraFin")) : "";
                    String observaciones = rs.getString("observaciones") != null ? rs.getString("observaciones") : "";

                    modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getInt("idEmpleado"),
                        rs.getInt("idTipoIncidencia"),
                        fechaInicioStr,
                        fechaFinStr,
                        observaciones
                    });
                    contador++;
                }

                if (contador == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron registros en ese rango.");
                }
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al filtrar datos: " + e.getMessage());
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        lblUsuarios = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        lblDesde = new javax.swing.JLabel();
        lblHasta = new javax.swing.JLabel();
        jdcD = new com.toedter.calendar.JDateChooser();
        jdcH = new com.toedter.calendar.JDateChooser();
        btnFiltrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblIncidencias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("REGISTRO DE INCIDENCIAS");

        btnRegresar.setBackground(new java.awt.Color(204, 255, 0));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar.png"))); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblUsuarios.setText("USUARIOS:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        lblDesde.setText("DESDE:");

        lblHasta.setText("HASTA: ");

        btnFiltrar.setBackground(new java.awt.Color(0, 255, 255));
        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/filtrar.png"))); // NOI18N
        btnFiltrar.setText("FILTRAR");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        tblIncidencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID EMPLEADO", "ID TIPO INCIDENCIA", "FECHA INICIO", "FECHA FIN", "OBSERVACIONES"
            }
        ));
        jScrollPane1.setViewportView(tblIncidencias);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuarios)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(174, 174, 174)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDesde)
                            .addComponent(jdcD, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHasta)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jdcH, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFiltrar)))))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 52, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresar)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarios)
                    .addComponent(lblDesde)
                    .addComponent(lblHasta))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFiltrar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jdcH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jdcD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
       filtrarEntrada();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new incidencias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcD;
    private com.toedter.calendar.JDateChooser jdcH;
    private javax.swing.JLabel lblDesde;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lblUsuarios;
    private javax.swing.JTable tblIncidencias;
    // End of variables declaration//GEN-END:variables
}
