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

/**
 *
 * @author kleberstevendiazcoello
 */


public class Reducir_Inventario extends javax.swing.JFrame {
    DefaultTableModel m = new DefaultTableModel();
    Operaciones op =  new Operaciones();
    Reporte_Operaciones rep = new Reporte_Operaciones();
   static int Cantidad;
   static String Codigo_Producto;
   int USUARIO;
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
     */
    public Reducir_Inventario() {
        initComponents();
        //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        USUARIO = menu_Cod.getCodigo_usuario();
        txt_usuario.setText(Main_Menu.nombre_usuario);
         this.setLocationRelativeTo(null);
         
        cmb_producto.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                 //To change body of generated methods, choose Tools | Templates.
                String cadena = cmb_producto.getEditor().getItem().toString();
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    m = rep.consultar_producto(cadena);
                    tabla_producto.setModel(m);
                }
                
                   
                if(e.getKeyCode()>= 65 && e.getKeyCode()<= 90 || e.getKeyCode()>= 96 && e.getKeyCode()<= 105 || e.getKeyCode()>= 96 && e.getKeyCode()== 8 ){
                    cmb_producto.setModel(op.geLista_Producto(cadena));
                    if(cmb_producto.getItemCount()>0){
                        cmb_producto.showPopup();
                        if(e.getKeyCode()!=8){
                            ((JTextComponent)cmb_producto.getEditor().getEditorComponent()).select(cadena.length(),cmb_producto.getEditor().getItem().toString().length());
                            
                            
                        }else{
                            cmb_producto.getEditor().setItem(cadena);
                            
                        }
                            
                    }else{
                        cmb_producto.addItem(cadena);
                    }
                }
            }
          
        });
        
        
        SNumeros(txt_cant);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialog_buscar = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmb_producto = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_producto = new javax.swing.JTable();
        btn_agregar_producto = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cmb_CodoPre = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_descripcion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_talla = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JLabel();
        btn_buscar_Producto = new javax.swing.JButton();
        btn_guadar_inventario = new javax.swing.JButton();
        btn_salirInventario = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txt_codigo_busqueda = new javax.swing.JTextField();
        txt_cant = new javax.swing.JTextField();

        Dialog_buscar.setTitle("Buscar");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 12)); // NOI18N
        jLabel1.setText("BUSCAR POR: ");

        cmb_producto.setEditable(true);
        cmb_producto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_productoItemStateChanged(evt);
            }
        });

        tabla_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla_producto);

        btn_agregar_producto.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_agregar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anadir.png"))); // NOI18N
        btn_agregar_producto.setText("AGREGAR");
        btn_agregar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_productoActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        jButton3.setText("SALIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cmb_CodoPre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione Busqueda>", "Descripcion", "Codigo" }));
        cmb_CodoPre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_CodoPreItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmb_CodoPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmb_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(btn_agregar_producto)
                .addGap(66, 66, 66)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmb_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_CodoPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregar_producto)
                    .addComponent(jButton3))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Dialog_buscarLayout = new javax.swing.GroupLayout(Dialog_buscar.getContentPane());
        Dialog_buscar.getContentPane().setLayout(Dialog_buscarLayout);
        Dialog_buscarLayout.setHorizontalGroup(
            Dialog_buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Dialog_buscarLayout.setVerticalGroup(
            Dialog_buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
        jLabel7.setText("Cantidad a Reducir");

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel8.setText("Usuario");

        txt_usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btn_buscar_Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/busqueda.png"))); // NOI18N
        btn_buscar_Producto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_buscar_Producto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_buscar_Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_ProductoActionPerformed(evt);
            }
        });

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
        jLabel4.setText("REDUCIR INVENTARIO");

        txt_codigo_busqueda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
                        .addGap(278, 278, 278)
                        .addComponent(btn_buscar_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 286, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
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
                                    .addComponent(txt_codigo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_codigo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_buscar_Producto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_descripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(lbl_talla, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_cant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if(lbl_descripcion.getText().equals("")||txt_cant.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS" , "ERROR AL GUARDAR" , JOptionPane.ERROR_MESSAGE);
        }else{
        int totalbuscado = 0;
        int nuevototal = 0;
        Cantidad =  Integer.parseInt(txt_cant.getText());
        Codigo_Producto = txt_codigo_busqueda.getText();
        String msj = i.Ingresar_Inventario(Codigo_Producto,Cantidad,USUARIO);
        System.out.println("Test :" + msj );
        totalbuscado = i.get_cantidad_total_producto(Codigo_Producto);
        nuevototal = (totalbuscado - Cantidad);
        if(nuevototal<0){
          JOptionPane.showMessageDialog(null, "ERROR" , "CANTIDAD ACTUAL ES :"+totalbuscado+", NO SE PUEDE REDUCIR A UN NUMERO NEGATIVO" , JOptionPane.ERROR_MESSAGE);
        }else{
            String test = i.Incremeneto_total_producto(Codigo_Producto,nuevototal);
            System.out.println("Test :" + test );
            if(msj.equals("Inventario Ingresado Correctamente")){
              JOptionPane.showMessageDialog(null, "Inventario Ingresado Correctamente" , "Guardado Exitoso" , JOptionPane.INFORMATION_MESSAGE); 
              limpiar();

            }else{
              JOptionPane.showMessageDialog(null, "REVISAR QUE TODOS LOS CAMPOS ESTEN CORRECTOS" , "INCORRECTO" , JOptionPane.ERROR_MESSAGE);
            }
        }
        
       }
    }//GEN-LAST:event_btn_guadar_inventarioActionPerformed

    private void btn_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_productoActionPerformed
        // TODO add your handling code here:
        int fsel = tabla_producto.getSelectedRow();
        try {
            String codigo, descripcion, precio, cantidad,importe,talla;
            if(fsel==-1){
                JOptionPane.showMessageDialog(null,"Dese seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
            else{
                m = (DefaultTableModel) tabla_producto.getModel();
                codigo = tabla_producto.getValueAt(fsel, 0).toString();
                descripcion = tabla_producto.getValueAt(fsel, 1).toString();
                precio = tabla_producto.getValueAt(fsel, 2).toString();
                talla = tabla_producto.getValueAt(fsel, 3).toString();
                txt_codigo_busqueda.setText(codigo);
                lbl_descripcion.setText(descripcion);
                lbl_talla.setText(talla);
                Dialog_buscar.setVisible(false);
                
            }
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_btn_agregar_productoActionPerformed

    private void btn_buscar_ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_ProductoActionPerformed
        // TODO add your handling code here:
        m = rep.consultar_producto();
        tabla_producto.setModel(m);
        Dialog_buscar.setSize(700, 500);
        Dialog_buscar.setLocationRelativeTo(null);
        Dialog_buscar.setVisible(true);
        
    }//GEN-LAST:event_btn_buscar_ProductoActionPerformed

    private void btn_salirInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirInventarioActionPerformed
        //System.exit(0);
       /* Main_Menu ventana_menuPrincipal = new Main_Menu();
        ventana_menuPrincipal.setVisible(true);
        ventana_menuPrincipal.setLocationRelativeTo(null);
        ventana_menuPrincipal.setResizable(false);*/
        dispose();
    }//GEN-LAST:event_btn_salirInventarioActionPerformed

    private void cmb_productoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_productoItemStateChanged
        // TODO add your handling code here
         if(cmb_CodoPre.getSelectedItem().equals("Descripcion")){
               
            cmb_producto.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String cadena = cmb_producto.getEditor().getItem().toString();
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String name = cmb_producto.getEditor().getItem().toString();
                    m = rep.consultar_producto_name(name);
                    tabla_producto.setModel(m);  
                }
                if(e.getKeyCode()>= 65 && e.getKeyCode()<= 90 || e.getKeyCode()>= 96 && e.getKeyCode()<= 105 || e.getKeyCode()>= 96 && e.getKeyCode()== 8 ){
                    cmb_producto.setModel(op.geLista_Producto(cadena));
                    if(cmb_producto.getItemCount()>0){
                        cmb_producto.showPopup();
                        if(e.getKeyCode()!=8){
                            ((JTextComponent)cmb_producto.getEditor().getEditorComponent()).select(cadena.length(),cmb_producto.getEditor().getItem().toString().length());
                            
                            
                        }else{
                            cmb_producto.getEditor().setItem(cadena);
                            
                        }
                            
                    }else{
                        cmb_producto.addItem(cadena);
                    }
                }
            }                   
            
        });
               
              
        }else if(cmb_CodoPre.getSelectedItem().equals("Codigo")){
            cmb_producto.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                //String cadena = cmb_producto.getEditor().getItem().toString();
                String cadena = cmb_producto.getEditor().getItem().toString();
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String codigo_producto = cmb_producto.getEditor().getItem().toString();
                    m = rep.consultar_producto_codigo(codigo_producto);
                    tabla_producto.setModel(m);  
                }
                if(e.getKeyCode()>= 65 && e.getKeyCode()<= 90 || e.getKeyCode()>= 96 && e.getKeyCode()<= 105 || e.getKeyCode()>= 96 && e.getKeyCode()== 8 ){
                    cmb_producto.setModel(op.geLista_Producto_porcodigo(cadena));
                    if(cmb_producto.getItemCount()>0){
                        cmb_producto.showPopup();
                        if(e.getKeyCode()!=8){
                            //((JTextComponent)cmb_producto.getEditor().getEditorComponent()).select(cadena.length(),cmb_producto.getEditor().getItem().toString().length());
                            
                            
                        }else{
                            cmb_producto.getEditor().setItem(cadena);
                            
                        }
                            
                    }else{
                        cmb_producto.addItem(cadena);
                    }
                }
            }                   
            
        });
            
        }
    }//GEN-LAST:event_cmb_productoItemStateChanged

    private void cmb_CodoPreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_CodoPreItemStateChanged
        if(cmb_CodoPre.getSelectedItem().equals("Descripcion")){
               
            cmb_producto.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String cadena = cmb_producto.getEditor().getItem().toString();
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String name = cmb_producto.getEditor().getItem().toString();
                    m = rep.consultar_producto_name(name);
                    tabla_producto.setModel(m);  
                }
                if(e.getKeyCode()>= 65 && e.getKeyCode()<= 90 || e.getKeyCode()>= 96 && e.getKeyCode()<= 105 || e.getKeyCode()>= 96 && e.getKeyCode()== 8 ){
                    cmb_producto.setModel(op.geLista_Producto(cadena));
                    if(cmb_producto.getItemCount()>0){
                        cmb_producto.showPopup();
                        if(e.getKeyCode()!=8){
                            ((JTextComponent)cmb_producto.getEditor().getEditorComponent()).select(cadena.length(),cmb_producto.getEditor().getItem().toString().length());
                            
                            
                        }else{
                            cmb_producto.getEditor().setItem(cadena);
                            
                        }
                            
                    }else{
                        cmb_producto.addItem(cadena);
                    }
                }
            }                   
            
        });
               
              
        }
    }//GEN-LAST:event_cmb_CodoPreItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Dialog_buscar.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JDialog Dialog_buscar;
    private javax.swing.JButton btn_agregar_producto;
    private javax.swing.JButton btn_buscar_Producto;
    private javax.swing.JButton btn_guadar_inventario;
    private javax.swing.JButton btn_salirInventario;
    private javax.swing.JComboBox cmb_CodoPre;
    private javax.swing.JComboBox cmb_producto;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_descripcion;
    private javax.swing.JLabel lbl_talla;
    private javax.swing.JTable tabla_producto;
    private javax.swing.JTextField txt_cant;
    private javax.swing.JTextField txt_codigo_busqueda;
    private javax.swing.JLabel txt_usuario;
    // End of variables declaration//GEN-END:variables
}