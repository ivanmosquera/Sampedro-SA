/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanpedroproyect;

import Class.Operaciones;
import Class.Usuario;
import DATABASE.ConnectionDB;
import com.sun.glass.events.KeyEvent;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Ivan Mosquera
 */
public class Modificar_Eliminar_Usuario extends javax.swing.JFrame {
    static String Cedula,Nombre,Apellido,Usuario,Contraseña;
    static int id_usuario, id_rol;
    Operaciones op = new Operaciones();
    Usuario u = new Usuario();

    /**
     * Creates new form Modificar_Eliminar_Usuario
     */
    public Modificar_Eliminar_Usuario() {
        initComponents();
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        try{
            String sql = ("SELECT * FROM rol ");
            pst = cn.prepareStatement(sql);
            rs =pst.executeQuery();
            while (rs.next()){
                    System.out.println(rs.getString("Detalle"));
                    cb_selectRol.addItem(rs.getString("Detalle"));
                }



            } catch (Exception ex){
                            System.out.println(ex);
            }
        
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
                           String sql = ("SELECT * FROM Usuario WHERE Nombre='"+cadena+"' AND Estado='ACTIVO'");
                           pst = cn.prepareStatement(sql);
                           //pst.setString(1, cadena);
                           rs =pst.executeQuery();
                           if (rs.next()){
                               txtcedu.setText(rs.getString("Cedula"));
                               txt_usuario.setText(rs.getString("Usuario"));
                               id_usuario = rs.getInt("id_Usuario");
                               id_rol = rs.getInt("fk_Rol");
                               cb_selectRol.setSelectedIndex(id_rol);
                           }



                        } catch (Exception ex){
                            System.out.println(ex);
                        }
                    
                }
                if(e.getKeyCode()>= 65 && e.getKeyCode()<= 90 || e.getKeyCode()>= 96 && e.getKeyCode()<= 105 || e.getKeyCode()>= 96 && e.getKeyCode()== 8 ){
                    cbx_Nombre.setModel(op.getListaUsuarios(cadena));
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
        txt_usuario = new javax.swing.JTextField();
        txt_password = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbx_Nombre = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btn_salirModificarCliente = new javax.swing.JButton();
        btn_eliminarUsuario = new javax.swing.JButton();
        btn_modificarUsuario = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        cb_selectRol = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SAMPEDRO S.A");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel6.setText("Perfil");
        jLabel6.setMaximumSize(new java.awt.Dimension(49, 14));
        jLabel6.setMinimumSize(new java.awt.Dimension(439, 14));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setText("Cedula");

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel4.setText("Usuario");

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel5.setText("Nueva contraseña");
        jLabel5.setMaximumSize(new java.awt.Dimension(49, 14));
        jLabel5.setMinimumSize(new java.awt.Dimension(439, 14));

        cbx_Nombre.setEditable(true);

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel1.setText("MODIFICAR-ELIMINAR USUARIO");

        btn_salirModificarCliente.setFont(new java.awt.Font("Bookman Old Style", 1, 11)); // NOI18N
        btn_salirModificarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_salirModificarCliente.setText("Salir");
        btn_salirModificarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salirModificarCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salirModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirModificarClienteActionPerformed(evt);
            }
        });

        btn_eliminarUsuario.setFont(new java.awt.Font("Bookman Old Style", 1, 11)); // NOI18N
        btn_eliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-x.png"))); // NOI18N
        btn_eliminarUsuario.setText("Eliminar");
        btn_eliminarUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminarUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_eliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarUsuarioActionPerformed(evt);
            }
        });

        btn_modificarUsuario.setFont(new java.awt.Font("Bookman Old Style", 1, 11)); // NOI18N
        btn_modificarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btn_modificarUsuario.setText("Modificar");
        btn_modificarUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_modificarUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_modificarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarUsuarioActionPerformed(evt);
            }
        });

        cb_selectRol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<seleccione Perfil>" }));
        cb_selectRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_selectRolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbx_Nombre, 0, 178, Short.MAX_VALUE)
                            .addComponent(txt_password, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(txt_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(txtcedu, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(cb_selectRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_modificarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_eliminarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_salirModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel1)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(cbx_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtcedu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_selectRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btn_modificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_eliminarUsuario)
                                .addGap(75, 75, 75))
                            .addComponent(btn_salirModificarCliente, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(0, 63, Short.MAX_VALUE))
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
    public static String Modificar_Usuario(){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "UPDATE `San Pedro`.`Usuario` SET `Cedula`= ? , `Nombre`= ? , `Usuario`= ?, `Contraseña`= sha1(?) , `fk_Rol`= ?   WHERE `id_Usuario`= ?;";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,Cedula);
            pst.setString(2,Nombre);
            pst.setString(3,Usuario);
            pst.setString(4,Contraseña);
            pst.setInt(5,id_rol);
            pst.setInt(6,id_usuario);
            pst.execute();
            System.out.println(id_rol);
            resul = "Usuario Modificado Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
        }
        cc.desconectar();
        return resul;
        
    }
    
    public static String Eliminar_Usuario(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "UPDATE `San Pedro`.`Usuario` SET `Estado`= ?  WHERE `id_Usuario`= ?;";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,"ELIMINADO");
            pst.setInt(2,id_usuario);
            pst.execute();
            resul = "USUARIO ELIMINADO CORRECTAMENTE";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
        }
        cc.desconectar();
        return resul;
        
        
    }
    
    private void limpiar(){
        cbx_Nombre.getEditor().setItem("");
        txtcedu.setText("");
        txt_password.setText("");
        txt_usuario.setText("");
        cb_selectRol.setSelectedIndex(0);
    }
    
    private void btn_salirModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirModificarClienteActionPerformed

        dispose();
    }//GEN-LAST:event_btn_salirModificarClienteActionPerformed

    private void btn_eliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarUsuarioActionPerformed
        // TODO add your handling code here:
        
        //id_usuario = Integer.parseInt(lbl_cliente.getText());
        String msj = Eliminar_Usuario();
        if(msj.equals("USUARIO ELIMINADO CORRECTAMENTE")){
            JOptionPane.showMessageDialog(null, "Usuario Eliminado Correctamente" , "USUARIO ELIMINADO" , JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(null, "Usuario no Eliminado" , "INCORRECTO" , JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_eliminarUsuarioActionPerformed

    private void btn_modificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarUsuarioActionPerformed
        // TODO add your handling code here:
        
        Nombre = cbx_Nombre.getSelectedItem().toString();
        Cedula = txtcedu.getText();
        Contraseña = txt_password.getText();
        Usuario = txt_usuario.getText();
        //id_usuario = Integer.parseInt(lbl_cliente.getText());
        String msj = Modificar_Usuario();
        if(msj.equals("Usuario Modificado Correctamente")){
            JOptionPane.showMessageDialog(null, "Usuario Modificado Correctamente" , "USUARIO MODIFICADO" , JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(null, "REVISAR QUE TODOS LOS CAMPOS ESTEN CORRECTOS" , "INCORRECTO" , JOptionPane.ERROR_MESSAGE);
        }

        limpiar();
    }//GEN-LAST:event_btn_modificarUsuarioActionPerformed

    private void cb_selectRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_selectRolActionPerformed
        id_rol = cb_selectRol.getSelectedIndex();
        System.out.println(id_rol);
    }//GEN-LAST:event_cb_selectRolActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminarUsuario;
    private javax.swing.JButton btn_modificarUsuario;
    private javax.swing.JButton btn_salirModificarCliente;
    private javax.swing.JComboBox cb_selectRol;
    private javax.swing.JComboBox cbx_Nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_usuario;
    private javax.swing.JTextField txtcedu;
    // End of variables declaration//GEN-END:variables
}