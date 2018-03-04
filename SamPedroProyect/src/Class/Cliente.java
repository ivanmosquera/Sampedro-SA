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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
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
        String sql = "INSERT INTO cliente values(null,?,?,?,?,?,?,?,?,?)";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,cliente.getCedula());
            pst.setString(2,cliente.getNombre());
            pst.setString(3,cliente.getCorreo());
            pst.setString(4,cliente.getTelefono());
            pst.setString(5,cliente.getCelular());
            pst.setString(6,cliente.getDireccion());
            pst.setString(7,cliente.getCiudad());
            pst.setString(8,cliente.getNota());
            pst.setString(9,"ACTIVO");
            pst.execute();
            resul = "CORRECTO INGRESO CLIENTE";
           
            
        }catch(SQLException e){
            resul = "INCORRECTO INGRESO CLIENTE"; 
            System.out.println(resul);
            System.out.println(pst);
            System.out.println(cliente.getCedula());
        }
        cc.desconectar();
        return resul;
    }
    
    public String existe_cliente(String Cedula){
        
        String msj = "";
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
        String sql  =  "SELECT * FROM cliente WHERE Cedula = ? ";
        
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, Cedula);
            rs = pst.executeQuery();
            
            if(rs.last()){
                msj = "Existe";
                System.out.println(msj);
            }
            else{
                msj = "No Existe";
                System.out.println(msj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error en buscqueda cliente : "+  e);
        }
        
        return msj;
        
    }
    
    
    
    public static String Modificar_Cliente(){
       String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "UPDATE cliente SET Cedula= ? , Nombre= ? , Correo= ?, Telefono= ? , Celular= ?, Direccion= ? , Ciudad= ? , Nota= ?   WHERE id_Cliente= ?;";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,mod.getCedula());
            pst.setString(2,mod.getNombre());
            pst.setString(3,mod.getCorreo());
            pst.setString(4,mod.getTelefono());
            pst.setString(5,mod.getCelular());
            pst.setString(6,mod.getDireccion());
            pst.setString(7,mod.getCiudad());
            pst.setString(8,mod.getNota());
            pst.setInt(9,mod.getId_cliente());
            pst.execute();
            resul = "Cliente Modificado Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
        }
        cc.desconectar();
        return resul;
        
    }
    
    public static String Eliminar_Cliente(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "UPDATE cliente SET Estado= ?  WHERE id_Cliente = ?;";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,"ELIMINADO");
            pst.setInt(2,mod.getId_cliente());
            pst.execute();
            resul = "CLIENTE ELIMINADO CORRECTAMENTE";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
        }
        cc.desconectar();
        return resul;
        
        
    }
    
    
}
