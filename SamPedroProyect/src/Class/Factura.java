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
    public static String Guardar_Factura(int id , int user,double efectivo, double tarjeta){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO factura values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.setInt(1,id);
            pst.setInt(2,fact.getCodigo_cliente());
            pst.setInt(3,1);
            pst.setString(4,dia);
            pst.setInt(5,1);
            pst.setFloat(6,fact.getSubtotal_static());
            pst.setFloat(7,fact.getDescuento_static());
            pst.setFloat(8,0);
            pst.setFloat(9,0);
            pst.setFloat(10, fact.getTotal_static());
            pst.setFloat(11, user);
            pst.setDouble(12, efectivo);
            pst.setDouble(13, tarjeta);
            
            pst.execute();
            resul = "Ingresado Correctamente Factura";
           
            
            
            
            
        }catch(SQLException e){
            resul = "Error Error en guardar Factura: "+e; 
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
            resul = "Error en id factura: "+e; 
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
            resul = "Ingresado Correctamente Detalle Factura";
           
            
            
            
            
        }catch(SQLException e){
            resul = "Error  en detalle Factura: "+e; 
            System.out.println(resul);
        }
        
       cc.desconectar();
        return resul;      
        }
     
    public static String Eliminar_Factura(int id,int usuario,String Motivo){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql3 = "INSERT INTO anulacion values(?,?,?,?)";
        Date date = new Date();
        ResultSet rs = null;
        //Caso 1: obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        //Caso 2: obtener la fecha y salida por pantalla con formato:
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dia = dateFormat.format(date);
        System.out.println(hora);
        System.out.println(dia);

        try{
            pst = cn.prepareStatement(sql3);
            pst.setInt(1,id);
            pst.setString(2,Motivo);
            pst.setInt(3,usuario);
            pst.setString(4,dia);
            
            
            
            pst.execute();
            resul = "Ingresado Correctamente Anulado";
           
            
            
            
            
        }catch(SQLException e){
            resul = "Error  en detalle Factura: "+e; 
            System.out.println(resul);
        }
        
       cc.desconectar();
        return resul;      
        }
    
        public static String update_estado_factura(int codigo){
        
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql = "UPDATE `San Pedro`.`factura` SET `fk_Estado`= ? WHERE `id_Factura`= ? ";
        try{
            pst = cn.prepareStatement(sql);
            pst.setInt(1,3);
            pst.setInt(2,codigo);
            pst.execute();
             resul = "correcto update estado factura" ;
            System.out.println(resul);
            
        } catch (Exception e){
            resul = "Error  de update estado factura" + e ;
        } 
        
        
         return resul;
        
    }
    
    
       
  }
    


    
    
    
    


