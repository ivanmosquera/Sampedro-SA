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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Reporte_Operaciones {
    public DefaultTableModel consultar_inventario(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
        String sql  =  "SELECT p.Descripcion,Talla,Precio,Cantidad,Mov,Fecha,Hora From inventario i"
                + ",producto p Where"
                + " id_Producto = fk_Producto";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
    }
    
    public DefaultTableModel consultar_Saldos_cliente(int codigo_cliente){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
        String sql  =  "Select id_Separado , id_Producto , P.Descripcion ,P.talla, S.Saldo  FROM separado S, detalle_separado , producto P WHERE fk_cliente = ? and id_separado = fk_separado and fk_producto =  id_Producto;";
        
        try {
            pst = cn.prepareStatement(sql);
            pst.setInt(1, codigo_cliente);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
    } 
    
    
    
    
   public DefaultTableModel consultar_producto(String cadena){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
        String sql  =  "SELECT id_Producto, Descripcion, Precio, Talla FROM producto Where Descripcion = '"+cadena+"';";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
    }
    
   
   
     public DefaultTableModel consultar_producto(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
        String sql  =  "SELECT id_Producto, Descripcion, Precio, Talla FROM producto ";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                   
                }
                 modelo.addRow(filas);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
    }
     
     
     
     public DefaultTableModel consultar_inventario_filter_nombre(String Nombre ){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
  
        
        
        String sql  =   "SELECT p.Descripcion,Talla,Precio,Cantidad,Mov,Fecha,Hora From inventario i"
                + ",producto p Where"
                + " id_Producto = fk_Producto and Descripcion = '"+Nombre+"';";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
         
     }
     
     public DefaultTableModel consultar_inventario_filter_talla(String Nombre,String talla ){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
  
        
        
        String sql  =   "SELECT p.Descripcion,Talla,Precio,Cantidad,Mov,Fecha,Hora From inventario i"
                + ",producto p Where"
                + " id_Producto = fk_Producto and Descripcion = '"+Nombre+"' and Talla = '"+talla+"';";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
         
     }
     
        
     public DefaultTableModel consultar_inventario_filter_Categoria(String Nombre,String Categoria ){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
  
        
        
        String sql  =   "SELECT p.Descripcion,Talla,Precio,Cantidad,Mov,Fecha,Hora From inventario i"
                + ",producto p Where"
                + " id_Producto = fk_Producto and Descripcion = '"+Nombre+"' and Categoria = '"+Categoria+"';";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
         
     }
     
     
     
        
     public DefaultTableModel consultar_inventario_filter_talla_categoria(String Nombre,String talla,String categoria ){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
  
        
        
        String sql  =   "SELECT p.Descripcion,Talla,Precio,Cantidad,Mov,Fecha,Hora From inventario i"
                + ",producto p Where"
                + " id_Producto = fk_Producto and Descripcion = '"+Nombre+"' and Talla = '"+talla+"' and Categoria = '"+categoria+"';";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
         
     }
     
     
      public DefaultTableModel consultar_inventario_filter_movimiento(String Nombre,String movimiento ){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
  
        
        
        String sql  =   "SELECT p.Descripcion,Talla,Precio,Cantidad,Mov,Fecha,Hora From inventario i"
                + ",producto p Where"
                + " id_Producto = fk_Producto and Descripcion = '"+Nombre+"' and Mov = '"+movimiento+"';";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
         
     }
      
      
      
      
      
      
      
      public DefaultTableModel consultar_inventario_filter_talla_mov(String Nombre,String talla,String movimiento ){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
  
        
        
        String sql  =   "SELECT p.Descripcion,Talla,Precio,Cantidad,Mov,Fecha,Hora From inventario i"
                + ",producto p Where"
                + " id_Producto = fk_Producto and Descripcion = '"+Nombre+"' and Talla = '"+talla+"' and Mov = '"+movimiento+"';";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
         
     }
      
 
    public DefaultTableModel consultar_Separados(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
        String sql  =  "Select U.Nombre, Fecha_separado ,Fecha_vencimiento , "
                + "C.Nombre, P.id_Producto, P.Descripcion ,P.talla, S.Saldo  "
                + "FROM separado S, detalle_separado , producto P, usuario U, cliente C "
                + "WHERE id_Cliente = fk_cliente AND "
                + "id_separado = fk_separado and fk_producto =  id_Producto AND"
                + " id_Usuario = fk_Usuario;";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
    }
      
     public DefaultTableModel consultar_Factura_fechaactual(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
        Date date = new Date();
        //Caso 1: obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        //Caso 2: obtener la fecha y salida por pantalla con formato:
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dia = dateFormat.format(date);
        String sql  = "Select id_Factura, F.Fecha, C.Nombre , Subtotal ,  Total From cliente c , factura F   Where fk_Cliente = id_Cliente and Fecha = ?;";
        
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, dia);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            for(int i = 1;i<=col;i++){
                modelo.addColumn(rsmd.getColumnName(i));}
            while(rs.next()){
                
                String filas[]= new String[col];
                for(int j = 0;j<col;j++){
                    filas[j]=rs.getString(j+1);
                    
                }
                modelo.addRow(filas);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
    } 
     
     
     public Float getTotal(){
         String resul = null , lats = null;
         ConnectionDB cc = new ConnectionDB();
         Connection cn = cc.getConnection();
         PreparedStatement pst =null;
         ResultSet rs = null;
          Date date = new Date();
        //Caso 1: obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        //Caso 2: obtener la fecha y salida por pantalla con formato:
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dia = dateFormat.format(date);
         String Desc;
         float total = (float) 0.0;
            try{
               String sql = ("Select SUM(Total) From Factura Where Fecha= ? ;");
                pst = cn.prepareStatement(sql);
                pst.setString(1, dia);
                rs =pst.executeQuery();
                if (rs.next()){
                    total = rs.getFloat("SUM(total)");
                          
                }



            } catch (Exception ex){
                            System.out.println(ex);
            }
                    
        return total;
     }
      
     
   
}
