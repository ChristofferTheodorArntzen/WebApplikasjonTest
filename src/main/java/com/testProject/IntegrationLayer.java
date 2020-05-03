/*
package com.testProject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/integrationLayer")
public class IntegrationLayer {


    private Client c = ClientBuilder.newClient();
    private final WebTarget target = c.target(Main.BASE_URI);


    @POST
    @Path("/createProduct")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(CreateProductRequest createProductRequest){

        Product product = new Product();

        product.setDesc(createProductRequest.desc);
        product.setType(createProductRequest.type);

        Response response = target.path("product/").request().post(Entity.entity(product, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());

        return Response.ok().build();
    }

}

*/
