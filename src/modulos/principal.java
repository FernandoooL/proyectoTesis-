package modulos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class principal extends javax.swing.JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sistemaLectorRFID";
    private static final String DB_USER = "root";
    private static final String DB_PASS = ""; // tu contraseña si tiene
    
    private JLabel lblFecha;
    private JLabel lblTitulo;
    private JLabel lblSubtitulo;
    
    public principal() {
    initComponents();      
    
    setTitle("VISTA ADMINISTRADOR");
    setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(new Color(173, 216, 230)); // Azul claro
    setLocationRelativeTo(null);

    iniciarInterfaz();
    iniciarReloj();
    evitarCerrarVentana();
    }


    public void evitarCerrarVentana (){
          // Agregar un WindowListener para interceptar el evento de cierre
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                confirmarCierre();
            }
        });
         // IMPORTANTE: Establecer la operación por defecto al cerrar en DO_NOTHING_ON_CLOSE
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
}

    private void confirmarCierre() {
       int option = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro de que quieres salir?",
            "Confirmar Salida",
            JOptionPane.YES_NO_OPTION
        );
        if (option == JOptionPane.YES_OPTION) {
            
            System.exit(0); // Termina la aplicación (opcional)
        }
    }
    
    private void iniciarInterfaz() {
                // Panel principal con BorderLayout
        getContentPane().setLayout(new BorderLayout());

        // === PANEL CENTRAL CON TÍTULO, SUBTÍTULO Y FECHA ===
        lblTitulo = new JLabel("RELOJ CHECADOR");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 80));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblSubtitulo = new JLabel("ESCUELA SECUNDARIA FEDERAL JOSÉ VASCONCELOS");
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 60));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblFecha = new JLabel();
        lblFecha.setFont(new Font("Arial", Font.BOLD, 100));
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);

        // Usamos BoxLayout para apilar verticalmente los textos
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setOpaque(false);

        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agrega espaciado y componentes en orden vertical
        panelCentro.add(Box.createVerticalStrut(100));   // Espacio superior
        panelCentro.add(lblTitulo);
        panelCentro.add(Box.createVerticalStrut(200));   // Espacio entre título y subtítulo
        panelCentro.add(lblSubtitulo);
        panelCentro.add(Box.createVerticalStrut(300));   // Espacio entre subtítulo y fecha/hora
        panelCentro.add(lblFecha);

        getContentPane().add(panelCentro, BorderLayout.CENTER);
        // Inicia reloj y listener
        iniciarReloj();
    }
    
    private void iniciarReloj() {
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar calendario = Calendar.getInstance();
            calendario.add(Calendar.HOUR_OF_DAY, -1); // Ajustar una hora menos si es necesario
            java.util.Date now = calendario.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMMM yyyy, hh:mm:ss a");
            String horaFormateada = sdf.format(now);
            lblFecha.setText(horaFormateada); // ✅ Solo actualizar el texto
        }
    });
    timer.start();
    }
    
  private void validarAcceso(String uid) {
    if (uid.isEmpty()) return;

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
        String sql = "SELECT nombre FROM empleados WHERE uid = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, uid);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String nombre = rs.getString("nombre");

            // Verificar si ya hay una entrada sin salida hoy
            String checkSql = "SELECT id FROM registros WHERE uid = ? AND DATE(fecha_entrada) = CURDATE() AND fecha_salida IS NULL";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, uid);
            ResultSet checkRs = checkStmt.executeQuery();

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

            if (checkRs.next()) {
                // Ya existe entrada sin salida → registrar salida
                int registroId = checkRs.getInt("id");

                String updateSql = "UPDATE registros SET fecha_salida = NOW() WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, registroId);
                updateStmt.executeUpdate();

                // Obtener la hora de salida actual
                String horaSalida = sdf.format(new java.util.Date());
                String mensaje = "👋 Hasta luego, " + nombre + "\nHora de salida: " + horaSalida;

                // Mostrar mensaje con cierre automático
                JOptionPane optionPane = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = optionPane.createDialog(this, "Despedida");
                dialog.setModal(false);
                dialog.setVisible(true);

                // Temporizador para cerrar después de 5 segundos (5000 ms)
                new java.util.Timer().schedule(new java.util.TimerTask() {
                    @Override
                    public void run() {
                        dialog.setVisible(false);
                        dialog.dispose();
                    }
                }, 5000);
            } else {
                // No hay entrada hoy → registrar entrada
            String insertSql = "INSERT INTO registros (uid, nombre, fecha_entrada) VALUES (?, ?, NOW())";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, uid);
            insertStmt.setString(2, nombre);
            insertStmt.executeUpdate();

            // Obtener la hora de entrada actual
            String horaEntrada = sdf.format(new java.util.Date());
            String mensaje = "✅ Bienvenido, " + nombre + "\nHora de entrada: " + horaEntrada;

            // Mostrar mensaje con cierre automático
            JOptionPane optionPane = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog(this, "Bienvenida");
            dialog.setModal(false);
            dialog.setVisible(true);

            // Temporizador para cerrar después de 5 segundos (5000 ms)
            new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    dialog.setVisible(false);
                    dialog.dispose();
                }
            }, 5000);
            }
        } else {
            JOptionPane.showMessageDialog(this, "❌ UID no reconocido.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "⚠️ Error al conectar con la base de datos.");
    }
}


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/credencial(1).png"))); // NOI18N
        jMenu1.setText("CREDENCIALES");

        jMenuItem7.setText("IMPRIMIR CREDENCIALES");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/catalogo.png"))); // NOI18N
        jMenu2.setText("CATALOGOS");

        jMenuItem1.setText("EMPLEADOS");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem4.setText("DEPARTAMENTOS");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("TIPOS DE EMPLEADOS");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("HORARIOS");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reportes.png"))); // NOI18N
        jMenu3.setText("REPORTES");

        jMenuItem12.setText("REPORTE DE EMPLEADOS");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuItem14.setText("REPORTE DE REGISTRO");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem14);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/incidencias.png"))); // NOI18N
        jMenu4.setText("INCIDENCIAS");

        jMenuItem9.setText("JUSTIFICANTES");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/seguridad.png"))); // NOI18N
        jMenu5.setText("SEGURIDAD");

        jMenuItem2.setText("PERFILES DE USUARIOS");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuItem3.setText("USUARIOS");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuBar1.add(jMenu5);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/auditoria.png"))); // NOI18N
        jMenu8.setText("AUDITORIA");

        jMenuItem11.setText("REGISTRO");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem11);

        jMenuBar1.add(jMenu8);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/system-log-out.png"))); // NOI18N
        jMenu9.setText("CERRAR SESION");

        jMenuItem10.setText("CERRAR SESION");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem10);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1132, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        empleados e = new empleados();
    e.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        perfil p = new perfil();
    p.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        nuevoUsuario u = new nuevoUsuario();
    u.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
       departamento d = new departamento();
    d.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        tipoEmpleado t = new tipoEmpleado();
    t.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        editarHorario edH = new editarHorario();
    edH.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        nuevoJustificante jus = new nuevoJustificante();
    jus.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        registro r = new registro();
    r.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        reporteEmpleado rep = new reporteEmpleado();
    rep.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Deseas cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION);
    if (respuesta == JOptionPane.YES_OPTION) {
    login l = new login();
    l.setVisible(true);
    dispose();
}

    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        reporteRegistro rep = new reporteRegistro();
    rep.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        ventanaCredencial vC = new ventanaCredencial();
    vC.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables

}

