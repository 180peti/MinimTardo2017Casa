package edu.upc.minimo;

public class Producto {

    String nombre;
    int precio,ventas;

    public Producto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.ventas = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = this.ventas + ventas;
    }
}
