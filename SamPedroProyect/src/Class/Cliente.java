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

/**
 *
 * @author kleberstevendiazcoello
 */
public class Cliente {
    static Ingreso_Nuevo_Cliente cliente = new Ingreso_Nuevo_Cliente();
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
            pst.setString(3,cliente.getApellido());
            pst.setString(4,cliente.getCorreo());
            pst.setString(5,cliente.getTelefono());
            pst.setString(6,cliente.getDireccion());
            pst.setString(7,cliente.getCiudad());
            pst.setString(8,cliente.getNota());
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
    
}
