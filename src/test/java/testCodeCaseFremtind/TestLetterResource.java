package testCodeCaseFremtind;

import codeCase.Database;
import codeCase.Main;
import codeCase.letterService.LetterEntity;
import codeCase.letterService.LetterSentResponse;
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

public class TestLetterResource {
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        Database database = Database.getDatabase();
        database.insertUser(new User(1, "Christoffer" ,"Address1", 25));

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void testGetLetterService() {

        LetterEntity letter = new LetterEntity(1, 1, "weqweqsadasd", false);

        Response response = target.path("/letter").request(MediaType.APPLICATION_JSON).post(Entity.json(letter));
        Assert.assertEquals(200, response.getStatus());
        LetterSentResponse letterSentResponse = response.readEntity(LetterSentResponse.class);
        Assert.assertTrue(letterSentResponse.isSent());
    }

}
