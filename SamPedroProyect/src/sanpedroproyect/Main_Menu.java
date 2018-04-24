/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanpedroproyect;

import DATABASE.ConnectionDB;
import java.sql.Connection;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import static sanpedroproyect.Login.permisos_usuario;

/**
 *
 * @author Pantheon
 */
public class Main_Menu extends javax.swing.JFrame {
    static int codigo_usuario;
    public static String nombre_usuario;
    static int rol_usuario;
    //static String [] permisos_usuario;
    Login l = new Login();
    /**
     * Creates new form Main_MEnu
     */
    public Main_Menu() {
        initComponents();
        lbl_user_name.setText(l.getUsername());
        lbl_nombreUsuario.setText(l.getNombre());
        nombre_usuario = l.getUsername();
        codigo_usuario = l.getCodigo_usuario();
        rol_usuario = l.getRol_usuario();
        //permisos_usuario = l.getPermisos_usuario();
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        btn_go_inventario.setEnabled(false);
        itemInventario_ingresoInventario.setEnabled(false);
        btn_go_RegistroCliente.setEnabled(false);
        //itemCliente_registrarCliente.setEnabled(false);
        itemCliente_modificar_eliminar.setEnabled(false);
        btn_go_registroFactura.setEnabled(false);
        btn_go_anulaciones.setEnabled(false);
        itemFactura_verFacturas.setEnabled(false);
        btn_go_separarPrenda.setEnabled(false);
        btn_go_cierreCaja.setEnabled(false);
        //itemFactura_nuevaFactura.setEnabled(false);
        itemProducto_ingresarProducto.setEnabled(false);
        //itemProducto_separarPrenda.setEnabled(false);
        itemConfig_registrarUsuario.setEnabled(false);
        itemConfig_modificarUsuario.setEnabled(false);
        itemConfig_nuevoRol.setEnabled(false);
        itemConfig_administrarRoles.setEnabled(false);
        verificar_Permisos();
        
        

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
        btn_go_inventario = new javax.swing.JButton();
        btn_go_registroFactura = new javax.swing.JButton();
        btn_go_anulaciones = new javax.swing.JButton();
        btn_go_separarPrenda = new javax.swing.JButton();
        btn_go_cierreCaja = new javax.swing.JButton();
        btn_go_RegistroCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbl_user_name = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_nombreUsuario = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemFactura_verFacturas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemCliente_modificar_eliminar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        itemProducto_ingresarProducto = new javax.swing.JMenuItem();
        itemInventario_ingresoInventario = new javax.swing.JMenuItem();
        itemProducto_abonar = new javax.swing.JMenuItem();
        itemProducto_ingresarCategoria = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        itemFactura_verFacturasFecha = new javax.swing.JMenuItem();
        itemReportes_detalleProductos = new javax.swing.JMenuItem();
        reporteinventario = new javax.swing.JMenuItem();
        reporteproducto = new javax.swing.JMenuItem();
        reporteseparados = new javax.swing.JMenuItem();
        itemReportes_ventaPrendas = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        itemConfig_registrarUsuario = new javax.swing.JMenuItem();
        itemConfig_modificarUsuario = new javax.swing.JMenuItem();
        itemConfig_nuevoRol = new javax.swing.JMenuItem();
        itemConfig_administrarRoles = new javax.swing.JMenuItem();
        itemConfig_cerrarSesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");
        setLocationByPlatform(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_go_inventario.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_go_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/portapapeles.png"))); // NOI18N
        btn_go_inventario.setText("Inventario");
        btn_go_inventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_inventario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_inventarioActionPerformed(evt);
            }
        });

        btn_go_registroFactura.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_go_registroFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/recepcion.png"))); // NOI18N
        btn_go_registroFactura.setText("Nueva Factura");
        btn_go_registroFactura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_registroFactura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_registroFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_registroFacturaActionPerformed(evt);
            }
        });

        btn_go_anulaciones.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_go_anulaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/archivo.png"))); // NOI18N
        btn_go_anulaciones.setText("Anulaciones");
        btn_go_anulaciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_anulaciones.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_anulaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_anulacionesActionPerformed(evt);
            }
        });

        btn_go_separarPrenda.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_go_separarPrenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/vestir.png"))); // NOI18N
        btn_go_separarPrenda.setText("Separar Prenda");
        btn_go_separarPrenda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_separarPrenda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_separarPrenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_separarPrendaActionPerformed(evt);
            }
        });

        btn_go_cierreCaja.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_go_cierreCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/proyecto-de-ley.png"))); // NOI18N
        btn_go_cierreCaja.setText("Cierre De Caja");
        btn_go_cierreCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_cierreCaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_cierreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_cierreCajaActionPerformed(evt);
            }
        });

        btn_go_RegistroCliente.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_go_RegistroCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario.png"))); // NOI18N
        btn_go_RegistroCliente.setText("Registro Cliente");
        btn_go_RegistroCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_RegistroCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_RegistroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_RegistroClienteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel1.setText("Usuario Actual  : ");

        lbl_user_name.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel2.setText("Nombre  : ");

        lbl_nombreUsuario.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_go_separarPrenda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_go_cierreCaja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 623, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_go_RegistroCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_go_registroFactura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_go_inventario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_go_anulaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(lbl_user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(lbl_nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lbl_user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lbl_nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(btn_go_inventario)
                .addGap(18, 18, 18)
                .addComponent(btn_go_RegistroCliente)
                .addGap(18, 18, 18)
                .addComponent(btn_go_registroFactura)
                .addGap(18, 18, 18)
                .addComponent(btn_go_anulaciones)
                .addGap(18, 18, 18)
                .addComponent(btn_go_separarPrenda)
                .addGap(18, 18, 18)
                .addComponent(btn_go_cierreCaja)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jMenu1.setText("Factura");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        itemFactura_verFacturas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemFactura_verFacturas.setText("Ver Facturas Totales");
        itemFactura_verFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFactura_verFacturasActionPerformed(evt);
            }
        });
        jMenu1.add(itemFactura_verFacturas);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cliente");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        itemCliente_modificar_eliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemCliente_modificar_eliminar.setText("Modificar/Eliminar cliente");
        itemCliente_modificar_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCliente_modificar_eliminarActionPerformed(evt);
            }
        });
        jMenu2.add(itemCliente_modificar_eliminar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Producto");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        itemProducto_ingresarProducto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemProducto_ingresarProducto.setText("Nuevo producto");
        itemProducto_ingresarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProducto_ingresarProductoActionPerformed(evt);
            }
        });
        jMenu3.add(itemProducto_ingresarProducto);

        itemInventario_ingresoInventario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemInventario_ingresoInventario.setText("Ingreso Inventario");
        itemInventario_ingresoInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInventario_ingresoInventarioActionPerformed(evt);
            }
        });
        jMenu3.add(itemInventario_ingresoInventario);

        itemProducto_abonar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemProducto_abonar.setText("Abonar");
        itemProducto_abonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProducto_abonarActionPerformed(evt);
            }
        });
        jMenu3.add(itemProducto_abonar);

        itemProducto_ingresarCategoria.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemProducto_ingresarCategoria.setText("Ingresar categoría");
        itemProducto_ingresarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProducto_ingresarCategoriaActionPerformed(evt);
            }
        });
        jMenu3.add(itemProducto_ingresarCategoria);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Reportes");
        jMenu6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        itemFactura_verFacturasFecha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemFactura_verFacturasFecha.setText("Reporte de ventas");
        itemFactura_verFacturasFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFactura_verFacturasFechaActionPerformed(evt);
            }
        });
        jMenu6.add(itemFactura_verFacturasFecha);

        itemReportes_detalleProductos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemReportes_detalleProductos.setText("Reporte de detalle de productos");
        itemReportes_detalleProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReportes_detalleProductosActionPerformed(evt);
            }
        });
        jMenu6.add(itemReportes_detalleProductos);

        reporteinventario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        reporteinventario.setText("Historial");
        reporteinventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteinventarioActionPerformed(evt);
            }
        });
        jMenu6.add(reporteinventario);

        reporteproducto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        reporteproducto.setText("Reporte de Inventario");
        reporteproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteproductoActionPerformed(evt);
            }
        });
        jMenu6.add(reporteproducto);

        reporteseparados.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        reporteseparados.setText("Reporte Separados");
        reporteseparados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteseparadosActionPerformed(evt);
            }
        });
        jMenu6.add(reporteseparados);

        itemReportes_ventaPrendas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemReportes_ventaPrendas.setText("Reporte de Venta de prendas");
        itemReportes_ventaPrendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReportes_ventaPrendasActionPerformed(evt);
            }
        });
        jMenu6.add(itemReportes_ventaPrendas);

        jMenuBar1.add(jMenu6);

        jMenu4.setText("Configuración");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        itemConfig_registrarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemConfig_registrarUsuario.setText("Registrar Usuario");
        itemConfig_registrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfig_registrarUsuarioActionPerformed(evt);
            }
        });
        jMenu4.add(itemConfig_registrarUsuario);

        itemConfig_modificarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemConfig_modificarUsuario.setText("Modificar/Eliminar Usuario");
        itemConfig_modificarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfig_modificarUsuarioActionPerformed(evt);
            }
        });
        jMenu4.add(itemConfig_modificarUsuario);

        itemConfig_nuevoRol.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemConfig_nuevoRol.setText("Nuevo Perfil");
        itemConfig_nuevoRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfig_nuevoRolActionPerformed(evt);
            }
        });
        jMenu4.add(itemConfig_nuevoRol);

        itemConfig_administrarRoles.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemConfig_administrarRoles.setText("Administrar perfiles");
        itemConfig_administrarRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfig_administrarRolesActionPerformed(evt);
            }
        });
        jMenu4.add(itemConfig_administrarRoles);

        itemConfig_cerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemConfig_cerrarSesion.setText("Cerrar sesión");
        itemConfig_cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfig_cerrarSesionActionPerformed(evt);
            }
        });
        jMenu4.add(itemConfig_cerrarSesion);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void verificar_Permisos(){
        if(rol_usuario == 1){
            itemConfig_registrarUsuario.setEnabled(true);
            itemConfig_modificarUsuario.setEnabled(true);
            itemConfig_nuevoRol.setEnabled(true);
            itemConfig_administrarRoles.setEnabled(true);
        }
        int i;
        for(i=0;i<permisos_usuario.length;i++){
            if(permisos_usuario[i].equals("RealizarSeparados")){
                btn_go_separarPrenda.setEnabled(true);
                //itemProducto_separarPrenda.setEnabled(true);
            }else if(permisos_usuario[i].equals("Facturar")){
                btn_go_registroFactura.setEnabled(true);
                //itemFactura_nuevaFactura.setEnabled(true);
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
                //itemCliente_registrarCliente.setEnabled(true);
            }else if(permisos_usuario[i].equals("ModificarCliente")){
                itemCliente_modificar_eliminar.setEnabled(true);
            }else if(permisos_usuario[i].equals("IngresarProducto")){
                itemProducto_ingresarProducto.setEnabled(true);
            }

        }
    }
    private void btn_go_separarPrendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_separarPrendaActionPerformed
          int i;
          int acceso_permitido = 0;
          for(i=0;i<permisos_usuario.length;i++){
              if(permisos_usuario[i].equals("RealizarSeparados")){
                    Separar ventana_separar = new Separar();
                    ventana_separar.setVisible(true);
                    ventana_separar.setResizable(false);
                    acceso_permitido = 1;
              }
          
          }
          if(acceso_permitido!=1){
             JOptionPane.showMessageDialog(null, "NO TIENE PERMISO PARA REALIZAR ESTA ACCIÓN" , "NO AUTORIZADO" , JOptionPane.ERROR_MESSAGE);
          }
        
    }//GEN-LAST:event_btn_go_separarPrendaActionPerformed

    private void btn_go_registroFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_registroFacturaActionPerformed
        //if(rol_usuario == 1){
          int i;
          int acceso_permitido = 0;
          for(i=0;i<permisos_usuario.length;i++){
              if(permisos_usuario[i].equals("Facturar")){
                    System.out.println(permisos_usuario[i]);
                  //System.out.println(Arrays.toString(permisos_usuario));
                    GUI_Factura ventana_factura = new GUI_Factura();
                    ventana_factura.setVisible(true);
                    ventana_factura.setResizable(true);
                    acceso_permitido = 1;
                    
              }
          
          }
          if(acceso_permitido!=1){
             JOptionPane.showMessageDialog(null, "NO TIENE PERMISO PARA REALIZAR ESTA ACCIÓN" , "NO AUTORIZADO" , JOptionPane.ERROR_MESSAGE);
          }
          
             
    }//GEN-LAST:event_btn_go_registroFacturaActionPerformed

    private void itemConfig_registrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfig_registrarUsuarioActionPerformed
        Ingreso_Nuevo_Usuario ventana_ingresoUsuario = new Ingreso_Nuevo_Usuario();
        ventana_ingresoUsuario.setVisible(true);
        ventana_ingresoUsuario.setResizable(false);
    }//GEN-LAST:event_itemConfig_registrarUsuarioActionPerformed

    private void itemConfig_administrarRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfig_administrarRolesActionPerformed
        // TODO add your handling code here:
        Modificar_Eliminar_Perfil ventana_modificarPerfil = new Modificar_Eliminar_Perfil();
        ventana_modificarPerfil.setVisible(true);
        ventana_modificarPerfil.setResizable(false);
        ventana_modificarPerfil.setLocationRelativeTo(null);
    }//GEN-LAST:event_itemConfig_administrarRolesActionPerformed

    private void btn_go_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_inventarioActionPerformed
        Inventario_Totales ventana_inventariosTotales = new Inventario_Totales();
        ventana_inventariosTotales.setVisible(true);
        ventana_inventariosTotales.setResizable(false);
    }//GEN-LAST:event_btn_go_inventarioActionPerformed

    private void btn_go_RegistroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_RegistroClienteActionPerformed
        Ingreso_Nuevo_Cliente ventana_ingresoCliente = new Ingreso_Nuevo_Cliente();
        ventana_ingresoCliente.setVisible(true);
        ventana_ingresoCliente.setResizable(false);
        
    }//GEN-LAST:event_btn_go_RegistroClienteActionPerformed

    private void btn_go_cierreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_cierreCajaActionPerformed
        Cierre_Caja ventana_cierreCaja = new Cierre_Caja();
        ventana_cierreCaja.setVisible(true);
        ventana_cierreCaja.setResizable(false);
        ventana_cierreCaja.setLocationRelativeTo(null);
       
    }//GEN-LAST:event_btn_go_cierreCajaActionPerformed

    private void itemFactura_verFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFactura_verFacturasActionPerformed
        Ver_Facturas ventana_verFacturas = new Ver_Facturas();
        ventana_verFacturas.setVisible(true);
        ventana_verFacturas.setResizable(false);
        ventana_verFacturas.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_itemFactura_verFacturasActionPerformed

    private void itemCliente_modificar_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCliente_modificar_eliminarActionPerformed
        Modificar_Eliminar_Cliente ventana_modificar_eliminarCliente = new Modificar_Eliminar_Cliente();
        ventana_modificar_eliminarCliente.setVisible(true);
        ventana_modificar_eliminarCliente.setResizable(false);
        
    }//GEN-LAST:event_itemCliente_modificar_eliminarActionPerformed

    private void itemProducto_ingresarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProducto_ingresarProductoActionPerformed
        Ingreso_Nueva_prenda ventana_nuevaPrenda = new Ingreso_Nueva_prenda();
        ventana_nuevaPrenda.setVisible(true);
        ventana_nuevaPrenda.setResizable(false);
        
    }//GEN-LAST:event_itemProducto_ingresarProductoActionPerformed

    private void itemInventario_ingresoInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInventario_ingresoInventarioActionPerformed
        Ingreso_Inventario ventana_ingresoInventario = new Ingreso_Inventario();
        ventana_ingresoInventario.setVisible(true);
        ventana_ingresoInventario.setResizable(false);
        
    }//GEN-LAST:event_itemInventario_ingresoInventarioActionPerformed

    private void itemConfig_cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfig_cerrarSesionActionPerformed
        Login ventana_login = new Login();
        ventana_login.setVisible(true);
        ventana_login.setResizable(false);
        dispose();
       
    }//GEN-LAST:event_itemConfig_cerrarSesionActionPerformed

    private void btn_go_anulacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_anulacionesActionPerformed
       // TODO add your handling code here:
        Ver_Facturas ventana_anulaciones = new Ver_Facturas();
        ventana_anulaciones.setVisible(true);
        ventana_anulaciones.setResizable(false);
        ventana_anulaciones.setLocationRelativeTo(null);
  
    }//GEN-LAST:event_btn_go_anulacionesActionPerformed

    private void itemConfig_modificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfig_modificarUsuarioActionPerformed
        // TODO add your handling code here:
        Modificar_Eliminar_Usuario ventana_modificarUsuario = new Modificar_Eliminar_Usuario();
        ventana_modificarUsuario.setVisible(true);
        ventana_modificarUsuario.setResizable(false);
        ventana_modificarUsuario.setLocationRelativeTo(null);
 
    }//GEN-LAST:event_itemConfig_modificarUsuarioActionPerformed

    private void itemProducto_abonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProducto_abonarActionPerformed
        // TODO add your handling code here:
        Abonar ventana_abonar = new Abonar();
        ventana_abonar.setVisible(true);
        ventana_abonar.setResizable(false);
        ventana_abonar.setLocationRelativeTo(null);
    }//GEN-LAST:event_itemProducto_abonarActionPerformed

    private void reporteinventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteinventarioActionPerformed
        // TODO add your handling code here:
        Reporte_Inventario ri = new Reporte_Inventario();
        ri.setVisible(true);
        ri.setResizable(false);
    }//GEN-LAST:event_reporteinventarioActionPerformed

    private void reporteproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteproductoActionPerformed
        // TODO add your handling code here:
        Inventario_Totales it = new Inventario_Totales();
        it.setVisible(true);
        it.setResizable(false);
    }//GEN-LAST:event_reporteproductoActionPerformed

    private void reporteseparadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteseparadosActionPerformed
        // TODO add your handling code here:
        Reporte_Separados rs = new Reporte_Separados();
        rs.setVisible(true);
        rs.setResizable(false);
    }//GEN-LAST:event_reporteseparadosActionPerformed

    private void itemConfig_nuevoRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfig_nuevoRolActionPerformed
        // TODO add your handling code here:
        Ingreso_Nuevo_Perfil ventana_ingresoPerfil = new Ingreso_Nuevo_Perfil();
        ventana_ingresoPerfil.setVisible(true);
        ventana_ingresoPerfil.setResizable(false);
    }//GEN-LAST:event_itemConfig_nuevoRolActionPerformed

    private void itemReportes_ventaPrendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReportes_ventaPrendasActionPerformed
        // TODO add your handling code here:
        prendasMVendidad ventana_reporteVentaPrendas = new prendasMVendidad();
        ventana_reporteVentaPrendas.setVisible(true);
        ventana_reporteVentaPrendas.setResizable(false);
        ventana_reporteVentaPrendas.setLocationRelativeTo(null);
    }//GEN-LAST:event_itemReportes_ventaPrendasActionPerformed

    private void itemFactura_verFacturasFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFactura_verFacturasFechaActionPerformed
        // TODO add your handling code here:
        Ver_Facturas_Por_FECHA ventana_verFacturasFecha = new Ver_Facturas_Por_FECHA();
        ventana_verFacturasFecha.setVisible(true);
        ventana_verFacturasFecha.setResizable(false);
        ventana_verFacturasFecha.setLocationRelativeTo(null);
    }//GEN-LAST:event_itemFactura_verFacturasFechaActionPerformed

    private void itemProducto_ingresarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProducto_ingresarCategoriaActionPerformed
        // TODO add your handling code here:
        Ingreso_Nueva_Categoria ventana_ingresoCategoria = new Ingreso_Nueva_Categoria();
        ventana_ingresoCategoria.setVisible(true);
        ventana_ingresoCategoria.setResizable(false);
        ventana_ingresoCategoria.setLocationRelativeTo(null);
    }//GEN-LAST:event_itemProducto_ingresarCategoriaActionPerformed

    private void itemReportes_detalleProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReportes_detalleProductosActionPerformed
        // TODO add your handling code here:
        Reporte_detalle_productos ventana_reporteDetalleProductos = new Reporte_detalle_productos();
        ventana_reporteDetalleProductos.setVisible(true);
        ventana_reporteDetalleProductos.setResizable(false);
        ventana_reporteDetalleProductos.setLocationRelativeTo(null);
    }//GEN-LAST:event_itemReportes_detalleProductosActionPerformed

    public  int getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(int codigo_usuario) {
        Main_Menu.codigo_usuario = codigo_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        Main_Menu.nombre_usuario = nombre_usuario;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_go_RegistroCliente;
    private javax.swing.JButton btn_go_anulaciones;
    private javax.swing.JButton btn_go_cierreCaja;
    private javax.swing.JButton btn_go_inventario;
    private javax.swing.JButton btn_go_registroFactura;
    private javax.swing.JButton btn_go_separarPrenda;
    private javax.swing.JMenuItem itemCliente_modificar_eliminar;
    private javax.swing.JMenuItem itemConfig_administrarRoles;
    private javax.swing.JMenuItem itemConfig_cerrarSesion;
    private javax.swing.JMenuItem itemConfig_modificarUsuario;
    private javax.swing.JMenuItem itemConfig_nuevoRol;
    private javax.swing.JMenuItem itemConfig_registrarUsuario;
    private javax.swing.JMenuItem itemFactura_verFacturas;
    private javax.swing.JMenuItem itemFactura_verFacturasFecha;
    private javax.swing.JMenuItem itemInventario_ingresoInventario;
    private javax.swing.JMenuItem itemProducto_abonar;
    private javax.swing.JMenuItem itemProducto_ingresarCategoria;
    private javax.swing.JMenuItem itemProducto_ingresarProducto;
    private javax.swing.JMenuItem itemReportes_detalleProductos;
    private javax.swing.JMenuItem itemReportes_ventaPrendas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_nombreUsuario;
    private javax.swing.JLabel lbl_user_name;
    private javax.swing.JMenuItem reporteinventario;
    private javax.swing.JMenuItem reporteproducto;
    private javax.swing.JMenuItem reporteseparados;
    // End of variables declaration//GEN-END:variables
}
