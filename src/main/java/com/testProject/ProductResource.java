package com.testProject;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/product")
public class ProductResource {

    TestDataBase testDataBase = TestDataBase.getDatabase();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id){

        Product product = testDataBase.findProduct(id);

        if(product != null){
            return Response.ok(product).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product){

        if(product != null){
            if(product.getId() == null){
                product.setId(testDataBase.nextId());
            }
        }

        boolean queryState = testDataBase.insertProduct(product);

        if(queryState){
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

/*
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response patchProduct(@PathParam("id") int id, Map<String,Object> updates) {

        Product product = dataBase.findProduct(id);

        if(product != null){
            if(updates.get("desc") != null){
                if(updates.get("desc") instanceof String){
                    product.setDesc( (String) updates.get("desc"));
                    dataBase.updateOrCreateProduct(product);
                }
            }
            return Response.ok().build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
*/

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id")  int id){

        Product product = testDataBase.findProduct(id);

        if(product  != null){
            testDataBase.deleteProduct(product);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

