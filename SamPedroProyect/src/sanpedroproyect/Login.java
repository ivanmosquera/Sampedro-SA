package sanpedroproyect;


import DATABASE.ConnectionDB;
import java.awt.event.KeyEvent;// importamos las librerias del evento KeyPressed
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import javax.swing.JOptionPane;// importacion de las librerias de la opcion de mensajes de panel

public class Login extends javax.swing.JFrame {

    static int Codigo_usuario;
    static String username;
    static int rol_usuario;
    static String [] permisos_usuario;
    public Login() {
        initComponents();
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
   public void iniciarSplash() {
        
        
    }

   public void validar_ingreso(){

        String usuario = txt_usuario.getText();
        String clave = String.valueOf(txt_password.getPassword());
        //String clave = String.valueOf(Login.jpassClave.getPassword());

        int resultado=0;
        
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        PreparedStatement pst2 =null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        //String Desc;
        try{
           String sql = ("SELECT * FROM usuario WHERE Usuario='"+usuario+"' AND Contraseña=sha1('"+clave+"') AND Estado='ACTIVO'");
           //String sql2 = ("SELECT Detalle, Nombre FROM rol JOIN roles_permisos ON rol.id_Rol = roles_permisos.fk_Rol JOIN permisos ON roles_permisos.fk_Permiso = permisos.id_Permiso WHERE rol.id_Rol="+rol_usuario+" ORDER BY Detalle");
           pst = cn.prepareStatement(sql);
           //pst.setString(1, cadena);
           rs =pst.executeQuery();
           if (rs.next()){
               Codigo_usuario = rs.getInt("id_Usuario");
               username = rs.getString("Usuario");
               rol_usuario = rs.getInt("fk_Rol");
               //EXTRAEMOS LOS PERMISOS SEGUN EL ROL LOGGUEADO
               String sql2 = ("SELECT Detalle, Nombre FROM rol JOIN roles_permisos ON rol.id_Rol = roles_permisos.fk_Rol JOIN permisos ON roles_permisos.fk_Permiso = permisos.id_Permiso WHERE rol.id_Rol="+rol_usuario+" ORDER BY Detalle");
               pst2 = cn.prepareStatement(sql2);
               rs2 =pst2.executeQuery();
               rs2.last();
               int numFils = rs2.getRow();
               rs2.beforeFirst();
               int i = 0;
               permisos_usuario = new String[numFils];
               while(rs2.next()){
                   permisos_usuario[i] = rs2.getString("Nombre");
                   i++;
               }
               //System.out.println(Arrays.toString(permisos_usuario));// imprimimos los permisos por consola del usuario loggueado
               JOptionPane.showMessageDialog(this,"Bienvenido");// mensaje
               dispose();
               Main_Menu ventana_menuPrincipal = new Main_Menu();
               ventana_menuPrincipal.setVisible(true);
               ventana_menuPrincipal.setLocationRelativeTo(null);
               ventana_menuPrincipal.setResizable(false);
           }
           else
            {
               JOptionPane.showMessageDialog(this,"Usuario o contraseña Incorrecto","Acceso denegado",JOptionPane.ERROR_MESSAGE);                     
            } 
           
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this,"ERROOOOR: " + ex);
            System.out.println(ex);
        }
    /*
    String SSQL="SELECT * FROM usuarios WHERE usuario='"+usuario+"' AND clave=sha1('"+clave+"')";

    Connection conect = null;

    try {

        conect = metodospool.dataSource.getConnection();
        Statement st = conect.createStatement();
        ResultSet rs = st.executeQuery(SSQL);

        if(rs.next()){

            resultado=1;

        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);

    }finally{


        try {

            conect.close();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "Error de desconexión", JOptionPane.ERROR_MESSAGE);

        }

    }

return resultado;
*/
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        btn_Aceptar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SAMPEDRO S.A.");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(530, 300));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Usuario :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(235, 80, 80, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Contraseña :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(240, 130, 80, 17);

        txt_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txt_usuario);
        txt_usuario.setBounds(340, 80, 130, 28);

        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
        });
        getContentPane().add(txt_password);
        txt_password.setBounds(340, 130, 130, 28);

        btn_Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/accept.png"))); // NOI18N
        btn_Aceptar.setText("Aceptar");
        btn_Aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_AceptarMouseEntered(evt);
            }
        });
        btn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AceptarActionPerformed(evt);
            }
        });
        btn_Aceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_AceptarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btn_AceptarKeyReleased(evt);
            }
        });
        getContentPane().add(btn_Aceptar);
        btn_Aceptar.setBounds(240, 180, 130, 40);

        btn_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida (2).png"))); // NOI18N
        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Salir);
        btn_Salir.setBounds(390, 180, 120, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/man key.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 20, 230, 250);

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel4.setText("Inicio de Sesión");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(180, 10, 230, 29);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 580, 300);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
      /*
        String  us="carlos",contraseña="123";  // la declaracion de variables
         String pass = new String(txt_password.getPassword());  
               
        if(txt_usuario.getText().endsWith(us) && pass.equals(contraseña))// igualamos  loos datois igresados con los que ya declaramos
        {
            JOptionPane.showMessageDialog(this,"bienvenido");// mensaje
            //new Thread(new Tiempo()).start();// llamado al procedimiento del ProcessBar
        }
        else
        {
           JOptionPane.showMessageDialog(this,"Usuario o contraseña Incorrecto");                     
        } */
        validar_ingreso();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
      
        System.exit(0);
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_AceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_AceptarKeyPressed
          
        
    }//GEN-LAST:event_btn_AceptarKeyPressed

    private void btn_AceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AceptarMouseEntered
      
    }//GEN-LAST:event_btn_AceptarMouseEntered

    private void btn_AceptarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_AceptarKeyReleased

    }//GEN-LAST:event_btn_AceptarKeyReleased

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
        
        if(evt.getKeyCode()== KeyEvent.VK_ENTER) // el evento keyPressed
        {
             //String  us="carlos",contraseña="123";  //la verificacion de los datos ingresados
               //String pass = new String(txt_password.getPassword());  
            validar_ingreso();
               /*
          if(txt_usuario.getText().endsWith(us) && pass.equals(contraseña))
           {
               JOptionPane.showMessageDialog(this,"bienvenido");
               //new Thread(new Tiempo()).start();
           }
          else
           {
            JOptionPane.showMessageDialog(this,"Usuario o contraseña Incorrecto");                     
           } */
        }
    }//GEN-LAST:event_txt_passwordKeyPressed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuarioActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    public  int getCodigo_usuario() {
        return Codigo_usuario;
    }

    public  void setCodigo_usuario(int Codigo_usuario) {
        Login.Codigo_usuario = Codigo_usuario;
    }

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        Login.username = username;
    }
    
    public  int getRol_usuario() {
        return rol_usuario;
    }

    public  void setRol_usuario(int rol_usuario) {
        Login.rol_usuario = rol_usuario;
    }
    
    public String [] getPermisos_usuario(){
        return permisos_usuario;
    }
    
    /*
    private class Tiempo implements Runnable{// la creacion de una clase privada que la implementamos con runnable para que arranque en cuanto accedamos a ella 
        @Override
        public void run() {
            for(int i=1;i<100;i++)//aki el for que nos dice k el i balla aumentando hasta 99  ya que le dimos al dimencion de que sea menor que  100 es decir 99
            {
                
                progreso.setValue(i);//aki valuamos el procesbar
                progreso.repaint();//aki bamos llenando el proces bar
                try {
                    Thread.sleep(20);
                    
                    if(i==99){ // este if nos dice k cuando el proceso bar llegue a 99 algo se ejecute , no ponemos k llegue a 100, pork por defecto no llega a 100
                       
                        // aki pones lo que quieres que haga despues de haber cargado el procesBar  
                        
                      }
                    
                }catch(Exception erro){
                    
                }
            }
        }
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
