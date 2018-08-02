/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanpedroproyect;

import Class.Inventario;
import Class.Mirender;
import Class.Operaciones;
import Class.Reporte_Operaciones;
import Class.SepararPrenda;
import Class.abono;
import DATABASE.ConnectionDB;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;
import static sanpedroproyect.Abonar.id_separado;
import static sanpedroproyect.GUI_Factura.total;

/**
 *
 * @author kleberstevendiazcoello
 */
public class abono_report extends javax.swing.JFrame {

    /**
     * Creates new form Reporte_Separados
     */
    DefaultTableModel m  = new DefaultTableModel();
    Reporte_Operaciones rp = new Reporte_Operaciones();
    abono a = new abono();
    Inventario i = new Inventario();
    Operaciones op = new Operaciones();
    SepararPrenda sp = new SepararPrenda();
    String id_producto_viejo;
    int id_Separado;
    int id_cliente_obtenido;
    float saldo_actual,total_abono;
    String totalabono_s;
    public abono_report(int separado_id) {
        initComponents();
        //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        m = a.consultar_abonos(separado_id);
        Tbl_abono.setModel(m);
        total_abono = a.getTotal(separado_id);
        totalabono_s = String.format(java.util.Locale.US,"%.2f", total_abono);
        lbl_abono.setText(totalabono_s);
        btn_generar_fac.setEnabled(false);
         String resul = null , lats = null;
         ConnectionDB cc = new ConnectionDB();
         Connection cn = cc.getConnection();
         PreparedStatement pst =null;
         ResultSet rs = null;

            try{
               String sql = ("SELECT p.id_Producto,c.id_Cliente,p.Talla,p.Descripcion,c.Nombre, s.saldo , p.Precio,s.id_Separado "
                       + "FROM separado  s ,cliente c , detalle_separado d , producto p "
                       + "where  id_cliente = fk_cliente "
                       + "and d.fk_separado = s.id_Separado and fk_producto = id_Producto  "
                       + "and s.id_Separado = ? LIMIT 0,1");

                pst = cn.prepareStatement(sql);
                pst.setInt(1, separado_id);
                rs =pst.executeQuery();
                if (rs.next()){
                    id_producto_viejo = rs.getString("id_Producto");
                    lbl_precio.setText(rs.getString("Precio"));
                    lbl_talla.setText(rs.getString("Talla"));
                    lbl_prenda.setText(rs.getString("Descripcion"));
                    lbl_cliente.setText(rs.getString("Nombre"));
                    lbl_saldo.setText(rs.getString("Saldo"));
                    id_Separado = rs.getInt("id_Separado");
                    id_cliente_obtenido = rs.getInt("id_Cliente");
                    saldo_actual =   Float.parseFloat(rs.getString("Saldo")); 
                }



            } catch (Exception ex){
                            System.out.println(ex);
            }
        cc.desconectar();
        
        if(Float.parseFloat(lbl_saldo.getText()) <= 0){
            btn_generar_fac.setEnabled(true);
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

        Dialog_buscar_pro = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        cmb_producto = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_producto = new javax.swing.JTable();
        btn_agregar_producto = new javax.swing.JButton();
        btn_SalirProducto = new javax.swing.JButton();
        cmb_CodoPre = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_abono = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lbl_abono = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_cliente = new javax.swing.JLabel();
        lbl_prenda = new javax.swing.JLabel();
        lbl_talla = new javax.swing.JLabel();
        lbl_precio = new javax.swing.JLabel();
        lbl_saldo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_generar_fac = new javax.swing.JButton();
        btn_eliminar_separado = new javax.swing.JButton();
        btn_eliminar_separado1 = new javax.swing.JButton();

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
        btn_agregar_producto.setText("OK");
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
                        .addGap(173, 173, 173)
                        .addComponent(btn_agregar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Tbl_abono.setModel(new javax.swing.table.DefaultTableModel(
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
        Tbl_abono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tbl_abonoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tbl_abono);

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setText("Detalle de Abonos : ");

        jButton1.setFont(new java.awt.Font("Bookman Old Style", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel3.setText("Detalle de Abonos");

        lbl_abono.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel4.setText("$");

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setText("Cliente : ");

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel5.setText("Prenda :");

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel6.setText("Talla    : ");

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel7.setText("Precio :");

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel8.setText("Saldo :");

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel9.setText("$");

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel10.setText("$");

        btn_generar_fac.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_generar_fac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/recepcion.png"))); // NOI18N
        btn_generar_fac.setText("Generar Factura");
        btn_generar_fac.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_generar_fac.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_generar_fac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generar_facActionPerformed(evt);
            }
        });

        btn_eliminar_separado.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_eliminar_separado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-x.png"))); // NOI18N
        btn_eliminar_separado.setText("Eliminar Separado");
        btn_eliminar_separado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminar_separado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_eliminar_separado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_separadoActionPerformed(evt);
            }
        });

