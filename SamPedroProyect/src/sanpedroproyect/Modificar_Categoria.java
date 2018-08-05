/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanpedroproyect;

import Class.Operaciones;
import DATABASE.ConnectionDB;
import com.sun.glass.events.KeyEvent;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import static sanpedroproyect.Modificar_Eliminar_Usuario.id_usuario;

/**
 *
 * @author Ivan Mosquera
 */
public class Modificar_Categoria extends javax.swing.JFrame {
    static String Nombre;
    static int id_categoria;
    Operaciones op = new Operaciones();
    /**
     * Creates new form Modificar_Eliminar_Categoria
     */
    public Modificar_Categoria() {
        initComponents();
        
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
                        try{
                           String sql = ("SELECT * FROM Categoria WHERE Nombre='"+cadena+"'");
                           pst = cn.prepareStatement(sql);
                           //pst.setString(1, cadena);
                           rs =pst.executeQuery();
                           if (rs.next()){
                               id_categoria = rs.getInt("id_Categoria");
                           }



                        } catch (Exception ex){
                            System.out.println(ex);
                        }
                    
                }
                if(e.getKeyCode()>= 65 && e.getKeyCode()<= 90 || e.getKeyCode()>= 96 && e.getKeyCode()<= 105 || e.getKeyCode()>= 96 && e.getKeyCode()== 8 ){
                    cbx_Nombre.setModel(op.getListaCategorias(cadena));
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
        jLabel2 = new javax.swing.JLabel();
        cbx_Nombre = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btn_salirModificarCliente = new javax.swing.JButton();
        btn_modificarUsuario = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel2.setText("Nombre");

        cbx_Nombre.setEditable(true);
        cbx_Nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel1.setText("MODIFICAR CATEGORÍA");

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

        btn_modificarUsuario.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_modificarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btn_modificarUsuario.setText("Modificar");
        btn_modificarUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_modificarUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_modificarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(btn_modificarUsuario)
                        .addGap(70, 70, 70)
                        .addComponent(btn_salirModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(125, 125, 125)
                        .addComponent(cbx_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(118, 118, 118))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(cbx_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_modificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salirModificarCliente))
                .addGap(0, 91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirModificarClienteActionPerformed

        dispose();
    }//GEN-LAST:event_btn_salirModificarClienteActionPerformed

    private void btn_modificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarUsuarioActionPerformed
        // TODO add your handling code here:

        Nombre = cbx_Nombre.getSelectedItem().toString();
        //id_usuario = Integer.parseInt(lbl_cliente.getText());
        String msj = Modificar_Categoria();
        if(msj.equals("Categoría Modificada Correctamente")){
            JOptionPane.showMessageDialog(null, "Categoría Modificada Correctamente" , "CATEGORÍA MODIFICADA" , JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(null, "REVISAR QUE TODOS LOS CAMPOS ESTEN CORRECTOS" , "INCORRECTO" , JOptionPane.ERROR_MESSAGE);
        }

        limpiar();
    }//GEN-LAST:event_btn_modificarUsuarioActionPerformed

    public static String Modificar_Categoria(){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "UPDATE `San Pedro`.`Categoria` SET `Nombre`= ? WHERE `id_Categoria`= ?;";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,Nombre);
            pst.setInt(2,id_categoria);
            pst.execute();
            resul = "Categoría Modificada Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
        }
        cc.desconectar();
        return resul;
        
    }
    
    
    private void limpiar(){
        cbx_Nombre.getEditor().setItem("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_modificarUsuario;
    private javax.swing.JButton btn_salirModificarCliente;
    private javax.swing.JComboBox cbx_Nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}