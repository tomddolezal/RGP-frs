import data.db.ConnectorException;
import data.db.HospitalCache;
import data.db.HospitalConnector;
import data.db.UserDatabase;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import security.TokenAuthenticator;
import servlet.*;

import javax.servlet.Servlet;

import static java.util.EnumSet.of;
import static javax.servlet.DispatcherType.REQUEST;

/**
 * A {@code FastReferralServer} is responsible for receiving and processing requests from the
 * RGP-FRS client web-app.
 */
public class FastReferralServer extends Server {

    private final TokenAuthenticator authenticator = new TokenAuthenticator();

    private UserDatabase udb;

    FastReferralServer(int socketAddress, String resourceBase) {
        super(socketAddress);

        udb = new UserDatabase();

        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/");
        addRequestFilters(contextHandler);
        contextHandler.setResourceBase(resourceBase);
        setHandler(contextHandler);
        addDefaultServlet(contextHandler, resourceBase);
        addDataServlets(contextHandler);
    }

    private void addRequestFilters(ServletContextHandler contextHandler) {
        AuthenticatedUserFilter filter = new AuthenticatedUserFilter(authenticator);
        contextHandler.addFilter(new FilterHolder(filter), "/hospitals/*", of(REQUEST));
    }

    private void addDefaultServlet(ServletContextHandler contextHandler, String resourceBase) {
        Servlet defaultServlet = new DefaultServlet();
        ServletHolder pwdHolder = new ServletHolder("default", defaultServlet);
        pwdHolder.setInitParameter("resourceBase", resourceBase);
        pwdHolder.setInitParameter("dirAllowed", "true");
        contextHandler.addServlet(pwdHolder, "/*");
    }

    private void addDataServlets(ServletContextHandler contextHandler) {
        try {
            contextHandler.addServlet(new ServletHolder(new HospitalServlet(new HospitalCache(new HospitalConnector() {
            }))), "/hospitals/*");
        } catch (ConnectorException e) {
            throw new RuntimeException("Error occurred starting hospital server");
        }
      
        contextHandler.addServlet(new ServletHolder(new UserLoginServlet(udb, authenticator)), "/login");
        contextHandler.addServlet(new ServletHolder(new UserCreateServlet(udb, authenticator)), "/signup");
        contextHandler.addServlet(new ServletHolder(new UserLogoutServlet(authenticator)), "/logout");
    }

}
