/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanpedroproyect;

import Class.Cliente;
import Class.Operaciones;
import Class.Reporte_Operaciones;
import Class.SepararPrenda;
import DATABASE.ConnectionDB;
import com.sun.glass.events.KeyEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import static sanpedroproyect.GUI_Factura.codigo_cliente;
import static sanpedroproyect.GUI_Factura.total;
import static sanpedroproyect.Ingreso_Nuevo_Cliente.Nombre;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Abonar extends javax.swing.JFrame {


    /**
     * Creates new form Modificar_Eliminar_Cliente
     */
    Operaciones op = new Operaciones();
    SepararPrenda sp = new SepararPrenda();
    Reporte_Operaciones rep = new Reporte_Operaciones();
    DefaultTableModel m = new DefaultTableModel();
    private String saldo,saldo_nuevo;
    static int id_separado;
    Double saldo_g,abono, tot;
    double x;
    String stotal;
    int USUARIO;
    Main_Menu menu_Cod = new Main_Menu();
    public Abonar() {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        txt_abonar.setEditable(false);
        Ingreso_Nuevo_Cliente.validarCedula(txt_cedula);
        USUARIO = menu_Cod.getCodigo_usuario();
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
                           String sql = ("SELECT * FROM cliente where Nombre = ?");
                           pst = cn.prepareStatement(sql);
                           pst.setString(1, cadena);
                           rs =pst.executeQuery();
                           if (rs.next()){
                               codigo_cliente = rs.getInt("id_Cliente");
                               txt_cedula.setText(rs.getString("Cedula"));
                           }



                        } catch (Exception ex){
                            System.out.println(ex);
                        }
                    m = rep.consultar_Saldos_cliente(codigo_cliente);
                    tabla_producto.setModel(m);
                    tabla_producto.getTableHeader().setFont(new java.awt.Font("Tahoma", Font.BOLD, 18)); 
                    //tabla_producto.setFont(new java.awt.Font("Tahoma", 0, 18)); 
                    Dialog_buscar_pro.setSize(1125,500);
                    Dialog_buscar_pro.setLocationRelativeTo(null);
                    Dialog_buscar_pro.setVisible(true);
                    txt_abonar.setEditable(true);
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
        txt_abonar.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
               saldo_g = (Double.parseDouble(txt_saldo.getText()));
               abono =Double.parseDouble(txt_abonar.getText());
               tot = (saldo_g - abono) ;
               //saldo_nuevo = (String.valueOf(tot));
               stotal = String.format(java.util.Locale.US,"%.2f", tot);
               txt_nuevo_saldo.setText(stotal);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(txt_abonar.getText().isEmpty()){
                    abono = 0.0;
                }else{
                   abono = (Double.parseDouble(txt_abonar.getText()));
                }
                saldo_g = (Double.parseDouble(txt_saldo.getText()));

                tot = (saldo_g - abono) ;
                stotal = String.format(java.util.Locale.US,"%.2f", tot);
                txt_nuevo_saldo.setText(stotal);
                if(txt_abonar.getText().isEmpty()){
                    txt_nuevo_saldo.setText(txt_saldo.getText());
                }
 
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

        Dialog_buscar_pro = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_producto = new javax.swing.JTable();
        btn_agregar_producto = new javax.swing.JButton();
        btn_SalirProducto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbx_Nombre = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btn_salirAbonar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_saldo = new javax.swing.JTextField();
        txt_nuevo_saldo = new javax.swing.JTextField();
        txt_abonar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblprenda = new javax.swing.JLabel();
        lbltalla = new javax.swing.JLabel();
        btn_guardarproducto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_cedula = new javax.swing.JTextField();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setText("Seleccione Prenda");

        tabla_producto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
        jScrollPane2.setViewportView(tabla_producto);

        btn_agregar_producto.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_agregar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anadir.png"))); // NOI18N
        btn_agregar_producto.setText("AGREGAR");
        btn_agregar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_productoActionPerformed(evt);
            }
        });

        btn_SalirProducto.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_SalirProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btn_SalirProducto.setText("SALIR");
        btn_SalirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(397, 397, 397))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(317, Short.MAX_VALUE)
                .addComponent(btn_agregar_producto)
                .addGap(120, 120, 120)
                .addComponent(btn_SalirProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_SalirProducto)
                    .addComponent(btn_agregar_producto))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Dialog_buscar_proLayout = new javax.swing.GroupLayout(Dialog_buscar_pro.getContentPane());
        Dialog_buscar_pro.getContentPane().setLayout(Dialog_buscar_proLayout);
        Dialog_buscar_proLayout.setHorizontalGroup(
            Dialog_buscar_proLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_buscar_proLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Dialog_buscar_proLayout.setVerticalGroup(
            Dialog_buscar_proLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_buscar_proLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel2.setText("Cliente");

        cbx_Nombre.setEditable(true);
        cbx_Nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbx_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_NombreActionPerformed(evt);
            }
        });
        cbx_Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cbx_NombreKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 36)); // NOI18N
        jLabel1.setText("ABONAR");

        btn_salirAbonar.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_salirAbonar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_salirAbonar.setText("Salir");
        btn_salirAbonar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salirAbonar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salirAbonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirAbonarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel4.setText("Cantidad a Abonar");

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel5.setText("Saldo");

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel6.setText("Nuevo Saldo");

        txt_saldo.setEditable(false);
        txt_saldo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_nuevo_saldo.setEditable(false);
        txt_nuevo_saldo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_abonar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_abonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_abonarKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel7.setText("Prenda");

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel8.setText("Talla");

        lblprenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbltalla.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_guardarproducto.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_guardarproducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible (1).png"))); // NOI18N
        btn_guardarproducto.setText("Guardar abono");
        btn_guardarproducto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardarproducto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarproductoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel9.setText("Cédula");

        txt_cedula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cedulaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_nuevo_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_abonar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(108, 108, 108)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbx_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(105, 105, 105)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_cedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblprenda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbltalla, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(btn_guardarproducto)
                        .addGap(110, 110, 110)
                        .addComponent(btn_salirAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 200, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(339, 339, 339))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbx_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(lblprenda, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltalla, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_abonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_nuevo_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_guardarproducto)
                    .addComponent(btn_salirAbonar))
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirAbonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirAbonarActionPerformed
        //System.exit(0);
        /*Main_Menu ventana_menuPrincipal = new Main_Menu();
        ventana_menuPrincipal.setVisible(true);
        ventana_menuPrincipal.setLocationRelativeTo(null);
        ventana_menuPrincipal.setResizable(false);*/
        dispose();
    }//GEN-LAST:event_btn_salirAbonarActionPerformed

    private void cbx_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_NombreActionPerformed

    private void btn_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_productoActionPerformed
        // TODO add your handling code here:
        int fsel = tabla_producto.getSelectedRow();
        try {
            String codigo, descripcion, precio, cantidad,importe,talla;
            
            double calcula = 0.0 , iva = 0.0;

            if(fsel==-1){
                JOptionPane.showMessageDialog(null,"Dese seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
            else{
                m = (DefaultTableModel) tabla_producto.getModel();
                id_separado = Integer.parseInt(tabla_producto.getValueAt(fsel, 0).toString());
                codigo = tabla_producto.getValueAt(fsel, 1).toString();
                descripcion = tabla_producto.getValueAt(fsel, 2).toString();
                talla = tabla_producto.getValueAt(fsel, 3).toString();
                saldo = tabla_producto.getValueAt(fsel, 6).toString();
                Dialog_buscar_pro.setVisible(false);
                
                x = (Double.parseDouble(saldo)) ;
                txt_saldo.setText(saldo);
                lbltalla.setText(talla);
                lblprenda.setText(descripcion);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_agregar_productoActionPerformed

    private void btn_SalirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirProductoActionPerformed
        Dialog_buscar_pro.dispose();
    }//GEN-LAST:event_btn_SalirProductoActionPerformed

    private void btn_guardarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarproductoActionPerformed
        // TODO add your handling code here:
        sp.Ingreso_nuevo_saldo(id_separado,Float.parseFloat(txt_nuevo_saldo.getText()));
        sp.Guardar_Abono(id_separado,Float.parseFloat(txt_abonar.getText()),1);//falta agregar USUARIO
         
        JOptionPane.showMessageDialog(null, "ABONO INGRESADO CORRECTAMENTE" , "ABONO INGRESADO" , JOptionPane.INFORMATION_MESSAGE);
        
        
    }//GEN-LAST:event_btn_guardarproductoActionPerformed

    private void txt_abonarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_abonarKeyTyped
        // TODO add your handling code here:
       char c = evt.getKeyChar();
       if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACKSPACE) && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txt_abonar.getText().contains(".")) {
            evt.consume();
        }

    }//GEN-LAST:event_txt_abonarKeyTyped

    private void cbx_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_NombreKeyTyped
        // TODO add your handling code here:
         
        char c=evt.getKeyChar(); 

          if(Character.isDigit(c)) { 
              getToolkit().beep(); 
              evt.consume(); 
              JOptionPane.showMessageDialog(null, "INGRESE SOLO LETRAS" , "ERROR" , JOptionPane.ERROR_MESSAGE); 
               
          } 
    }//GEN-LAST:event_cbx_NombreKeyTyped

    private void txt_cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cedulaKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String cedu = txt_cedula.getText().toString();
            System.out.println("Aplasto ENter");
            cbx_Nombre.removeAllItems();
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
                       codigo_cliente = rs.getInt("id_Cliente");
                       txt_cedula.setText(rs.getString("Cedula"));
                   }
                } catch (Exception ex){
                    System.out.println(ex);
                }
                System.out.println(cbx_Nombre.getEditor().getItem());
                if(cbx_Nombre.getEditor().getItem()!=""){
                    m = rep.consultar_Saldos_cliente(codigo_cliente);
                    tabla_producto.setModel(m);
                    Dialog_buscar_pro.setSize(500, 500);
                    Dialog_buscar_pro.setLocationRelativeTo(null);
                    Dialog_buscar_pro.setVisible(true);
                    txt_abonar.setEditable(true); 
                }else{
                    JOptionPane.showMessageDialog(null, "NO EXISTE CLIENTE CON CEDULA INGRESADA" , "ERROR" , JOptionPane.ERROR_MESSAGE); 
                }
                
        }
    }//GEN-LAST:event_txt_cedulaKeyReleased

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_buscar_pro;
    private javax.swing.JButton btn_SalirProducto;
    private javax.swing.JButton btn_agregar_producto;
    private javax.swing.JButton btn_guardarproducto;
    private javax.swing.JButton btn_salirAbonar;
    private javax.swing.JComboBox cbx_Nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblprenda;
    private javax.swing.JLabel lbltalla;
    private javax.swing.JTable tabla_producto;
    private javax.swing.JTextField txt_abonar;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextField txt_nuevo_saldo;
    private javax.swing.JTextField txt_saldo;
    // End of variables declaration//GEN-END:variables
}
