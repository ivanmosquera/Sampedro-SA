/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanpedroproyect;

import Class.Detalle_Cierre_Abono;
import Class.Detalle_Cierre_Anulada;
import Class.Detalle_Cierre_Caja;
import Class.Mirender;
import Class.Productos;
import Class.Reporte_Operaciones;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import static sanpedroproyect.GUI_Factura.total;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Cierre_Caja extends javax.swing.JFrame {

    /**
     * Creates new form Reporte_Separados
     */
    DefaultTableModel m  = new DefaultTableModel();
    DefaultTableModel a  = new DefaultTableModel();
    DefaultTableModel s  = new DefaultTableModel();
    DefaultTableModel anulaciones   = new DefaultTableModel();
    Reporte_Operaciones rp = new Reporte_Operaciones();
    float total_ventas,total_anuladas;
    double total_efectivo , total_tarjeta,total_saldo;
    double total_efectivo_a , total_tarjeta_a;
    public Cierre_Caja() {
        initComponents();
        m = rp.consultar_Factura_fechaactual();
        Tbl_ventas_hoy.setModel(m);
        total_ventas = rp.getTotal();
        String total = String.format(java.util.Locale.US,"%.2f", total_ventas);
        lbl_ventas_hoy.setText(String.valueOf(total));
        total_efectivo = rp.getTotal_pago_efectivo();
        total_tarjeta = rp.getTotal_pago_tarjeta();
        total_saldo = rp.getTotal_Saldos();
        String total_efectivo_double = String.format(java.util.Locale.US,"%.2f", total_efectivo);
        String total_tarjeta_double = String.format(java.util.Locale.US,"%.2f", total_tarjeta);
        String total_efectivo_saldos = String.format(java.util.Locale.US,"%.2f", total_saldo);
        lbl_efectivo_hoy.setText(total_efectivo_double);
        lbl_tarjeta_hoy.setText(total_tarjeta_double);
        txt_saldos_hoy.setText(total_efectivo_saldos);
        
        
        a = rp.consultar_Factura_fechaactual_anuladas();
        tbl_anuladas_hoy.setModel(a);
        total_anuladas = rp.getTotal_anuladas();
        String totala = String.format(java.util.Locale.US,"%.2f", total_anuladas);
        lbl_Anuladas_hoy.setText(String.valueOf(totala));
        total_efectivo_a = rp.getTotal_pago_efectivo_anuladas();
        total_tarjeta_a = rp.getTotal_pago_tarjeta_anuladas();
        String total_efectivo_double_a = String.format(java.util.Locale.US,"%.2f", total_efectivo_a);
        String total_tarjeta_double_a = String.format(java.util.Locale.US,"%.2f", total_tarjeta_a);
        lbl_efectivo_hoy_a.setText(total_efectivo_double_a);
        lbl_tarjeta_hoy_a.setText(total_tarjeta_double_a);
        
        anulaciones = rp.consultar_Factura_fechaactual_anuladas_cambios();
        tbl_anuladas_cambios.setModel(anulaciones);
        
        
        s = rp.consultar_abonos();
        tbl_abonos_hoy.setModel(s);
        float total_abono = rp.getTotal_abonos_hoy();
        lbl_abono.setText(String.valueOf(total_abono));
        
        double totalcaja = total_abono + total_efectivo  ;
        lbl_total_caja.setText(String.valueOf(totalcaja));
        
        double totaltarjeta = total_tarjeta - total_tarjeta_a;
        String total_tarjeta_decimales = String.format(java.util.Locale.US,"%.2f", totaltarjeta);
        lbl_tarjeta_total.setText(String.valueOf(total_tarjeta_decimales));
        
        double totalneto = totalcaja + totaltarjeta ;
        String total_decimales = String.format(java.util.Locale.US,"%.2f", totalneto);
        lbl_total.setText(String.valueOf(total_decimales));
        
      
        
        //String totalb = String.format(java.util.Locale.US,"%.2f", total_anuladas);
        //lbl_Anuladas_hoy.setText(String.valueOf(totala));
        //total_efectivo_a = rp.getTotal_pago_efectivo_anuladas();
        //total_tarjeta_a = rp.getTotal_pago_tarjeta_anuladas();
        //String total_efectivo_double_b = String.format(java.util.Locale.US,"%.2f", total_efectivo_a);
        //String total_tarjeta_double_b = String.format(java.util.Locale.US,"%.2f", total_tarjeta_a);
        //lbl_efectivo_hoy_a.setText(total_efectivo_double_a);
        //lbl_tarjeta_hoy_a.setText(total_tarjeta_double_a);
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_ventas_hoy = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_ventas_hoy = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_anuladas_hoy = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lbl_Anuladas_hoy = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        btn_imprimir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lbl_total_caja = new javax.swing.JLabel();
        lbl_efectivo = new javax.swing.JLabel();
        lbl_efectivo1 = new javax.swing.JLabel();
        lbl_efectivo_hoy = new javax.swing.JLabel();
        lbl_tarjeta_hoy = new javax.swing.JLabel();
        lbl_efectivo2 = new javax.swing.JLabel();
        lbl_efectivo3 = new javax.swing.JLabel();
        lbl_efectivo_hoy_a = new javax.swing.JLabel();
        lbl_tarjeta_hoy_a = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_abonos_hoy = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        lbl_abono = new javax.swing.JLabel();
        lbl_efectivo4 = new javax.swing.JLabel();
        lbl_tarjeta_total = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_anuladas_cambios = new javax.swing.JTable();
        lbl_efectivo5 = new javax.swing.JLabel();
        txt_saldos_hoy = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Tbl_ventas_hoy.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Tbl_ventas_hoy);

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel1.setText("Cierre De Caja");

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel2.setText("Ventas Realizadas Día de hoy :");

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setText("Sumatoria Total de Ventas : ");

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel4.setText("Anulaciones de Fecha Actual");

        tbl_anuladas_hoy.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_anuladas_hoy);

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel5.setText("Sumatoria Facturas Anuladas : ");

        btn_salir.setFont(new java.awt.Font("Bookman Old Style", 1, 12)); // NOI18N
        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        btn_imprimir.setFont(new java.awt.Font("Bookman Old Style", 1, 12)); // NOI18N
        btn_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/impresora.png"))); // NOI18N
        btn_imprimir.setText("Imprimir");
        btn_imprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_imprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel6.setText("Total en Efectivo:");

        lbl_efectivo.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_efectivo.setText("Total en Efectivo de Ventas:");

        lbl_efectivo1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_efectivo1.setText("Total en Tarjeta de Ventas :");

        lbl_efectivo2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_efectivo2.setText("Total en Efectivo Anuladas :");

        lbl_efectivo3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_efectivo3.setText("Total en Tarjeta Anuladas  :");

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel7.setText("Abonos realizados");

        tbl_abonos_hoy.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbl_abonos_hoy);

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel8.setText("Sumatoria Abonos : ");

        lbl_efectivo4.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_efectivo4.setText("Total en Tarjeta :");

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel9.setText("TOTAL:");

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel10.setText("Anulaciones por cambio de prenda ");

        tbl_anuladas_cambios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbl_anuladas_cambios);

        lbl_efectivo5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        lbl_efectivo5.setText("Total de Saldos :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(451, 451, 451)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(377, 377, 377)
                .addComponent(btn_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 407, Short.MAX_VALUE)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(465, 465, 465))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_ventas_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbl_efectivo3)
                                        .addComponent(lbl_efectivo2)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_efectivo_hoy_a, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_tarjeta_hoy_a, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_Anuladas_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbl_efectivo5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_saldos_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbl_efectivo1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_tarjeta_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbl_efectivo)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbl_efectivo_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(226, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_abono, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_efectivo4)))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_total_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_tarjeta_total, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(lbl_ventas_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(lbl_efectivo))
                            .addComponent(lbl_efectivo_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_efectivo1)
                            .addComponent(lbl_tarjeta_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_efectivo5)
                            .addComponent(txt_saldos_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lbl_Anuladas_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_efectivo2)
                                .addGap(39, 39, 39)
                                .addComponent(lbl_efectivo3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_efectivo_hoy_a, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(lbl_tarjeta_hoy_a, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(lbl_abono, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(lbl_total_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_efectivo4)
                            .addComponent(lbl_tarjeta_total, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(lbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_salir)
                    .addComponent(btn_imprimir))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
       /* Main_Menu ventana_menuPrincipal = new Main_Menu();
        ventana_menuPrincipal.setVisible(true);
        ventana_menuPrincipal.setLocationRelativeTo(null);
        ventana_menuPrincipal.setResizable(false);*/
        dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirActionPerformed
        // TODO add your handling code here:
        priceInvoice();
        
    }//GEN-LAST:event_btn_imprimirActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tbl_ventas_hoy;
    private javax.swing.JButton btn_imprimir;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_Anuladas_hoy;
    private javax.swing.JLabel lbl_abono;
    private javax.swing.JLabel lbl_efectivo;
    private javax.swing.JLabel lbl_efectivo1;
    private javax.swing.JLabel lbl_efectivo2;
    private javax.swing.JLabel lbl_efectivo3;
    private javax.swing.JLabel lbl_efectivo4;
    private javax.swing.JLabel lbl_efectivo5;
    private javax.swing.JLabel lbl_efectivo_hoy;
    private javax.swing.JLabel lbl_efectivo_hoy_a;
    private javax.swing.JLabel lbl_tarjeta_hoy;
    private javax.swing.JLabel lbl_tarjeta_hoy_a;
    private javax.swing.JLabel lbl_tarjeta_total;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JLabel lbl_total_caja;
    private javax.swing.JLabel lbl_ventas_hoy;
    private javax.swing.JTable tbl_abonos_hoy;
    private javax.swing.JTable tbl_anuladas_cambios;
    private javax.swing.JTable tbl_anuladas_hoy;
    private javax.swing.JLabel txt_saldos_hoy;
    // End of variables declaration//GEN-END:variables
private void priceInvoice(){
        //String sourcefile = "/Users/kleberstevendiazcoello/Documents/GitHub/Sampedro-SA/SamPedroProyect/src/sanpedroproyect/FACTURA_IMPRIMIR.jrxml";
        
        DefaultTableModel order_list = new DefaultTableModel();
         DefaultTableModel order_list_a = new DefaultTableModel();
         DefaultTableModel order_list_b = new DefaultTableModel();
        String id, fecha,  cliente, subtotal, total,efectivo, tarjeta;
        String id_a, fecha_a,  cliente_a, subtotal_a, total_a,efectivo_a, tarjeta_a;
        String id_b, fecha_b,  cliente_b, subtotal_b, total_b,efectivo_b, tarjeta_b;
        
        InputStream is = (InputStream)this.getClass().getClassLoader().getResourceAsStream("sanpedroproyect/Cierre_Caja_i.jrxml");
        
        try {
            int i = 0;
            JasperReport jr = JasperCompileManager.compileReport(is);
            HashMap<String,Object> para = new HashMap<>();
            para.put("TOTALVENTAS",lbl_ventas_hoy.getText());
            para.put("EFECTIVO", lbl_efectivo_hoy.getText());
            para.put("TARJETA", lbl_tarjeta_hoy.getText());
            para.put("TOTALANULADAS",lbl_Anuladas_hoy.getText());
            para.put("EFECTIVOA", lbl_efectivo_hoy_a.getText());
            para.put("TARJETAA", lbl_tarjeta_hoy_a.getText());
            para.put("TOTALCAJA",lbl_total_caja.getText());
            para.put("TOTALABONADO",lbl_abono.getText());
            
            order_list = (DefaultTableModel) Tbl_ventas_hoy.getModel();
            int numero_filas = order_list.getRowCount();
            ArrayList<Detalle_Cierre_Caja> plist = new ArrayList<>();

            for(i=0;i<numero_filas;i++){
                id = Tbl_ventas_hoy.getValueAt(i, 0).toString();
                fecha = Tbl_ventas_hoy.getValueAt(i, 1).toString();
                cliente = Tbl_ventas_hoy.getValueAt(i, 2).toString();
                subtotal = Tbl_ventas_hoy.getValueAt(i, 3).toString();
                total = Tbl_ventas_hoy.getValueAt(i,4).toString();
                efectivo = Tbl_ventas_hoy.getValueAt(i,5).toString();    
                tarjeta = Tbl_ventas_hoy.getValueAt(i,6).toString(); 
                String tipo = "VENTA";
                plist.add(new Detalle_Cierre_Caja(id, fecha,  cliente, subtotal, total,efectivo, tarjeta,tipo));
  
            }
            
            
            /*order_list_a = (DefaultTableModel) tbl_anuladas_hoy.getModel();
            int numero_filas_a = order_list_a.getRowCount();
            //ArrayList<Detalle_Cierre_Caja> plist = new ArrayList<>();

            for(i=0;i<numero_filas_a;i++){
                id_a = tbl_anuladas_hoy.getValueAt(i, 0).toString();
                fecha_a = tbl_anuladas_hoy.getValueAt(i, 1).toString();
                cliente_a = tbl_anuladas_hoy.getValueAt(i, 2).toString();
                subtotal_a = tbl_anuladas_hoy.getValueAt(i, 3).toString();
                total_a = tbl_anuladas_hoy.getValueAt(i,4).toString();
                efectivo_a = tbl_anuladas_hoy.getValueAt(i,5).toString();    
                tarjeta_a = tbl_anuladas_hoy.getValueAt(i,6).toString(); 
                String tipoanulado = "ANULADO";
                plist.add(new Detalle_Cierre_Caja(id_a, fecha_a,  cliente_a, subtotal_a, total_a,efectivo_a, tarjeta_a,tipoanulado));
  
            }*/
            
            
           /* order_list_b = (DefaultTableModel) tbl_abonos_hoy.getModel();
            int numero_filas_ab = order_list_b.getRowCount();
            //ArrayList<Detalle_Cierre_Abono> plist_ab = new ArrayList<>();
            for(i=0;i<numero_filas_ab;i++){
                id_b = tbl_abonos_hoy.getValueAt(i, 0).toString();
                cliente_b = Tbl_ventas_hoy.getValueAt(i, 1).toString();
                total_b = tbl_abonos_hoy.getValueAt(i, 2).toString();
                fecha_b = tbl_abonos_hoy.getValueAt(i, 4).toString();
                subtotal_b = "0";
                efectivo_b = "0";
                tarjeta_b = "0";
                String tipoabono = "ABONO";
                plist.add(new Detalle_Cierre_Caja(id_b, fecha_b,  cliente_b, subtotal_b, total_b,efectivo_b, tarjeta_b,tipoabono));
  
            }*/
            
            
            
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs );
            JasperViewer.viewReport(jp,false);
            
            
            
           
            
        } catch (JRException ex) {
            Logger.getLogger(GUI_Factura.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("El ERROES ES ESTE "+ex);
        }
        
    }





}

