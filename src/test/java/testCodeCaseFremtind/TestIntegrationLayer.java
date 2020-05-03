package testCodeCaseFremtind;

import codeCaseFremtind.Database;
import codeCaseFremtind.IntegrationLayer.ResponseObject;
import codeCaseFremtind.IntegrationLayer.UserAndAgreement;
import codeCaseFremtind.Main;
import codeCaseFremtind.subjectSystem.Agreement;
import codeCaseFremtind.subjectSystem.User;
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
import javax.xml.crypto.Data;

public class TestIntegrationLayer {

    private HttpServer server;
    private WebTarget target;

    Database db = Database.getDatabase();

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        Database database = Database.getDatabase();
        database.insertUser(new User(1, "Christoffer" ,"Address1", 25));
        database.insertAgreement(new Agreement(1, "hus", false, "Forsikring for huset"));
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testIntegrationLayer(){

        UserAndAgreement userAndAgreement = new UserAndAgreement("Bil", "Bil forsikring for Volvo #131232", "Christoffer",
                "Addresse1", 25);

        ResponseObject responseObject = target.path("/integrationLayer/createUserAndAgreement").request(MediaType.APPLICATION_JSON).post(Entity.json(userAndAgreement), ResponseObject.class);

        System.out.println(responseObject);
        Assert.assertNotNull(responseObject);
        Assert.assertEquals(2, responseObject.getAgreementId().intValue());
        Assert.assertEquals(true, responseObject.getAgreementStatus());
    }


}
