package org.bensonvzw.mvn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Hello {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPerson(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Persoon p = new Persoon("Joske", "Vermeulen");
            String result = mapper.writeValueAsString(p);
            return Response.status(200).entity(result).build();
        }catch (JsonProcessingException ex){
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }
}
