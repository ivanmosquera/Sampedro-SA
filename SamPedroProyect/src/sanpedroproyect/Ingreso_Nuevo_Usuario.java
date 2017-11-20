/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanpedroproyect;
import Class.Usuario;
import DATABASE.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.hsqldb.Library;

/**
 *
 * @author Ivan Mosquera
 */
public class Ingreso_Nuevo_Usuario extends javax.swing.JFrame {
    static Usuario user = new Usuario();

    /**
     * Creates new form Ingreso_Nuevo_Usuario
     */
    public Ingreso_Nuevo_Usuario() {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String Desc;
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_usuario = new javax.swing.JTextField();
        btn_limpiarUsuario = new javax.swing.JButton();
        btn_guardarUsuario = new javax.swing.JButton();
        btn_salirUsuario = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_password = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_cedula = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        cb_selectRol = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_limpiarUsuario.setFont(new java.awt.Font("Bookman Old Style", 1, 11)); // NOI18N
        btn_limpiarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/escoba.png"))); // NOI18N
        btn_limpiarUsuario.setText("Limpiar");
        btn_limpiarUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_limpiarUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_limpiarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarUsuarioActionPerformed(evt);
            }
        });

        btn_guardarUsuario.setFont(new java.awt.Font("Bookman Old Style", 1, 11)); // NOI18N
        btn_guardarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible (1).png"))); // NOI18N
        btn_guardarUsuario.setText("Guardar");
        btn_guardarUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardarUsuario.setMaximumSize(new java.awt.Dimension(60, 65));
        btn_guardarUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarUsuarioActionPerformed(evt);
            }
        });

        btn_salirUsuario.setFont(new java.awt.Font("Bookman Old Style", 1, 11)); // NOI18N
        btn_salirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_salirUsuario.setText("Salir");
        btn_salirUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salirUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirUsuarioActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel9.setText("Contraseña");
        jLabel9.setMaximumSize(new java.awt.Dimension(49, 14));
        jLabel9.setMinimumSize(new java.awt.Dimension(439, 14));

        jLabel11.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel11.setText("Ingresar Nuevo Usuario");

        jLabel12.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel12.setText("Nombre");

        jLabel13.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel13.setText("Cedula");

        jLabel14.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel14.setText("Usuario");

        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel10.setText("Perfil");
        jLabel10.setMaximumSize(new java.awt.Dimension(49, 14));
        jLabel10.setMinimumSize(new java.awt.Dimension(439, 14));

        cb_selectRol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<seleccione Perfil>" }));
        cb_selectRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_selectRolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(141, 141, 141)
                                .addComponent(txt_nombre))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_guardarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cb_selectRol, 0, 214, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_limpiarUsuario)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                        .addComponent(btn_salirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_password)
                                    .addComponent(txt_usuario)
                                    .addComponent(txt_cedula, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addGap(104, 104, 104))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_selectRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_limpiarUsuario)
                    .addComponent(btn_guardarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salirUsuario))
                .addGap(232, 232, 232))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static String Ingresar_usuario(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO usuario values(null,?,sha1(?),?,?,?,?)";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,user.usuario);
            pst.setString(2,user.contraseña);
            pst.setString(3,user.nombre);
            pst.setString(4,user.cedula);
            pst.setString(5,"ACTIVO");
            pst.setInt(6,user.id_Rol);
            pst.execute();
            resul = "Usuario Ingresado Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
            System.out.println(pst);
        }
        
        return resul;
    }
    
    private void limpiar(){
        txt_nombre.setText("");
        txt_cedula.setText("");
        txt_usuario.setText("");
        txt_password.setText("");
        cb_selectRol.setSelectedIndex(0);
    }
    private void btn_limpiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarUsuarioActionPerformed
        limpiar();
    }//GEN-LAST:event_btn_limpiarUsuarioActionPerformed

    private void btn_guardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarUsuarioActionPerformed
        // TODO add your handling code here:
        user.nombre = txt_nombre.getText();
        user.cedula = txt_cedula.getText();
        user.usuario = txt_usuario.getText();
        user.contraseña = txt_password.getText();
        int id_rol = cb_selectRol.getSelectedIndex();
        System.out.println(id_rol);
        user.id_Rol = id_rol;
        String msj = Ingresar_usuario();
        if(msj.equals("Usuario Ingresado Correctamente")){
          JOptionPane.showMessageDialog(null, msj , "Guardado Exitoso" , JOptionPane.INFORMATION_MESSAGE); 
          limpiar();
        }else{
          JOptionPane.showMessageDialog(null, "REVISAR QUE TODOS LOS CAMPOS ESTÉN CORRECTOS" , "INCORRECTO" , JOptionPane.ERROR_MESSAGE);
        }
        //JOptionPane.showMessageDialog(null, "InfoBox: " + msj , "Guardado Exitoso" , JOptionPane.INFORMATION_MESSAGE);
        //limpiar();
    }//GEN-LAST:event_btn_guardarUsuarioActionPerformed

    private void btn_salirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirUsuarioActionPerformed

        dispose();
    }//GEN-LAST:event_btn_salirUsuarioActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void cb_selectRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_selectRolActionPerformed
        int id_rol = cb_selectRol.getSelectedIndex();
        System.out.println(id_rol);
    }//GEN-LAST:event_cb_selectRolActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardarUsuario;
    private javax.swing.JButton btn_limpiarUsuario;
    private javax.swing.JButton btn_salirUsuario;
    private javax.swing.JComboBox cb_selectRol;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
