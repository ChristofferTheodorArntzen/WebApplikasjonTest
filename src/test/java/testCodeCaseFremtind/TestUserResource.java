package testCodeCaseFremtind;

import codeCaseFremtind.Database;
import codeCaseFremtind.Main;
import codeCaseFremtind.fagsystem.user.User;
import codeCaseFremtind.fagsystem.user.UserResponse;
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

public class TestUserResource {
    private HttpServer server;
    private WebTarget target;

    Database db = Database.getDatabase();

    @Before
    public void setUp() {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();


        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void testGetUser(){

        User user = new User(1, "Christoffer" ,"Address1", 25);

        db.insertUser(user);

        Response response = target.path(Main.BASE_USER_URI + user.getId()).request(MediaType.APPLICATION_JSON).get(Response.class);

        Assert.assertEquals(200, response.getStatus());

        User userResponse = response.readEntity(User.class);

        Assert.assertEquals(user.getId(), userResponse.getId());
        Assert.assertEquals(user.getAge(), userResponse.getAge());
        Assert.assertEquals(user.getName(), userResponse.getName());
    }

    @Test
    public void testPostUser(){

        User user = new User( "Theodor" ,"Address15", 15);

        Response response = target.path(Main.BASE_USER_URI).request().post(Entity.json(user));

        UserResponse userResponse = response.readEntity(UserResponse.class);
        User userFromDatabase = db.findUser(userResponse.getId());

        Assert.assertEquals(200 , response.getStatus());
        Assert.assertEquals(userFromDatabase.getId(), userResponse.getId());

    }

    @Test
    public void testPutUser(){

        User user = new User(1, "Christoffer" ,"Address1", 25);
        db.insertUser(user);
        System.out.println("Created user with parameter " + user.toString() +" trying to replace age and address");

        user.setAge(44);
        user.setAddress("Address4");

        Response response = target.path(Main.BASE_USER_URI + user.getId()).request().put(Entity.json(user));

        Assert.assertEquals(200, response.getStatus());

        User userResponse = response.readEntity(User.class);
        Assert.assertEquals(user.getAge(), userResponse.getAge());
        Assert.assertEquals(user.getAddress(), userResponse.getAddress());
    }
}
