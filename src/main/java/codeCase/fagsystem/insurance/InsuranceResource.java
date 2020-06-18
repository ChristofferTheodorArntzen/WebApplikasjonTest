package codeCase.fagsystem.insurance;

import codeCase.Database;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/fagsystem/insurance")
public class InsuranceResource {

    Database database = Database.getDatabase();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInsurance(@PathParam("id") int id){

        Insurance insurance = database.findInsurance(id);

        if(insurance != null){
            return Response.ok(insurance).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postInsurance(Insurance insurance){

        if(insurance.getId() == null){
            insurance.setId(database.nextInsuranceId());
        }

        boolean queryState = database.insertInsurance(insurance);

        if(queryState){

            System.out.println(String.format("Insurance with id: %d, type %s was created.", insurance.getId(), insurance.getInsuranceType()));

            InsuranceResponse response = new InsuranceResponse(insurance.getId());
            return Response.ok(response).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putInsurance(@PathParam("id") int id, Map<String, Object> updates){

        Insurance insurance = database.findInsurance(id);

        if(insurance != null && updates.size() > 0){

            if(updates.get("insuranceType") instanceof String) {
                insurance.setInsuranceType( (String) updates.get("insuranceType"));
                database.updateOrCreateInsurance(insurance);
            }

            if(updates.get("status") instanceof Boolean){
                insurance.setStatus((boolean) updates.get("status"));
                database.updateOrCreateInsurance(insurance);
            }

            if(updates.get("desc") instanceof String){
                insurance.setDesc((String) updates.get("desc"));
                database.updateOrCreateInsurance(insurance);
            }
            String response = String.format("Insurance with id: %d was updated to, insuranceType: %s, status: %b and desc: %s",
                    insurance.getId(), insurance.getInsuranceType(), insurance.getStatus(), insurance.getDesc());
            System.out.println(response);

            return Response.ok(insurance).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
