package codeCaseFremtind.IntegrationLayer;



import codeCaseFremtind.Database;
import codeCaseFremtind.Main;
import codeCaseFremtind.letterService.LetterEntity;
import codeCaseFremtind.letterService.LetterSentResponse;
import codeCaseFremtind.fagsystem.insurance.Insurance;
import codeCaseFremtind.fagsystem.insurance.InsuranceResponse;
import codeCaseFremtind.fagsystem.user.User;
import codeCaseFremtind.fagsystem.user.UserResponse;

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserAndInsurance(UserAndInsurance userAndInsurance){

        if(userAndInsurance != null){

            User user = new User();
            user.setName(userAndInsurance.getName());
            user.setAddress(userAndInsurance.getAddress());
            user.setAge(userAndInsurance.getAge());

            Client c = ClientBuilder.newClient();
            WebTarget target = c.target(Main.BASE_URI);

            Response responseCreatedUser = target.path(Main.BASE_USER_URI).request().post(Entity.json(user));

            UserResponse userResponse = responseCreatedUser.readEntity(UserResponse.class);

            Insurance insurance = new Insurance();
            insurance.setDesc(userAndInsurance.getDesc());
            insurance.setStatus(false);
            insurance.setInsuranceType(userAndInsurance.getInsuranceType());

            Response responseCreatedInsurance = target.path(Main.BASE_INSURANCE_URI).request().post(Entity.json(insurance));

            InsuranceResponse insuranceResponse = responseCreatedInsurance.readEntity(InsuranceResponse.class);

            LetterEntity letter = new LetterEntity();
            letter.setUserId(userResponse.getId());
            letter.setInsuranceId(insuranceResponse.getId());
            letter.setLetterContent("Din avtale er nå aktiv.");
            if(letter.getInsuranceId() != null && letter.getUserId() != null){
                Response response = target.path(Main.BASE_LETTER_URI).request().post(Entity.json(letter));

                LetterSentResponse letterSentResponse = response.readEntity(LetterSentResponse.class);

                if (letterSentResponse.isSent()){

                    Insurance insuranceToSetStatus = db.findInsurance(insuranceResponse.getId());
                    insuranceToSetStatus.setStatus(true);
                    String path = Main.BASE_INSURANCE_URI + insuranceToSetStatus.getId();
                    Response updatedInsuranceStatusResponse = target.path(path).request().put(Entity.json(insuranceToSetStatus));

                    Insurance updatedInsurance = updatedInsuranceStatusResponse.readEntity(Insurance.class);

                    ResponseObject responseObject = new ResponseObject(insuranceToSetStatus.getId(), updatedInsurance.getStatus());

                    return Response.ok(responseObject).build();
                }
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

