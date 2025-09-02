package modulos;

import conexion.ConexionBD;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Empleado;


public class nuevoEmpleado extends javax.swing.JFrame {
     ConexionBD cn = new ConexionBD();
   Connection con;
   PreparedStatement ps;
   ResultSet rs;
    
    
     private empleados ventanaOrigen;
     private File archivoSeleccionado;
   
    public nuevoEmpleado(empleados ventanaOrigen) {
        initComponents();
//        txtID.setEditable(false);
        this.ventanaOrigen = ventanaOrigen;
        this.setLocationRelativeTo(null);
        llenarComboTipoEmpleado();
        llenarComboEstatus();
        llenarComboDepartamento();
        llenarComboHorario();
    }

    private nuevoEmpleado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void llenarComboTipoEmpleado() {
    jcbTipo.removeAllItems();
    try (Connection con = ConexionBD.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT id, nombre FROM tiposempleados")) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            jcbTipo.addItem(new ItemCombo(id, nombre));
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar tipos de empleado", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

   private void llenarComboEstatus() {
    jcbEstatus.removeAllItems();
    try (Connection con = ConexionBD.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT id, nombre FROM estatusempleados")) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            jcbEstatus.addItem(new ItemCombo(id, nombre));
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar estatus", "Error", JOptionPane.ERROR_MESSAGE);
    }
}       
        private void llenarComboDepartamento() {
    jcbDepa.removeAllItems();
    try (Connection con = ConexionBD.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT id, nombre FROM departamentos")) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            jcbDepa.addItem(new ItemCombo(id, nombre));
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar departamentos", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

        private void llenarComboHorario() {
    jcbHorario.removeAllItems();
    try (Connection con = ConexionBD.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT id, nombre FROM horarios")) { 

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            jcbHorario.addItem(new ItemCombo(id, nombre));
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar horarios", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    public static class ItemCombo {
    private int id;
    private String nombre;

    public ItemCombo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return nombre;
    }
}


    public ByteArrayInputStream comprimirImagen(File archivoOriginal, float calidad) throws IOException {
    BufferedImage imagenOriginal = ImageIO.read(archivoOriginal);
    if (imagenOriginal == null) {
        throw new IOException("No se pudo leer la imagen.");
    }

    // Opcional: redimensionar si es muy grande
    int maxAncho = 600;
    int maxAlto = 600;
    int ancho = imagenOriginal.getWidth();
    int alto = imagenOriginal.getHeight();

    if (ancho > maxAncho || alto > maxAlto) {
        float escala = Math.min((float) maxAncho / ancho, (float) maxAlto / alto);
        ancho = (int) (ancho * escala);
        alto = (int) (alto * escala);
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        BufferedImage imagenRedimensionada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = imagenRedimensionada.createGraphics();
        // Fondo blanco para evitar transparencia (JPEG no soporta alfa)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, ancho, alto);
        g2d.drawImage(imagenEscalada, 0, 0, null);
        g2d.dispose();

        imagenOriginal = imagenRedimensionada;
    }

    // Comprimir a JPEG
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
    ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
    writer.setOutput(ios);

    ImageWriteParam param = writer.getDefaultWriteParam();
    if (param.canWriteCompressed()) {
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(calidad); // calidad de 0.0 (muy baja) a 1.0 (máxima)
    }

    writer.write(null, new IIOImage(imagenOriginal, null, null), param);
    ios.close();
    writer.dispose();

    return new ByteArrayInputStream(baos.toByteArray());
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblTipoE = new javax.swing.JLabel();
        lblEstatus = new javax.swing.JLabel();
        lblDepartamento = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblAp = new javax.swing.JLabel();
        lblAp2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblDomicilio = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblCurp = new javax.swing.JLabel();
        lblObservaciones = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        txtNombre = new javax.swing.JTextField();
        txtAp = new javax.swing.JTextField();
        txtAp2 = new javax.swing.JTextField();
        txtDomicilio = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCurp = new javax.swing.JTextField();
        txtObservaciones = new javax.swing.JTextField();
        lblUID = new javax.swing.JLabel();
        txtUID = new javax.swing.JTextField();
        jcbTipo = new javax.swing.JComboBox();
        jcbEstatus = new javax.swing.JComboBox();
        jcbDepa = new javax.swing.JComboBox();
        lblFoto = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();
        jcbHorario = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("REGISTRAR NUEVO EMPLEADO");

        lblTipoE.setText("TIPO EMPLEADO:");

        lblEstatus.setText("ESTATUS EMPLEADO:");

        lblDepartamento.setText("DEPARTAMENTO:");

        lblHorario.setText("HORARIO:");

        lblNombre.setText("NOMBRE:");

        lblAp.setText("APELLIDO PATERNO:");

        lblAp2.setText("APELLIDO MATERNO:");

        lblFecha.setText("FECHA NACIMIENTO:");

        lblDomicilio.setText("DOMICILIO:");

        lblTelefono.setText("TELEFONO:");

        lblCurp.setText("CURP:");

        lblObservaciones.setText("OBSERVACIONES:");

        btnAceptar.setBackground(new java.awt.Color(51, 255, 255));
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disquete.png"))); // NOI18N
        btnAceptar.setText("GUARDAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(204, 255, 51));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar.png"))); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        txtDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomicilioActionPerformed(evt);
            }
        });

