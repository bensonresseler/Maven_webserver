package org.bensonvzw.mvn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/producten")
public class Producten {
    private List<Product> producten = ProductenLijst.getProducten();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducten() {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("in getProducten");
        try {
            String result = objectMapper.writeValueAsString(producten);
            return Response.status(200).entity(result).build();
        } catch (JsonProcessingException ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response bewaarProduct(Map<String, String> input) {
        int id = Integer.parseInt(input.get("id"));
        String naam = input.get("naam");
        int prijs = Integer.parseInt(input.get("prijs"));
        Product p = new Product(id, naam, prijs);
        producten.add(p);
        return Response.status(200).entity("OK").build();
    }

    @GET
    @Path("/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productid") int productId) {
        Product product = producten.stream().filter(p -> p.getId() == productId).findFirst().orElse(null);
        if (product == null){
            return Response.status(404).entity("Object niet gevonden.").build();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String result = objectMapper.writeValueAsString(product);
            return Response.status(200).entity(result).build();
        } catch (JsonProcessingException ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productid") int productId) {
        Product product = producten.stream().filter(p -> p.getId() == productId).findFirst().orElse(null);
        if (product == null){
            return Response.status(404).entity("Object niet gevonden.").build();
        }
        producten.remove(product);
        return Response.status(200).entity("OK").build();
    }
}
