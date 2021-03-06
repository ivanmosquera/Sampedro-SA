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
import static sanpedroproyect.Detalle_Factura.codigo_cliente;
import static sanpedroproyect.GUI_Factura.codigo_cliente;
import static sanpedroproyect.GUI_Factura_SALDO.total;

/**
 *
 * @author Pantheon
 */
public class GUI_Factura_SALDO extends javax.swing.JFrame implements Printable{
    
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
    String stotal,stotal_i,stotal_v,stotal2;
    double  sub_total_i= 0;
    double total_i = 0;
    double vaucher= 0;
    double  sub_total_v= 0;
    double total_v = 0;
    double totalfin = 0;
    double totalfin2 = 0;
    float saldofav;
    
    int USUARIO;
    Main_Menu menu_Cod = new Main_Menu();
    static int codigo_cliente, id_estado;
    static float subtotal_static,Descuento_static,Voucher_static,Iva_static,Total_static;
    double efectivo;
    double tarjeta;
    double subpago,subpago_v;
    double cambio;
    double saldofavor;
    String pago_cliente,pago_cliente_v;
    double total_encontrado;
    double Totalpagar;
    static double totalpagadofinal = 0;
    static double cambiofinal = 0;
    float totalxproductos;
    int facturar_anterior_obtenida , id_usuario_obtenida;
    ArrayList<String> codigo_p_obtenido;
    ArrayList<Integer> cantidad_p_obtenido;
    double iva_usado ;
    double iva_total;
    


    /**
     * Creates new form GUI_Factura
     */
    int id_factura_actual , id_sumada;
    Date date = new Date ();
    
    public GUI_Factura_SALDO(String totalsaldo,int factura_anterior, int id_cliente, String cedula_obtenida, ArrayList<String> codigo_producto,ArrayList<Integer> cantidad_producto) {
        initComponents();
        System.out.println(Login.rol_usuario);
        //btn_guardar_fact.setEnabled(false);
        //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        id_factura_actual= factura.Get_last_id_factura();
        id_sumada = (id_factura_actual + 1 );
        txt_numFactura.setText(String.valueOf(id_sumada));
        txt_vendedor.setText(menu_Cod.getNombre_usuario());
        facturar_anterior_obtenida = factura_anterior;
        id_usuario_obtenida = id_cliente;
        cantidad_p_obtenido = cantidad_producto;
        codigo_p_obtenido = codigo_producto;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        txt_fecha.setText(dia);
        this.setLocationRelativeTo(null);
        codigo_cliente = id_cliente;
        set_clientes(cedula_obtenida);
        System.out.println("el id del cliente es "+ id_cliente);
        /*separacion */
        USUARIO = menu_Cod.getCodigo_usuario();
        btn_imprimir.setToolTipText("Antes de Imprimir, Guarde la Factura");
        btn_imprimir.setEnabled(false);
        lbl_efectivo.setEnabled(false);
        txt_vaucher_pago.setEnabled(false);
        lbl_pagovaucher.setEnabled(false);
        txt_efectivo.setEnabled(false);
        btn_guardar_fact.setEnabled(false);
        txt_descto.setEnabled(false);
        txt_saldofavor.setText(totalsaldo);
        verificar_Permisos();
        Ingreso_Nuevo_Cliente.SNumeros(txt_can);
        Ingreso_Nuevo_Cliente.SNumeros(txt_numFactura);
        Ingreso_Nuevo_Cliente.validarCedula(txt_cedula);
        
          
       /* cbx_Nombre.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
        
          
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
                                System.out.println(codigo_cliente);
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
          
        }); */ 
    }
    private void verificar_Permisos(){
        if(Login.rol_usuario == 1){
            txt_descto.setEnabled(true);
        }
        int i;
        for(i=0;i<Login.permisos_usuario.length;i++){
            if(Login.permisos_usuario[i].equals("RealizarDescuentos")){
                txt_descto.setEnabled(true);
            }

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
        Dialog_calculo = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        txt_efectivo = new javax.swing.JTextField();
        txt_vaucher_pago = new javax.swing.JTextField();
        lbl_efectivo = new javax.swing.JLabel();
        lbl_pagovaucher = new javax.swing.JLabel();
        lbl_efectivo1 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        btn_realizar_pago = new javax.swing.JButton();
        btn_Salircalculo = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        lbl_dolarefectivo = new javax.swing.JLabel();
        lbl_dolartarjeta = new javax.swing.JLabel();
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
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btn_calcular = new javax.swing.JButton();
        lbl_vaucher1 = new javax.swing.JLabel();
        txt_saldofavor = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();

        Dialog_buscar_pro.setTitle("Buscar Producto");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        cmb_producto.setEditable(true);
        cmb_producto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tabla_producto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane2.setViewportView(tabla_producto);
        if (tabla_producto.getColumnModel().getColumnCount() > 0) {
            tabla_producto.getColumnModel().getColumn(1).setPreferredWidth(180);
        }

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

        jLabel18.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel18.setText("Cantidad");

        txt_can.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cmb_CodoPre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        jLabel19.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
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
                        .addComponent(btn_SalirProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_CodoPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        Dialog_calculo.setTitle("Buscar Producto");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txt_efectivo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_efectivo.setText("0");

        txt_vaucher_pago.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_vaucher_pago.setText("0");

        lbl_efectivo.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        lbl_efectivo.setText("Pago con Efectivo");

        lbl_pagovaucher.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        lbl_pagovaucher.setText("Pago con Tarjeta");

        lbl_efectivo1.setFont(new java.awt.Font("Bookman Old Style", 1, 36)); // NOI18N
        lbl_efectivo1.setText("TOTAL :");

        txt_total.setEditable(false);
        txt_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btn_realizar_pago.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_realizar_pago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/proyecto-de-ley.png"))); // NOI18N
        btn_realizar_pago.setText("CONFIRMAR");
        btn_realizar_pago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_realizar_pago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_realizar_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_realizar_pagoActionPerformed(evt);
            }
        });

