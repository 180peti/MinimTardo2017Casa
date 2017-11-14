package edu.upc.minimo;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
