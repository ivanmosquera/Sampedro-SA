/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import DATABASE.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; //necesario para realizar consultas SELECT
import java.sql.SQLException;
import java.sql.Statement;
import sanpedroproyect.GUI_Factura;
//import org.fife.ui.autocomplete.*;
/**
 *
 * @author Ivan Mosquera
 */
public class Factura {
    static GUI_Factura  fact = new GUI_Factura();
    public static Prenda[] cargar_Productos(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "SELECT * from producto";
        Prenda [] prendas = null;
        int i = 0;
        try{
            pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery (); //Cargamos datos devueltos de la consulta SELECT en rs
            resul = "Cargado Correctamente";
            System.out.println(resul);
            while(rs.next()){
                i++;
            }
            prendas = new Prenda[i];
            i = 0;
            rs.beforeFirst();
            /*Imprimimos por pantalla todos los idProducto devueltos de la bÃºsqueda*/
            while (rs.next()) {
                //prendas[i] = new Prenda();
                int productid = rs.getInt("id_Producto");
                String detalle = rs.getString("Descripcion");
                float precio = rs.getFloat("Precio");
                String talla = rs.getString("Talla");
                prendas[i] = new Prenda(productid, detalle, talla, precio);
                System.out.println("Codigo : " + prendas[i].Codigo + " Detalle: " + prendas[i].Detalle + " Precio: " + prendas[i].Precio + " Talla: " + prendas[i].Talla);
                //System.out.println("Codigo : " + productid + " Detalle: " + detalle + " Precio: " + precio + " Talla: " + talla);

                i++;
            }  
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
            System.out.println(pst);
            
        }/*
        Statement s = cc.createStatement(); 
        ResultSet rs = s.executeQuery ("SELECT idProducto from producto");
        while (rs.next()) 
        { 
            System.out.println (rs.getInt (1)); 
        }*/
        return prendas;      
  
    }
    
}
