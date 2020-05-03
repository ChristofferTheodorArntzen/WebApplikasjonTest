package codeCaseFremtind;

import codeCaseFremtind.IntegrationLayer.IntegrationLayer;
import codeCaseFremtind.letterService.LetterService;
import codeCaseFremtind.subjectSystem.User;
import codeCaseFremtind.subjectSystem.SubjectSystemService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */

    public static HttpServer startServer() {

        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(SubjectSystemService.class);
        classSet.add(LetterService.class);
        classSet.add(IntegrationLayer.class);
        final ResourceConfig rc = new ResourceConfig().registerClasses(classSet);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();

        Database.getDatabase().insertUser(new User(1, "Christoffer" ,"Address1", 25));

        System.out.println(Database.getDatabase().findUser(1).getName());

        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.in.read();
        server.stop();
    }
}

