package codeCaseFremtind.subjectSystem;

import codeCaseFremtind.Database;

import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/subjectSystem")
public class SubjectSystemService {

    Database database = Database.getDatabase();

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int userId){

        User user = database.findUser(userId);

        if(user != null){
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(User user){

        //Checks if data posted is valid
        if(user != null){
            if(user.getId() == null){
                // creates a new id for the user
                user.setId(database.nextUserId());
            }

            // Insert the user data to the database
            boolean queryState = database.insertUser(user);

            // Check if the user was inserted or not and build a response.
            if(queryState){
                System.out.println(String.format("User with id: %d, name: %s was created.", user.getId(), user.getName()));
                return Response.ok(user.getId()).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/putUser/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("id") int id, Map<String, Object> updates){

        User user = database.findUser(id);

        if (user != null && updates.size() > 0){
            //TODO: make this more efficient? can send one param to update,
            // but if all fields are sent in a update there will be three calls to database for saving the entity
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

            return Response.ok(response).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();



    }

    @GET
    @Path("/agreement/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAgreement(@PathParam("id") int agreementId){

        Agreement agreement = database.findAgreement(agreementId);

        if(agreement != null){
            return Response.ok(agreement).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/createAgreement")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postAgreement(Agreement agreement){

        if(agreement != null){
            if(agreement.getId() == null){
                 agreement.setId(database.nextAgreementId());
            }

            boolean queryState = database.insertAgreement(agreement);

            if(queryState){
                String response = String.format("Agreement with id: %d, type %s was created.", agreement.getId(), agreement.getAgreementType());
                System.out.println(response);
                return Response.ok(agreement.getId()).status(Response.Status.ACCEPTED).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/putAgreement/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAgreement(@PathParam("id") int id, Map<String, Object> updates){

        Agreement agreement = database.findAgreement(id);

        if (agreement != null && updates.size() > 0){
            //TODO: make this more efficient? can send one param to update,
            // but if all fields are sent in a update there will be three calls to database for saving the entity
            if (updates.get("agreementType") instanceof String) {
                agreement.setAgreementType( (String) updates.get("agreementType"));
                database.updateOrCreateAgreement(agreement);
            }

            if(updates.get("status") instanceof Boolean){
                agreement.setStatus((boolean) updates.get("status"));
                database.updateOrCreateAgreement(agreement);
            }

            if(updates.get("desc") instanceof String){
                agreement.setDesc((String) updates.get("desc"));
                database.updateOrCreateAgreement(agreement);
            }
            String response = String.format("Agreement with id: %d was updated to, agreementType: %s, status: %b and desc: %s",
                    agreement.getId(), agreement.getAgreementType(), agreement.getStatus(), agreement.getDesc());
            System.out.println(response);

            return Response.ok(agreement.getStatus()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
