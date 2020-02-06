import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("NewClassNamingConvention")
public class FastReferralServerTest {

    private FastReferralServer server;

    @Before
    public void startJetty() throws Exception {
        // Create Server
        server = serverOnLocalHost();

        // Start Server
        server.start();
    }

    private static FastReferralServer serverOnLocalHost() {
        return new FastReferralServer(8080, "app/public/");
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }

    @Test
    public void shouldGetOnBaseEndPoint() throws Exception {
        HttpURLConnection http = (HttpURLConnection) new URL("http://localhost:8080/").openConnection();
        http.connect();
        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
    }

    @Test
    public void shouldGetOnIndex() throws Exception {
        HttpURLConnection http = (HttpURLConnection) new URL("http://localhost:8080/index.html").openConnection();
        http.connect();
        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
    }

    @Test
    public void shouldUnauthorizedOnHospitalPointIfUserIsntLoggedIn() throws Exception {
        HttpURLConnection http = (HttpURLConnection) new URL("http://localhost:8080/hospitals/").openConnection();
        http.connect();
        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.UNAUTHORIZED_401));
    }

    @Test
    public void shouldGet404Page() throws Exception {
        HttpURLConnection http = (HttpURLConnection) new URL("http://localhost:8080/fakeurl/").openConnection();
        http.connect();
        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.NOT_FOUND_404));
    }
}