        btn_Salircalculo.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_Salircalculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btn_Salircalculo.setText("SALIR");
        btn_Salircalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalircalculoActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel21.setText("$");

        lbl_dolarefectivo.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_dolarefectivo.setText("$");

        lbl_dolartarjeta.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_dolartarjeta.setText("$");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbl_efectivo1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_efectivo)
                                    .addComponent(lbl_pagovaucher))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_dolartarjeta)
                                    .addComponent(lbl_dolarefectivo))
                                .addGap(3, 3, 3)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_efectivo, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                .addComponent(txt_vaucher_pago))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btn_realizar_pago)
                        .addGap(106, 106, 106)
                        .addComponent(btn_Salircalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_efectivo1)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_efectivo)
                    .addComponent(txt_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_dolarefectivo))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_pagovaucher)
                    .addComponent(txt_vaucher_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_dolartarjeta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Salircalculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_realizar_pago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(101, 101, 101))
        );

        javax.swing.GroupLayout Dialog_calculoLayout = new javax.swing.GroupLayout(Dialog_calculo.getContentPane());
        Dialog_calculo.getContentPane().setLayout(Dialog_calculoLayout);
        Dialog_calculoLayout.setHorizontalGroup(
            Dialog_calculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Dialog_calculoLayout.setVerticalGroup(
            Dialog_calculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel1.setLabelFor(txt_numFactura);
        jLabel1.setText("Factura Num.");

        txt_numFactura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel2.setText("Fecha");

        txt_fecha.setEditable(false);
        txt_fecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_numFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel3.setText("Cliente");

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel4.setText("Dirección");

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel5.setText("Cédula/RUC");

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel6.setText("Vendedor");

        txt_mail.setEditable(false);
        txt_mail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_cedula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cedulaActionPerformed(evt);
            }
        });
        txt_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cedulaKeyReleased(evt);
            }
        });

        txt_vendedor.setEditable(false);
        txt_vendedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_vendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vendedorActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel8.setText("Forma De Pago");

        Combo_FORMA_PAGO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        btn_consumidor_final.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_consumidor_final.setText("Consumidor Final");
        btn_consumidor_final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consumidor_finalActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel9.setText("Mail");

        txt_dir.setEditable(false);
        txt_dir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel16.setText("Telefono");

        txt_telefono.setEditable(false);
        txt_telefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbx_Nombre.setEditable(true);
        cbx_Nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel10.setText("Subtotal");

        txt_subtotal.setEditable(false);
        txt_subtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel11.setText("Descuento");

        txt_descto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        lbl_vaucher.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        lbl_vaucher.setText("Recargo");

        txt_vaucher.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_vaucher.setText("0");
        txt_vaucher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_vaucherKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel15.setText("I.V.A");

        txt_iva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_iva.setText("0");
        txt_iva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ivaKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel14.setText("NOTA :");

        txt_nota.setColumns(20);
        txt_nota.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nota.setRows(5);
        jScrollPane1.setViewportView(txt_nota);

        btn_imprimir.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/impresora.png"))); // NOI18N
        btn_imprimir.setText("Imprimir");
        btn_imprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_imprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirActionPerformed(evt);
            }
        });

        btn_Limpiar.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/escoba.png"))); // NOI18N
        btn_Limpiar.setText("Limpiar");
        btn_Limpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Limpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });

        btn_Salir.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_Salir.setText("Salir");
        btn_Salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        Tabla_ventas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Tabla_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "TALLA", "CANTIDAD", "PRECIO", "PRECIO MINIMO", "TOTAL"
            }
        ));
        jScrollPane3.setViewportView(Tabla_ventas);

        jButton5.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tienda-online.png"))); // NOI18N
        jButton5.setText("Agregar Producto");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btn_guardar_fact.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_guardar_fact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible (1).png"))); // NOI18N
        btn_guardar_fact.setText("Guardar Factura");
        btn_guardar_fact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar_fact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_factActionPerformed(evt);
            }
        });

        btn_nueva_f.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_nueva_f.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/recepcion.png"))); // NOI18N
        btn_nueva_f.setText("Nueva Factura");
        btn_nueva_f.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_nueva_f.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_nueva_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nueva_fActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-x.png"))); // NOI18N
        jButton6.setText("Quitar Producto");
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        cmb_descuento.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        cmb_descuento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "$", "%" }));
        cmb_descuento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_descuentoItemStateChanged(evt);
            }
        });
        cmb_descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_descuentoActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel17.setText("%");

        jLabel20.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel20.setText("$");

        btn_calcular.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btn_calcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/proyecto-de-ley.png"))); // NOI18N
        btn_calcular.setText("Calcular Total");
        btn_calcular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_calcular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calcularActionPerformed(evt);
            }
        });

        lbl_vaucher1.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        lbl_vaucher1.setText("Saldo a Favor");

        txt_saldofavor.setEditable(false);
        txt_saldofavor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_saldofavor.setText("0");
        txt_saldofavor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_saldofavorKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel22.setText("$");

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
                        .addGap(31, 31, 31)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Factura_panelLayout.createSequentialGroup()
                                .addComponent(btn_consumidor_final)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Factura_panelLayout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_telefono))
                                .addGroup(Factura_panelLayout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(Combo_FORMA_PAGO, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(Factura_panelLayout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(46, 46, 46)
                                    .addComponent(txt_mail)))))
                    .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(448, 448, 448))
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(Factura_panelLayout.createSequentialGroup()
                            .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Factura_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(11, 11, 11))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(jLabel15)
                                .addComponent(lbl_vaucher)
                                .addComponent(lbl_vaucher1))
                            .addGap(81, 81, 81)
                            .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_vaucher, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(Factura_panelLayout.createSequentialGroup()
                                    .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_descto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmb_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(Factura_panelLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel20)
                                                .addComponent(jLabel17)
                                                .addComponent(jLabel22)))))
                                .addComponent(txt_saldofavor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btn_calcular))
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(64, 64, 64)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btn_guardar_fact)
                        .addGap(58, 58, 58)
                        .addComponent(btn_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Factura_panelLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(btn_nueva_f)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Factura_panelLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Factura_panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(Combo_FORMA_PAGO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(txt_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(10, 10, 10)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_vaucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_vaucher)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_vaucher1)
                            .addComponent(txt_saldofavor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(10, 10, 10)
                        .addComponent(btn_calcular)
                        .addGap(64, 64, 64)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(117, Short.MAX_VALUE))
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

        priceInvoice();
  
    }//GEN-LAST:event_btn_imprimirActionPerformed

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
        limpiar();
        
    }//GEN-LAST:event_btn_LimpiarActionPerformed

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
                           String sql = ("SELECT * FROM cliente where id_Cliente = ?");
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
            int modo_pago = 0;
            DefaultTableModel order_list_guardar = new DefaultTableModel();

                if(txt_subtotal.getText().equals("")){
                    subtotal_static = 0;
                }else{
                    subtotal_static = (Float.parseFloat(txt_subtotal.getText()));
                }


                if(txt_descto.getText().equals("")){
                    Descuento_static=0;
                }else{
                    Descuento_static = (Float.parseFloat(txt_descto.getText()));
                }

                if(txt_total.getText().equals("")){
                   Total_static  =0;
                }else{
                    Total_static = (Float.parseFloat(txt_total.getText()));
                }



                if(txt_vaucher.getText().equals("")){
                    Voucher_static=0;
                }else{
                    Voucher_static = (Float.parseFloat(txt_vaucher.getText()));
                }


                if(txt_iva.getText().equals("")){
                    Iva_static = 0;
                }else{
                    Iva_static = (Float.parseFloat(txt_iva.getText()));
                }

                float  pago_efectivo, pago_tarjeta;
                
                if(txt_efectivo.getText().equals("")){
                    pago_efectivo = 0;
                }else{
                    pago_efectivo = (Float.parseFloat(txt_efectivo.getText()));
                }
                if(txt_vaucher_pago.getText().equals("")){
                    pago_tarjeta = 0;
                }else{
                    pago_tarjeta = (Float.parseFloat(txt_vaucher_pago.getText()));
                }
            if(txt_saldofavor.getText().equals("")){
                    saldofav = 0;
                }else{
                    saldofav = (Float.parseFloat(txt_saldofavor.getText()));
                }
            if(Combo_FORMA_PAGO.getEditor().getItem().toString().equals("Efectivo")){
                    modo_pago = 1;
                }else if(Combo_FORMA_PAGO.getEditor().getItem().toString().equals("Tarjeta")){
                    modo_pago = 2;
                }else{
                    modo_pago = 3;
            }
                
            int i = 0;
            String codigo_a_guardar , codigo_p_cambio;
            int cantidad = 0 ;
            int cantidadelim = 0;
            int codigo_obtenido;
            float totalpagar, subtotal,totalneto;
            int id_factura = 0;
            id_factura = Integer.parseInt(txt_numFactura.getText().toString());
            totalneto = saldofav + Total_static;
            String s = factura.Guardar_Factura_saldo(id_sumada,codigo_cliente,USUARIO,pago_efectivo,pago_tarjeta,Voucher_static,Iva_static,saldofav,Descuento_static,Total_static,subtotal_static,totalneto,modo_pago);     
            System.out.println("Estado de Factura" + s);
            codigo_obtenido = factura.Get_last_id_factura();
            System.out.println("el cod " + codigo_obtenido);
            
           /* HARA LA DEVOLUCION DE LOS PRODUCTOS */
            int numero_filas_s = codigo_p_obtenido.size();
            for(i=0;i<numero_filas_s;i++){
                int cantidad_actual_s = 0;
                int nueva_cantidad_s = 0;
                codigo_p_cambio =  codigo_p_obtenido.get(i).toString();
                cantidadelim = cantidad_p_obtenido.get(i);
              
                inv.Ingresar_Inventario_Anulacion(codigo_p_cambio,cantidadelim);
                cantidad_actual_s = inv.get_cantidad_total_producto(codigo_p_cambio);
                nueva_cantidad_s = (cantidad_actual_s + cantidadelim) ;
                inv.Incremeneto_total_producto(codigo_p_cambio , nueva_cantidad_s );
            }

            /*Neceista el id para guardar todos los productos de una factura*/
            order_list_guardar = (DefaultTableModel) Tabla_ventas.getModel();
            int numero_filas = order_list_guardar.getRowCount();
                for(i=0;i<numero_filas;i++){
                    int cantidad_actual = 0;
                    int nueva_cantidad = 0;
                    totalxproductos= 0;
                    codigo_a_guardar = Tabla_ventas.getValueAt(i, 0).toString();
                    cantidad = Integer.parseInt(Tabla_ventas.getValueAt(i, 3).toString());
                    totalxproductos = Float.parseFloat(Tabla_ventas.getValueAt(i, 6).toString());
                    factura.Guardar_Detalle_Factura(codigo_obtenido, codigo_a_guardar, cantidad); 
                    factura.Guardar_Detalle_X_Producto(USUARIO,id_factura,codigo_cliente,codigo_a_guardar,cantidad,totalxproductos);
                    inv.Decremento_inventario(codigo_a_guardar,cantidad);
                    cantidad_actual = inv.get_cantidad_total_producto(codigo_a_guardar);
                    nueva_cantidad = (cantidad_actual - cantidad) ;
                    inv.Incremeneto_total_producto(codigo_a_guardar , nueva_cantidad );
                }
            String eliminar = factura.Eliminar_Factura(facturar_anterior_obtenida,id_usuario_obtenida,"Cambio de Prenda Factura");  //FALATA PONER MOTIVO   
            String s2 = factura.UpdateCambio(facturar_anterior_obtenida);
            System.out.println("" + s2);
            JOptionPane.showMessageDialog(null, "Anterior Factura Anulada y Factura Ingresada Correcatemente" , "Guardado Exitoso" , JOptionPane.INFORMATION_MESSAGE);
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
                    importe = Float.parseFloat(Tabla_ventas.getValueAt(fsel,6).toString());
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
            lbl_vaucher.setEnabled(false);
            txt_vaucher.setEnabled(false);
            txt_vaucher_pago.setEnabled(false);
            lbl_pagovaucher.setEnabled(false);
            txt_efectivo.setEnabled(false);
            lbl_dolarefectivo.setEnabled(false);
            lbl_dolartarjeta.setEnabled(false);
        }
        else if(Combo_FORMA_PAGO.getSelectedItem().equals("Efectivo")){
            lbl_efectivo.setEnabled(true);
            lbl_vaucher.setEnabled(false);
            txt_vaucher.setEnabled(false);
            txt_vaucher_pago.setEnabled(false);
            lbl_pagovaucher.setEnabled(false);
            txt_vaucher.setEnabled(false);
            txt_efectivo.setEnabled(true);
            lbl_dolarefectivo.setEnabled(true);
            lbl_dolartarjeta.setEnabled(false);
            System.out.println("EFECTIVO"); 
        }else if(Combo_FORMA_PAGO.getSelectedItem().equals("Tarjeta Credito")){
            lbl_efectivo.setEnabled(false);
            lbl_vaucher.setEnabled(true);
            txt_vaucher.setEnabled(true);
            txt_vaucher_pago.setEnabled(true);
            lbl_pagovaucher.setEnabled(true);
            txt_efectivo.setEnabled(false);
            lbl_dolarefectivo.setEnabled(false);
            lbl_dolartarjeta.setEnabled(true);
        }else if(Combo_FORMA_PAGO.getSelectedItem().equals("Tarjeta Débito")) {
             System.out.println("Tarjeta Débito");
            lbl_efectivo.setEnabled(false);
            lbl_vaucher.setEnabled(true);
            txt_vaucher.setEnabled(true);
            txt_vaucher_pago.setEnabled(true);
            lbl_pagovaucher.setEnabled(true);
            txt_efectivo.setEnabled(false);
        }
        else if(Combo_FORMA_PAGO.getSelectedItem().equals("Mixto")) {
             System.out.println("mixto");
            lbl_efectivo.setEnabled(true);
            lbl_vaucher.setEnabled(true);
            txt_vaucher.setEnabled(true);
            txt_vaucher_pago.setEnabled(true);
            lbl_pagovaucher.setEnabled(true);
            txt_efectivo.setEnabled(true);
            lbl_dolarefectivo.setEnabled(true);
            lbl_dolartarjeta.setEnabled(true);
        }
    }//GEN-LAST:event_Combo_FORMA_PAGOItemStateChanged

    private void Combo_FORMA_PAGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_FORMA_PAGOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Combo_FORMA_PAGOActionPerformed

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

    private void txt_desctoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_desctoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_desctoActionPerformed

    private void txt_cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cedulaKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String cedu = txt_cedula.getText().toString();
                    System.out.println("Aplasto ENter");
                        String resul = null , lats = null;
                        ConnectionDB cc = new ConnectionDB();
                        Connection cn = cc.getConnection();
                        PreparedStatement pst =null;
                        ResultSet rs = null;
                        String Desc;
                        try{
                           String sql = ("SELECT * FROM cliente where Cedula = ?");
                           pst = cn.prepareStatement(sql);
                           pst.setString(1,cedu);
                           rs =pst.executeQuery();
                           if (rs.next()){
                               cbx_Nombre.getEditor().setItem(rs.getString("Nombre"));
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
    }//GEN-LAST:event_txt_cedulaKeyReleased

    private void txt_cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cedulaActionPerformed

    private void btn_SalirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirProductoActionPerformed
        Dialog_buscar_pro.dispose();
    }//GEN-LAST:event_btn_SalirProductoActionPerformed

    private void btn_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_productoActionPerformed
        // TODO add your handling code here:
        int fsel = tabla_producto.getSelectedRow();
        try {
            String codigo, descripcion, precio, precio_min, cantidad,importe,talla;
            float x;
            float calcula;
            float iva;

            if(fsel==-1){
                JOptionPane.showMessageDialog(null,"Dese seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
            else if(Integer.parseInt(txt_can.getText())<=0){
                JOptionPane.showMessageDialog(null,"Ingrese cantidad positiva","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
            else if(Integer.parseInt(txt_can.getText())>Integer.parseInt(tabla_producto.getValueAt(fsel, 5).toString())){
                JOptionPane.showMessageDialog(null,"No hay dicha cantidad en stock","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
            else if(!(txt_can.getText().isEmpty())){
                m = (DefaultTableModel) tabla_producto.getModel();
                codigo = tabla_producto.getValueAt(fsel, 0).toString();
                descripcion = tabla_producto.getValueAt(fsel, 1).toString();
                precio = tabla_producto.getValueAt(fsel, 2).toString();
                precio_min = tabla_producto.getValueAt(fsel, 3).toString();
                talla = tabla_producto.getValueAt(fsel, 4).toString();
                cantidad = txt_can.getText();
                Dialog_buscar_pro.setVisible(false);
                x = (Float.parseFloat(precio)) * Integer.parseInt(cantidad);
                importe = String.format(java.util.Locale.US,"%.2f", x);
                m = (DefaultTableModel) Tabla_ventas.getModel();
                String filaElemento[] = {
                    codigo,descripcion,talla,cantidad,precio,precio_min,importe
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

    private void cmb_CodoPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_CodoPreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_CodoPreActionPerformed

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
                    if(e.getKeyCode()>= 65 && e.getKeyCode()<= 90 || e.getKeyCode()>= 96 && e.getKeyCode()<= 105 || e.getKeyCode()>= 96 && e.getKeyCode()== 8 ){
                        cmb_producto.setModel(op.geLista_Producto_porcodigo(cadena));
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

    private void btn_calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calcularActionPerformed
        // TODO add your handling code here:
        saldofavor = (Double.parseDouble(txt_saldofavor.getText())); 
        if (txt_subtotal.getText().equals("")){
            sub_total = 0;
        }else{
           sub_total =(Double.parseDouble(txt_subtotal.getText())); 
        }
        
        if(txt_iva.getText().equals("")){
            iva = 0;
        }else{
            iva = (Double.parseDouble(txt_iva.getText()));
        }
        
        if(txt_vaucher.getText().equals("")){
            vaucher = 0;
        }else{
            vaucher = (Double.parseDouble(txt_vaucher.getText()));
        }
         
         
         
         if(cmb_descuento.getSelectedItem().equals("$")){
             if(txt_descto.getText().equals("")){
                     desc = 0;
                     sub_total =Double.parseDouble(txt_subtotal.getText());
                     total = sub_total - desc ;
                     sub_total_i = total;
                     iva_total = ((sub_total_i * iva)/100);
                     total_i = sub_total_i + iva_total ;
                     iva_total= iva_usado;
                     totalfin = total_i + vaucher - saldofavor;
                     stotal = String.format(java.util.Locale.US,"%.2f", totalfin);
                     totalfin2 = total_i + vaucher;
                     stotal2 = String.format(java.util.Locale.US,"%.2f", totalfin);
                     txt_total.setText(stotal);
             }else{
                     desc = (Double.parseDouble(txt_descto.getText()));
                     sub_total =Double.parseDouble(txt_subtotal.getText());
                     total = sub_total - desc ;
                     sub_total_i = total;
                     iva_total = ((sub_total_i * iva)/100);
                     total_i = sub_total_i + iva_total ;
                     iva_usado = iva_total;
                     totalfin = total_i + vaucher - saldofavor;
                     stotal = String.format(java.util.Locale.US,"%.2f", totalfin);
                     totalfin2 = total_i + vaucher;
                     stotal2 = String.format(java.util.Locale.US,"%.2f", totalfin);
                     txt_total.setText(stotal);
             }
                     
            }else{
             
                if(txt_descto.getText().equals("")){
                    desc = 0;
                   sub_total =Double.parseDouble(txt_subtotal.getText());
                   total = sub_total - ((sub_total * desc)/100) ;
                   sub_total_i = total;
                   iva_total = ((sub_total_i * iva)/100);
                   total_i = sub_total_i + iva_total ;
                   iva_usado = iva_total;
                   totalfin = total_i + vaucher - saldofavor;
                   stotal = String.format(java.util.Locale.US,"%.2f", totalfin);
                   txt_total.setText(stotal);
                   totalfin2 = total_i + vaucher;
                   stotal2 = String.format(java.util.Locale.US,"%.2f", totalfin);
                }else{
                   desc = (Double.parseDouble(txt_descto.getText()));
                   sub_total =Double.parseDouble(txt_subtotal.getText());
                   total = sub_total - ((sub_total * desc)/100) ;
                   sub_total_i = total;
                   iva_total = ((sub_total_i * iva)/100);
                   total_i = sub_total_i + iva_total ;
                   iva_usado = iva_total;
                   totalfin = total_i + vaucher - saldofavor ;
                   stotal = String.format(java.util.Locale.US,"%.2f", totalfin);
                   txt_total.setText(stotal);
                   totalfin2 = total_i + vaucher;
                   stotal2 = String.format(java.util.Locale.US,"%.2f", totalfin);
                }
                
            }
        Dialog_calculo.setSize(700, 500);
        Dialog_calculo.setLocationRelativeTo(null);
        Dialog_calculo.setVisible(true);
    }//GEN-LAST:event_btn_calcularActionPerformed

    private void cmb_descuentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_descuentoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_descuentoItemStateChanged

    private void cmb_descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_descuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_descuentoActionPerformed

    private void btn_realizar_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_realizar_pagoActionPerformed
        double pay , calculus,dif,cambio;
       Totalpagar =  (Double.parseDouble(txt_total.getText())); 
       efectivo = (Double.parseDouble(txt_efectivo.getText())); 
       tarjeta = (Double.parseDouble(txt_vaucher_pago.getText())); 
       
       pay =  efectivo + tarjeta;
       totalpagadofinal = pay;
       calculus = pay - Totalpagar;
       
       if(calculus > 0){
            dif = Totalpagar - tarjeta;
            cambio = efectivo - dif;
           JOptionPane.showMessageDialog(null,"Su cambio es : "+(cambio *(-1))+" $ ","Su cambio",JOptionPane.INFORMATION_MESSAGE);
           btn_guardar_fact.setEnabled(true);
           Dialog_calculo.setVisible(false);
           txt_efectivo.setText(String.valueOf(efectivo - cambio));
           cambiofinal = cambio;
       }else if(calculus < 0){
           JOptionPane.showMessageDialog(null,"Faltan  : "+calculus+" $ !!! ","OJO",JOptionPane.WARNING_MESSAGE);
       }else if(calculus == 0){
           
           JOptionPane.showMessageDialog(null," Gracias por su compra"," Campra Exitosa!!",JOptionPane.INFORMATION_MESSAGE);
           btn_guardar_fact.setEnabled(true);
           Dialog_calculo.setVisible(false);
       }
       
    }//GEN-LAST:event_btn_realizar_pagoActionPerformed

    private void btn_SalircalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalircalculoActionPerformed
        // TODO add your handling code here:
        Dialog_calculo.dispose();
    }//GEN-LAST:event_btn_SalircalculoActionPerformed

    private void txt_saldofavorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_saldofavorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_saldofavorKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_FORMA_PAGO;
    private javax.swing.JDialog Dialog_buscar_pro;
    private javax.swing.JDialog Dialog_calculo;
    private javax.swing.JPanel Factura_panel;
    private javax.swing.JTable Tabla_ventas;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton btn_SalirProducto;
    private javax.swing.JButton btn_Salircalculo;
    private javax.swing.JButton btn_agregar_producto;
    private javax.swing.JButton btn_calcular;
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
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_dolarefectivo;
    private javax.swing.JLabel lbl_dolartarjeta;
    private javax.swing.JLabel lbl_efectivo;
    private javax.swing.JLabel lbl_efectivo1;
    private javax.swing.JLabel lbl_pagovaucher;
    private javax.swing.JLabel lbl_vaucher;
    private javax.swing.JLabel lbl_vaucher1;
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
    private javax.swing.JTextField txt_saldofavor;
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
        DateFormat hourFormatd = new SimpleDateFormat("HH:mm:ss");
        final String horad = hourFormatd.format(date);
        DateFormat dateFormatD = new SimpleDateFormat("yyyy/MM/dd");
        String diaD = dateFormatD.format(date);
        try {
            int i = 0;
            JasperReport jr = JasperCompileManager.compileReport(is);
            HashMap<String,Object> para = new HashMap<>();
            para.put("CLIENTE", cbx_Nombre.getEditor().getItem().toString());
            para.put("CEDULA", txt_cedula.getText());
            para.put("DIRECCION", txt_dir.getText());
            para.put("TELEFONO",txt_telefono.getText());
            para.put("SUBTOTAL", txt_subtotal.getText());
            para.put("TOTAL", stotal2);
            para.put("NOTA",txt_nota.getText());
            para.put("IVA",txt_iva.getText());
            para.put("IVAUSADO",String.valueOf(iva_usado));
            para.put("DESCUENTO",txt_descto.getText());
            para.put("VAUCHER",txt_vaucher.getText());
            para.put("TOTALPAGADO",String.valueOf(totalpagadofinal));
            para.put("CAMBIO",String.valueOf(cambiofinal));
            para.put("HORA", horad);
            para.put("FECHA", diaD);
            para.put("VENDEDOR",txt_vendedor.getText());
            para.put("FORMAPA", Combo_FORMA_PAGO.getEditor().getItem().toString());
            para.put("Saldo", txt_saldofavor.getText());
            
            
            
            
            
            
            order_list = (DefaultTableModel) Tabla_ventas.getModel();
            int numero_filas = order_list.getRowCount();
            ArrayList<Productos> plist = new ArrayList<>();

            for(i=0;i<numero_filas;i++){
                codigo = Tabla_ventas.getValueAt(i, 0).toString();
                descripcion = Tabla_ventas.getValueAt(i, 1).toString();
                talla = Tabla_ventas.getValueAt(i, 2).toString();
                cantidad = Tabla_ventas.getValueAt(i, 3).toString();
                precio = Tabla_ventas.getValueAt(i,4).toString();
                total = Tabla_ventas.getValueAt(i,6).toString();    
                plist.add(new Productos(descripcion, talla, precio, cantidad, total));
  
            }
            
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);
            JasperViewer.viewReport(jp,false);
            
            
            
           
            
        } catch (JRException ex) {
            Logger.getLogger(GUI_Factura_SALDO.class.getName()).log(Level.SEVERE, null, ex);
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
                "CODIGO", "DESCRIPCION", "TALLA", "CANTIDAD", "PRECIO", "PRECIO MINIMO", "TOTAL"
            }
        ));
    }
    
        private void set_clientes(String cedula){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String Desc;
        try{
            String sql = ("SELECT * FROM cliente where Cedula = ?");
            pst = cn.prepareStatement(sql);
            pst.setString(1,cedula);
            rs =pst.executeQuery();
            if (rs.next()){
                codigo_cliente = rs.getInt("id_Cliente");
                txt_cedula.setText(rs.getString("Cedula"));
                txt_dir.setText(rs.getString("Direccion"));
                txt_mail.setText(rs.getString("Correo"));
                txt_telefono.setText(rs.getString("Telefono"));
                cbx_Nombre.getEditor().setItem(rs.getString("Nombre"));
                txt_nota.setText(rs.getString("Nota"));
                          
                }



            } catch (Exception ex){
               System.out.println(ex);
            }
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
