package codeCaseFremtind;

import codeCaseFremtind.IntegrationLayer.IntegrationLayer;
import codeCaseFremtind.letterService.LetterResource;
import codeCaseFremtind.fagsystem.insurance.InsuranceResource;
import codeCaseFremtind.fagsystem.user.User;
import codeCaseFremtind.fagsystem.user.UserResource;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/codeCaseFremtind/";
    public static final String BASE_INSURANCE_URI = "/fagsystem/insurance/";
    public static final String BASE_USER_URI = "/fagsystem/user/";
    public static final String BASE_LETTER_URI = "/letter/";

    public static HttpServer startServer() {

        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(UserResource.class);
        classSet.add(LetterResource.class);
        classSet.add(InsuranceResource.class);
        classSet.add(IntegrationLayer.class);
        final ResourceConfig rc = new ResourceConfig().registerClasses(classSet);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();

        Database.getDatabase().insertUser(new User(1, "Christoffer" ,"Address1", 25));

        System.out.println(Database.getDatabase().findUser(1).getName());

        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\n Hit enter to stop it...", BASE_URI));

        System.in.read();
        server.stop();
    }
}

