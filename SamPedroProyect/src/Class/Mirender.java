/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Class;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Mirender extends DefaultTableCellRenderer {
    private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table,
      Object value,
      boolean isSelected,
      boolean hasFocus,
      int row,
      int column)
   {
       
       
      /* componente = super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
      if (  )
      {
         this.setOpaque(true);
         this.setBackground(Color.RED);
         this.setForeground(Color.YELLOW);
      } else {
         // Restaurar los valores por defecto
      }
*/
      return componente;
   }
   
}
