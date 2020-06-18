package testCodeCaseFremtind;

import codeCase.Database;
import codeCase.Main;
import codeCase.fagsystem.insurance.Insurance;
import codeCase.fagsystem.insurance.InsuranceResponse;
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

public class TestInsuranceResource {
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
    public void testGetInsurance(){

        Insurance insurance = new Insurance(1, "hus", false, "Forsikring for huset");
        db.insertInsurance(insurance);

        Insurance insuranceResponse = target.path(Main.BASE_INSURANCE_URI + insurance.getId()).request(MediaType.APPLICATION_JSON).get(Insurance.class);

        Assert.assertEquals(1, insuranceResponse.getId().intValue());
        Assert.assertEquals("hus", insuranceResponse.getInsuranceType());
        Assert.assertFalse(insuranceResponse.getStatus());
        Assert.assertEquals("Forsikring for huset", insuranceResponse.getDesc());

    }

    @Test
    public void testPostInsurance(){

        Insurance insurance = new Insurance(2, "Bil", true, "Forsikring for bilen");

        Response response = target.path(Main.BASE_INSURANCE_URI).request().post(Entity.json(insurance));

        InsuranceResponse insuranceResponse = response.readEntity(InsuranceResponse.class);

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(insurance.getId(), insuranceResponse.getId());
    }

    @Test
    public void testPutInsurance(){

        Insurance insurance = new Insurance(1,"Bil", true, "Forsikring for bilen");

        db.insertInsurance(insurance);
        System.out.println("Created insurance with parameter " + insurance.toString() +" trying to replace status and insuranceType");

        insurance.setStatus(false);
        insurance.setInsuranceType("Hus");

        Response response = target.path(Main.BASE_INSURANCE_URI + insurance.getId()).request().put(Entity.json(insurance));

        Assert.assertEquals(200, response.getStatus());

        Insurance insuranceResponse = response.readEntity(Insurance.class);

        Assert.assertEquals(insurance.getStatus(), insuranceResponse.getStatus());
        Assert.assertEquals(insurance.getInsuranceType(), insuranceResponse.getInsuranceType());

    }
}
