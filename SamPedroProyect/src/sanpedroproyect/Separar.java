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
import Class.SepararPrenda;
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
import java.util.Calendar;
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
import static sanpedroproyect.Separar.total;

/**
 *
 * @author Pantheon
 */
public class Separar extends javax.swing.JFrame {
    
    static Integer Codigo;
    SepararPrenda separar = new SepararPrenda();
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



    /**
     * Creates new form GUI_Factura
     */
    Double abono;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat df  = DateFormat.getDateInstance();
    public Separar() {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        USUARIO = menu_Cod.getCodigo_usuario();
        Ingreso_Nuevo_Cliente.validarCedula(txt_cedula);
        txt_vendedor.setText(Main_Menu.nombre_usuario);
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
        
        txt_abono.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                abono = (Double.parseDouble(txt_abono.getText()));
                sub_total =Double.parseDouble(txt_subtotal.getText());
                total = (sub_total - abono);
                stotal = String.format(java.util.Locale.US,"%.2f", total);
                txt_saldo.setText(stotal);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(txt_abono.getText().isEmpty()){
                    desc = 0;
                }else{
                   abono = (Double.parseDouble(txt_abono.getText()));
                }
                sub_total =Double.parseDouble(txt_subtotal.getText());
                total = sub_total - ((sub_total * desc)/100);
                stotal = String.format(java.util.Locale.US,"%.2f", total);
                txt_saldo.setText(stotal);
                if(txt_abono.getText().isEmpty()){
                    txt_abono.setText(txt_subtotal.getText());
                }
 
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
              
            } ); 
        
        
        
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
        cmb_CodoPre = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        Dialog_seleccionar_fecha = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        Factura_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_cedula = new javax.swing.JTextField();
        txt_vendedor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbx_Nombre = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        txt_abono = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_nota = new javax.swing.JTextArea();
        btn_Limpiar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla_ventas = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        btn_guardar_fact = new javax.swing.JButton();
        txt_saldo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

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

        cmb_CodoPre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione Metodo>", "Descripcion", "Codigo" }));
        cmb_CodoPre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_CodoPreItemStateChanged(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel20.setText("Busqueda Por :");

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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(btn_agregar_producto)
                                .addGap(49, 49, 49)
                                .addComponent(btn_SalirProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_CodoPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_CodoPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregar_producto)
                    .addComponent(btn_SalirProducto))
                .addContainerGap(67, Short.MAX_VALUE))
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Seleccione la Fecha de vencimiento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel1)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Dialog_seleccionar_fechaLayout = new javax.swing.GroupLayout(Dialog_seleccionar_fecha.getContentPane());
        Dialog_seleccionar_fecha.getContentPane().setLayout(Dialog_seleccionar_fechaLayout);
        Dialog_seleccionar_fechaLayout.setHorizontalGroup(
            Dialog_seleccionar_fechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Dialog_seleccionar_fechaLayout.setVerticalGroup(
            Dialog_seleccionar_fechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel19.setText("Separar Prenda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addContainerGap())
        );

        Factura_panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel3.setText("Cliente");

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel5.setText("Cédula/RUC");

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel6.setText("Vendedor");

        txt_cedula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cedulaKeyReleased(evt);
            }
        });

        txt_vendedor.setEditable(false);
        txt_vendedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbx_Nombre.setEditable(true);
        cbx_Nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel10.setText("Subtotal");

        txt_subtotal.setEditable(false);
        txt_subtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_abono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_abono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_abonoActionPerformed(evt);
            }
        });
        txt_abono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_abonoKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel13.setText("Abono");

        jLabel14.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel14.setText("NOTA :");

        txt_nota.setColumns(20);
        txt_nota.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nota.setRows(5);
        jScrollPane1.setViewportView(txt_nota);

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

        Tabla_ventas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        btn_guardar_fact.setText("Guardar ");
        btn_guardar_fact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar_fact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_factActionPerformed(evt);
            }
        });

        txt_saldo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_saldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_saldoKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel15.setText("Saldo");

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

        javax.swing.GroupLayout Factura_panelLayout = new javax.swing.GroupLayout(Factura_panel);
        Factura_panel.setLayout(Factura_panelLayout);
        Factura_panelLayout.setHorizontalGroup(
            Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(34, 34, 34)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_vendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(cbx_Nombre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(199, 199, 199)
                        .addComponent(jLabel7))
                    .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_abono, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_subtotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(txt_saldo))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_guardar_fact, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_Salir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(98, 98, 98))))
        );
        Factura_panelLayout.setVerticalGroup(
            Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Factura_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbx_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 32, Short.MAX_VALUE)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_abono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28)
                        .addComponent(btn_guardar_fact)))
                .addGap(12, 12, 12)
                .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addComponent(btn_Limpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Salir))
                    .addGroup(Factura_panelLayout.createSequentialGroup()
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(txt_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(Factura_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addContainerGap())
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
    private void limpiar(){
        txt_cedula.setText("");
        txt_subtotal.setText("");
        sub_total = 0;
        txt_abono.setText("");
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
    private void btn_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_productoActionPerformed
        // TODO add your handling code here:
        int fsel = tabla_producto.getSelectedRow();
        try {
            String codigo, descripcion, precio, cantidad,importe,talla;
            double x;
            double calcula = 0.0 , iva = 0.0;
            
            if(fsel==-1){
                JOptionPane.showMessageDialog(null,"Dese seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
            else{
                m = (DefaultTableModel) tabla_producto.getModel();
                codigo = tabla_producto.getValueAt(fsel, 0).toString();
                descripcion = tabla_producto.getValueAt(fsel, 1).toString();
                precio = tabla_producto.getValueAt(fsel, 2).toString();
                talla = tabla_producto.getValueAt(fsel, 3).toString();
                cantidad = "1";
                Dialog_buscar_pro.setVisible(false);
                 x = (Double.parseDouble(precio)) * Integer.parseInt(cantidad);
                 importe = String.valueOf(x);
                 m = (DefaultTableModel) Tabla_ventas.getModel();
                 String filaElemento[] = {
                     codigo,descripcion,talla,cantidad,precio,importe
                 };
                 m.addRow(filaElemento);
                 
                 calcula = (Double.parseDouble(importe));
                 sub_total = sub_total + calcula;
                 //iva = total * (0.12);
                 txt_subtotal.setText(String.valueOf(sub_total));
                 
                 
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
        limpiar();
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_SalirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirProductoActionPerformed
        Dialog_buscar_pro.dispose();
    }//GEN-LAST:event_btn_SalirProductoActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed

        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_guardar_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_factActionPerformed
        // TODO add your handling code here:
        if(txt_subtotal.getText().equals("")||txt_abono.getText().equals("")||txt_saldo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LLENE TODO LOS CAMPOS" , "ERROR AL GUARDAR" , JOptionPane.INFORMATION_MESSAGE);
        }else{
            DefaultTableModel order_list_guardar = new DefaultTableModel();
        subtotal_static = (Float.parseFloat(txt_subtotal.getText()));
        Total_static = (Float.parseFloat(txt_abono.getText()));
        
        int i = 0;
        String codigo_a_guardar;
        int cantidad = 0 ;
        int codigo_obtenido;
        float saldo = Float.parseFloat(txt_saldo.getText());
        
        //String fechaobtenida = dateFormat.format(calendario_dia.getDate());
        //System.out.println("Fecha " + fechaobtenida);
        Date date = new Date(); 
        date = sumarRestarDiasFecha(date, 30);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dia = dateFormat.format(date);
        System.out.println("nueva fecha " + dia);
        
        String s = separar.Separar_prenda(codigo_cliente,1,USUARIO,saldo,dia,txt_nota.getText());   
        System.out.println("" + s);
        
        codigo_obtenido = separar.Get_last_id_separado();
        System.out.println("" + codigo_obtenido);
        
        
        /*Neceista el id para guardar todos los productos de una factura*/
        order_list_guardar = (DefaultTableModel) Tabla_ventas.getModel();
        int numero_filas = order_list_guardar.getRowCount();
            for(i=0;i<numero_filas;i++){
                int  cantidad_actual=0;
                int nueva_cantidad = 0;
                codigo_a_guardar = Tabla_ventas.getValueAt(i, 0).toString();
                cantidad = Integer.parseInt(Tabla_ventas.getValueAt(i, 3).toString());
                separar.Guardar_Detalle_separado(codigo_obtenido, codigo_a_guardar, cantidad);
                separar.Guardar_abono_separado(codigo_obtenido,Total_static,USUARIO);
                inv.Decremento_inventario_Separado(codigo_a_guardar,cantidad);
                cantidad_actual = inv.get_cantidad_total_producto(codigo_a_guardar);
                nueva_cantidad = (cantidad_actual - cantidad) ;
                inv.Incremeneto_total_producto(codigo_a_guardar , nueva_cantidad );

            }
            JOptionPane.showMessageDialog(null, "Prenda Separada Correctamente" , "SEPARADO CORRECTO" , JOptionPane.INFORMATION_MESSAGE);
        limpiar();
        }
        
    }//GEN-LAST:event_btn_guardar_factActionPerformed

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

        }else{
            
            
            
            
        }
            
    }//GEN-LAST:event_cmb_CodoPreItemStateChanged

    private void txt_abonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_abonoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
       if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACKSPACE) && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txt_abono.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_abonoKeyTyped

    private void txt_abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_abonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_abonoActionPerformed

    private void txt_saldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_saldoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
       if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACKSPACE) && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txt_abono.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_saldoKeyTyped

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
                        try{
                           String sql = ("SELECT * FROM cliente where Cedula = ?");
                           pst = cn.prepareStatement(sql);
                           pst.setString(1,cedu);
                           rs =pst.executeQuery();
                           if (rs.next()){
                               cbx_Nombre.getEditor().setItem(rs.getString("Nombre"));
                               txt_cedula.setText(rs.getString("Cedula"));
                           }

                        } catch (Exception ex){
                            System.out.println(ex);
                        }
                    
                }
    }//GEN-LAST:event_txt_cedulaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_buscar_pro;
    private javax.swing.JDialog Dialog_seleccionar_fecha;
    private javax.swing.JPanel Factura_panel;
    private javax.swing.JTable Tabla_ventas;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton btn_SalirProducto;
    private javax.swing.JButton btn_agregar_producto;
    private javax.swing.JButton btn_guardar_fact;
    private javax.swing.JComboBox cbx_Nombre;
    private javax.swing.JComboBox cmb_CodoPre;
    private javax.swing.JComboBox cmb_producto;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabla_producto;
    private javax.swing.JTextField txt_abono;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextArea txt_nota;
    private javax.swing.JTextField txt_saldo;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JTextField txt_vendedor;
    // End of variables declaration//GEN-END:variables

    public Date sumarRestarDiasFecha(Date fecha, int dias){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha); // Configuramos la fecha que se recibe
      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
      return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }
}
