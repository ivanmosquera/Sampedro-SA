/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanpedroproyect;

import Class.EXPORTAREXCEL;
import Class.Mirender;
import Class.Reporte_Operaciones;
import DATABASE.ConnectionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.oxbow.swingbits.table.filter.TableRowFilterSupport;
import static sanpedroproyect.GUI_Factura.total;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Ver_Facturas_Por_FECHA extends javax.swing.JFrame {

    /**
     * Creates new form Reporte_Separados
     */
    DefaultTableModel m  = new DefaultTableModel();
    Reporte_Operaciones rp = new Reporte_Operaciones();
    
    public Ver_Facturas_Por_FECHA() {
        initComponents();
        //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);  
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String Desc;
        try{
            String sql = ("SELECT Usuario FROM usuario ");
            pst = cn.prepareStatement(sql);
            rs =pst.executeQuery();
             while(rs.next()){
                    
                    cmb_usuario.addItem(rs.getString("Usuario"));
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

        FORMA_BUSQUEDA = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_ver_facturas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btn_excel = new javax.swing.JButton();
        dateDesde = new com.toedter.calendar.JDateChooser();
        dateHasta = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lblTotalFecha = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cmb_usuario = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Tbl_ver_facturas = new javax.swing.JTable(){
            public  boolean isCellEditable(int rowIndex , int colIndex){
                return false;
            }
        };
        Tbl_ver_facturas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Tbl_ver_facturas.setModel(new javax.swing.table.DefaultTableModel(
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
        Tbl_ver_facturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tbl_ver_facturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tbl_ver_facturas);

        jButton1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_excel.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon.png"))); // NOI18N
        btn_excel.setText("Exportar Excel");
        btn_excel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_excel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excelActionPerformed(evt);
            }
        });

        dateDesde.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        dateHasta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel12.setText("Desde :");

        jLabel13.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel13.setText("Hasta :");

        jLabel14.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel14.setText("Facturas Por Fecha ");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Consultar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel15.setText("TOTAL :");

        lblTotalFecha.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel17.setText("$");

        jLabel16.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel16.setText("Usuario :");

        cmb_usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_usuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione Usuario>" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(btn_excel)
                        .addGap(48, 48, 48)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel16))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(dateHasta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                            .addComponent(dateDesde, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(79, 79, 79)
                                        .addComponent(jButton2))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(jLabel14)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel14)
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cmb_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(dateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblTotalFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(btn_excel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void Tbl_ver_facturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_ver_facturasMouseClicked
        // TODO add your handling code here:
        int index= Tbl_ver_facturas.getSelectedRow();
        TableModel model = Tbl_ver_facturas.getModel();
        int IDFACTURA = Integer.parseInt(model.getValueAt(index, 0).toString());
        Detalle_Factura de = new Detalle_Factura(IDFACTURA);
        de.setVisible(true);
        de.setResizable(false);
        de.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_Tbl_ver_facturasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excelActionPerformed
        // TODO add your handling code here:
        EXPORTAREXCEL ex = new EXPORTAREXCEL();
        try {
            ex.exportarExcel(Tbl_ver_facturas);
        } catch (IOException ex1) {
            Logger.getLogger(Reporte_Inventario.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }//GEN-LAST:event_btn_excelActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (cmb_usuario.getSelectedItem().equals("<Seleccione Usuario>")){
            Date date = new Date(); 
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateDesde.getDate();
            String desde = dateFormat.format(date);
            System.out.println(desde);
            date = dateHasta.getDate();
            String hasta = dateFormat.format(date);
            System.out.println(hasta);
            DefaultTableModel nm =  new DefaultTableModel();
            nm = rp.consultar_Factura_fecha(desde, hasta);
            Tbl_ver_facturas.setModel(nm);   
            float total = rp.getTotal_Fechas(desde, hasta);
            String stotal = String.format(java.util.Locale.US,"%.2f", total);
            lblTotalFecha.setText(stotal);
        }else{
            String user = cmb_usuario.getSelectedItem().toString();
            Date date = new Date(); 
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateDesde.getDate();
            String desde = dateFormat.format(date);
            System.out.println(desde);
            date = dateHasta.getDate();
            String hasta = dateFormat.format(date);
            System.out.println(hasta);
            DefaultTableModel nm =  new DefaultTableModel();
            nm = rp.consultar_Factura_fecha_usuario(desde, hasta,user);
            Tbl_ver_facturas.setModel(nm);   
            float total = rp.getTotal_Fechas_Usuario(desde, hasta, user);
            String stotal = String.format(java.util.Locale.US,"%.2f", total);
            lblTotalFecha.setText(stotal);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup FORMA_BUSQUEDA;
    private javax.swing.JTable Tbl_ver_facturas;
    private javax.swing.JButton btn_excel;
    private javax.swing.JComboBox cmb_usuario;
    private com.toedter.calendar.JDateChooser dateDesde;
    private com.toedter.calendar.JDateChooser dateHasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalFecha;
    // End of variables declaration//GEN-END:variables
}
