/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DATABASE;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author kleberstevendiazcoello
 */
public class ConnectionDB {
    Connection conectar = null;
    
    public ConnectionDB (){ 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar =  DriverManager.getConnection("jdbc:mysql://localhost/Sam","root","");
            //JOptionPane.showMessageDialog(this,"ERROOOOR: ");
            System.out.println("conexion exitosa");
            
        }catch(Exception e){
            System.out.println(e);
            
        }
            
    }
    public Connection getConnection(){
        return conectar;
        
    }
    
    public void desconectar(){
        conectar = null;
    }
    
}
