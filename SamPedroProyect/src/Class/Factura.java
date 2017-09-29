/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import static Class.Cliente.cliente;
import DATABASE.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; //necesario para realizar consultas SELECT
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import sanpedroproyect.GUI_Factura;
//import org.fife.ui.autocomplete.*;
/**
 *
 * @author Ivan Mosquera
 */
public class Factura {
    static GUI_Factura  fact = new GUI_Factura();
    public static String Guardar_Factura(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO factura values(null,?,?,?,?,?,?,?,?,?)";
        Date date = new Date();
        ResultSet rs = null;
        //Caso 1: obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        //Caso 2: obtener la fecha y salida por pantalla con formato:
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        int flag = 0;
        int id_ultimo;
        try{
            pst = cn.prepareStatement(sql);
            pst.setInt(1,fact.getCodigo_cliente());
            pst.setInt(2,1);
            pst.setString(3,dia);
            pst.setInt(4,1);
            pst.setFloat(5,fact.getSubtotal_static());
            pst.setFloat(6,fact.getDescuento_static());
            pst.setFloat(7,0);
            pst.setFloat(8,0);
            pst.setFloat(9, fact.getTotal_static());
            
            pst.execute();
            resul = "Ingresado Correctamente";
           
            
            
            
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
        }
        
       cc.desconectar();
        return resul;      
 }
    
    
    
    public static int Get_last_id_factura(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql2 = "SELECT MAX(id_Factura) FROM factura";
        String sql3 = "INSERT INTO detalle_factura values(?,?,?)";
        int id_ultimo = 0 ;
        try{
         
            pst = cn.prepareStatement(sql2);
            rs =pst.executeQuery();
            if (rs.next()){
                id_ultimo = rs.getInt("MAX(id_Factura)");
            }
            
            
            
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
        }
        
        return id_ultimo;
        
    } 
    
    
    
    
    
    public static String Guardar_Detalle_Factura(int id,int producto,int cantidad){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql3 = "INSERT INTO detalle_factura values(?,?,?)";
        Date date = new Date();
        ResultSet rs = null;

        try{
            pst = cn.prepareStatement(sql3);
            pst.setInt(1,id);
            pst.setInt(2,producto);
            pst.setInt(3,cantidad);
            
            pst.execute();
            resul = "Ingresado Correctamente";
           
            
            
            
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
        }
        
       cc.desconectar();
        return resul;      
        }
        
    
       
  }
    


    
    
    
    


