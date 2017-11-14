package edu.upc.minimo;

import java.util.HashMap;

public class Pedido {

    HashMap<String,Producto> objetos;
    HashMap<String,Integer> cantidades;
    String nombreusuario;

    public Pedido(String user, Producto[] objetos, int[] cantidades) {
        this.nombreusuario=user;
        this.objetos=new HashMap<String,Producto>();
        this.cantidades=new HashMap<String, Integer>();
        if(objetos.length==cantidades.length) {
            int i = 0;
            for (Producto p : objetos) {
                if (!this.objetos.containsKey(p.getNombre())) {
                    this.objetos.put(p.getNombre(), p);
                    this.cantidades.put(p.getNombre(), cantidades[i]);
                } else {
                    this.cantidades.put(p.getNombre(), cantidades[i] + this.cantidades.get(p.getNombre()));
                }
                i++;
            }
        }
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public HashMap<String, Producto> getObjetos() {
        return objetos;
    }

    public HashMap<String, Integer> getCantidades() {
        return cantidades;
    }
}
