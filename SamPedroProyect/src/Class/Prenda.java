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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import sanpedroproyect.Ingreso_Nueva_prenda;
import sanpedroproyect.Ingreso_Nuevo_Cliente;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Prenda {
    static Ingreso_Nueva_prenda  pr = new Ingreso_Nueva_prenda();
    public static String Ingresar_Prenda(){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "INSERT INTO producto values(?,?,?,?,?,?,?,?)";
        Date date = new Date();
        //Caso 1: obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);
        //Caso 2: obtener la fecha y salida por pantalla con formato:
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        System.out.println(hora);
        System.out.println(dia);
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,pr.getCodigo());
            pst.setString(2,pr.getDetalle());
            pst.setString(3,pr.getPrecio());
            pst.setString(4,pr.getTalla());
            pst.setInt(5, 1);
            pst.setInt(6, 1);
            pst.setString(7,dia);
            pst.setString(8,hora);
            pst.execute();
            resul = "Ingresado Correctamente";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
            System.out.println(pst);
            
        }
        
        return resul;
    }
}
