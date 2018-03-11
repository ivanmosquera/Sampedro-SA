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
import java.util.ArrayList;
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
import static sanpedroproyect.Detalle_Factura.total;
import static sanpedroproyect.GUI_Factura.codigo_cliente;

/**
 *
 * @author Pantheon
 */
public class Detalle_Factura extends javax.swing.JFrame {
    
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
    String stotal;
    int USUARIO;
    Main_Menu menu_Cod = new Main_Menu();
    
    static int codigo_cliente, id_estado;
    static float subtotal_static,Descuento_static,Voucher_static,Iva_static,Total_static;
    int id_de_la_factura;
 


    /**
     * Creates new form GUI_Factura
     * @param idFactura
     */
    public Detalle_Factura(int idFactura) {
        initComponents();
        id_de_la_factura = idFactura;
        Ingreso_Nuevo_Cliente.SNumeros(txt_numFactura);
        Ingreso_Nuevo_Cliente.SNumeros(txt_fecha);
        txt_numFactura.setText(String.valueOf(id_de_la_factura));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        System.out.println("" + idFactura);
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String Desc;
        try{
            String sql = ("SELECT * FROM cliente c , factura f , usuario where fk_cliente = id_Cliente and id_Factura = ? and fk_Usuario = id_Usuario;");
            pst = cn.prepareStatement(sql);
            pst.setInt(1, idFactura);
            rs =pst.executeQuery();
            if (rs.next()){
                codigo_cliente = rs.getInt("id_Cliente");
                cbx_Nombre.setText(rs.getString("Nombre"));
                txt_cedula.setText(rs.getString("Cedula"));
                txt_fecha.setText(String.valueOf(rs.getDate("Fecha")));
                txt_dir.setText(rs.getString("Direccion"));
                txt_mail.setText(rs.getString("Correo"));
                txt_telefono.setText(rs.getString("Telefono"));
                txt_total.setText(rs.getString("Total"));
                txt_subtotal.setText(rs.getString("Subtotal"));
                txt_descto.setText(rs.getString("Descuento"));
                txt_iva.setText(rs.getString("Iva"));
                txt_vaucher.setText(rs.getString("Vaucher"));
                txt_nota.setText(rs.getString("Nota"));
                txt_vendedor.setText(rs.getString("Usuario"));
                USUARIO = rs.getInt("id_Usuario");
                          
                }



            } catch (Exception ex){
                            System.out.println(ex);
            }
        
        
        
        //* ITEMS*/
        
         m = (DefaultTableModel) Tabla_ventas.getModel();

        String cod,des,Talla,Total_s,cant_s,precio_s;
        int  Cantidad;
        float Precio,Total;
        
                
        try{
            String sql = ("SELECT * FROM detalle_factura , producto where fk_producto = id_Producto and fk_factura = ?");
            pst = cn.prepareStatement(sql);
            pst.setInt(1, idFactura);
            rs =pst.executeQuery();
            if (rs.next()){
                cod = rs.getString("id_Producto");
                des = rs.getString("Descripcion");
                Talla = rs.getString("Talla");
                Cantidad = rs.getInt("Cantidad");
                Precio = rs.getFloat("Precio");
                Total = (Cantidad * Precio);
                Total_s = String.valueOf(Total);
                precio_s = String.valueOf(Precio);
                cant_s = String.valueOf(Cantidad);
                
                String filaElemento[] = {
                    cod,des,Talla,cant_s,precio_s,Total_s
                 };
                m.addRow(filaElemento);
                
                
                
                
                
                          
                }



            } catch (Exception ex){
                            System.out.println(ex);
            }
        
        cc.desconectar();
        
        
        
        
        
        
        
        
        
        
        
        //SELECT * FROM detalle_factura , producto where fk_producto = id_Producto and fk_factura = 15;
       
        
        
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

        dialog_motivo = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_motivo = new javax.swing.JTextArea();
        btn_guardar_fact1 = new javax.swing.JButton();
        btn_Salir1 = new javax.swing.JButton();
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
        jLabel9 = new javax.swing.JLabel();
        txt_dir = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_descto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_vaucher = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_iva = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_nota = new javax.swing.JTextArea();
        btn_Salir = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla_ventas = new javax.swing.JTable();
        btn_guardar_fact = new javax.swing.JButton();
        cbx_Nombre = new javax.swing.JTextField();
        btn_guardar_fact2 = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel17.setText("Motivo de Eliminaciòn");

        txt_motivo.setColumns(20);
        txt_motivo.setRows(5);
        jScrollPane2.setViewportView(txt_motivo);

        btn_guardar_fact1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_guardar_fact1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible (1).png"))); // NOI18N
        btn_guardar_fact1.setText("OK");
        btn_guardar_fact1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar_fact1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar_fact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_fact1ActionPerformed(evt);
            }
        });

        btn_Salir1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Salir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_Salir1.setText("Salir");
        btn_Salir1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Salir1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_Salir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Salir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_guardar_fact1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Salir1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Salir1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btn_guardar_fact1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(71, 71, 71))
        );

        javax.swing.GroupLayout dialog_motivoLayout = new javax.swing.GroupLayout(dialog_motivo.getContentPane());
        dialog_motivo.getContentPane().setLayout(dialog_motivoLayout);
        dialog_motivoLayout.setHorizontalGroup(
            dialog_motivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialog_motivoLayout.setVerticalGroup(
            dialog_motivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setText("Factura Num.");

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setText("Fecha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txt_numFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel8.setText("Forma De Pago");

        Combo_FORMA_PAGO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta Credito", "Tarjeta Débito"}));

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel9.setText("Mail");

        txt_dir.setEditable(false);

        jLabel16.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel16.setText("Telefono");

        txt_telefono.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel10.setText("Subtotal");

        txt_subtotal.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel11.setText("Descuento");

        txt_descto.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel12.setText("Vaucher");

        txt_vaucher.setEditable(false);

        jLabel15.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel15.setText("I.V.A");

        txt_iva.setEditable(false);

        txt_total.setEditable(false);

        jLabel13.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel13.setText("Total");

        jLabel14.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel14.setText("NOTA :");

        txt_nota.setEditable(false);
        txt_nota.setColumns(20);
        txt_nota.setRows(5);
        jScrollPane1.setViewportView(txt_nota);

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

        btn_guardar_fact.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_guardar_fact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible (1).png"))); // NOI18N
        btn_guardar_fact.setText("Eliminar Factura");
        btn_guardar_fact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar_fact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_factActionPerformed(evt);
            }
        });

        cbx_Nombre.setEditable(false);

        btn_guardar_fact2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_guardar_fact2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btn_guardar_fact2.setText("Cambio de prenda");
        btn_guardar_fact2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar_fact2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar_fact2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_fact2ActionPerformed(evt);
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
                            .addComponent(cbx_Nombre))
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Factura_panelLayout.createSequentialGroup()
                                .addGap(199, 199, 199)
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
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Factura_panelLayout.createSequentialGroup()
                                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12))
                                .addGap(45, 45, 45)
                                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_vaucher, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Factura_panelLayout.createSequentialGroup()
                                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Factura_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(11, 11, 11))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(27, 27, 27)
                                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_descto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_guardar_fact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_guardar_fact2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Factura_panelLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        Factura_panelLayout.setVerticalGroup(
            Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(cbx_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 95, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_descto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_vaucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Factura_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(btn_guardar_fact)
                        .addGap(40, 40, 40)))
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Factura_panelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btn_guardar_fact2)
                        .addGap(40, 40, 40)
                        .addComponent(btn_Salir))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Factura_panelLayout.createSequentialGroup()
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txt_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
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

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        //System.exit(0); 
        Ver_Facturas ve = new Ver_Facturas();
        ve.setVisible(true);
        ve.setLocationRelativeTo(null);
        ve.setResizable(false);
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_guardar_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_factActionPerformed
        // TODO add your handling code here:
        dialog_motivo.setSize(480, 354);
        dialog_motivo.setLocationRelativeTo(null);
        dialog_motivo.setVisible(true);
        
        
        
    }//GEN-LAST:event_btn_guardar_factActionPerformed

    private void btn_guardar_fact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_fact1ActionPerformed
        // TODO add your handling code here:
        String motivo = txt_motivo.getText().toString();
        DefaultTableModel order_list_guardar = new DefaultTableModel();
        subtotal_static = (Float.parseFloat(txt_subtotal.getText()));
        Descuento_static = (Float.parseFloat(txt_descto.getText()));
        Total_static = (Float.parseFloat(txt_total.getText()));
        int i = 0;
        String codigo_a_guardar;
        int cantidad = 0 ;
        int codigo_obtenido;
        String s = factura.Eliminar_Factura(id_de_la_factura,USUARIO,motivo);  //FALATA PONER MOTIVO   
        String s2 = factura.Update(id_de_la_factura);
        System.out.println("" + s2);
   
        /*Neceista el id para guardar todos los productos de una factura*/
        order_list_guardar = (DefaultTableModel) Tabla_ventas.getModel();
        int numero_filas = order_list_guardar.getRowCount();
            for(i=0;i<numero_filas;i++){
                int cantidad_actual = 0;
                int nueva_cantidad = 0;
                codigo_a_guardar = Tabla_ventas.getValueAt(i, 0).toString();
                cantidad = Integer.parseInt(Tabla_ventas.getValueAt(i, 3).toString());
                //factura.Guardar_Detalle_Factura(codigo_obtenido, codigo_a_guardar, cantidad); 
                inv.Ingresar_Inventario_Anulacion(codigo_a_guardar,cantidad);
                cantidad_actual = inv.get_cantidad_total_producto(codigo_a_guardar);
                nueva_cantidad = (cantidad_actual + cantidad) ;
                inv.Incremeneto_total_producto(codigo_a_guardar , nueva_cantidad );
            }
            
        JOptionPane.showMessageDialog(null, "FACTURA ELIMINADA EXITOSAMENTE" , "ELIMINACIÓN EXITOSA" , JOptionPane.INFORMATION_MESSAGE);  
        Ver_Facturas ve = new Ver_Facturas();
        ve.setVisible(true);
        ve.setLocationRelativeTo(null);
        ve.setResizable(false);
        dialog_motivo.dispose();
        dispose();
        
    }//GEN-LAST:event_btn_guardar_fact1ActionPerformed

    private void btn_Salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Salir1ActionPerformed
        // TODO add your handling code here:
        dialog_motivo.dispose();
    }//GEN-LAST:event_btn_Salir1ActionPerformed

    private void btn_guardar_fact2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_fact2ActionPerformed
        // TODO add your handling code here:
        String motivo = txt_motivo.getText().toString();
        DefaultTableModel order_list_guardar = new DefaultTableModel();
        subtotal_static = (Float.parseFloat(txt_subtotal.getText()));
        Descuento_static = (Float.parseFloat(txt_descto.getText()));
        Total_static = (Float.parseFloat(txt_total.getText()));
        int i = 0;
        String codigo_a_guardar;
        int cantidad = 0 ;
        int codigo_obtenido;
        String s = factura.Eliminar_Factura(id_de_la_factura,USUARIO,motivo);  //FALATA PONER MOTIVO   
        String s2 = factura.Update(id_de_la_factura);
        System.out.println("" + s2);
   
        /*Neceista el id para guardar todos los productos de una factura*/
        order_list_guardar = (DefaultTableModel) Tabla_ventas.getModel();
        int numero_filas = order_list_guardar.getRowCount();
            for(i=0;i<numero_filas;i++){
                int cantidad_actual = 0;
                int nueva_cantidad = 0;
                codigo_a_guardar = Tabla_ventas.getValueAt(i, 0).toString();
                cantidad = Integer.parseInt(Tabla_ventas.getValueAt(i, 3).toString());
                //factura.Guardar_Detalle_Factura(codigo_obtenido, codigo_a_guardar, cantidad); 
                inv.Ingresar_Inventario_Anulacion(codigo_a_guardar,cantidad);
                cantidad_actual = inv.get_cantidad_total_producto(codigo_a_guardar);
                nueva_cantidad = (cantidad_actual + cantidad) ;
                inv.Incremeneto_total_producto(codigo_a_guardar , nueva_cantidad );
            }
         String totalsaldo =  txt_total.getText().toString();
            
        JOptionPane.showMessageDialog(null, "FACTURA ELIMINADA EXITOSAMENTE, SU SALDO A FAVOR ES :"+totalsaldo+"" , "SALDO A FAVOR" , JOptionPane.INFORMATION_MESSAGE);  
        GUI_Factura_SALDO ve  = new GUI_Factura_SALDO(totalsaldo);
        ve.setVisible(true);
        ve.setLocationRelativeTo(null);
        ve.setResizable(false);
        dialog_motivo.dispose();
        dispose();
    }//GEN-LAST:event_btn_guardar_fact2ActionPerformed

    /**
     * @param args the command line arguments
     */

    /**
     *
     * @param args
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_FORMA_PAGO;
    private javax.swing.JPanel Factura_panel;
    private javax.swing.JTable Tabla_ventas;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton btn_Salir1;
    private javax.swing.JButton btn_guardar_fact;
    private javax.swing.JButton btn_guardar_fact1;
    private javax.swing.JButton btn_guardar_fact2;
    private javax.swing.JTextField cbx_Nombre;
    private javax.swing.JDialog dialog_motivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextField txt_descto;
    private javax.swing.JTextField txt_dir;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_iva;
    private javax.swing.JTextField txt_mail;
    private javax.swing.JTextArea txt_motivo;
    private javax.swing.JTextArea txt_nota;
    private javax.swing.JTextField txt_numFactura;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_vaucher;
    private javax.swing.JTextField txt_vendedor;
    // End of variables declaration//GEN-END:variables


   
   
}
