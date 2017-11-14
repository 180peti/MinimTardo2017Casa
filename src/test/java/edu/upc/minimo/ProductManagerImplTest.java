package edu.upc.minimo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProductManagerImplTest {
    ProductManagerImpl test;
    @Before
    public void setUp() throws Exception {
        test=ProductManagerImpl.getInstance();
        Usuario a=new Usuario("Pepe");
        test.setUsuarios(a);
        a=new Usuario("Ruben");
        test.setUsuarios(a);
    }

    @After
    public void tearDown() throws Exception {
        test.getInstance().borrar();
    }


    @Test
    public void realizarPedido(){
        Producto[] pedido=new Producto[3];
        pedido[0]=test.getProductos().get(0);
        pedido[1]=test.getProductos().get(2);
        pedido[2]=test.getProductos().get(3);
        int[] cantidad=new int[3];
        cantidad[0]=2;
        cantidad[1]=6;
        cantidad[2]=3;
        assertEquals(0,test.getPedidos().size());
        test.realizarPedido("Pepe",pedido,cantidad);
        assertEquals(1,test.getPedidos().size());
    }

    @Test
    public void servirPedido(){
        Producto[] pedido=new Producto[3];
        pedido[0]=test.getProductos().get(0);
        pedido[1]=test.getProductos().get(2);
        pedido[2]=test.getProductos().get(3);
        int[] cantidad=new int[3];
        cantidad[0]=2;
        cantidad[1]=6;
        cantidad[2]=3;
        test.realizarPedido("Pepe",pedido,cantidad);

        pedido=new Producto[3];
        pedido[0]=test.getProductos().get(1);
        pedido[1]=test.getProductos().get(2);
        pedido[2]=test.getProductos().get(4);
        cantidad=new int[3];
        cantidad[0]=1;
        cantidad[1]=12;
        cantidad[2]=3;
        Usuario us=new Usuario("Juan");
        test.setUsuarios(us);
        test.realizarPedido(us.getNombre(),pedido,cantidad);

        String frase=test.servirPedido();
        assertEquals(1,test.getPedidos().size());
        assertEquals(us.getNombre(),test.getPedidos().get(0).getNombreusuario());
    }

    /*
    @Test
    public void orden(){
        ArrayList<Producto> a=test.ordenPrecio();
        assertEquals(2,a.get(0).getPrecio());
        assertEquals(5,a.get(1).getPrecio());
        assertEquals(7,a.get(2).getPrecio());
        assertEquals(10,a.get(3).getPrecio());
        assertEquals(13,a.get(4).getPrecio());
        assertEquals(15,a.get(5).getPrecio());
    }*/

    /*
    @Test
    public void servirYOrdenarVentas(){
        Producto[] pedido=new Producto[3];
        pedido[0]=test.getProductos().get(0);
        pedido[1]=test.getProductos().get(2);
        pedido[2]=test.getProductos().get(3);
        int[] cantidad=new int[3];
        cantidad[0]=2;
        cantidad[1]=6;
        cantidad[2]=3;
        Usuario user=new Usuario("Pepe");
        test.setUsuarios(user);
        test.realizarPedido("Pepe",pedido,cantidad);
        String frase=test.servirPedido();
        ArrayList<Pedido> a=test.pedidosUsuario("Pepe");
        ArrayList<Producto> b=test.ordenVentas();
        b=test.ordenPrecio();
        a=null;
    }
    */
}