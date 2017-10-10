/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanpedroproyect;

import DATABASE.ConnectionDB;
import java.sql.Connection;
import javax.swing.Icon;

/**
 *
 * @author Pantheon
 */
public class Main_Menu extends javax.swing.JFrame {
    static int codigo_usuario;
    static String nombre_usuario;
    Login l = new Login();
    /**
     * Creates new form Main_MEnu
     */
    public Main_Menu() {
        initComponents();
        lbl_user_name.setText(l.getUsername());
        nombre_usuario = l.getUsername();
        codigo_usuario = l.getCodigo_usuario();
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        
        

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
        btn_go_reportes = new javax.swing.JButton();
        btn_go_cierreCaja = new javax.swing.JButton();
        btn_go_RegistroCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbl_user_name = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemFactura_nuevaFactura = new javax.swing.JMenuItem();
        itemFactura_verFacturas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemCliente_registrarCliente = new javax.swing.JMenuItem();
        itemCliente_modificar_eliminar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        itemProducto_ingresarProducto = new javax.swing.JMenuItem();
        itemProducto_separarPrenda = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        itemInventario_ingresoInventario = new javax.swing.JMenuItem();
        itemInventario_inventariosTotales = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        itemConfig_registrarUsuario = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        itemConfig_administrarRoles = new javax.swing.JMenuItem();
        itemConfig_cerrarSesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");
        setLocationByPlatform(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_go_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/portapapeles.png"))); // NOI18N
        btn_go_inventario.setText("Inventario");
        btn_go_inventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_inventario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_inventarioActionPerformed(evt);
            }
        });

        btn_go_registroFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/recepcion.png"))); // NOI18N
        btn_go_registroFactura.setText("Nueva Factura");
        btn_go_registroFactura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_registroFactura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_registroFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_registroFacturaActionPerformed(evt);
            }
        });

        btn_go_anulaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/archivo.png"))); // NOI18N
        btn_go_anulaciones.setText("Anulaciones");
        btn_go_anulaciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_anulaciones.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_anulaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_anulacionesActionPerformed(evt);
            }
        });

        btn_go_separarPrenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/vestir.png"))); // NOI18N
        btn_go_separarPrenda.setText("Separar Prenda");
        btn_go_separarPrenda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_separarPrenda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_separarPrenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_separarPrendaActionPerformed(evt);
            }
        });

        btn_go_reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/analitica.png"))); // NOI18N
        btn_go_reportes.setText("Reportes");
        btn_go_reportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_reportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_reportesActionPerformed(evt);
            }
        });

        btn_go_cierreCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/proyecto-de-ley.png"))); // NOI18N
        btn_go_cierreCaja.setText("Cierre De Caja");
        btn_go_cierreCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_cierreCaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_cierreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_cierreCajaActionPerformed(evt);
            }
        });

        btn_go_RegistroCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario.png"))); // NOI18N
        btn_go_RegistroCliente.setText("Registro Cliente");
        btn_go_RegistroCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_go_RegistroCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_go_RegistroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_go_RegistroClienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario Actual  : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(lbl_user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_go_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_go_RegistroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_go_registroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btn_go_reportes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_go_separarPrenda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addComponent(btn_go_cierreCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_go_anulaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(506, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(btn_go_reportes)
                .addGap(18, 18, 18)
                .addComponent(btn_go_cierreCaja)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jMenu1.setText("Factura");

        itemFactura_nuevaFactura.setText("Nueva Factura");
        itemFactura_nuevaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFactura_nuevaFacturaActionPerformed(evt);
            }
        });
        jMenu1.add(itemFactura_nuevaFactura);

        itemFactura_verFacturas.setText("Ver Facturas");
        itemFactura_verFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFactura_verFacturasActionPerformed(evt);
            }
        });
        jMenu1.add(itemFactura_verFacturas);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cliente");

        itemCliente_registrarCliente.setText("Registrar cliente");
        itemCliente_registrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCliente_registrarClienteActionPerformed(evt);
            }
        });
        jMenu2.add(itemCliente_registrarCliente);

        itemCliente_modificar_eliminar.setText("Modificar/Eliminar cliente");
        itemCliente_modificar_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCliente_modificar_eliminarActionPerformed(evt);
            }
        });
        jMenu2.add(itemCliente_modificar_eliminar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Producto");

        itemProducto_ingresarProducto.setText("Ingresar producto");
        itemProducto_ingresarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProducto_ingresarProductoActionPerformed(evt);
            }
        });
        jMenu3.add(itemProducto_ingresarProducto);

        itemProducto_separarPrenda.setText("Separar prenda");
        itemProducto_separarPrenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProducto_separarPrendaActionPerformed(evt);
            }
        });
        jMenu3.add(itemProducto_separarPrenda);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Inventario");

        itemInventario_ingresoInventario.setText("Ingreso Inventario");
        itemInventario_ingresoInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInventario_ingresoInventarioActionPerformed(evt);
            }
        });
        jMenu5.add(itemInventario_ingresoInventario);

        itemInventario_inventariosTotales.setText("Inventarios totales");
        itemInventario_inventariosTotales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInventario_inventariosTotalesActionPerformed(evt);
            }
        });
        jMenu5.add(itemInventario_inventariosTotales);

        jMenuBar1.add(jMenu5);

        jMenu4.setText("Configuración");

        itemConfig_registrarUsuario.setText("Registrar Usuario");
        itemConfig_registrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfig_registrarUsuarioActionPerformed(evt);
            }
        });
        jMenu4.add(itemConfig_registrarUsuario);

        jMenuItem1.setText("Modificar/Eliminar Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem2.setText("Nuevo Rol");
        jMenu4.add(jMenuItem2);

        itemConfig_administrarRoles.setText("Administrar roles");
        itemConfig_administrarRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfig_administrarRolesActionPerformed(evt);
            }
        });
        jMenu4.add(itemConfig_administrarRoles);

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
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_go_separarPrendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_separarPrendaActionPerformed
        Separar ventana_separar = new Separar();
        ventana_separar.setVisible(true);
        ventana_separar.setResizable(false);
        dispose();
        
    }//GEN-LAST:event_btn_go_separarPrendaActionPerformed

    private void btn_go_registroFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_registroFacturaActionPerformed
        GUI_Factura ventana_factura = new GUI_Factura();
        ventana_factura.setVisible(true);
        ventana_factura.setResizable(false);
        dispose();     
    }//GEN-LAST:event_btn_go_registroFacturaActionPerformed

    private void itemConfig_registrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfig_registrarUsuarioActionPerformed
        Ingreso_Nuevo_Usuario ventana_ingresoUsuario = new Ingreso_Nuevo_Usuario();
        ventana_ingresoUsuario.setVisible(true);
        ventana_ingresoUsuario.setResizable(false);
        dispose();
    }//GEN-LAST:event_itemConfig_registrarUsuarioActionPerformed

    private void itemConfig_administrarRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfig_administrarRolesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemConfig_administrarRolesActionPerformed

    private void btn_go_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_inventarioActionPerformed
        Ingreso_Inventario ventana_ingresoInventario = new Ingreso_Inventario();
        ventana_ingresoInventario.setVisible(true);
        ventana_ingresoInventario.setResizable(false);
        dispose();
    }//GEN-LAST:event_btn_go_inventarioActionPerformed

    private void btn_go_RegistroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_RegistroClienteActionPerformed
        Ingreso_Nuevo_Cliente ventana_ingresoCliente = new Ingreso_Nuevo_Cliente();
        ventana_ingresoCliente.setVisible(true);
        ventana_ingresoCliente.setResizable(false);
        dispose();
    }//GEN-LAST:event_btn_go_RegistroClienteActionPerformed

    private void btn_go_cierreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_cierreCajaActionPerformed
        Cierre_Caja ventana_cierreCaja = new Cierre_Caja();
        ventana_cierreCaja.setVisible(true);
        ventana_cierreCaja.setResizable(false);
        dispose();
    }//GEN-LAST:event_btn_go_cierreCajaActionPerformed

    private void itemFactura_nuevaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFactura_nuevaFacturaActionPerformed
        GUI_Factura ventana_factura = new GUI_Factura();
        ventana_factura.setVisible(true);
        ventana_factura.setResizable(false);
        dispose(); 
    }//GEN-LAST:event_itemFactura_nuevaFacturaActionPerformed

    private void itemFactura_verFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFactura_verFacturasActionPerformed
        Ver_Facturas ventana_verFacturas = new Ver_Facturas();
        ventana_verFacturas.setVisible(true);
        ventana_verFacturas.setResizable(false);
        dispose();
    }//GEN-LAST:event_itemFactura_verFacturasActionPerformed

    private void itemCliente_registrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCliente_registrarClienteActionPerformed
        Ingreso_Nuevo_Cliente ventana_ingresoCliente = new Ingreso_Nuevo_Cliente();
        ventana_ingresoCliente.setVisible(true);
        ventana_ingresoCliente.setResizable(false);
        dispose();
    }//GEN-LAST:event_itemCliente_registrarClienteActionPerformed

    private void itemCliente_modificar_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCliente_modificar_eliminarActionPerformed
        Modificar_Eliminar_Cliente ventana_modificar_eliminarCliente = new Modificar_Eliminar_Cliente();
        ventana_modificar_eliminarCliente.setVisible(true);
        ventana_modificar_eliminarCliente.setResizable(false);
        dispose();
    }//GEN-LAST:event_itemCliente_modificar_eliminarActionPerformed

    private void itemProducto_ingresarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProducto_ingresarProductoActionPerformed
        Ingreso_Nueva_prenda ventana_nuevaPrenda = new Ingreso_Nueva_prenda();
        ventana_nuevaPrenda.setVisible(true);
        ventana_nuevaPrenda.setResizable(false);
        dispose();
    }//GEN-LAST:event_itemProducto_ingresarProductoActionPerformed

    private void itemProducto_separarPrendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProducto_separarPrendaActionPerformed
        Separar ventana_separar = new Separar();
        ventana_separar.setVisible(true);
        ventana_separar.setResizable(false);
        dispose();
    }//GEN-LAST:event_itemProducto_separarPrendaActionPerformed

    private void itemInventario_ingresoInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInventario_ingresoInventarioActionPerformed
        Ingreso_Inventario ventana_ingresoInventario = new Ingreso_Inventario();
        ventana_ingresoInventario.setVisible(true);
        ventana_ingresoInventario.setResizable(false);
        dispose();
    }//GEN-LAST:event_itemInventario_ingresoInventarioActionPerformed

    private void itemInventario_inventariosTotalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInventario_inventariosTotalesActionPerformed
        Inventario_Totales ventana_inventariosTotales = new Inventario_Totales();
        ventana_inventariosTotales.setVisible(true);
        ventana_inventariosTotales.setResizable(false);
        dispose();
    }//GEN-LAST:event_itemInventario_inventariosTotalesActionPerformed

    private void itemConfig_cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfig_cerrarSesionActionPerformed
        Login ventana_login = new Login();
        ventana_login.setVisible(true);
        ventana_login.setResizable(false);
        dispose();
    }//GEN-LAST:event_itemConfig_cerrarSesionActionPerformed

    private void btn_go_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_reportesActionPerformed
        Reportes ventana_reportes = new Reportes();
        ventana_reportes.setVisible(true);
        ventana_reportes.setResizable(false);
        ventana_reportes.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_btn_go_reportesActionPerformed

    private void btn_go_anulacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_go_anulacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_go_anulacionesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                //new Main_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_go_RegistroCliente;
    private javax.swing.JButton btn_go_anulaciones;
    private javax.swing.JButton btn_go_cierreCaja;
    private javax.swing.JButton btn_go_inventario;
    private javax.swing.JButton btn_go_registroFactura;
    private javax.swing.JButton btn_go_reportes;
    private javax.swing.JButton btn_go_separarPrenda;
    private javax.swing.JMenuItem itemCliente_modificar_eliminar;
    private javax.swing.JMenuItem itemCliente_registrarCliente;
    private javax.swing.JMenuItem itemConfig_administrarRoles;
    private javax.swing.JMenuItem itemConfig_cerrarSesion;
    private javax.swing.JMenuItem itemConfig_registrarUsuario;
    private javax.swing.JMenuItem itemFactura_nuevaFactura;
    private javax.swing.JMenuItem itemFactura_verFacturas;
    private javax.swing.JMenuItem itemInventario_ingresoInventario;
    private javax.swing.JMenuItem itemInventario_inventariosTotales;
    private javax.swing.JMenuItem itemProducto_ingresarProducto;
    private javax.swing.JMenuItem itemProducto_separarPrenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_user_name;
    // End of variables declaration//GEN-END:variables
}
