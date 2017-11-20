/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanpedroproyect;

import Class.Factura;
import Class.Inventario;
import Class.Operaciones;
import Class.Prenda;
import Class.Productos;
import Class.Reporte_Operaciones;
import DATABASE.ConnectionDB;
import com.sun.glass.events.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.PrinterJob;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import static sanpedroproyect.GUI_Factura.total;

/**
 *
 * @author Pantheon
 */
public class GUI_Factura extends javax.swing.JFrame implements Printable{
    
    static Integer Codigo;
    Factura factura = new Factura();
    Inventario inv = new Inventario();
    Operaciones op = new Operaciones();
    DefaultTableModel m = new DefaultTableModel();
    Reporte_Operaciones rep = new Reporte_Operaciones();
    static double total = 0;
    double sub_total = 0.0;
    double iva = 0;
    double desc = 0;
    String stotal,stotal_i,stotal_v;
    double  sub_total_i= 0;
    double total_i = 0;
    double vaucher= 0;
    double  sub_total_v= 0;
    double total_v = 0;
    int USUARIO;
    Main_Menu menu_Cod = new Main_Menu();
    static int codigo_cliente, id_estado;
    static float subtotal_static,Descuento_static,Voucher_static,Iva_static,Total_static;
    double efectivo;
    double tarjeta;
    double subpago,subpago_v;
    double cambio;
    String pago_cliente,pago_cliente_v;
    double total_encontrado;


    /**
     * Creates new form GUI_Factura
     */
    int id_factura_actual , id_sumada;
    Date date = new Date ();
    
