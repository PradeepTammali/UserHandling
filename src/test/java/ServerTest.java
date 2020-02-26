import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.server.ResourceConfig;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class ServerTest {
    private Server server;
    private ServletContextHandler servletContextHandler;

    @BeforeAll
    public void startServer() throws Exception {
        server = new Server(8080);
        server.setStopAtShutdown(true);

        servletContextHandler = new ServletContextHandler(server, "/");
        servletContextHandler.setResourceBase("src/main/java");
        servletContextHandler.setClassLoader(getClass().getClassLoader());

        final ResourceConfig config = new ResourceConfig();
        config.packages("api");

        final ServletHolder servletHolder = new ServletHolder(new ServletContainer(config));
        servletContextHandler.addServlet(servletHolder, "/api/*");
        server.setHandler(servletContextHandler);
        server.start();
    }

    @AfterAll
    public void stopServer() throws Exception {
        server.stop();
    }

    @Test
    public void testServer() {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://localhost:8080/api/methods/ispalindrome");
            connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed! HTTP Error Code: " + connection.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}