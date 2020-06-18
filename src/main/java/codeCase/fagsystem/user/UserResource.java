package codeCase.fagsystem.user;

import codeCase.Database;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/fagsystem/user")
public class UserResource {

    Database database = Database.getDatabase();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int userId){

        User user = database.findUser(userId);

        if(user != null){
            return Response.ok(user).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUser(User user){

        if(user.getId() == null){
            // creates a new id for the user
            user.setId(database.nextUserId());
        }

        // Insert the user data to the database
        boolean queryState = database.insertUser(user);

        // Check if the user was inserted or not and build a response.
        if(queryState){
            System.out.println(String.format("User with id: %d, name: %s was created.", user.getId(), user.getName()));
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            return Response.ok(userResponse).build();
        }

       return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("id") int id, Map<String, Object> updates){

        User user = database.findUser(id);

        if (user != null && updates.size() > 0){

            if(updates.get("name") instanceof String){
                user.setName((String) updates.get("name"));
                database.updateOrCreateUser(user);
            }

            if (updates.get("address") instanceof String) {
                user.setAddress( (String) updates.get("address"));
                database.updateOrCreateUser(user);
            }

            if(updates.get("age") instanceof Integer){
                user.setAge((int) updates.get("age"));
                database.updateOrCreateUser(user);
            }

            String response = String.format("User with id: %d was updated to, name: %s, address: %s and age: %d",
                    user.getId(), user.getName(), user.getAddress(), user.getAge());
            System.out.println(response);

            return Response.ok(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
