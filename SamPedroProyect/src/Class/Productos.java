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
public class Productos {
    private String PRODUCTO;
    private String TALLA;
    private String PRECIO;
    private String CANTIDAD;
    private String TOTAL;

    public Productos(String PRODUCTO, String TALLA, String PRECIO, String CANTIDAD, String TOTAL) {
        this.PRODUCTO = PRODUCTO;
        this.TALLA = TALLA;
        this.PRECIO = PRECIO;
        this.CANTIDAD = CANTIDAD;
        this.TOTAL = TOTAL;
    }

    public String getPRODUCTO() {
        return PRODUCTO;
    }

    public void setPRODUCTO(String PRODUCTO) {
        this.PRODUCTO = PRODUCTO;
    }

    public String getTALLA() {
        return TALLA;
    }

    public void setTALLA(String TALLA) {
        this.TALLA = TALLA;
    }

    public String getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(String PRECIO) {
        this.PRECIO = PRECIO;
    }

    public String getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(String CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }


    
}
