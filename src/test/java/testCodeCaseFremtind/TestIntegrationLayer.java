package testCodeCaseFremtind;

import codeCase.Database;
import codeCase.IntegrationLayer.ResponseObject;
import codeCase.IntegrationLayer.UserAndInsurance;
import codeCase.Main;
import codeCase.fagsystem.insurance.Insurance;
import codeCase.fagsystem.user.User;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TestIntegrationLayer {

    private HttpServer server;
    private WebTarget target;

    Database db = Database.getDatabase();

    @Before
    public void setUp() {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        Database database = Database.getDatabase();
        database.insertUser(new User(1, "Christoffer" ,"Address1", 25));
        database.insertInsurance(new Insurance(1, "hus", false, "Forsikring for huset"));
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void testIntegrationLayer(){

        UserAndInsurance userAndInsurance = new UserAndInsurance("Bil", "Bil forsikring for Volvo #131232", "Christoffer",
                "Addresse1", 25);

        Response response = target.path("/integrationLayer").request(MediaType.APPLICATION_JSON).post(Entity.json(userAndInsurance));

        ResponseObject responseObject = response.readEntity(ResponseObject.class);

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(2, responseObject.getInsuranceId().intValue());
        Assert.assertEquals(true, responseObject.getInsuranceStatus());
    }


}
