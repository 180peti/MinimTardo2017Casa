package edu.upc.minimo;

import java.util.ArrayList;

public interface ProductManager {

    ArrayList<Producto> ordenPrecio();

    boolean realizarPedido(String user,Producto[] nombres,int[] cantidad);

    String servirPedido();

    ArrayList<Pedido> pedidosUsuario(String user);

    ArrayList<Producto> ordenVentas();
}
