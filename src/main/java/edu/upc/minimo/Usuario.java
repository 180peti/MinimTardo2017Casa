package edu.upc.minimo;

import java.util.ArrayList;

public class Usuario {

    String nombre;
    ArrayList<Pedido> pendientes,realizados;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.pendientes=new ArrayList<Pedido>();
        this.realizados=new ArrayList<Pedido>();
    }

    public void añadirPedido(Pedido p){
        pendientes.add(p);
    }

    public void pedidoRealizado(){
        realizados.add(pendientes.remove(0));
    }

    //GETTERS Y SETTERS


    public ArrayList<Pedido> getRealizados() {
        return realizados;
    }

    public String getNombre() {
        return nombre;
    }
}
