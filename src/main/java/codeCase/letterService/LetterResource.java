package codeCase.letterService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/letter")
public class LetterResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLetterService(LetterEntity letter){

        String response = String.format("Sending letter to user %d with related insurance %d", letter.getUserId(), letter.getInsuranceId());
        System.out.println(letter.getLetterContent());
        LetterSentResponse responseObject = new LetterSentResponse(true);
        System.out.println(response);
        return Response.ok(responseObject).build();
    }
}
