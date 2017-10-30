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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import sanpedroproyect.Ingreso_Inventario;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Inventario {
     String Descripcion;
     String Talla;
     static Ingreso_Inventario Nuevo_Inventario = new Ingreso_Inventario();

    public static String Ingresar_Inventario(String codigoproducto,int cantidad,int usuario) {
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql = "INSERT INTO inventario values(null,?,?,?,?,?,?)";
         Date date = new Date();
        //Caso 1: obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        //Caso 2: obtener la fecha y salida por pantalla con formato:
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,codigoproducto);
            pst.setInt(2,cantidad);
            pst.setString(3,"INGRESO");
            pst.setInt(4,usuario);
            pst.setString(5,dia);
            pst.setString(6,hora);
            pst.execute();
            resul = "Inventario Ingresado Correctamente";
            System.out.println(resul);
        } catch (Exception e){
            System.out.println("Error Incremento " + e);
        }
        
       
       
       
        
        return resul ;
    }
    
    public static String Decremento_inventario(String codigo,int cantidad){
        
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql = "INSERT INTO inventario values(null,?,?,?,?,?,?)";
         Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,codigo);
            pst.setInt(2,cantidad);
            pst.setString(3,"SALIDA");
            pst.setInt(4,1);
            pst.setString(5,dia);
            pst.setString(6,hora);
            pst.execute();
            resul = "Decrementado Inventario correcto";
            System.out.println(resul);
        } catch (Exception e){
            System.out.println("Error Decremento " + e);
        }
        
       
       
       
        
        return null;
    }
    
        public static String Decremento_inventario_Cambio_Prenda(String codigo,int cantidad){
        
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql = "INSERT INTO inventario values(null,?,?,?,?,?,?)";
         Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,codigo);
            pst.setInt(2,cantidad);
            pst.setString(3,"SEPARADO");
            pst.setInt(4,1);
            pst.setString(5,dia);
            pst.setString(6,hora);
            pst.execute();
            resul = "Decrementado Inventario correcto";
            System.out.println(resul);
        } catch (Exception e){
            System.out.println("Error Decremento " + e);
        }
        
       
       
       
        
        return null;
    }
    
    
    

      public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String Talla) {
        this.Talla = Talla;
    }

    public void Decremento_inventario_Separado(String codigo_a_guardar, int cantidad) {
               
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql = "INSERT INTO inventario values(null,?,?,?,?,?,?)";
         Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,codigo_a_guardar);
            pst.setInt(2,cantidad);
            pst.setString(3,"SEPARADO");
            pst.setInt(4,1);
            pst.setString(5,dia);
            pst.setString(6,hora);
            pst.execute();
            resul = "Ingresado Correctamente";
            System.out.println(resul);
        } catch (Exception e){
            System.out.println("Error de decremento de inventario "+e);
        }
    }
    
      public void Aumento_inventario_Devolucion_Separado(String codigo_a_guardar, int cantidad) {
               
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql = "INSERT INTO inventario values(null,?,?,?,?,?,?)";
         Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,codigo_a_guardar);
            pst.setInt(2,cantidad);
            pst.setString(3,"DEVOLUCION SEPARADO");
            pst.setInt(4,1);
            pst.setString(5,dia);
            pst.setString(6,hora);
            pst.execute();
            resul = "Ingresado Correctamente";
            System.out.println(resul);
        } catch (Exception e){
            System.out.println("Error de decremento de inventario "+e);
        }
    } 
    
    
    
    public static String Incremeneto_total_producto(String codigop , int can){
        
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql = "UPDATE `San Pedro`.`producto` SET `cantidad_total`= ? WHERE `id_Producto`= ?; ";
        try{
            pst = cn.prepareStatement(sql);
            pst.setInt(1,can);
            pst.setString(2,codigop);
            pst.execute();
            System.out.println(resul);
            resul = "Incremento a producto correcto" ;
        } catch (Exception e){
            resul = "Error  de update producto" + e ;
        } 
        
        
         return resul;
        
    }
    
    
    public static int get_cantidad_total_producto(String cod){
         String resul = null , lats = null;
         ConnectionDB cc = new ConnectionDB();
         Connection cn = cc.getConnection();
         PreparedStatement pst =null;
         ResultSet rs = null;
         int cantidad_ob = 0;
           String Desc;
        try{
                String sql = ("SELECT cantidad_total FROM producto where id_Producto = ?");
                pst = cn.prepareStatement(sql);
                pst.setString(1, cod);
                rs =pst.executeQuery();
                if (rs.next()){
                    cantidad_ob = rs.getInt("cantidad_total");           
                          
                }
            } catch (Exception ex){
            System.out.println(ex);
             }                    
         return cantidad_ob;
    }
    
    
    
    
        public static String Ingresar_Inventario_Anulacion(String codigo_a_guardar, int cantidad) {
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql = "INSERT INTO inventario values(null,?,?,?,?,?,?)";
         Date date = new Date();
        //Caso 1: obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        //Caso 2: obtener la fecha y salida por pantalla con formato:
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1, codigo_a_guardar);
            pst.setInt(2,cantidad);
            pst.setString(3,"DEVOLUCIÓN");
            pst.setInt(4,1);
            pst.setString(5,dia);
            pst.setString(6,hora);
            pst.execute();
            resul = "Ingresado Correctamente Devlucion";
            System.out.println(resul);
        } catch (Exception e){
            System.out.println(e);
        }
        
       
       
       
        
        return null;
    }
        
        
    public void Aumento_inventario__genracion_factura_separado(String codigo_a_guardar, int cantidad) {
               
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        String sql = "INSERT INTO inventario values(null,?,?,?,?,?,?)";
         Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,codigo_a_guardar);
            pst.setInt(2,cantidad);
            pst.setString(3,"SEPARADO COMPLETO");
            pst.setInt(4,1);
            pst.setString(5,dia);
            pst.setString(6,hora);
            pst.execute();
            resul = "Ingresado Correctamente";
            System.out.println(resul);
        } catch (Exception e){
            System.out.println("Error de decremento de inventario "+e);
        }
    } 

}
