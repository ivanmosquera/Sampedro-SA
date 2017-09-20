/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Class;

import DATABASE.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Operaciones {
    public DefaultComboBoxModel geLista(String Cadena){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        String sql  =  "SELECT Nombre From cliente WHERE Nombre LIKE '"+Cadena+"%';";
        try{
           pst = cn.prepareStatement(sql); 
           rs = pst.executeQuery();
           while(rs.next()){
               modelo.addElement(rs.getString("Nombre"));
               System.out.println(rs.getString("Nombre"));
           }
        }catch(Exception e){
            resul = "Error :" + e;
            System.out.println(resul);
        }
        
        return modelo;
    
}
    
}
