/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import static Class.Cliente.cliente;
import DATABASE.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sanpedroproyect.Ingreso_Nuevo_Usuario;

/**
 *
 * @author Ivan Mosquera
 */
public class Usuario {
    static Ingreso_Nuevo_Usuario nuevo_usuario = new Ingreso_Nuevo_Usuario();
    public String usuario;
    public String contraseña;
    public String nombre;
    public String apellido;
    public String cedula;

    public Usuario() {
    }

    public Usuario(String usuario, String contraseña, String nombre, String apellido, String cedula) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }
    
    

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    
    
    
    
}
