/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Class;

/**
 *
 * @author kleberstevendiazcoello
 */
public class Detalle_Cierre_Abono {
    String id_a ;
    String fecha_a;
    String cliente_a;
    String subtotal_a;
    String total_a;
    String efectivo_a;
    String tarjeta_a;

    public Detalle_Cierre_Abono(String id_a, String fecha_a, String cliente_a, String subtotal_a, String total_a, String efectivo_a, String tarjeta_a) {
        this.id_a = id_a;
        this.fecha_a = fecha_a;
        this.cliente_a = cliente_a;
        this.subtotal_a = subtotal_a;
        this.total_a = total_a;
        this.efectivo_a = efectivo_a;
        this.tarjeta_a = tarjeta_a;
    }

    public String getId_a() {
        return id_a;
    }

    public void setId_a(String id_a) {
        this.id_a = id_a;
    }

    public String getFecha_a() {
        return fecha_a;
    }

    public void setFecha_a(String fecha_a) {
        this.fecha_a = fecha_a;
    }

    public String getCliente_a() {
        return cliente_a;
    }

    public void setCliente_a(String cliente_a) {
        this.cliente_a = cliente_a;
    }

    public String getSubtotal_a() {
        return subtotal_a;
    }

    public void setSubtotal_a(String subtotal_a) {
        this.subtotal_a = subtotal_a;
    }

    public String getTotal_a() {
        return total_a;
    }

    public void setTotal_a(String total_a) {
        this.total_a = total_a;
    }

    public String getEfectivo_a() {
        return efectivo_a;
    }

    public void setEfectivo_a(String efectivo_a) {
        this.efectivo_a = efectivo_a;
    }

    public String getTarjeta_a() {
        return tarjeta_a;
    }

    public void setTarjeta_a(String tarjeta_a) {
        this.tarjeta_a = tarjeta_a;
    }
    
    
    
}