    public GUI_Factura() {
        initComponents();
        System.out.println(Login.rol_usuario);
        //btn_guardar_fact.setEnabled(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        id_factura_actual= factura.Get_last_id_factura();
        id_sumada = (id_factura_actual + 1 );
        txt_numFactura.setText(String.valueOf(id_sumada));
        txt_vendedor.setText(menu_Cod.getNombre_usuario());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        txt_fecha.setText(dia);
        //lbl_vaucher.setVisible(false);
        //txt_vaucher.setVisible(false);
        USUARIO = menu_Cod.getCodigo_usuario();
        btn_imprimir.setToolTipText("Antes de Imprimir, Guarde la Factura");
        btn_imprimir.setEnabled(false);
        lbl_efectivo.setEnabled(false);
        txt_vaucher_pago.setEnabled(false);
        lbl_pagovaucher.setEnabled(false);
        txt_efectivo.setEnabled(false);
        btn_guardar_fact.setEnabled(false);
        txt_descto.setEnabled(false);
        verificar_Permisos();
        
        
     
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
                               txt_dir.setText(rs.getString("Direccion"));
                               txt_mail.setText(rs.getString("Correo"));
                               txt_telefono.setText(rs.getString("Telefono"));
                          
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
        txt_descto.getDocument().addDocumentListener(new DocumentListener() {
        
            @Override
            public void insertUpdate(DocumentEvent e) {
                
                if(cmb_descuento.getSelectedItem().equals("$")){
                     desc = (Double.parseDouble(txt_descto.getText()));
                     sub_total =Double.parseDouble(txt_subtotal.getText());
                     total = sub_total - desc ;
                     stotal = String.format(java.util.Locale.US,"%.2f", total);
                     txt_total.setText(stotal);
                }else{
                
                desc = (Double.parseDouble(txt_descto.getText()));
                sub_total =Double.parseDouble(txt_subtotal.getText());
                total = sub_total - ((sub_total * desc)/100) ;
                stotal = String.format(java.util.Locale.US,"%.2f", total);
                txt_total.setText(stotal);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(cmb_descuento.getSelectedItem().equals("$")){
                    
                    if(txt_descto.getText().isEmpty()){
                        desc = 0;
                    }else{
                       desc = (Double.parseDouble(txt_descto.getText()));
                    }
                    sub_total =Double.parseDouble(txt_subtotal.getText());
                    total = sub_total - desc ;
                    stotal = String.format(java.util.Locale.US,"%.2f", total);
                    txt_total.setText(stotal);

                    if(txt_descto.getText().isEmpty()){
                        txt_total.setText(txt_subtotal.getText());
                    }
                    
                }else{

                    if(txt_descto.getText().isEmpty()){
                        desc = 0;
                    }else{
                       desc = (Double.parseDouble(txt_descto.getText()));
                    }
                    sub_total =Double.parseDouble(txt_subtotal.getText());
                    total = sub_total - ((sub_total * desc)/100);
                    stotal = String.format(java.util.Locale.US,"%.2f", total);
                    txt_total.setText(stotal);
                    if(txt_descto.getText().isEmpty()){
                        txt_total.setText(txt_subtotal.getText());
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
              
            } ); 
        
        
        txt_iva.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                                
                iva = (Double.parseDouble(txt_iva.getText()));
                sub_total_i = total;
                total_i = sub_total_i + ((sub_total_i * iva)/100) ;
                stotal_i = String.format(java.util.Locale.US,"%.2f", total_i);
                txt_total.setText(stotal_i);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                    if(txt_iva.getText().isEmpty()){
                        iva = 0;
                    }else{
                       iva= (Double.parseDouble(txt_iva.getText()));
                    }
                     sub_total_i = total;
                     total_i = sub_total_i + ((sub_total_i * iva)/100) ;
                     stotal_i = String.format(java.util.Locale.US,"%.2f", total_i);
                     txt_total.setText(stotal_i);
                    if(txt_iva.getText().isEmpty()){
                        stotal = String.format(java.util.Locale.US,"%.2f", total);
                        txt_total.setText(stotal);
                    }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        txt_vaucher.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                vaucher = (Double.parseDouble(txt_vaucher.getText()));
                sub_total_v = (Double.parseDouble(txt_total.getText()));
                total_v = sub_total_v + vaucher ;
                stotal_v = String.format(java.util.Locale.US,"%.2f", total_v);
                txt_total.setText(stotal_v);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                    if(txt_vaucher.getText().isEmpty()){
                        vaucher = 0;
                    }else{
                       vaucher= (Double.parseDouble(txt_vaucher.getText()));
                    }
                     sub_total_v = (Double.parseDouble(txt_total.getText()));
                     total_v = sub_total_v + vaucher ;
                     stotal_v = String.format(java.util.Locale.US,"%.2f", total_v);
                     txt_total.setText(stotal_v);
                    if(txt_vaucher.getText().isEmpty()){
                         if(txt_iva.getText().isEmpty()){
                            stotal = String.format(java.util.Locale.US,"%.2f", total);
                            txt_total.setText(stotal);
                            }
                         else{
                             stotal_i = String.format(java.util.Locale.US,"%.2f", total_i);
                             txt_total.setText(stotal_i);
                         }
                    }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        /*txt_efectivo.getDocument().addDocumentListener(new DocumentListener() {
        
            @Override
            public void insertUpdate(DocumentEvent e) {
     
                efectivo = (Double.parseDouble(txt_efectivo.getText()));
                total_encontrado =Double.parseDouble(txt_total.getText());
                subpago = total_encontrado - efectivo ;
                pago_cliente = String.format(java.util.Locale.US,"%.2f", subpago);
                total_pagado.setText(pago_cliente);
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
 

                if(txt_efectivo.getText().isEmpty()){
                    efectivo = 0;
                }else{
                    efectivo = (Double.parseDouble(txt_efectivo.getText()));
                }
                    sub_total =Double.parseDouble(txt_subtotal.getText());
                    total_encontrado =Double.parseDouble(txt_total.getText());
                    subpago = total_encontrado - efectivo ;
                    pago_cliente = String.format(java.util.Locale.US,"%.2f", subpago);
                        total_pagado.setText(pago_cliente);
                if(txt_efectivo.getText().isEmpty()){
                        total_pagado.setText(txt_efectivo.getText());
                    }
                }
            

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
              
            } ); 
        txt_vaucher_pago.getDocument().addDocumentListener(new DocumentListener() {
        
            @Override
            public void insertUpdate(DocumentEvent e) {
     
                tarjeta = (Double.parseDouble(txt_vaucher_pago.getText()));
                total_encontrado =Double.parseDouble(total_pagado.getText());
                subpago_v = total_encontrado - tarjeta ;
                pago_cliente_v = String.format(java.util.Locale.US,"%.2f", subpago_v);
                total_pagado.setText(pago_cliente_v);
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
 

                if(txt_vaucher_pago.getText().isEmpty()){
                    tarjeta = 0;
                }else{
                    tarjeta = (Double.parseDouble(txt_vaucher_pago.getText()));
                }
                    sub_total =Double.parseDouble(txt_subtotal.getText());
                    total_encontrado =Double.parseDouble(total_pagado.getText());
                    subpago_v = total_encontrado - tarjeta ;
                    pago_cliente_v = String.format(java.util.Locale.US,"%.2f", subpago_v);
                    total_pagado.setText(pago_cliente_v);
                if(txt_vaucher_pago.getText().isEmpty()){
                        total_pagado.setText(txt_vaucher_pago.getText());
                    }
                }
            

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
              
            } ); 
        
        
     
        */
        
    }
    private void verificar_Permisos(){
        if(Login.rol_usuario == 1){
            txt_descto.setEnabled(true);
        }
        int i;
        for(i=0;i<Login.permisos_usuario.length;i++){
            if(Login.permisos_usuario[i].equals("RealizarDescuentos")){
                txt_descto.setEnabled(true);
            }/*else if(permisos_usuario[i].equals("Facturar")){
                btn_go_registroFactura.setEnabled(true);
                itemFactura_nuevaFactura.setEnabled(true);
            }else if(permisos_usuario[i].equals("Anular")){
                btn_go_anulaciones.setEnabled(true);
                itemFactura_verFacturas.setEnabled(true);
            }else if(permisos_usuario[i].equals("GenerarReportesCierreDeCaja")){
                btn_go_cierreCaja.setEnabled(true);
            }else if(permisos_usuario[i].equals("IngresoInventario")){
                btn_go_inventario.setEnabled(true);
                itemInventario_ingresoInventario.setEnabled(true);
            }else if(permisos_usuario[i].equals("RegistrarCliente")){
                btn_go_RegistroCliente.setEnabled(true);
                itemCliente_registrarCliente.setEnabled(true);
            }else if(permisos_usuario[i].equals("ModificarCliente")){
                itemCliente_modificar_eliminar.setEnabled(true);
            }else if(permisos_usuario[i].equals("IngresarProducto")){
                itemProducto_ingresarProducto.setEnabled(true);
            }*/

        }
    }
    
    public Integer getCodigo() {
        return Codigo;
    }

    public void setCodigo(Integer Codigo) {
        this.Codigo = Codigo;
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
        cmb_producto = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_producto = new javax.swing.JTable();
        btn_agregar_producto = new javax.swing.JButton();
        btn_SalirProducto = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txt_can = new javax.swing.JTextField();
        cmb_CodoPre = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_numFactura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        Factura_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_mail = new javax.swing.JTextField();
        txt_cedula = new javax.swing.JTextField();
        txt_vendedor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Combo_FORMA_PAGO = new javax.swing.JComboBox<>();
        btn_consumidor_final = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_dir = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        cbx_Nombre = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_descto = new javax.swing.JTextField();
        lbl_vaucher = new javax.swing.JLabel();
        txt_vaucher = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_iva = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_nota = new javax.swing.JTextArea();
        btn_imprimir = new javax.swing.JButton();
        btn_Limpiar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla_ventas = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        btn_guardar_fact = new javax.swing.JButton();
        btn_nueva_f = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        cmb_descuento = new javax.swing.JComboBox();
        lbl_pagovaucher = new javax.swing.JLabel();
        lbl_efectivo = new javax.swing.JLabel();
        txt_efectivo = new javax.swing.JTextField();
        txt_vaucher_pago = new javax.swing.JTextField();
        btn_realizar_pago = new javax.swing.JButton();

        Dialog_buscar_pro.setTitle("Buscar Producto");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        cmb_producto.setEditable(true);

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

        btn_agregar_producto.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_agregar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anadir.png"))); // NOI18N
        btn_agregar_producto.setText("AGREGAR");
        btn_agregar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_productoActionPerformed(evt);
            }
        });

        btn_SalirProducto.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_SalirProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btn_SalirProducto.setText("SALIR");
        btn_SalirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirProductoActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel18.setText("Cantidad");

        cmb_CodoPre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione Busqueda>", "Descripcion", "Codigo" }));
        cmb_CodoPre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_CodoPreItemStateChanged(evt);
            }
        });
        cmb_CodoPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_CodoPreActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel19.setText("Busqueda Por :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addGap(26, 26, 26)
                        .addComponent(txt_can, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btn_agregar_producto)
                        .addGap(34, 34, 34)
                        .addComponent(btn_SalirProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_CodoPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(cmb_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_CodoPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(54, 54, 54)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_agregar_producto)
                    .addComponent(btn_SalirProducto))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout Dialog_buscar_proLayout = new javax.swing.GroupLayout(Dialog_buscar_pro.getContentPane());
        Dialog_buscar_pro.getContentPane().setLayout(Dialog_buscar_proLayout);
        Dialog_buscar_proLayout.setHorizontalGroup(
            Dialog_buscar_proLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Dialog_buscar_proLayout.setVerticalGroup(
            Dialog_buscar_proLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setText("Factura Num.");

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setText("Fecha");

        txt_fecha.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_numFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_numFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        Factura_panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setText("Cliente");

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel4.setText("Dirección");

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel5.setText("Cédula/RUC");

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel6.setText("Vendedor");

        txt_mail.setEditable(false);

        txt_cedula.setEditable(false);

        txt_vendedor.setEditable(false);
        txt_vendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vendedorActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel8.setText("Forma De Pago");

        Combo_FORMA_PAGO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"" ,  "Efectivo", "Tarjeta Credito", "Tarjeta Débito","Mixto"}));
        Combo_FORMA_PAGO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Combo_FORMA_PAGOItemStateChanged(evt);
            }
        });
        Combo_FORMA_PAGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_FORMA_PAGOActionPerformed(evt);
            }
        });

        btn_consumidor_final.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_consumidor_final.setText("Consumidor Final");
        btn_consumidor_final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consumidor_finalActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel9.setText("Mail");

        txt_dir.setEditable(false);

        jLabel16.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel16.setText("Telefono");

        txt_telefono.setEditable(false);

        cbx_Nombre.setEditable(true);

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel10.setText("Subtotal");

        txt_subtotal.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel11.setText("Descuento");

        txt_descto.setText("0");
        txt_descto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_desctoActionPerformed(evt);
            }
        });
        txt_descto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_desctoKeyTyped(evt);
            }
        });

        lbl_vaucher.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_vaucher.setText("Vaucher");

        txt_vaucher.setText("0");
        txt_vaucher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_vaucherKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel15.setText("I.V.A");

        txt_iva.setText("0");
        txt_iva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ivaKeyTyped(evt);
            }
        });

        txt_total.setEditable(false);

        jLabel13.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel13.setText("Total");

        jLabel14.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel14.setText("NOTA :");

        txt_nota.setColumns(20);
        txt_nota.setRows(5);
        jScrollPane1.setViewportView(txt_nota);

        btn_imprimir.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/impresora.png"))); // NOI18N
        btn_imprimir.setText("Imprimir");
        btn_imprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_imprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirActionPerformed(evt);
            }
        });

        btn_Limpiar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/escoba.png"))); // NOI18N
        btn_Limpiar.setText("Limpiar");
        btn_Limpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Limpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });

        btn_Salir.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_Salir.setText("Salir");
        btn_Salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        Tabla_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "TALLA", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
        jScrollPane3.setViewportView(Tabla_ventas);

        jButton5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tienda-online.png"))); // NOI18N
        jButton5.setText("Agregar Producto");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btn_guardar_fact.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_guardar_fact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible (1).png"))); // NOI18N
        btn_guardar_fact.setText("Guardar Factura");
        btn_guardar_fact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar_fact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_factActionPerformed(evt);
            }
        });

        btn_nueva_f.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_nueva_f.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/recepcion.png"))); // NOI18N
        btn_nueva_f.setText("Nueva Factura");
        btn_nueva_f.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_nueva_f.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_nueva_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nueva_fActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-x.png"))); // NOI18N
        jButton6.setText("Quitar Producto");
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        cmb_descuento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "$", "%" }));

        lbl_pagovaucher.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_pagovaucher.setText("Pago con Tarjeta");

        lbl_efectivo.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_efectivo.setText("Pago con Efectivo");

        txt_efectivo.setText("0");
        txt_efectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_efectivoKeyTyped(evt);
            }
        });

        txt_vaucher_pago.setText("0");
        txt_vaucher_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vaucher_pagoActionPerformed(evt);
            }
        });
        txt_vaucher_pago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_vaucher_pagoKeyTyped(evt);
            }
        });

        btn_realizar_pago.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_realizar_pago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/proyecto-de-ley.png"))); // NOI18N
        btn_realizar_pago.setText("Calcular");
        btn_realizar_pago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_realizar_pago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_realizar_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_realizar_pagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Factura_panelLayout = new javax.swing.GroupLayout(Factura_panel);
        Factura_panel.setLayout(Factura_panelLayout);
        Factura_panelLayout.setHorizontalGroup(
            Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addGap(34, 34, 34)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_vendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(cbx_Nombre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Factura_panelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btn_consumidor_final)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addGroup(Factura_panelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Factura_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(Combo_FORMA_PAGO, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Factura_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_telefono))
                                    .addGroup(Factura_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(39, 39, 39)
                                        .addComponent(txt_mail, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(448, 448, 448))
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Factura_panelLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(431, 431, 431))
                        .addGroup(Factura_panelLayout.createSequentialGroup()
                            .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Factura_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(11, 11, 11))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(jLabel15)
                                .addComponent(lbl_vaucher)
                                .addComponent(lbl_pagovaucher)
                                .addComponent(lbl_efectivo))
                            .addGap(27, 27, 27)
                            .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(Factura_panelLayout.createSequentialGroup()
                                    .addComponent(txt_descto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmb_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txt_vaucher, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(Factura_panelLayout.createSequentialGroup()
                                    .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_vaucher_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(69, 69, 69)
                                    .addComponent(btn_realizar_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Factura_panelLayout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Factura_panelLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(64, 64, 64)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)))
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btn_guardar_fact)
                        .addGap(58, 58, 58)
                        .addComponent(btn_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Factura_panelLayout.createSequentialGroup()
                        .addComponent(btn_nueva_f, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btn_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        Factura_panelLayout.setVerticalGroup(
            Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btn_consumidor_final)
                    .addComponent(cbx_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(Combo_FORMA_PAGO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Factura_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_descto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)))
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_imprimir)
                            .addComponent(btn_guardar_fact))
                        .addGap(81, 81, 81)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_Limpiar)
                            .addComponent(btn_nueva_f)
                            .addComponent(btn_Salir)))
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txt_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_vaucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_vaucher))
                        .addGap(10, 10, 10)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Factura_panelLayout.createSequentialGroup()
                                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_pagovaucher)
                                    .addComponent(txt_vaucher_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_efectivo)
                                    .addComponent(txt_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btn_realizar_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Factura_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Factura_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirActionPerformed
        //prenda = Factura.cargar_Productos();
        //Factura.cargar_Productos();
        priceInvoice();
       /*  Map<String , Object> parameters = new HashMap(); 
        parameters.put("CAJERO", "KLEBER");
        ArrayList<Productos> plist = new ArrayList<>();
        
        
        try {
            String source ="/Users/kleberstevendiazcoello/Documents/GitHub/Sampedro-SA/SamPedroProyect/src/sanpedroproyect/FACTURA_IMPRIMIR.jrxml";
            //JasperReport jr = null;
            InputStream is = (InputStream)this.getClass().getClassLoader().getResourceAsStream("sanpedroproyect/FACTURA_IMPRIMIR.jrxml");
            JasperDesign design = JRXmlLoader.load(is);
            //jr = (JasperReport) JRLoader.loadObject(source);
            JasperReport jcm = JasperCompileManager.compileReport(design);
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
            JasperPrint jp = JasperFillManager.fillReport(jcm ,parameters ,jcs);
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
            //jv.setTitle(source);
            
            
            
        } catch (JRException ex) {
            Logger.getLogger(GUI_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
     */
    }//GEN-LAST:event_btn_imprimirActionPerformed

    private void btn_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_productoActionPerformed
        // TODO add your handling code here:
        int fsel = tabla_producto.getSelectedRow();
        try {
            String codigo, descripcion, precio, cantidad,importe,talla;
            float x;
            float calcula;
            float iva;
            
            if(fsel==-1){
                JOptionPane.showMessageDialog(null,"Dese seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
            else if(!(txt_can.getText().isEmpty())){
                m = (DefaultTableModel) tabla_producto.getModel();
                codigo = tabla_producto.getValueAt(fsel, 0).toString();
                descripcion = tabla_producto.getValueAt(fsel, 1).toString();
                precio = tabla_producto.getValueAt(fsel, 2).toString();
                talla = tabla_producto.getValueAt(fsel, 3).toString();
                cantidad = txt_can.getText();
                Dialog_buscar_pro.setVisible(false);
                 x = (Float.parseFloat(precio)) * Integer.parseInt(cantidad);
                 importe = String.format(java.util.Locale.US,"%.2f", x);
                 m = (DefaultTableModel) Tabla_ventas.getModel();
                 String filaElemento[] = {
                     codigo,descripcion,talla,cantidad,precio,importe
                 };
                 m.addRow(filaElemento);
                 calcula = (Float.parseFloat(importe));
                 sub_total = sub_total + calcula;
                 //iva = total * (0.12);
                 
                 txt_subtotal.setText(String.format(java.util.Locale.US,"%.2f", sub_total));
                 txt_total.setText(String.format(java.util.Locale.US,"%.2f", sub_total));
                 
            }else{
                JOptionPane.showMessageDialog(null,"AGREGE LA CANTIDAD","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btn_agregar_productoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        m = rep.consultar_producto();
        tabla_producto.setModel(m);
        Dialog_buscar_pro.setSize(700, 500);
        Dialog_buscar_pro.setLocationRelativeTo(null);
        Dialog_buscar_pro.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        // TODO add your handling code here:
        /*
        id_factura_actual= factura.Get_last_id_factura();
        id_sumada = (id_factura_actual + 1 );
        txt_numFactura.setText(String.valueOf(id_sumada));
        txt_cedula.setText("");
        txt_dir.setText("");
        txt_can.setText("");
        txt_mail.setText("");
        txt_subtotal.setText("");
        sub_total = 0;
        txt_telefono.setText("");
        txt_descto.setText("");
        txt_iva.setText("");
        txt_vaucher.setText("");
        txt_vaucher_pago.setText("");
        txt_efectivo.setText("");
        txt_total.setText("");
        txt_nota.setText("");
        cbx_Nombre.removeAllItems();
        Tabla_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "TALLA", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));*/
        limpiar();
        
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_SalirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirProductoActionPerformed
        Dialog_buscar_pro.dispose();
    }//GEN-LAST:event_btn_SalirProductoActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        //System.exit(0); 
        /*Main_Menu ventana_menuPrincipal = new Main_Menu();
        ventana_menuPrincipal.setVisible(true);
        ventana_menuPrincipal.setLocationRelativeTo(null);
        ventana_menuPrincipal.setResizable(false);*/
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_consumidor_finalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consumidor_finalActionPerformed
        // TODO add your handling code here:
            String resul = null , lats = null;
                        ConnectionDB cc = new ConnectionDB();
                        Connection cn = cc.getConnection();
                        PreparedStatement pst =null;
                        ResultSet rs = null;
                        String Desc;
                        try{
                           String sql = ("SELECT * FROM cliente where Nombre = ?");
                           pst = cn.prepareStatement(sql);
                           pst.setString(1, "Consumidor Final");
                           rs =pst.executeQuery();
                           if (rs.next()){
                               cbx_Nombre.getEditor().setItem(rs.getString("Nombre"));
                               txt_cedula.setText(rs.getString("Cedula"));
                               txt_dir.setText(rs.getString("Direccion"));
                               txt_mail.setText(rs.getString("Correo"));
                               txt_telefono.setText(rs.getString("Telefono"));
                          
                           }
                            



                        } catch (Exception ex){
                            System.out.println(ex);
                        }
                        cc.desconectar();
    }//GEN-LAST:event_btn_consumidor_finalActionPerformed

    private void btn_guardar_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_factActionPerformed
        // TODO add your handling code here:
        if(!txt_subtotal.getText().equals("")){
        DefaultTableModel order_list_guardar = new DefaultTableModel();
        subtotal_static = (Float.parseFloat(txt_subtotal.getText()));
        Descuento_static = (Float.parseFloat(txt_descto.getText()));
        Total_static = (Float.parseFloat(txt_total.getText()));
        int i = 0;
        String codigo_a_guardar;
        int cantidad = 0 ;
        int codigo_obtenido;
        double pago_efectivo, pago_tarjeta;
        pago_efectivo = Double.parseDouble(txt_efectivo.getText());
        pago_tarjeta = Double.parseDouble(txt_vaucher_pago.getText());
        String s = factura.Guardar_Factura(id_sumada,USUARIO,pago_efectivo,pago_tarjeta);     
        System.out.println("" + s);
        
        codigo_obtenido = factura.Get_last_id_factura();
        System.out.println("" + codigo_obtenido);
        
        
        /*Neceista el id para guardar todos los productos de una factura*/
        order_list_guardar = (DefaultTableModel) Tabla_ventas.getModel();
        int numero_filas = order_list_guardar.getRowCount();
            for(i=0;i<numero_filas;i++){
                int cantidad_actual = 0;
                int nueva_cantidad = 0;
                codigo_a_guardar = Tabla_ventas.getValueAt(i, 0).toString();
                cantidad = Integer.parseInt(Tabla_ventas.getValueAt(i, 3).toString());
                factura.Guardar_Detalle_Factura(codigo_obtenido, codigo_a_guardar, cantidad); 
                inv.Decremento_inventario(codigo_a_guardar,cantidad);
                cantidad_actual = inv.get_cantidad_total_producto(codigo_a_guardar);
                nueva_cantidad = (cantidad_actual - cantidad) ;
                inv.Incremeneto_total_producto(codigo_a_guardar , nueva_cantidad );
                

            }
        
        JOptionPane.showMessageDialog(null, "Factura Ingresada Correcatemente" , "Guardado Exitoso" , JOptionPane.INFORMATION_MESSAGE);
        btn_imprimir.setEnabled(true);
        
        }else{
           JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS" , "ERROR AL GUARDAR" , JOptionPane.ERROR_MESSAGE); 
        }
       
        
    }//GEN-LAST:event_btn_guardar_factActionPerformed

    private void txt_vendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_vendedorActionPerformed

    private void btn_nueva_fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nueva_fActionPerformed
        // TODO add your handling code here:
        limpiar();
        btn_imprimir.setEnabled(false);
        id_factura_actual= factura.Get_last_id_factura();
        id_sumada = (id_factura_actual + 1 );
        txt_numFactura.setText(String.valueOf(id_sumada));
        txt_vendedor.setText(menu_Cod.getNombre_usuario());
        
    }//GEN-LAST:event_btn_nueva_fActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        float x, subtotal,importe,subtotal_actual;
        int fsel; 
        int resp;
        int fil;
        try {
            fsel = Tabla_ventas.getSelectedRow();
            if(fsel == -1 ){
                JOptionPane.showMessageDialog(null,"Debe Seleccionar Producto a Eliminar","Advertencia",JOptionPane.WARNING_MESSAGE);
                
            }else{
                resp = JOptionPane.showConfirmDialog(null, "¿Está Seguro de Quitar el Producto?","Eliminar",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    importe = Float.parseFloat(Tabla_ventas.getValueAt(fsel,5).toString());
                    subtotal_actual = (Float.parseFloat(txt_subtotal.getText()) -  importe );
                    txt_subtotal.setText(String.valueOf(subtotal_actual));
                    m = (DefaultTableModel) Tabla_ventas.getModel();
                    m.removeRow(fsel);
                    
                    
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se realizo correctamente la accion de quitar" + e,"Advertencia",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void Combo_FORMA_PAGOItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Combo_FORMA_PAGOItemStateChanged
        // TODO add your handling code here:
        if(Combo_FORMA_PAGO.getSelectedItem().equals("")){
            lbl_efectivo.setEnabled(false);
            txt_vaucher_pago.setEnabled(false);
            lbl_pagovaucher.setEnabled(false);
            txt_efectivo.setEnabled(false);
        }
        else if(Combo_FORMA_PAGO.getSelectedItem().equals("Efectivo")){
            lbl_efectivo.setEnabled(true);
            txt_vaucher_pago.setEnabled(false);
            lbl_pagovaucher.setEnabled(false);
            txt_efectivo.setEnabled(true);
            System.out.println("EFECTIVO"); 
        }else if(Combo_FORMA_PAGO.getSelectedItem().equals("Tarjeta Credito")){
            lbl_efectivo.setEnabled(false);
            txt_vaucher_pago.setEnabled(true);
            lbl_pagovaucher.setEnabled(true);
            txt_efectivo.setEnabled(false);
        }else if(Combo_FORMA_PAGO.getSelectedItem().equals("Tarjeta Débito")) {
             System.out.println("Tarjeta Débito");
            lbl_efectivo.setEnabled(false);
            txt_vaucher_pago.setEnabled(true);
            lbl_pagovaucher.setEnabled(true);
            txt_efectivo.setEnabled(false);
        }
        else if(Combo_FORMA_PAGO.getSelectedItem().equals("Mixto")) {
             System.out.println("mixto");
            lbl_efectivo.setEnabled(true);
            txt_vaucher_pago.setEnabled(true);
            lbl_pagovaucher.setEnabled(true);
            txt_efectivo.setEnabled(true);
        }
    }//GEN-LAST:event_Combo_FORMA_PAGOItemStateChanged

    private void txt_vaucher_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vaucher_pagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_vaucher_pagoActionPerformed

    private void Combo_FORMA_PAGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_FORMA_PAGOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Combo_FORMA_PAGOActionPerformed

    private void cmb_CodoPreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_CodoPreItemStateChanged
        // TODO add your handling code here:
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
            }                   
            
        });
            
        }
    }//GEN-LAST:event_cmb_CodoPreItemStateChanged

    private void txt_desctoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_desctoKeyTyped
        // TODO add your handling code here:
       char c = evt.getKeyChar();
       if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACKSPACE) && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txt_descto.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_desctoKeyTyped

    private void txt_ivaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ivaKeyTyped
        // TODO add your handling code here:
               char c = evt.getKeyChar();
       if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACKSPACE) && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txt_iva.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_ivaKeyTyped

    private void txt_vaucherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vaucherKeyTyped
        // TODO add your handling code here:
       char c = evt.getKeyChar();
       if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACKSPACE) && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txt_vaucher.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_vaucherKeyTyped

    private void txt_efectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_efectivoKeyTyped
        // TODO add your handling code here:
       char c = evt.getKeyChar();
       if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACKSPACE) && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txt_efectivo.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_efectivoKeyTyped

    private void txt_vaucher_pagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vaucher_pagoKeyTyped
        // TODO add your handling code here:
       char c = evt.getKeyChar();
       if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACKSPACE) && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txt_vaucher_pago.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_vaucher_pagoKeyTyped

    private void cmb_CodoPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_CodoPreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_CodoPreActionPerformed

    private void btn_realizar_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_realizar_pagoActionPerformed
        // TODO add your handling code here:
        double total, efectivo, tarjeta,cambio,total_pagado,subtot,
                subtot2;
        double tot = 0;
        String scambio;
        total = Double.parseDouble(txt_total.getText());
        System.out.println("" + total);
        efectivo = Double.parseDouble(txt_efectivo.getText());
        tarjeta =  Double.parseDouble(txt_vaucher_pago.getText());
        
        total_pagado = total - tarjeta - efectivo;
        subtot2 =  (total) - (total_pagado);
        System.out.println("" + total_pagado);
        if(total_pagado == 0){
            JOptionPane.showMessageDialog(null, "PAGO COMPLETADO PROCEDA A GUARDAR FACTURA", "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
            btn_guardar_fact.setEnabled(true);
        }else if(total_pagado > 0){
            System.out.println("subtot2" +  subtot2);
            JOptionPane.showMessageDialog(null, "FALTAN PARA COMPLETAR EL PAGO", "INCORRECTO", JOptionPane.WARNING_MESSAGE);
        }else if (total_pagado < 0){
           subtot =  total - tarjeta ;
           cambio = efectivo - subtot ;
           scambio = String.format(java.util.Locale.US,"%.2f", cambio);
           JOptionPane.showMessageDialog(null, "PAGO COMPLETADO PROCEDA A GUARDAR FACTURA, CAMBIO :" +scambio+ "$", "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
           btn_guardar_fact.setEnabled(true);

        }
        
        
    }//GEN-LAST:event_btn_realizar_pagoActionPerformed

    private void txt_desctoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_desctoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_desctoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_FORMA_PAGO;
    private javax.swing.JDialog Dialog_buscar_pro;
    private javax.swing.JPanel Factura_panel;
    private javax.swing.JTable Tabla_ventas;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton btn_SalirProducto;
    private javax.swing.JButton btn_agregar_producto;
    private javax.swing.JButton btn_consumidor_final;
    private javax.swing.JButton btn_guardar_fact;
    private javax.swing.JButton btn_imprimir;
    private javax.swing.JButton btn_nueva_f;
    private javax.swing.JButton btn_realizar_pago;
    private javax.swing.JComboBox cbx_Nombre;
    private javax.swing.JComboBox cmb_CodoPre;
    private javax.swing.JComboBox cmb_descuento;
    private javax.swing.JComboBox cmb_producto;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_efectivo;
    private javax.swing.JLabel lbl_pagovaucher;
    private javax.swing.JLabel lbl_vaucher;
    private javax.swing.JTable tabla_producto;
    private javax.swing.JTextField txt_can;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextField txt_descto;
    private javax.swing.JTextField txt_dir;
    private javax.swing.JTextField txt_efectivo;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_iva;
    private javax.swing.JTextField txt_mail;
    private javax.swing.JTextArea txt_nota;
    private javax.swing.JTextField txt_numFactura;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_vaucher;
    private javax.swing.JTextField txt_vaucher_pago;
    private javax.swing.JTextField txt_vendedor;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if(pageIndex>0){
            return NO_SUCH_PAGE;
        }
        Graphics2D hub = (Graphics2D) graphics;
        hub.translate(pageFormat.getImageableX() + 30, pageFormat.getImageableY() + 30);
        hub.scale(1.0, 1.0);
        
        Factura_panel.printAll(graphics);
        
        return PAGE_EXISTS;
    }
    
    
    private void priceInvoice(){
        //String sourcefile = "/Users/kleberstevendiazcoello/Documents/GitHub/Sampedro-SA/SamPedroProyect/src/sanpedroproyect/FACTURA_IMPRIMIR.jrxml";
        
        DefaultTableModel order_list = new DefaultTableModel();
        String codigo,descripcion,precio,talla,cantidad,total;
        InputStream is = (InputStream)this.getClass().getClassLoader().getResourceAsStream("sanpedroproyect/FACTURA_IMPRIMIR.jrxml");
        
        try {
            int i = 0;
            JasperReport jr = JasperCompileManager.compileReport(is);
            HashMap<String,Object> para = new HashMap<>();
            para.put("CLIENTE", cbx_Nombre.getEditor().getItem().toString());
            para.put("CEDULA", txt_cedula.getText());
            para.put("DIRECCION", txt_dir.getText());
            para.put("TELEFONO",txt_telefono.getText());
            para.put("SUBTOTAL", txt_subtotal.getText());
            para.put("TOTAL", txt_total.getText());
            para.put("NOTA",txt_nota.getText());
            
            order_list = (DefaultTableModel) Tabla_ventas.getModel();
            int numero_filas = order_list.getRowCount();
            ArrayList<Productos> plist = new ArrayList<>();

            for(i=0;i<numero_filas;i++){
                codigo = Tabla_ventas.getValueAt(i, 0).toString();
                descripcion = Tabla_ventas.getValueAt(i, 1).toString();
                talla = Tabla_ventas.getValueAt(i, 2).toString();
                cantidad = Tabla_ventas.getValueAt(i, 3).toString();
                precio = Tabla_ventas.getValueAt(i,4).toString();
                total = Tabla_ventas.getValueAt(i,5).toString();    
                plist.add(new Productos(descripcion, talla, precio, cantidad, total));
  
            }
            
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);
            JasperViewer.viewReport(jp,false);
            
            
            
           
            
        } catch (JRException ex) {
            Logger.getLogger(GUI_Factura.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("El ERROES ES ESTE "+ex);
        }
        
    }
    
    
    private void limpiar(){
        id_factura_actual= factura.Get_last_id_factura();
        id_sumada = (id_factura_actual + 1 );
        txt_numFactura.setText(String.valueOf(id_sumada));
        cbx_Nombre.getEditor().setItem("");
        //txt_numFactura.setText("");
        //txt_fecha.setText("");
        txt_cedula.setText("");
        txt_dir.setText("");
        txt_can.setText("");
        txt_mail.setText("");
        txt_subtotal.setText("0");
        
        txt_telefono.setText("");
        txt_descto.setText("0");
        txt_iva.setText("0");
        txt_vaucher.setText("0");
        txt_vaucher_pago.setText("");
        txt_efectivo.setText("");
        txt_total.setText("");
        txt_nota.setText("");
        cbx_Nombre.removeAllItems();
        Tabla_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "TALLA", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
    }
    
    
    
    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public float getSubtotal() {
        return subtotal_static;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal_static = subtotal;
    }

    public float getSubtotal_static() {
        return subtotal_static;
    }

    public void setSubtotal_static(float subtotal_static) {
        this.subtotal_static = subtotal_static;
    }

    public float getDescuento_static() {
        return Descuento_static;
    }

    public void setDescuento_static(float Descuento_static) {
        this.Descuento_static = Descuento_static;
    }

    public float getIva_static() {
        return Iva_static;
    }

    public void setIva_static(float Iva_static) {
        this.Iva_static = Iva_static;
    }

    public float getTotal_static() {
        return Total_static;
    }

    public void setTotal_static(float Total_static) {
        this.Total_static = Total_static;
    }

    public float getVoucher_static() {
        return Voucher_static;
    }

    public void setVoucher_static(float Voucher_static) {
        this.Voucher_static = Voucher_static;
    }
}
