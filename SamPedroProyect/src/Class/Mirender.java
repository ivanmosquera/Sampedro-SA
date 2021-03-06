/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Class;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Mirender extends DefaultTableCellRenderer {
    private Component componente;
    Date date = new Date();
    @Override
    public Component getTableCellRendererComponent(JTable table,
      Object value,
      boolean isSelected,
      boolean hasFocus,
      int row,
      int column)
   {
       
       
      JLabel label = (JLabel) super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
     Object fechaven = table.getValueAt(row, 3);
     String vencido = String.valueOf(fechaven);
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     Date date1 = new Date() ;
     String dia = dateFormat.format(date);

     Date date2 = new Date();
        try {
            date1 = dateFormat.parse(dia);
        } catch (ParseException ex) {
            Logger.getLogger(Mirender.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            date2 = dateFormat.parse(vencido);
        } catch (ParseException ex) {
            Logger.getLogger(Mirender.class.getName()).log(Level.SEVERE, null, ex);
        }
     int i = date2.compareTo(date1);

      Color c = Color.WHITE;
      if ( i == -1 )
      {
       label.setForeground(Color.RED);
       
      } else {
         // Restaurar los valores por defecto
          label.setForeground(Color.BLACK);
      }
      

      return label;
   }
   
}
