package com.example.n550j.acarreos.Objetos;

/**
 * Created by N550J on 27/09/2017.
 */

public class Vehiculo {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getNumero_pasajeros() {
        return numero_pasajeros;
    }

    public void setNumero_pasajeros(String numero_pasajeros) {
        this.numero_pasajeros = numero_pasajeros;
    }


    public Vehiculo(String id, String marca, String longitud, String ancho, String numero_pasajeros,int imagen) {
        this.id=id;
        this.marca = marca;
        this.longitud = longitud;
        this.ancho = ancho;
        this.numero_pasajeros = numero_pasajeros;
        this.imagen=imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    int imagen;
    String marca;
    String longitud;
    String ancho;
    String numero_pasajeros;

}
