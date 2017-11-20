/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanpedroproyect;

import DATABASE.ConnectionDB;
import com.sun.glass.events.KeyEvent;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import static sanpedroproyect.Modificar_Eliminar_Usuario.id_usuario;

/**
 *
 * @author Ivan Mosquera
 */
public class Modificar_Eliminar_Perfil extends javax.swing.JFrame {

    /**
     * Creates new form Modificar_Eliminar_Perfil
     */
    private static int id_Perfil;
    private int nuevo_IdPermiso;
    private static String [] permisos_perfil;
    private static String nombre_Perfil;
    private static boolean seleccionado_Separar = false;
    private static boolean seleccionado_Facturar = false;
    private static boolean seleccionado_Anular = false;
    private static boolean seleccionado_CierreDeCaja = false;
    private static boolean seleccionado_IngresoInventario = false;
    private static boolean seleccionado_RegistrarCliente = false;
    private static boolean seleccionado_ModificarCliente = false;
    private static boolean seleccionado_IngresarProducto = false;
    private static boolean seleccionado_RealizarDescuentos = false;
    /*
    private static boolean seleccionado_Facturar = false;
    private static boolean cambio_estado_Facturar = false;
    private static boolean cambio_estado_Facturar = false;
    private static boolean cambio_estado_Facturar = false;
    private static boolean cambio_estado_Facturar = false;
    private static boolean cambio_estado_Facturar = false;
    private static boolean cambio_estado_Facturar = false;
    private static boolean cambio_estado_Facturar = false;
    private static boolean cambio_estado_Facturar = false;*/
    public Modificar_Eliminar_Perfil() {
        initComponents();
        obtenerPerfiles();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ckbox_Facturar = new javax.swing.JCheckBox();
        ckbox_Separar = new javax.swing.JCheckBox();
        ckbox_Anular = new javax.swing.JCheckBox();
        ckbox_registrarCliente = new javax.swing.JCheckBox();
        ckbox_reporteCierreCaja = new javax.swing.JCheckBox();
        ckbox_ingresarProducto = new javax.swing.JCheckBox();
        ckbox_ingresarInventario = new javax.swing.JCheckBox();
        ckbox_modificarCliente = new javax.swing.JCheckBox();
        ckbox_realizarDescuentos = new javax.swing.JCheckBox();
        btn_modificarPerfil = new javax.swing.JButton();
        btn_salirModificarPerfil = new javax.swing.JButton();
        cb_selectRol = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SAMPEDRO S.A");

        jLabel11.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel11.setText("Modificar Perfil");

        jLabel12.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel12.setText("Nombre");

        jLabel13.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel13.setText("Permisos");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ckbox_Facturar.setText("Facturar");
        ckbox_Facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbox_FacturarActionPerformed(evt);
            }
        });

        ckbox_Separar.setText("Realizar Separados");

        ckbox_Anular.setText("Anular factura");

        ckbox_registrarCliente.setText("Registrar cliente");

        ckbox_reporteCierreCaja.setText("Generar reportes Cierre de caja");

        ckbox_ingresarProducto.setText("Ingresar producto");

        ckbox_ingresarInventario.setText("Ingresar inventario");

        ckbox_modificarCliente.setText("Modificar o eliminar cliente");

        ckbox_realizarDescuentos.setText("Realizar descuentos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbox_realizarDescuentos)
                    .addComponent(ckbox_reporteCierreCaja)
                    .addComponent(ckbox_ingresarProducto)
                    .addComponent(ckbox_ingresarInventario)
                    .addComponent(ckbox_modificarCliente)
                    .addComponent(ckbox_registrarCliente)
                    .addComponent(ckbox_Anular)
                    .addComponent(ckbox_Separar)
                    .addComponent(ckbox_Facturar))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ckbox_Facturar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbox_Separar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbox_Anular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbox_registrarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbox_modificarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbox_ingresarInventario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbox_ingresarProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbox_reporteCierreCaja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbox_realizarDescuentos)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        btn_modificarPerfil.setFont(new java.awt.Font("Bookman Old Style", 1, 11)); // NOI18N
        btn_modificarPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btn_modificarPerfil.setText("Modificar");
        btn_modificarPerfil.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_modificarPerfil.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_modificarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarPerfilActionPerformed(evt);
            }
        });

        btn_salirModificarPerfil.setFont(new java.awt.Font("Bookman Old Style", 1, 11)); // NOI18N
        btn_salirModificarPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_salirModificarPerfil.setText("Salir");
        btn_salirModificarPerfil.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salirModificarPerfil.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salirModificarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirModificarPerfilActionPerformed(evt);
            }
        });

        cb_selectRol.setEditable(true);
        cb_selectRol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<seleccione Perfil>" }));
        cb_selectRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_selectRolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_selectRol, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_modificarPerfil)
                            .addComponent(btn_salirModificarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel11)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cb_selectRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btn_modificarPerfil)
                        .addGap(45, 45, 45)
                        .addComponent(btn_salirModificarPerfil)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void obtenerPerfiles(){
        cb_selectRol.removeAllItems();
        cb_selectRol.addItem("<seleccione Perfil>");
        limpiar();
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        try{
            String sql = ("SELECT Detalle FROM rol ");
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
    private void obtener_IdPerfil(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        try{
            String sql = ("SELECT id_Rol FROM rol WHERE Detalle='"+nombre_Perfil+"'");
            pst = cn.prepareStatement(sql);
            rs =pst.executeQuery();
            while (rs.next()){
                    System.out.println(rs.getInt("id_Rol"));
                    id_Perfil = rs.getInt("id_Rol");
                }



            } catch (Exception ex){
                            System.out.println(ex);
            }
    }
    private void limpiar(){
        cb_selectRol.setSelectedIndex(0);
        ckbox_Anular.setSelected(false);
        ckbox_Facturar.setSelected(false);
        ckbox_Separar.setSelected(false);
        ckbox_ingresarInventario.setSelected(false);
        ckbox_ingresarProducto.setSelected(false);
        ckbox_modificarCliente.setSelected(false);
        ckbox_realizarDescuentos.setSelected(false);
        ckbox_reporteCierreCaja.setSelected(false);
        ckbox_registrarCliente.setSelected(false);
    } 
    
    public String Modificar_NombrePerfil(){
        //obtener_IdPerfil();
        System.out.println(String.valueOf(cb_selectRol.getSelectedItem()));
        nombre_Perfil = String.valueOf(cb_selectRol.getSelectedItem());
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "UPDATE `San Pedro`.`Rol` SET `Detalle`= ?   WHERE `id_Rol`= ?;";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1, nombre_Perfil);
            pst.setInt(2,id_Perfil);
            pst.execute();
            System.out.println(id_Perfil);
            resul = "Perfil Modificado Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
        }
        cc.desconectar();
        return resul;
        
    }
    private void btn_salirModificarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirModificarPerfilActionPerformed

        dispose();
    }//GEN-LAST:event_btn_salirModificarPerfilActionPerformed

    public void obtener_IdPermiso(String permiso){
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        try{
            String sql = ("SELECT id_Permiso FROM permisos WHERE Nombre='"+permiso+"'");
            pst = cn.prepareStatement(sql);
            rs =pst.executeQuery();
            if (rs.next()){
                    System.out.println(rs.getInt("id_Permiso"));
                    nuevo_IdPermiso = rs.getInt("id_Permiso");
                }

            } catch (Exception ex){
                    System.out.println(ex);
            }
    }
    
    public String Ingresar_permisos(int id_permiso){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO roles_permisos values(?,?)";
        try{
            pst = cn.prepareStatement(sql);
            pst.setInt(1,id_Perfil);
            pst.setInt(2,id_permiso);
            pst.execute();
            resul = "Permiso Ingresados Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
            System.out.println(pst);
        }
        
        return resul;
    }
    
    public String remover_Permisos(int id_permiso){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "DELETE FROM roles_permisos WHERE fk_Rol="+id_Perfil+" AND fk_Permiso="+id_permiso+"";
        try{
            pst = cn.prepareStatement(sql);
            //pst.setInt(1,id_Perfil);
            //pst.setInt(2,id_permiso);
            pst.execute();
            resul = "Permiso Removido Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
            System.out.println(pst);
        }
        
        return resul;
    }
    
    private void btn_modificarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarPerfilActionPerformed
        // TODO add your handling code here:
        String msj = Modificar_NombrePerfil();
        if(msj.equals("Perfil Modificado Correctamente")){
            //JOptionPane.showMessageDialog(null, "Perfil Modificado Correctamente" , "PERFIL MODIFICADO" , JOptionPane.INFORMATION_MESSAGE);
            if(ckbox_Facturar.isSelected() != seleccionado_Facturar){
                if(ckbox_Facturar.isSelected()){
                    obtener_IdPermiso("Facturar");
                    Ingresar_permisos(nuevo_IdPermiso);
                }else{
                    obtener_IdPermiso("Facturar");
                    remover_Permisos(nuevo_IdPermiso);
                }
            }; 
            if(ckbox_Separar.isSelected() != seleccionado_Separar){
                if(ckbox_Separar.isSelected()){
                    obtener_IdPermiso("RealizarSeparados");
                    Ingresar_permisos(nuevo_IdPermiso);
                }else{
                    obtener_IdPermiso("RealizarSeparados");
                    remover_Permisos(nuevo_IdPermiso);
                }
            }; 
            if(ckbox_Anular.isSelected() != seleccionado_Anular){
                if(ckbox_Anular.isSelected()){
                    obtener_IdPermiso("Anular");
                    Ingresar_permisos(nuevo_IdPermiso);
                }else{
                    obtener_IdPermiso("Anular");
                    remover_Permisos(nuevo_IdPermiso);
                }
            }; 
            if(ckbox_ingresarInventario.isSelected() != seleccionado_IngresoInventario){
                if(ckbox_ingresarInventario.isSelected()){
                    obtener_IdPermiso("IngresoInventario");
                    Ingresar_permisos(nuevo_IdPermiso);
                }else{
                    obtener_IdPermiso("IngresoInventario");
                    remover_Permisos(nuevo_IdPermiso);
                }
            };
            if(ckbox_ingresarProducto.isSelected() != seleccionado_IngresarProducto){
                if(ckbox_ingresarProducto.isSelected()){
                    obtener_IdPermiso("IngresarProducto");
                    Ingresar_permisos(nuevo_IdPermiso);
                }else{
                    obtener_IdPermiso("IngresarProducto");
                    remover_Permisos(nuevo_IdPermiso);
                }
            };
            if(ckbox_registrarCliente.isSelected() != seleccionado_RegistrarCliente){
                if(ckbox_registrarCliente.isSelected()){
                    obtener_IdPermiso("RegistrarCliente");
                    Ingresar_permisos(nuevo_IdPermiso);
                }else{
                    obtener_IdPermiso("RegistrarCliente");
                    remover_Permisos(nuevo_IdPermiso);
                }
            };
            if(ckbox_modificarCliente.isSelected() != seleccionado_ModificarCliente){
                if(ckbox_modificarCliente.isSelected()){
                    obtener_IdPermiso("ModificarCliente");
                    Ingresar_permisos(nuevo_IdPermiso);
                }else{
                    obtener_IdPermiso("ModificarCliente");
                    remover_Permisos(nuevo_IdPermiso);
                }
            };
            if(ckbox_reporteCierreCaja.isSelected() != seleccionado_CierreDeCaja){
                if(ckbox_reporteCierreCaja.isSelected()){
                    obtener_IdPermiso("GenerarReportesCierreDeCaja");
                    Ingresar_permisos(nuevo_IdPermiso);
                }else{
                    obtener_IdPermiso("GenerarReportesCierreDeCaja");
                    remover_Permisos(nuevo_IdPermiso);
                }
            };
            if(ckbox_realizarDescuentos.isSelected() != seleccionado_RealizarDescuentos){
                if(ckbox_realizarDescuentos.isSelected()){
                    obtener_IdPermiso("RealizarDescuentos");
                    Ingresar_permisos(nuevo_IdPermiso);
                }else{
                    obtener_IdPermiso("RealizarDescuentos");
                    remover_Permisos(nuevo_IdPermiso);
                }
            };
            JOptionPane.showMessageDialog(null, "Perfil Modificado Correctamente" , "PERFIL MODIFICADO" , JOptionPane.INFORMATION_MESSAGE);
            obtenerPerfiles();
            limpiar();
        }else{
            JOptionPane.showMessageDialog(null, "REVISAR QUE TODOS LOS CAMPOS ESTEN CORRECTOS" , "INCORRECTO" , JOptionPane.ERROR_MESSAGE);
        }

        limpiar();
    }//GEN-LAST:event_btn_modificarPerfilActionPerformed

    private void cb_selectRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_selectRolActionPerformed
        // TODO add your handling code here:
        System.out.println(cb_selectRol.getSelectedIndex());
        if ((cb_selectRol.getSelectedIndex()) == 0){
            limpiar();
        }
        nombre_Perfil = String.valueOf(cb_selectRol.getSelectedItem());
        obtener_IdPerfil();
        ckbox_Anular.setSelected(false);
        ckbox_Facturar.setSelected(false);
        ckbox_Separar.setSelected(false);
        ckbox_ingresarInventario.setSelected(false);
        ckbox_ingresarProducto.setSelected(false);
        ckbox_modificarCliente.setSelected(false);
        ckbox_realizarDescuentos.setSelected(false);
        ckbox_registrarCliente.setSelected(false);
        ckbox_reporteCierreCaja.setSelected(false);
        seleccionado_Separar = false;
        seleccionado_Facturar = false;
        seleccionado_Anular = false;
        seleccionado_CierreDeCaja = false;
        seleccionado_IngresoInventario = false;
        seleccionado_RegistrarCliente = false;
        seleccionado_ModificarCliente = false;
        seleccionado_IngresarProducto = false;
        seleccionado_RealizarDescuentos = false;
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        try{
           //id_Perfil = cb_selectRol.getSelectedIndex();  // REALIZAR FUNCION DE OBTENER IDPERFIL A PARTIR DEL NOMBRE DEL PERFIL
           String sql = ("SELECT Detalle, Nombre FROM rol JOIN roles_permisos ON rol.id_Rol = roles_permisos.fk_Rol JOIN permisos ON roles_permisos.fk_Permiso = permisos.id_Permiso WHERE rol.id_Rol="+id_Perfil+" ORDER BY Detalle");
           pst = cn.prepareStatement(sql);
           //pst.setString(1, cadena);
           rs =pst.executeQuery();
           rs.last();
           int numFils = rs.getRow();
           rs.beforeFirst();
           int i = 0;
           permisos_perfil = new String[numFils];
           while(rs.next()){
               permisos_perfil[i] = rs.getString("Nombre");
               i++;
           }
           //System.out.println(Arrays.toString(permisos_perfil));
            i = 0;
            for(i=0;i<permisos_perfil.length;i++){
                if(permisos_perfil[i].equals("RealizarSeparados")){
                    ckbox_Separar.setSelected(true);
                    seleccionado_Separar = true;
                }else if(permisos_perfil[i].equals("Facturar")){
                    ckbox_Facturar.setSelected(true);
                    seleccionado_Facturar = true;
                }else if(permisos_perfil[i].equals("Anular")){
                    ckbox_Anular.setSelected(true);
                    seleccionado_Anular = true;
                }else if(permisos_perfil[i].equals("GenerarReportesCierreDeCaja")){
                    ckbox_reporteCierreCaja.setSelected(true);
                    seleccionado_CierreDeCaja = true;
                }else if(permisos_perfil[i].equals("IngresoInventario")){
                    ckbox_ingresarInventario.setSelected(true);
                    seleccionado_IngresoInventario = true;
                }else if(permisos_perfil[i].equals("RegistrarCliente")){
                    ckbox_registrarCliente.setSelected(true);
                    seleccionado_RegistrarCliente = true;
                }else if(permisos_perfil[i].equals("ModificarCliente")){
                    ckbox_modificarCliente.setSelected(true);
                    seleccionado_ModificarCliente = true;
                }else if(permisos_perfil[i].equals("IngresarProducto")){
                    ckbox_ingresarProducto.setSelected(true);
                    seleccionado_IngresarProducto = true;
                }else if(permisos_perfil[i].equals("RealizarDescuentos")){
                    ckbox_realizarDescuentos.setSelected(true);
                    seleccionado_RealizarDescuentos = true;
                }
                

            }
           /*
           if (rs.next()){
               txtcedu.setText(rs.getString("Cedula"));
               txt_usuario.setText(rs.getString("Usuario"));
               id_usuario = rs.getInt("id_Usuario");
               id_rol = rs.getInt("fk_Rol");
               cb_selectRol.setSelectedIndex(id_rol);
           }*/



        } catch (Exception ex){
            System.out.println(ex);
        }
    }//GEN-LAST:event_cb_selectRolActionPerformed

    private void ckbox_FacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbox_FacturarActionPerformed
        // TODO add your handling code here:
        //cambio_estado_Facturar = true;
        
    }//GEN-LAST:event_ckbox_FacturarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_modificarPerfil;
    private javax.swing.JButton btn_salirModificarPerfil;
    private javax.swing.JComboBox cb_selectRol;
    private javax.swing.JCheckBox ckbox_Anular;
    private javax.swing.JCheckBox ckbox_Facturar;
    private javax.swing.JCheckBox ckbox_Separar;
    private javax.swing.JCheckBox ckbox_ingresarInventario;
    private javax.swing.JCheckBox ckbox_ingresarProducto;
    private javax.swing.JCheckBox ckbox_modificarCliente;
    private javax.swing.JCheckBox ckbox_realizarDescuentos;
    private javax.swing.JCheckBox ckbox_registrarCliente;
    private javax.swing.JCheckBox ckbox_reporteCierreCaja;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
