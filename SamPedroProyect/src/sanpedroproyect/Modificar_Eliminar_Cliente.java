/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanpedroproyect;

import Class.Cliente;
import Class.Operaciones;
import DATABASE.ConnectionDB;
import com.sun.glass.events.KeyEvent;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import static sanpedroproyect.GUI_Factura.codigo_cliente;
import static sanpedroproyect.Ingreso_Nuevo_Cliente.Nombre;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Modificar_Eliminar_Cliente extends javax.swing.JFrame {
static String Cedula,Nombre,Apellido,Correo,Telefono,Celular,Direccion,Ciudad,Nota;
static int id_cliente;
Operaciones op = new Operaciones();
Cliente c = new Cliente();

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    public String getCedula() {
        return Cedula;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String Nota) {
        this.Nota = Nota;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    
    /**
     * Creates new form Modificar_Eliminar_Cliente
     */
    public Modificar_Eliminar_Cliente() {
        initComponents();
        //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        SLetras(txt_ciu);
        //SLetras(txt_dir);
        SNumeros(txt_fono);
        SNumeros(txt_celular);
        Ingreso_Nuevo_Cliente.validarCedula(txtcedu);
        cbx_Nombre.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                 //To change body of generated methods, choose Tools | Templates.
                String cadena = cbx_Nombre.getEditor().getItem().toString();
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    System.out.println("Aplasto ENter");
                        String resul = null , lats = null;
                        ConnectionDB cc = new ConnectionDB();
                        Connection cn = cc.getConnection();
                        PreparedStatement pst =null;
                        ResultSet rs = null;
                        String Desc;
                        try{
                           String sql = ("SELECT * FROM cliente WHERE Nombre='"+cadena+"' AND Estado='ACTIVO'");
                           pst = cn.prepareStatement(sql);
                           //pst.setString(1, cadena);
                           rs =pst.executeQuery();
                           if (rs.next()){

                               txtcedu.setText(rs.getString("Cedula"));
                               txt_dir.setText(rs.getString("Direccion"));
                               txt_correo.setText(rs.getString("Correo"));
                               txt_fono.setText(rs.getString("Telefono"));
                               txt_celular.setText(rs.getString("Celular"));
                               txt_nota.setText(rs.getString("Nota"));
                               txt_ciu.setText(rs.getString("Ciudad"));
                               lbl_cliente.setText(rs.getString("id_Cliente"));
                           }



                        } catch (Exception ex){
                            System.out.println(ex);
                        }
                    
                }
                if(e.getKeyCode()>= 65 && e.getKeyCode()<= 90 || e.getKeyCode()>= 96 && e.getKeyCode()<= 105 || e.getKeyCode()>= 96 && e.getKeyCode()== 8 ){
                    cbx_Nombre.setModel(op.geLista(cadena));
                    if(cbx_Nombre.getItemCount()>0){
                        cbx_Nombre.showPopup();
                        if(e.getKeyCode()!=8){
                            ((JTextComponent)cbx_Nombre.getEditor().getEditorComponent()).select(cadena.length(),cbx_Nombre.getEditor().getItem().toString().length());
                            
                            
                        }else{
                            cbx_Nombre.getEditor().setItem(cadena);
                            
                        }
                            
                    }else{
                        cbx_Nombre.addItem(cadena);
                    }
                }
            }
          
        });
        
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtcedu = new javax.swing.JTextField();
        txt_fono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_dir = new javax.swing.JTextField();
        txt_correo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_ciu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_nota = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_cliente = new javax.swing.JLabel();
        cbx_Nombre = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btn_salirModificarCliente = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txt_celular = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtcedu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcedu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtceduActionPerformed(evt);
            }
        });
        txtcedu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtceduKeyReleased(evt);
            }
        });

        txt_fono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel8.setText("Correo");

        txt_dir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_correo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel6.setText("Ciudad");
        jLabel6.setMaximumSize(new java.awt.Dimension(49, 14));
        jLabel6.setMinimumSize(new java.awt.Dimension(439, 14));

        txt_ciu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel7.setText("Nota");
        jLabel7.setMaximumSize(new java.awt.Dimension(49, 14));
        jLabel7.setMinimumSize(new java.awt.Dimension(439, 14));

        txt_nota.setColumns(20);
        txt_nota.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nota.setRows(5);
        jScrollPane1.setViewportView(txt_nota);

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel3.setText("Cedula / RUC");

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel4.setText("Telefono");

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel5.setText("Dirección");
        jLabel5.setMaximumSize(new java.awt.Dimension(49, 14));
        jLabel5.setMinimumSize(new java.awt.Dimension(439, 14));

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel10.setText("Codigo");

        lbl_cliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_cliente.setText("Codigo_Cliente");

        cbx_Nombre.setEditable(true);
        cbx_Nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel1.setText("MODIFICAR-ELIMINAR CLIENTE");

        btn_salirModificarCliente.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_salirModificarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_salirModificarCliente.setText("Salir");
        btn_salirModificarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salirModificarCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salirModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirModificarClienteActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-x.png"))); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        jButton1.setText("Modificar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel9.setText("Celular");

        txt_celular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ciu, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel2))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbx_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_cliente)
                                    .addComponent(txt_fono, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcedu, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(94, 94, 94)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_salirModificarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 85, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(111, 111, 111))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(cbx_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lbl_cliente))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtcedu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(75, 75, 75))
                            .addComponent(btn_salirModificarCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_fono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ciu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Nombre = cbx_Nombre.getSelectedItem().toString();
        Cedula = txtcedu.getText();
        Direccion = txt_dir.getText();
        Telefono = txt_fono.getText();
        Celular = txt_celular.getText();
        Ciudad = txt_ciu.getText();
        Correo = txt_correo.getText();
        Nota = txt_nota.getText();
        id_cliente = Integer.parseInt(lbl_cliente.getText());
        String msj = c.Modificar_Cliente();
        if(msj.equals("Cliente Modificado Correctamente")){
            JOptionPane.showMessageDialog(null, "Cliente Modificado Correctamente" , "CLIENTE MODIFICADO" , JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(null, "REVISAR QUE TODOS LOS CAMPOS ESTEN CORRECTOS" , "INCORRECTO" , JOptionPane.ERROR_MESSAGE);
        }

        limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int dialogResult = 1;
        dialogResult = JOptionPane.showConfirmDialog(null, "Está seguro/a que desea eliminar el cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(dialogResult == 0){
           id_cliente = Integer.parseInt(lbl_cliente.getText());
            String msj = c.Eliminar_Cliente();
            if(msj.equals("CLIENTE ELIMINADO CORRECTAMENTE")){
                JOptionPane.showMessageDialog(null, "Cliente Eliminado Correctamente" , "CLIENTE ELIMINADO" , JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Cliente no Eliminado" , "INCORRECTO" , JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_salirModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirModificarClienteActionPerformed

        dispose();
    }//GEN-LAST:event_btn_salirModificarClienteActionPerformed

    private void txtceduKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtceduKeyReleased

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String cedu = txtcedu.getText().toString();
            System.out.println("Aplasto ENter");
            String resul = null , lats = null;
            ConnectionDB cc = new ConnectionDB();
            Connection cn = cc.getConnection();
            PreparedStatement pst =null;
            ResultSet rs = null;
            try{
                String sql = ("SELECT * FROM cliente where Cedula = ?");
                pst = cn.prepareStatement(sql);
                pst.setString(1,cedu);
                rs =pst.executeQuery();
                if (rs.next()){
                    cbx_Nombre.getEditor().setItem(rs.getString("Nombre"));
                    lbl_cliente.setText(rs.getString("id_Cliente"));
                    txtcedu.setText(rs.getString("Cedula"));
                    txt_dir.setText(rs.getString("Direccion"));
                    txt_ciu.setText(rs.getString("Ciudad"));
                    txt_correo.setText(rs.getString("Correo"));
                    txt_fono.setText(rs.getString("Telefono"));
                    txt_celular.setText(rs.getString("Celular"));
                    txt_nota.setText(rs.getString("Nota"));

                }

            } catch (Exception ex){
                System.out.println(ex);
            }

        }
    }//GEN-LAST:event_txtceduKeyReleased

    private void txtceduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtceduActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtceduActionPerformed

    
    
     private void limpiar(){
        cbx_Nombre.getEditor().setItem("");
        txtcedu.setText("");
        txt_ciu.setText("");
        txt_correo.setText("");
        txt_dir.setText("");
        txt_fono.setText("");
        txt_celular.setText("");
        txt_nota.setText("");
    }

         public void SLetras(JTextField a){
        a.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                 char c = e.getKeyChar();
                 if(Character.isDigit(c)){
                     e.consume();
                 }
            }
            
            
    });
        
    }
     
      public void SNumeros(JTextField a){
        a.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                 char c = e.getKeyChar();
                 if(Character.isLetter(c)){
                     e.consume();
                 }
            }
            
            
    });
        
    }
      
                               
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_salirModificarCliente;
    private javax.swing.JComboBox cbx_Nombre;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_cliente;
    private javax.swing.JTextField txt_celular;
    private javax.swing.JTextField txt_ciu;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_dir;
    private javax.swing.JTextField txt_fono;
    private javax.swing.JTextArea txt_nota;
    private javax.swing.JTextField txtcedu;
    // End of variables declaration//GEN-END:variables
}
