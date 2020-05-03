package testCodeCaseFremtind;

import codeCaseFremtind.Database;
import codeCaseFremtind.Main;
import codeCaseFremtind.letterService.LetterEntity;
import codeCaseFremtind.letterService.LetterService;
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

public class TestSubjectSystemService {
    private HttpServer server;
    private WebTarget target;

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
    public void testGetUser(){

        User user = target.path("/subjectSystem/user/1").request(MediaType.APPLICATION_JSON).get(User.class);


        Assert.assertEquals(1, user.getId().intValue());
        Assert.assertEquals("Christoffer", user.getName());
        Assert.assertEquals("Address1", user.getAddress());
        Assert.assertEquals(25, user.getAge());

        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getAddress());
        System.out.println(user.getAge());
    }

    @Test
    public void testPostUser(){

        User user = new User(2, "Theodor" ,"Address15", 15);

        Integer response = target.path("/subjectSystem/createUser").request().post(Entity.json(user), Integer.class);

        Assert.assertEquals(response, user.getId());

    }

    @Test
    public void testPutUser(){

        Agreement agreement = new Agreement(2, "Bil", true, "Forsikring for bilen");

        String response = target.path("/subjectSystem/putAgreement/1").request().put(Entity.json(agreement), String.class);

        System.out.println(response);
    }

    @Test
    public void testGetAgreement(){

        Agreement agreement = target.path("/subjectSystem/agreement/1").request(MediaType.APPLICATION_JSON).get(Agreement.class);

        Assert.assertEquals(1, agreement.getId().intValue());
        Assert.assertEquals("hus", agreement.getAgreementType());
        Assert.assertFalse(agreement.getStatus());
        Assert.assertEquals("Forsikring for huset", agreement.getDesc());

    }

    @Test
    public void testPostAgreement(){

        Agreement agreement = new Agreement(2, "Bil", true, "Forsikring for bilen");

        Integer response = target.path("/subjectSystem/createAgreement").request().post(Entity.json(agreement), Integer.class);

        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testPutAgreement(){

        Agreement agreement = new Agreement(1,"Bil", true, "Forsikring for bilen");

        String response = target.path("/subjectSystem/putAgreement/1").request().put(Entity.json(agreement), String.class);
        // TODO: make asserts
        System.out.println(response);
    }

    @Test
    public void testGetLetterService() {

        User user = Database.getDatabase().findUser(1);

        LetterEntity letter = new LetterEntity(1, 1, 11313, false);

        Boolean response = target.path("/letterService/1").request(MediaType.TEXT_PLAIN).post(Entity.json(letter), Boolean.class);

        System.out.println(response);

        Assert.assertNotNull(response);
        Assert.assertTrue(response);

    }


}
