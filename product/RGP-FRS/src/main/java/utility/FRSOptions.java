package utility;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static java.lang.Integer.parseInt;

public class FRSOptions
{
    public static int PORT_NUMBER;
    public static String RESOURCE_BASE = "src/main/resources/app/";
    public static String DB_URL = "jdbc:postgresql://localhost:5432/rgpreferral";
    public static String DB_USER = "postgres";
    public static String DB_PASS = "postgres";

    public static String ADMIN_USER = "admin";
    public static String ADMIN_PASS = "admin";

    private static final Logger logger = LoggerFactory.getLogger(FRSOptions.class);

    private static Level getLogLevelFromString(String value)
    {
        Level logLevel;
        switch(value)
        {
            case "WARN":
                logLevel = Level.WARN;
                break;
            case "ERROR":
                logLevel = Level.ERROR;
                break;
            case "INFO":
                logLevel = Level.INFO;
                break;
            case "DEBUG":
                logLevel = Level.DEBUG;
                break;
            case "FATAL":
                logLevel = Level.FATAL;
                break;
            case "OFF":
                logLevel = Level.OFF;
                break;
            case "TRACE":
                logLevel = Level.TRACE;
                break;
            default:
                throw new IllegalArgumentException("Invalid log level: " + value);
        }
        return logLevel;
    }

    private static void setLogLevel(Level logLevel)
    {
        org.apache.log4j.LogManager.getLogger("org.eclipse.jetty").setLevel(logLevel);
    }

    private static String[] getLineOptions(String line)
    {
        line = line.trim();
        if (line.equals("") || line.startsWith("#")) {
            return null;
        }
        int delim = line.indexOf("=");
        String key = line.substring(0, delim).trim();
        String value = line.substring(delim + 1).trim();
        return new String[] {key, value};
    }

    public static void initOptions(String configPath) {
        // Set the default log level to error.
        setLogLevel(Level.ERROR);

        try {
            File config = new File(configPath);
            BufferedReader reader = new BufferedReader(new FileReader(config));
            int lineNum = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                String[] lineOptions = getLineOptions(line);
                if(lineOptions == null)
                {
                    continue;
                }
                String key = lineOptions[0];
                String value = lineOptions[1];
                switch (key) {
                    case "PORT":
                        extractPort(lineNum, value);
                        break;
                    case "RESOURCEBASE":
                        RESOURCE_BASE = value;
                        break;
                    case "LOG_LEVEL":
                        Level logLevel = getLogLevelFromString(value);
                        setLogLevel(logLevel);
                        break;
                    case "DB_URL":
                        DB_URL = value;
                        break;
                    case "DB_USERNAME":
                        DB_USER = value;
                        break;
                    case "DB_PASSWORD":
                        DB_PASS = value;
                        break;
                    case "DEFAULT_ADMIN_USERNAME":
                        ADMIN_USER = value;
                        break;
                    case "DEFAULT_ADMIN_PASSWORD":
                        ADMIN_PASS = value;
                        break;
                    default:
                        logger.warn("[CONFIG] Invalid key on line " + lineNum + " \"" + key + "\"");
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("Cannot find config file: " + configPath, e);
        } catch (IOException e) {
            logger.error("IO related issue: ", e);
        }
    }

    private static void extractPort(int lineNum, String value) {
        try {
            PORT_NUMBER = parseInt(value);
        } catch (NumberFormatException formatEx) {
            logger.error("[CONFIG] Invalid port number on line " + lineNum + " \"" + value + "\".",
                    formatEx
            );
        }
    }
}
