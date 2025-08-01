package modulos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.BorderFactory;
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
import javax.swing.Timer;


public class vistaUsuario extends javax.swing.JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sistemaLectorRFID";
    private static final String DB_USER = "root";
    private static final String DB_PASS = ""; // tu contraseña si tiene
    
    private JLabel lblFecha;
    private JLabel lblTitulo;
    private JLabel lblSubtitulo;
    
    public vistaUsuario() {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("VISTA USUARIO");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        iniciarReloj();
        iniciarInterfaz();
        txtArea.setVisible(true);
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

    // === PANEL DEL RELOJ CENTRADO ===
    lblTitulo = new JLabel("RELOJ CHECADOR");
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 80));
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    
    lblSubtitulo = new JLabel("ESCUELA SECUNDARIA FEDERAL JOSÉ VASCONCELOS");
    lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 60));
    lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);

    lblFecha = new JLabel();
    lblFecha.setFont(new Font("Arial", Font.BOLD, 100));
    lblFecha.setHorizontalAlignment(SwingConstants.CENTER);

    // Panel con disposición vertical para el título y la fecha
    JPanel panelCentro = new JPanel();
    panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
    panelCentro.setOpaque(false);

    lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);

    
        // Espaciado modificado
        panelCentro.add(Box.createVerticalStrut(100)); // ↑ más espacio arriba
        panelCentro.add(lblTitulo);
        panelCentro.add(Box.createVerticalStrut(200));  // espacio entre título y subtítulo
        panelCentro.add(lblSubtitulo);
        panelCentro.add(Box.createVerticalStrut(300));  // ↑ más espacio entre título y fecha/hora
        panelCentro.add(lblFecha);

        getContentPane().add(panelCentro, BorderLayout.CENTER);

    // Panel inferior (si tienes botones u otros elementos abajo)
    JPanel panelAbajo = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panelAbajo.setOpaque(false);
    getContentPane().add(panelAbajo, BorderLayout.SOUTH);

    agregarKeyListenerRFID(); // Solo aquí
    }
    
    private void iniciarReloj() {
        // NO crear de nuevo el JLabel ni el panel
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar calendario = Calendar.getInstance();
            calendario.add(Calendar.HOUR_OF_DAY, -1); // Ajustar una hora menos si es necesario
            java.util.Date now = calendario.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMMM yyyy, hh:mm:ss a");
            String horaFormateada = sdf.format(now);
            lblFecha.setText(horaFormateada);
        }
    });
    timer.start();
    }
    
    private void agregarKeyListenerRFID() {
        txtArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    evt.consume(); // Evita salto de línea
                    String uid = txtArea.getText().trim();
                    txtArea.setText(""); // Limpiar para la siguiente lectura
                    validarAcceso(uid);
                }
            }
        });
    }
    
    private void validarAcceso(String uid) {
    if (uid.isEmpty()) return;

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
        String sql = "SELECT nombre, apellidoPaterno, apellidoMaterno FROM empleados WHERE uid = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, uid);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String nombreCompleto = rs.getString("nombre") + " " +
                                    rs.getString("apellidoPaterno") + " " +
                                    rs.getString("apellidoMaterno");

            String checkSql = "SELECT id FROM registros WHERE uid = ? AND DATE(fecha_entrada) = CURDATE() AND fecha_salida IS NULL";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, uid);
            ResultSet checkRs = checkStmt.executeQuery();

            ZonedDateTime ahoraCDMX = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).minusHours(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String horaActual = ahoraCDMX.format(formatter);


            if (checkRs.next()) {
                // Registrar salida
                int registroId = checkRs.getInt("id");
                String updateSql = "UPDATE registros SET fecha_salida = NOW() WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, registroId);
                updateStmt.executeUpdate();

                mostrarMensajeAcceso("Hasta luego", nombreCompleto, null, horaActual);
                lblNombre.setText("<html><div style='text-align: center;'>"
    + "<h1 style='font-size:48px;'>Hasta luego</h1>"
    + "<p style='font-size:36px; margin: 5px 0;'>" + nombreCompleto + "</p>"
    + "<p style='font-size:32px; margin: 5px 0;'>Hora de salida: " + horaActual + "</p>"
    + "</div></html>");


            } else {
                // Registrar entrada
                String insertSql = "INSERT INTO registros (uid, nombre, fecha_entrada) VALUES (?, ?, NOW())";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setString(1, uid);
                insertStmt.setString(2, nombreCompleto);
                insertStmt.executeUpdate();

                mostrarMensajeAcceso("Bienvenido", nombreCompleto, horaActual, null);
                lblNombre.setText("<html><div style='text-align: center;'>"
    + "<h1 style='font-size:48px;'>Bienvenido</h1>"
    + "<p style='font-size:36px; margin: 5px 0;'>" + nombreCompleto + "</p>"
    + "<p style='font-size:32px; margin: 5px 0;'>Hora de entrada: " + horaActual + "</p>"
    + "</div></html>");



            }
        } else {
            JOptionPane.showMessageDialog(this, "❌ UID no reconocido.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "⚠️ Error al conectar con la base de datos.");
    }
}

    private void mostrarMensajeAcceso(String titulo, String nombreCompleto, String horaEntrada, String horaSalida) {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
    panel.add(lblTitulo, BorderLayout.NORTH);

    JPanel bottomPanel = new JPanel(new BorderLayout());
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel lblNombre = new JLabel(nombreCompleto);
    lblNombre.setFont(new Font("Arial", Font.PLAIN, 18));
    bottomPanel.add(lblNombre, BorderLayout.WEST);

    if (horaEntrada != null) {
        JLabel lblHoraEntrada = new JLabel("Hora de entrada: " + horaEntrada);
        lblHoraEntrada.setFont(new Font("Arial", Font.PLAIN, 16));
        bottomPanel.add(lblHoraEntrada, BorderLayout.EAST);
    } else if (horaSalida != null) {
        JLabel lblHoraSalida = new JLabel("Hora de salida: " + horaSalida);
        lblHoraSalida.setFont(new Font("Arial", Font.PLAIN, 16));
        bottomPanel.add(lblHoraSalida, BorderLayout.EAST);
    }

    panel.add(bottomPanel, BorderLayout.SOUTH);

    JOptionPane optionPane = new JOptionPane(panel, JOptionPane.INFORMATION_MESSAGE);
    JDialog dialog = optionPane.createDialog(this, titulo);
    dialog.setModal(false);
    dialog.setVisible(true);

    new java.util.Timer().schedule(new java.util.TimerTask() {
        @Override
        public void run() {
            dialog.setVisible(false);
            dialog.dispose();
        }
    }, 5000);
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        lblNombre.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblNombre.setText(".");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(356, Short.MAX_VALUE)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 900, 1230, 260));
        getContentPane().add(txtArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 880, -1, 10));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-sesion.png"))); // NOI18N
        jMenu1.setText("CERRAR SESION");

        jMenuItem1.setText("CERRAR SESION");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Deseas cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION);
    if (respuesta == JOptionPane.YES_OPTION) {
    login l = new login();
    l.setVisible(true);
    dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    }
    
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
            java.util.logging.Logger.getLogger(vistaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtArea;
    // End of variables declaration//GEN-END:variables
}
