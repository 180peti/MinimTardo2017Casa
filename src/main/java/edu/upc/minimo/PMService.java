package edu.upc.minimo;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cafeteria")
public class PMService {

    ProductManagerImpl test=ProductManagerImpl.getInstance();


    @Path("/productos")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String productos(){
        String cosas="";
        for(Producto p:test.ordenPrecio()){
            cosas=cosas+"El producto "+p.getNombre()+" vale "+p.getPrecio()+" euros.\n";
        }
        return cosas;
    }

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
    }

}
