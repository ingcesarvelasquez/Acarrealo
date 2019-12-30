package com.example.n550j.acarreos.Objetos;

/**
 * Created by N550J on 07/10/2017.
 */

public class Usuario {
    String nombre, email, contrasena, apellido,telefono, id_u, identificador;

    public Usuario() {
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Usuario(String identificador, String nombre, String email, String contrasena, String apellido, String telefono, String id_u) {
        this.identificador=identificador;
        this.nombre = nombre;
        this.email = email;

        this.contrasena = contrasena;
        this.apellido = apellido;
        this.telefono = telefono;
        this.id_u=id_u;
    }
    public Usuario(String email, String contrasena) {
        this.email = email;
        this.contrasena = contrasena;
    }

    public String getId_u() {
        return id_u;
    }

    public void setId_u(String id_u) {
        this.id_u = id_u;
    }



    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



}