        lblUID.setText("UID:");

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcbEstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcbDepa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblFoto.setText("FOTOGRAFIA:");

        btnFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cheque.png"))); // NOI18N
        btnFoto.setText("SELECCIONAR FOTOGRAFIA");
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });

        jcbHorario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(lblUID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUID, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCurp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(lblObservaciones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblDomicilio)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtDomicilio))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblTipoE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblEstatus)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblNombre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblAp)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtAp, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblFecha)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblTelefono)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblAp2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtAp2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblDepartamento)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcbDepa, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(31, 31, 31)
                                    .addComponent(lblHorario)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcbHorario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAceptar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegresar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFoto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUID))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTipoE)
                        .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblEstatus)
                            .addGap(6, 6, 6))
                        .addComponent(jcbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartamento)
                    .addComponent(jcbDepa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHorario)
                    .addComponent(jcbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAp)
                            .addComponent(txtAp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAp2)
                            .addComponent(txtAp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFecha)
                            .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTelefono)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDomicilio)
                    .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCurp)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblObservaciones))
                            .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFoto)
                    .addComponent(btnFoto))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnRegresar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
  String uid = txtUID.getText().trim();
int tipoE = ((ItemCombo) jcbTipo.getSelectedItem()).getId();
int estatus = ((ItemCombo) jcbEstatus.getSelectedItem()).getId();
int departamento = ((ItemCombo) jcbDepa.getSelectedItem()).getId();
int horario = ((ItemCombo) jcbHorario.getSelectedItem()).getId();
String nombre = txtNombre.getText().trim();
String ap = txtAp.getText().trim();
String ap2 = txtAp2.getText().trim();
String domicilio = txtDomicilio.getText().trim();
String telefono = txtTelefono.getText().trim();
String curp = txtCurp.getText().trim();
String observaciones = txtObservaciones.getText().trim();
java.util.Date fecha = jdcFecha.getDate();


