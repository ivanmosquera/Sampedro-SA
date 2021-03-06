/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanpedroproyect;

import DATABASE.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static sanpedroproyect.Ingreso_Nuevo_Usuario.user;

/**
 *
 * @author Ivan Mosquera
 */
public class Ingreso_Nueva_Categoria extends javax.swing.JFrame {
    private String nombre_categoria;
    /**
     * Creates new form Ingreso_Nueva_Categoria
     */
    public Ingreso_Nueva_Categoria() {
        initComponents();
    }
    
    public String Ingresar_Categoria(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO categoria values(null,?,null)";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,nombre_categoria);
            pst.execute();
            resul = "Categoría Ingresada Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
            System.out.println(pst);
        }
        
        return resul;
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
        jLabel3 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        btn_guardar_nuevaCategoria = new javax.swing.JButton();
        btn_salirIngresoCategoria = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel3.setText("Nombre de categoría");

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_guardar_nuevaCategoria.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_guardar_nuevaCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible (1).png"))); // NOI18N
        btn_guardar_nuevaCategoria.setText("Guardar");
        btn_guardar_nuevaCategoria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar_nuevaCategoria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar_nuevaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_nuevaCategoriaActionPerformed(evt);
            }
        });

        btn_salirIngresoCategoria.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_salirIngresoCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_salirIngresoCategoria.setText("Salir");
        btn_salirIngresoCategoria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salirIngresoCategoria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salirIngresoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirIngresoCategoriaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel1.setText("Ingresar Nueva Categoría");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addGap(21, 21, 21)
                        .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btn_guardar_nuevaCategoria)
                        .addGap(76, 76, 76)
                        .addComponent(btn_salirIngresoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_guardar_nuevaCategoria, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_salirIngresoCategoria, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardar_nuevaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_nuevaCategoriaActionPerformed
        // TODO add your handling code here:
        nombre_categoria = txt_nombre.getText();
        if(txt_nombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LLENAR CAMPO DE NOMBRE DE CATEGORIA" , "ERROR AL GUARDAR" , JOptionPane.ERROR_MESSAGE);
        }else{
            String msj = Ingresar_Categoria();
            if(msj.equals("Categoría Ingresada Correctamente")){
                JOptionPane.showMessageDialog(null, "Categoría Ingresada Correctamente" , "Guardado Exitoso" , JOptionPane.INFORMATION_MESSAGE);
                txt_nombre.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "REVISAR QUE TODOS LOS CAMPOS ESTEN CORRECTOS" , "INCORRECTO" , JOptionPane.ERROR_MESSAGE);
            }
            //dispose();
        }
        

    }//GEN-LAST:event_btn_guardar_nuevaCategoriaActionPerformed

    private void btn_salirIngresoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirIngresoCategoriaActionPerformed
        //System.exit(0);
        dispose();
    }//GEN-LAST:event_btn_salirIngresoCategoriaActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar_nuevaCategoria;
    private javax.swing.JButton btn_salirIngresoCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
