/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanpedroproyect;

import Class.Inventario;
import Class.Operaciones;
import Class.Reporte_Operaciones;
import DATABASE.ConnectionDB;
import com.sun.glass.events.KeyEvent;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import static sanpedroproyect.GUI_Factura.codigo_cliente;

/**
 *
 * @author kleberstevendiazcoello
 */


public class Ingreso_producto_inventario extends javax.swing.JFrame {
    DefaultTableModel m = new DefaultTableModel();
    Operaciones op =  new Operaciones();
    Reporte_Operaciones rep = new Reporte_Operaciones();
   static int Cantidad;
   int USUARIO;
   int codigo;
   static String Codigo_Producto;
   Main_Menu menu_Cod = new Main_Menu();

    public String getCodigo_Producto() {
        return Codigo_Producto;
    }

    public void setCodigo_Producto(String Codigo_Producto) {
        this.Codigo_Producto = Codigo_Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }
    Inventario i = new Inventario();
    /**
     * Creates new form Inventario
     * @param codigo_producto_obtenido
     */
    public Ingreso_producto_inventario(String codigo_producto_obtenido) {
        initComponents();
        //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        USUARIO = menu_Cod.getCodigo_usuario();
        lbl_username.setText(Main_Menu.nombre_usuario);
        this.setLocationRelativeTo(null);
        String resul = null , lats = null;
        SNumeros(txt_cant);
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String Desc;
        try{
            String sql = ("SELECT * FROM producto where id_Producto = ?");
            pst = cn.prepareStatement(sql);
            pst.setString(1, codigo_producto_obtenido);
            rs =pst.executeQuery();
            if (rs.next()){
                lbl_descripcion.setText(rs.getString("Descripcion"));
                lbl_talla.setText(rs.getString("Talla"));
                txt_codigo_busqueda.setText(String.valueOf(codigo_producto_obtenido));
                               
                          
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

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_descripcion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_talla = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        btn_guadar_inventario = new javax.swing.JButton();
        btn_salirInventario = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txt_codigo_busqueda = new javax.swing.JTextField();
        txt_cant = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel2.setText("Codigo");

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel3.setText("Producto");

        lbl_descripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel5.setText("Talla");

        lbl_talla.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel7.setText("Cantidad a Ingresar");

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel8.setText("Usuario");

        lbl_username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_guadar_inventario.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_guadar_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible (1).png"))); // NOI18N
        btn_guadar_inventario.setText("Guardar");
        btn_guadar_inventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guadar_inventario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guadar_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guadar_inventarioActionPerformed(evt);
            }
        });

        btn_salirInventario.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_salirInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_salirInventario.setText("Salir");
        btn_salirInventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salirInventario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salirInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirInventarioActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));

        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel4.setText("INGRESO INVENTARIO");

        txt_codigo_busqueda.setEditable(false);
        txt_codigo_busqueda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_codigo_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codigo_busquedaActionPerformed(evt);
            }
        });

        txt_cant.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_username, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(358, 358, 358))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbl_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addGap(42, 42, 42)
                                        .addComponent(lbl_talla, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_cant, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_codigo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(218, 218, 218))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(btn_guadar_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addComponent(btn_salirInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel8)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addGap(29, 29, 29)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_codigo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_descripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(lbl_talla, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_cant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_guadar_inventario)
                    .addComponent(btn_salirInventario))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guadar_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guadar_inventarioActionPerformed
        // TODO add your handling code here:
        if(lbl_descripcion.getText().equals("")|| txt_cant.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS" , "ERROR AL GUARDAR" , JOptionPane.ERROR_MESSAGE);
        }else{
        int totalbuscado = 0;
        int nuevototal = 0;
        Cantidad =  Integer.parseInt(txt_cant.getText());
        Codigo_Producto = txt_codigo_busqueda.getText();
        String msj = i.Ingresar_Inventario(Codigo_Producto,Cantidad,USUARIO);
        System.out.println("Test :" + msj );
        totalbuscado = i.get_cantidad_total_producto(Codigo_Producto);
        nuevototal = (totalbuscado + Cantidad);
        String test = i.Incremeneto_total_producto(Codigo_Producto,nuevototal);
        System.out.println("Test :" + test );
        if(msj.equals("Inventario Ingresado Correctamente")){
          JOptionPane.showMessageDialog(null, "Inventario Ingresado Correctamente" , "Guardado Exitoso" , JOptionPane.INFORMATION_MESSAGE); 
          limpiar();
        }else{
          JOptionPane.showMessageDialog(null, "REVISAR QUE TODOS LOS CAMPOS ESTEN CORRECTOS" , "INCORRECTO" , JOptionPane.ERROR_MESSAGE);
        }
       } 
    }//GEN-LAST:event_btn_guadar_inventarioActionPerformed

    private void btn_salirInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirInventarioActionPerformed
        //System.exit(0);
        Main_Menu ventana_menuPrincipal = new Main_Menu();
        ventana_menuPrincipal.setVisible(true);
        ventana_menuPrincipal.setLocationRelativeTo(null);
        ventana_menuPrincipal.setResizable(false);
        dispose();
    }//GEN-LAST:event_btn_salirInventarioActionPerformed

    private void txt_codigo_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigo_busquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigo_busquedaActionPerformed

    public void limpiar(){
        txt_cant.setText("");
        lbl_descripcion.setText("");
        lbl_talla.setText("");
        txt_codigo_busqueda.setText("");
                
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
    private javax.swing.JButton btn_guadar_inventario;
    private javax.swing.JButton btn_salirInventario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_descripcion;
    private javax.swing.JLabel lbl_talla;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JTextField txt_cant;
    private javax.swing.JTextField txt_codigo_busqueda;
    // End of variables declaration//GEN-END:variables
}
