
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.servlet.ServletHandler;
import utility.FRSOptions;

import static utility.FRSOptions.PORT_NUMBER;
import static utility.FRSOptions.RESOURCE_BASE;

/**
 * {@code Main} is the entry point for the {@link FastReferralServer}.
 */
public class Main extends ServletHandler {

    private static final String DEFAULT_CONFIG_FILE = "config/config.conf";

    /**
     * Starts up the FRS-Server
     *
     * @param args First argument contains path for Configuration file
     * @throws Exception If anything goes wrong with starting the server.
     */
    public static void main(String[] args) throws Exception {
        FRSOptions.initOptions(args.length >= 1 ? args[0] : DEFAULT_CONFIG_FILE);
        BasicConfigurator.configure();
        FastReferralServer server = new FastReferralServer(PORT_NUMBER, RESOURCE_BASE);
        server.start();
    }
}
