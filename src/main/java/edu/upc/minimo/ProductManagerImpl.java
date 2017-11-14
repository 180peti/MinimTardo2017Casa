package edu.upc.minimo;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductManagerImpl implements ProductManager {

    HashMap<String,Usuario> usuarios;
    ArrayList<Producto> productos;
    ArrayList<Pedido> pedidos;
    private static ProductManagerImpl instance;
    Logger log=Logger.getLogger(ProductManagerImpl.class);

    public ProductManagerImpl(){
        usuarios=new HashMap<String, Usuario>();
        productos=new ArrayList<Producto>();
        Producto a=new Producto("Leche",5);
        productos.add(a);
        a=new Producto("Pan",2);
        productos.add(a);
        a=new Producto("Huevos",10);
        productos.add(a);
        a=new Producto("Jamon",7);
        productos.add(a);
        a=new Producto("Pizza",15);
        productos.add(a);
        a=new Producto("Pescado",13);
        productos.add(a);
        pedidos=new ArrayList<Pedido>();
    }

    public static ProductManagerImpl getInstance() {
        if (instance==null){instance=new ProductManagerImpl();}
        return instance;
    }

    public void borrar(){
        instance=null;
    }

    public void ordenarP(ArrayList<Producto> cosas){

        this.productos=new ArrayList<Producto>(cosas.size());
        Producto buf;
        for(int i=0;i<cosas.size();i++){
            for(int j=0;j<cosas.size();j++){
                if(i!=j){
                    if(cosas.get(i).getPrecio()>cosas.get(j).getPrecio()&&i<j){
                         buf=cosas.remove(i);
                         cosas.add(i,cosas.remove(j-1));
                         cosas.add(j,buf);
                    }
                }
            }
        }
        this.productos=cosas;

    }

    public ArrayList<Producto> ordenPrecio() {
        ordenarP(productos);
        return productos;
    }

    public boolean realizarPedido(String user,Producto[] cosas,int[] cantidad) {
        boolean hecho=false;
        if(usuarios.containsKey(user))
        {
            Pedido a=new Pedido(user, cosas,cantidad);
            usuarios.get(user).añadirPedido(a);
            pedidos.add(a);
        }
        return false;
    }

    public String servirPedido() {
        String frase="";
        int precio=0;
        Pedido siguiente=pedidos.remove(0);
        for(Producto p:productos){
            if (siguiente.getObjetos().containsKey(p.getNombre())){
                int cantidad=siguiente.getCantidades().get(p.getNombre());
                frase=frase+"Ha pedido "+cantidad+" unidades de "+p.getNombre()+"\n";
                precio=precio+cantidad*p.getPrecio();
                p.setVentas(cantidad);
            }
        }
        usuarios.get(siguiente.getNombreusuario()).pedidoRealizado();
        frase=frase+"Precio total: "+precio;
        return frase;
    }

    public ArrayList<Pedido> pedidosUsuario(String user) {
        ArrayList<Pedido> hechos=null;
        if(usuarios.containsKey(user)){
            hechos=usuarios.get(user).getRealizados();
        }
        return hechos;
    }

    private void ordenarV(ArrayList<Producto> cosas){
        this.productos=new ArrayList<Producto>(cosas.size());
        Producto buf;
        for(int i=0;i<cosas.size();i++){
            for(int j=0;j<cosas.size();j++){
                if(i!=j){
                    if(cosas.get(i).getVentas()<cosas.get(j).getVentas()&&i<j){
                        buf=cosas.remove(i);
                        cosas.add(i,cosas.remove(j-1));
                        cosas.add(j,buf);
                    }
                }
            }
        }
        this.productos=cosas;
    }

    public ArrayList<Producto> ordenVentas() {
        ordenarV(productos);
        return productos;
    }


    //GETTERS Y SETTERS


    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setUsuarios(Usuario user) {
        this.usuarios.put(user.getNombre(),user);
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
