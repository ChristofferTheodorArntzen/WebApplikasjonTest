package codeCaseFremtind.letterService;


import codeCaseFremtind.Database;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/letterService")
public class LetterService {

    Database db = Database.getDatabase();

    @POST
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postLetterService(@PathParam("id") int id, Map<String, Object> updates){

        LetterEntity letter = db.findLetter(id);

        if(letter == null){
            letter = new LetterEntity();
            letter.setId(db.nextLetterId());

            if(updates.get("userId") instanceof Integer){
                letter.setUserId((Integer) updates.get("userId"));
            }

            if(updates.get("agreementId") instanceof Integer){
                letter.setAgreementId((Integer) updates.get("agreementId"));
            }

            letter.setStatus(true);
            boolean queryState = db.insertLetter(letter);

            System.out.println();
            String.format("Sending letter to user %d with related agreement %d", letter.getUserId(), letter.getAgreementId());

            return Response.ok(letter.getStatus(), MediaType.APPLICATION_JSON).build();
        }

        return  Response.status(Response.Status.NOT_FOUND).build();
    }
}