        btn_eliminar_separado1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_eliminar_separado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btn_eliminar_separado1.setText("Cambiar Prenda");
        btn_eliminar_separado1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminar_separado1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_eliminar_separado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_separado1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_precio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_talla, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lbl_prenda, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbl_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lbl_abono, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_eliminar_separado, javax.swing.GroupLayout.PREFERRED_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(btn_generar_fac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_eliminar_separado1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jLabel3)))
                .addGap(59, 59, 59))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lbl_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(lbl_prenda, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lbl_talla, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(lbl_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_abono, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(lbl_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_generar_fac)
                        .addGap(26, 26, 26)
                        .addComponent(btn_eliminar_separado)
                        .addGap(26, 26, 26)
                        .addComponent(btn_eliminar_separado1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //float saldof =Float.parseFloat(lbl_saldo.getText());
        //String S2 =  sp.Update(saldof,id_separado);
        //System.out.println(S2);
        Reporte_Separados rs = new Reporte_Separados();
        rs.setVisible(true);
        rs.setLocationRelativeTo(null);
        rs.setResizable(false);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Tbl_abonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_abonoMouseClicked

    }//GEN-LAST:event_Tbl_abonoMouseClicked

    private void btn_generar_facActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generar_facActionPerformed
        // TODO add your handling code here:
       
        Factura_Prenda_Separada fns = new Factura_Prenda_Separada(id_cliente_obtenido,id_producto_viejo,id_Separado);
        fns.setVisible(true);
        fns.setLocationRelativeTo(null);
        fns.setResizable(false);
        dispose();
    }//GEN-LAST:event_btn_generar_facActionPerformed

    private void btn_eliminar_separadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_separadoActionPerformed
        a.Eliminar_Separado(id_Separado);
        int total_anterior = i.get_cantidad_total_producto(id_producto_viejo);
        int cantidad = total_anterior + 1;
        i.Incremeneto_total_producto(id_producto_viejo , cantidad);
        i.Aumento_inventario_Devolucion_Separado(id_producto_viejo, 1);
        Reporte_Separados rs = new Reporte_Separados();
        rs.setVisible(true);
        rs.setLocationRelativeTo(null);
        rs.setResizable(false);
        dispose();
       
        
        
    }//GEN-LAST:event_btn_eliminar_separadoActionPerformed

    private void btn_eliminar_separado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_separado1ActionPerformed
        // TODO add your handling code here:
        m = rp.consultar_producto();
        tabla_producto.setModel(m);
        Dialog_buscar_pro.setSize(700, 500);
        Dialog_buscar_pro.setLocationRelativeTo(null);
        Dialog_buscar_pro.setVisible(true);
    }//GEN-LAST:event_btn_eliminar_separado1ActionPerformed

    private void btn_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_productoActionPerformed
        // TODO add your handling code here:
        int fsel = tabla_producto.getSelectedRow();
        String codigo_prenda_cambio;
        float nuevo_saldo, preciofloat;
        try {
            String codigo, descripcion, precio, cantidad,importe,talla;
            float x;
            float calcula;
            float iva;

            if(fsel==-1){
                JOptionPane.showMessageDialog(null,"Dese seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
            }

                m = (DefaultTableModel) tabla_producto.getModel();
                codigo = tabla_producto.getValueAt(fsel, 0).toString();
                descripcion = tabla_producto.getValueAt(fsel, 1).toString();
                precio = tabla_producto.getValueAt(fsel, 2).toString();
                talla = tabla_producto.getValueAt(fsel, 3).toString();
                preciofloat = Float.parseFloat(precio);
                int total_anterior_ca = i.get_cantidad_total_producto(id_producto_viejo);
                int cantidad_ca = total_anterior_ca + 1;
                i.Incremeneto_total_producto(id_producto_viejo , cantidad_ca);
                i.Aumento_inventario_Devolucion_Separado(id_producto_viejo, 1);
                
                
                String s1 = sp.Update_Prenda(codigo,id_Separado);
                System.out.println(s1);
                i.Decremento_inventario_Separado(codigo,1);
                int cantidad_actual = i.get_cantidad_total_producto(codigo);
                int nueva_cantidad = (cantidad_actual - 1) ;
                i.Incremeneto_total_producto(codigo , nueva_cantidad );
                
                nuevo_saldo = preciofloat - total_abono;
                
                
                String nuevosaldo = String.format(java.util.Locale.US,"%.2f", nuevo_saldo);
                
                Dialog_buscar_pro.dispose();
                lbl_precio.setText(precio);
                lbl_talla.setText(talla);
                lbl_prenda.setText(descripcion);
                lbl_saldo.setText(nuevosaldo);
                float saldof =Float.parseFloat(lbl_saldo.getText());
                /*System.out.println(saldof);
                System.out.println(id_Separado);
                String S2 =  sp.Update(saldof,id_separado);
                System.out.println(S2);*/
                 String resul = null , lats = null;
                ConnectionDB cc = new ConnectionDB();
                Connection cn = cc.getConnection();
                PreparedStatement pst =null;
                String sql = "UPDATE separado SET Saldo= ? WHERE id_Separado = ? ";
                ResultSet rs = null;
                try{
                    pst = cn.prepareStatement(sql);
                    pst.setFloat(1,saldof);
                    pst.setInt(2,id_Separado);
                    pst.execute();
                    resul = "Update abono correcto";
                    System.out.println(resul);

                }catch(SQLException e){
                    resul = "Error  update abono: "+e; 
                    System.out.println(resul);
                }

               cc.desconectar();





                } catch (Exception e) {
                }
    }//GEN-LAST:event_btn_agregar_productoActionPerformed

    private void btn_SalirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirProductoActionPerformed
        Dialog_buscar_pro.dispose();
    }//GEN-LAST:event_btn_SalirProductoActionPerformed

    private void cmb_CodoPreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_CodoPreItemStateChanged
        // TODO add your handling code here:
        if(cmb_CodoPre.getSelectedItem().equals("Descripcion")){

            cmb_producto.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(java.awt.event.KeyEvent e) {
                    String cadena = cmb_producto.getEditor().getItem().toString();
                    if(e.getKeyCode() == e.VK_ENTER){
                        String name = cmb_producto.getEditor().getItem().toString();
                        m = rp.consultar_producto_name(name);
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

        }else if(cmb_CodoPre.getSelectedItem().equals("Descripcion")){
            cmb_producto.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(java.awt.event.KeyEvent e) {
                    //String cadena = cmb_producto.getEditor().getItem().toString();
                    String cadena = cmb_producto.getEditor().getItem().toString();
                    if(e.getKeyCode() == e.VK_ENTER){
                       String codigo_producto = cmb_producto.getEditor().getItem().toString();
                        m = rp.consultar_producto_codigo(codigo_producto);
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
    }//GEN-LAST:event_cmb_CodoPreItemStateChanged

    private void cmb_CodoPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_CodoPreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_CodoPreActionPerformed

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_buscar_pro;
    private javax.swing.JTable Tbl_abono;
    private javax.swing.JButton btn_SalirProducto;
    private javax.swing.JButton btn_agregar_producto;
    private javax.swing.JButton btn_eliminar_separado;
    private javax.swing.JButton btn_eliminar_separado1;
    private javax.swing.JButton btn_generar_fac;
    private javax.swing.JComboBox cmb_CodoPre;
    private javax.swing.JComboBox cmb_producto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel lbl_abono;
    private javax.swing.JLabel lbl_cliente;
    private javax.swing.JLabel lbl_precio;
    private javax.swing.JLabel lbl_prenda;
    private javax.swing.JLabel lbl_saldo;
    private javax.swing.JLabel lbl_talla;
    private javax.swing.JTable tabla_producto;
    // End of variables declaration//GEN-END:variables
}