try {
    Connection con = ConexionBD.getConnection();

    String insertSql = "INSERT INTO empleados (uid, idTipoEmpleado, idEstatusEmpleado, idDepartamento, idHorario, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, domicilio, telefono, curp, observaciones, fotografia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    ps = con.prepareStatement(insertSql);
    ps.setString(1, uid);
    ps.setInt(2, tipoE);
    ps.setInt(3, estatus);
    ps.setInt(4, departamento);
    ps.setInt(5, horario);
    ps.setString(6, nombre);
    ps.setString(7, ap);
    ps.setString(8, ap2);

    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
    ps.setDate(9, fechaSQL);

    ps.setString(10, domicilio);
    ps.setString(11, telefono);
    ps.setString(12, curp);
    ps.setString(13, observaciones);

    if (archivoSeleccionado != null) {
        // Aquí comprimes la imagen antes de enviarla a BD
        float calidadCompresion = 0.7f; // Puedes ajustar calidad aquí (0.0 a 1.0)
        ByteArrayInputStream imagenComprimidaStream = comprimirImagen(archivoSeleccionado, calidadCompresion);

        // Nota: Para setBinaryStream necesitamos la longitud, la obtenemos así:
        int longitudStream = imagenComprimidaStream.available();

        ps.setBinaryStream(14, imagenComprimidaStream, longitudStream);
    } else {
        ps.setNull(14, java.sql.Types.BLOB); // No se seleccionó imagen
    }

    int resultado = ps.executeUpdate();

    if (resultado > 0) {
        JOptionPane.showMessageDialog(this, "Empleado guardado exitosamente.");
        if (ventanaOrigen != null) {
            ventanaOrigen.cargarDatosTabla();
        }
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(this, "No se pudo guardar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
    }

} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Error de base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}        catch (IOException ex) {
             Logger.getLogger(nuevoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
    try {
        if (ps != null) ps.close();
        if (con != null) con.close();
    } catch (SQLException ex) {
        // Ignorar
    }
}
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void txtDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDomicilioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDomicilioActionPerformed

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed
       JFileChooser selector = new JFileChooser();
    selector.setDialogTitle("Seleccionar fotografía");
    
    // Filtro para mostrar solo imágenes
    FileNameExtensionFilter filtro = new FileNameExtensionFilter(
        "Imágenes JPG, PNG y JPEG", "jpg", "jpeg", "png"
    );
    selector.setFileFilter(filtro);
    
    int resultado = selector.showOpenDialog(this);
    
    if (resultado == JFileChooser.APPROVE_OPTION) {
        archivoSeleccionado = selector.getSelectedFile();
        
        // Mostrar la imagen en un JLabel (por ejemplo, lblFoto)
        try {
            ImageIcon icono = new ImageIcon(archivoSeleccionado.getAbsolutePath());
            // Escalar la imagen para que encaje en el JLabel
            Image imagenEscalada = icono.getImage().getScaledInstance(
                lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH
            );
            lblFoto.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar imagen: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_btnFotoActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose(); // Cierra la ventana actual
    for (Frame f : Frame.getFrames()) {
        if (f instanceof empleados) {
            f.setVisible(true); // Muestra la existente si ya está creada
            return;
        }
    }
    }//GEN-LAST:event_btnRegresarActionPerformed

   
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
            java.util.logging.Logger.getLogger(nuevoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nuevoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nuevoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nuevoEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nuevoEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnFoto;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox jcbDepa;
    private javax.swing.JComboBox jcbEstatus;
    private javax.swing.JComboBox jcbHorario;
    private javax.swing.JComboBox jcbTipo;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JLabel lblAp;
    private javax.swing.JLabel lblAp2;
    private javax.swing.JLabel lblCurp;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDomicilio;
    private javax.swing.JLabel lblEstatus;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoE;
    private javax.swing.JLabel lblUID;
    private javax.swing.JTextField txtAp;
    private javax.swing.JTextField txtAp2;
    private javax.swing.JTextField txtCurp;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtObservaciones;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUID;
    // End of variables declaration//GEN-END:variables
//private Connection ConexionBD() {
//        try {
//        Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de tener el driver en tu proyecto
//        return DriverManager.getConnection(
//            "jdbc:mysql://localhost:3306/sistemaLectorRFID", // Cambia "mi_basedatos" por el nombre de tu base de datos
//            "root",                                  // Cambia "usuario" por tu nombre de usuario
//            ""                                // Cambia "contraseña" por tu contraseña
//        );
//    } catch (Exception e) {
//        JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage());
//        return null;
//    }
//    }

}
