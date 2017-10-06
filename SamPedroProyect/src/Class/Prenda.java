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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import sanpedroproyect.Ingreso_Nueva_prenda;
import sanpedroproyect.Ingreso_Nuevo_Cliente;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Prenda {
    
    public int Codigo;
    public String Detalle;
    public String Talla;
    public float Precio;

    public Prenda() {
        
    }
    
    public Prenda(int Codigo, String Detalle, String Talla, float Precio) {
        this.Codigo = Codigo;
        this.Detalle = Detalle;
        this.Talla = Talla;
        this.Precio = Precio;
    }
    
    static Ingreso_Nueva_prenda  pr = new Ingreso_Nueva_prenda();
    public static String Ingresar_Prenda(int usuario,int categoria){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO producto values(?,?,?,?,?,?,?,?,?)";
        Date date = new Date();
        //Caso 1: obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        //Caso 2: obtener la fecha y salida por pantalla con formato:
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        System.out.println(hora);
        System.out.println(dia);
        String formattedString = String.format(java.util.Locale.US,"%.2f", pr.getPrecio());
        float precio = Float.parseFloat(formattedString);
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,pr.getCodigo());
            pst.setString(2,pr.getDetalle());
            pst.setFloat(3, precio);
            pst.setString(4,pr.getTalla());
            pst.setInt(5, categoria);
            pst.setInt(6, usuario);
            pst.setString(7,dia);
            pst.setString(8,hora);
            pst.setInt(9, 0);
            pst.execute();
            resul = "Prenda Ingresado Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Prenda  Incorrectamente";
            System.out.println(resul);
            System.out.println(pst);
            
        }
        
        return resul;
    }
    
    
    
      public DefaultTableModel consultar_Producto_total(String Nombre ){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
  
        
        
        String sql  = "SELECT id_Producto AS codigo , P.Descripcion ,Talla, C.Nombre AS Categoria , cantidad_total as Cantidad FROM producto P , categoria C WHERE fk_Categoria = id_Categoria ;";
        
        try {
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();
            //for(int i = 1;i<=col;i++){
              //  modelo.addColumn(rsmd.getColumnName(i));}
        //
            
            modelo.addColumn("Codigo");
            modelo.addColumn("Descripcion");
            modelo.addColumn("Talla");
            modelo.addColumn("Categoria");
            modelo.addColumn("Cantidad");
            
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
      
      
      
      public int GetidCategoria(String nombre){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int id_c = 0;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
        try{
            String sql = ("SELECT id_Categoria FROM categoria Where Nombre = ? ");
            pst = cn.prepareStatement(sql);
            pst.setString(1,nombre);
            rs =pst.executeQuery();
            if (rs.next()){
                  id_c = rs.getInt("id_Categoria");
                }

            
                
            } catch (Exception ex){
                            System.out.println(ex);
            }
          System.out.println("Categoria id " +  id_c);
          return id_c;
      }
}
