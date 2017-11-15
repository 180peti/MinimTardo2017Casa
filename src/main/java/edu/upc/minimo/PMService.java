package edu.upc.minimo;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cafeteria")
public class PMService {

    ProductManagerImpl test=ProductManagerImpl.getInstance();




    @Path("/productos/precio")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String productosprecio(){
        String cosas="";
        for(Producto p:test.ordenPrecio()){
            cosas=cosas+"El producto "+p.getNombre()+" vale "+p.getPrecio()+" euros.\n";
        }
        return cosas;
    }

    @Path("/productos/ventas")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String productosventa(){
        String cosas="";
        for(Producto p:test.ordenVentas()){
            cosas=cosas+"El producto "+p.getNombre()+" se ha vendido "+p.getVentas()+" veces.\n";
        }
        return cosas;
    }

    @Path("/pedir")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String pedido(){
        Producto[] pedidoestandar=new Producto[4];
        pedidoestandar[0]=test.getProductos().get(0);
        pedidoestandar[1]=test.getProductos().get(1);
        pedidoestandar[2]=test.getProductos().get(2);
        pedidoestandar[3]=test.getProductos().get(3);
        int[] cantidad=new int[4];
        cantidad[0]=2;
        cantidad[1]=5;
        cantidad[2]=4;
        cantidad[3]=8;
        if(!test.getUsuarios().containsKey("Pepe")){
            Usuario usuario=new Usuario("Pepe");
            test.setUsuarios(usuario);
        }
        boolean si=test.realizarPedido("Pepe",pedidoestandar,cantidad);
        if(si)
            return "Pedido estandar realizado";
        else
            return "Fallo en el pedido";
    }

    @Path("/servir")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String servir(){

        return test.servirPedido();

    }

    @Path("/{usuario}/realizados")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String pedidosRealizados(@PathParam("usuario") String user){
        String frase="";
        for(Pedido p:test.pedidosUsuario(user)){
            for(Producto b:test.getProductos()){
                if(p.getObjetos().containsKey(b.getNombre())){
                    frase=frase+"Se le ha servido "+p.getCantidades().get(b.getNombre())+" veces el producto "+p.getObjetos().get(b.getNombre()).getNombre()+"\n";
                }
            }
            frase=frase+"\n";
        }
        return frase;
    }
    /*
    @Path("/pedir")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response hacerPedido(String usuario,String[] cosas,int[] cantidades){
        int estado=501;
        if(test.getUsuarios().containsKey(usuario)) {
            int i = 0;
            Producto[] pedir = new Producto[cosas.length];
            int[] cantidad = new int[cantidades.length];
            for (String cosa : cosas) {
                for (Producto p : test.getProductos()) {
                    if (cosa == p.getNombre()) {
                        pedir[i] = p;
                        cantidad[i] = cantidades[i];
                        i++;
                        break;
                    }
                }
            }
            Producto[] tienen=new Producto[i];
            int[] cuantos = new int[i];
            i=0;
            for(Producto p:pedir){
                if (p != null) {
                    tienen[i]=p;
                    cuantos[i]=cantidad[i];
                    i++;
                }
            }
            test.realizarPedido(usuario,tienen,cuantos);
            estado=201;
        }

        return Response.status(estado).build();
    }*/

}
