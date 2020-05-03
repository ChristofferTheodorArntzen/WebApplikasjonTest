package codeCaseFremtind.IntegrationLayer;



import codeCaseFremtind.Database;
import codeCaseFremtind.Main;
import codeCaseFremtind.letterService.LetterEntity;
import codeCaseFremtind.subjectSystem.Agreement;
import codeCaseFremtind.subjectSystem.User;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/integrationLayer")
public class IntegrationLayer {

    Database db = Database.getDatabase();

    @POST
    @Path("/createUserAndAgreement")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAgreement(UserAndAgreement userAndAgreement){

        if(userAndAgreement != null){

            User user = new User();
            user.setName(userAndAgreement.getName());
            user.setAddress(userAndAgreement.getAddress());
            user.setAge(userAndAgreement.getAge());

            Client c = ClientBuilder.newClient();
            WebTarget target = c.target(Main.BASE_URI);

            Integer responseCreatedUserId = target.path("/subjectSystem/createUser").request().post(Entity.json(user), Integer.class);

            Agreement agreement = new Agreement();
            agreement.setDesc(userAndAgreement.getDesc());
            agreement.setStatus(false);
            agreement.setAgreementType(userAndAgreement.getAgreementType());

            Integer responseCreatedAgreementId = target.path("/subjectSystem/createAgreement").request().post(Entity.json(agreement), Integer.class);

            LetterEntity letter = new LetterEntity();
            letter.setUserId(responseCreatedUserId);
            letter.setAgreementId(responseCreatedAgreementId);
            letter.setId(db.nextLetterId());
            if(letter.getAgreementId() != null && letter.getUserId() != null){
                Boolean responseLetterSent = target.path("/letterService/" + letter.getId()).request().post(Entity.json(letter), Boolean.class);

                if (responseLetterSent != null && responseLetterSent){

                    Agreement agreementToSetStatus = db.findAgreement(responseCreatedAgreementId);
                    agreementToSetStatus.setStatus(true);
                    String path = String.format("/subjectSystem/putAgreement/%d", agreementToSetStatus.getId());
                    Boolean updatedAgreementStatusResponse = target.path(path).request().put(Entity.json(agreementToSetStatus), Boolean.class);

                    ResponseObject responseObject = new ResponseObject(agreementToSetStatus.getId(), updatedAgreementStatusResponse);

                    return Response.ok(responseObject, MediaType.APPLICATION_JSON).build();
                }
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

