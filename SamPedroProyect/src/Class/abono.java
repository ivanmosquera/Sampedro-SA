/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Class;

import static Class.Cliente.mod;
import static Class.Prenda.pr;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kleberstevendiazcoello
 */
public class abono {
        public DefaultTableModel consultar_abonos(int id){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        ResultSet rs = null;
        int col;
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData rsmd = null;
        String sql  = "SELECT valor, Fecha , Usuario  "
                + "FROM separado s, abono a  , cliente c , usuario u "
                + "where id_separado = fk_Separado and id_cliente = fk_cliente and a.fk_Usuario = id_Usuario and s.id_separado = ? ";
        
        try {
            pst = cn.prepareStatement(sql);
            pst.setInt(1,id);
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
            
            System.out.println("Consultar abono");
            
        } catch (SQLException ex) {
            Logger.getLogger(Reporte_Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("error : "+  e);
        }
        
        return modelo;
    }

        
        
  public float getTotal(int id){
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
               String sql = ("Select SUM(valor) "
                        + "FROM separado s , abono a  , cliente c , usuario u "
                + "where id_separado = fk_Separado and id_cliente = fk_cliente and a.fk_Usuario = id_Usuario and s.id_separado = ? ");
                pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                rs =pst.executeQuery();
                if (rs.next()){
                    total = rs.getFloat("SUM(valor)");
                          
                }



            } catch (Exception ex){
                            System.out.println(ex);
            }
                    
        return total;
     }
  
  
  
  
  public static String Eliminar_Separado(int id){
        String resul = null , lats = null;
        ConnectionDB cc = new ConnectionDB();
        Connection cn = cc.getConnection();
        PreparedStatement pst =null;
        String sql = "UPDATE `San Pedro`.`separado` SET `fk_Estado`= ? WHERE `id_Separado`= ? ;";
        try{
            pst = cn.prepareStatement(sql);
            pst.setInt(1,3);
            pst.setInt(2,id);
            pst.execute();
            resul = "Separacion Eliminada CORRECTAMENTE";
            System.out.println(resul);
            
        }catch(SQLException e){
            resul = "Error : "+e; 
            System.out.println(resul);
        }
        cc.desconectar();
        return resul;
        
        
    }
}
