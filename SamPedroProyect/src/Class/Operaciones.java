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
        String sql  =  "SELECT Nombre From cliente WHERE Estado = 'ACTIVO' AND  Nombre LIKE '"+Cadena+"%' ";
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
    public DefaultComboBoxModel getListaUsuarios(String Cadena){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        String sql  =  "SELECT Nombre From Usuario WHERE Estado = 'ACTIVO' AND  Nombre LIKE '"+Cadena+"%' ";
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
    public DefaultComboBoxModel getListaCategorias(String Cadena){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        String sql  =  "SELECT Nombre From Categoria WHERE Nombre LIKE '"+Cadena+"%' ";
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
    
    
    
        public DefaultComboBoxModel geLista_Producto(String Cadena){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        String sql  =  "SELECT Descripcion From producto WHERE Descripcion LIKE '"+Cadena+"%';";
        try{
           pst = cn.prepareStatement(sql); 
           rs = pst.executeQuery();
           while(rs.next()){
               modelo.addElement(rs.getString("Descripcion"));
               
           }
        }catch(Exception e){
            resul = "Error :" + e;
            System.out.println(resul);
        }
        
        return modelo;
    
}   
        
    public DefaultComboBoxModel geLista_Producto_porcodigo(String Cadena){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        String sql  =  "SELECT id_Producto From producto WHERE id_Producto LIKE '"+Cadena+"%';";
        try{
           pst = cn.prepareStatement(sql); 
           rs = pst.executeQuery();
           while(rs.next()){
               modelo.addElement(rs.getString("id_Producto"));
               
           }
        }catch(Exception e){
            resul = "Error :" + e;
            System.out.println(resul);
        }
        
        return modelo;
    
}   
    
    
}
