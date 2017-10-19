/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Class;

import static Class.Factura.fact;
import DATABASE.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kleberstevendiazcoello
 */
public class SepararPrenda {
    

    
    public static String Separar_prenda(int codigo,int estado,int usuario,float saldo, String dia_vencimiento,String Nota){
        
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO separado values(null,?,?,?,?,?,?,?)";
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
            pst.setInt(1,codigo);
            pst.setInt(2,estado);
            pst.setInt(3,usuario);
            pst.setString(4,dia);
            pst.setString(5,dia_vencimiento);
            pst.setFloat(6,saldo);
            pst.setString(7,Nota);
            pst.execute();
            resul = "Ingresado Correctamente";

            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
        }
        
       cc.desconectar();
        return resul;   
        
    }
    
    
    public static String Guardar_abono_separado(int idseparado,float valor_abonado,int usuario){
        
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO abono values(null,?,?,?,?)";
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
            pst.setInt(1,idseparado);
            pst.setFloat(2,valor_abonado);
            pst.setString(3,dia);
            pst.setInt(4,usuario);
            pst.execute();
            resul = "Ingresado Correctamente";

            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
        }
        
       cc.desconectar();
        return resul;   
        
    }
    
    
    
    public static String Guardar_Detalle_separado(int idseparado,int producto,int cantidad){
        
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO detalle_separado values(null,?,?,?)";
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
            pst.setInt(1,idseparado);
            pst.setInt(2,producto);
            pst.setInt(3,cantidad);
            pst.execute();
            resul = "Ingresado Correctamente";

            
        }catch(SQLException e){
            resul = "Error DETALLE SEPARADO : "+e; 
            System.out.println(resul);
        }
        
       cc.desconectar();
        return resul;   
        
    }
    
    public static int Get_last_id_separado(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql2 = "SELECT MAX(id_Separado) FROM separado";
        int id_ultimo = 0 ;
        try{
         
            pst = cn.prepareStatement(sql2);
            rs =pst.executeQuery();
            if (rs.next()){
                id_ultimo = rs.getInt("MAX(id_Separado)");
            }
            
            
            
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
        }
        
        return id_ultimo;
        
    }
    
    
        
    public static String Ingreso_nuevo_saldo(int codigop , Float nuevo_saldo){
        
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql = "UPDATE `San Pedro`.`separado` SET `Saldo`= ? WHERE `id_Separado`= ? ";
        try{
            pst = cn.prepareStatement(sql);
            pst.setFloat(1,nuevo_saldo);
            pst.setInt(2,codigop);
            pst.execute();
            System.out.println(resul);
        } catch (Exception e){
            resul = "Error update saldo " + e ;
        } 
        
        
         return resul;
        
    }
    
    
    
    public static String Guardar_Abono(int idseparado,float valor,int user){
        
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO abono values(null,?,?,?,?)";
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
            pst.setInt(1,idseparado);
            pst.setFloat(2,valor);
            pst.setString(3,dia);
            pst.setInt(4,user);
            pst.execute();
            resul = "Ingresado Correctamente";

            
        }catch(SQLException e){
            resul = "Error  en guardar abono : "+e; 
            System.out.println(resul);
        }
        
       cc.desconectar();
        return resul;   
        
    }

    
    
    
    
}
