package com.testProject;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        //c.getConfiguration().isEnabled(new org.glassfish.jersey.media.json.JsonJaxbFeature());
        TestDataBase testDataBase = TestDataBase.getDatabase();

        testDataBase.insertProduct(new Product(1, "type1", "this is created on startup"));

        target = c.target(Main.BASE_URI);



    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testGetProduct(){

        Product product = target.path("product/1").request(MediaType.APPLICATION_JSON).get(Product.class);


        Assert.assertEquals(java.util.Optional.of(1).get(), product.getId());
        Assert.assertEquals("type1", product.getType());
        Assert.assertEquals("this is created on startup", product.getDesc());


        System.out.println(product.getId());
        System.out.println(product.getType());
        System.out.println(product.getDesc());

    }

}
