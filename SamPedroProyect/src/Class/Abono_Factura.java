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
public class Abono_Factura {
    private String VALOR;
    private String FECHA;

    public Abono_Factura(String VALOR, String FECHA) {
        this.VALOR = VALOR;
        this.FECHA = FECHA;
    }

    public String getVALOR() {
        return VALOR;
    }

    public void setVALOR(String VALOR) {
        this.VALOR = VALOR;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }
    
}
