/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Class;

import DATABASE.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sanpedroproyect.Ingreso_Nuevo_Cliente;
import sanpedroproyect.Modificar_Eliminar_Cliente;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Cliente {
    static Ingreso_Nuevo_Cliente cliente = new Ingreso_Nuevo_Cliente();
    static Modificar_Eliminar_Cliente mod = new Modificar_Eliminar_Cliente();
    public static String Ingresar_cliente(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO cliente values(null,?,?,?,?,?,?,?,?)";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,cliente.getCedula());
            pst.setString(2,cliente.getNombre());
            pst.setString(3,cliente.getCorreo());
            pst.setString(4,cliente.getTelefono());
            pst.setString(5,cliente.getDireccion());
            pst.setString(6,cliente.getCiudad());
            pst.setString(7,cliente.getNota());
            pst.setString(8,"ACTIVO");
            pst.execute();
            resul = "Ingresado Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
            System.out.println(pst);
            System.out.println(cliente.getCedula());
        }
        
        return resul;
    }
    
    public static String Modificar_Cliente(){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "UPDATE `San Pedro`.`cliente` SET `Cedula`= ? , `Nombre`= ? , `Correo`= ?, `Telefono`= ? , `Direccion`= ? , `Ciudad`= ? , `Nota`= ?   WHERE `id_Cliente`= ?;";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,mod.getCedula());
            pst.setString(2,mod.getNombre());
            pst.setString(3,mod.getCorreo());
            pst.setString(4,mod.getTelefono());
            pst.setString(5,mod.getDireccion());
            pst.setString(6,mod.getCiudad());
            pst.setString(7,mod.getNota());
            pst.setInt(8,mod.getId_cliente());
            pst.execute();
            resul = "Modificado Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
        }
        
        return resul;
        
    }
    
    public static String Eliminar_Cliente(){
String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "UPDATE `San Pedro`.`cliente` SET `Estado`= ?  WHERE `id_Cliente`= ?;";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,"ELIMINADO");
            pst.setInt(2,mod.getId_cliente());
            pst.execute();
            resul = "ELIMINADO CORRECTAMENTE";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
        }
        
        return resul;
        
        
    }
    
    
}
