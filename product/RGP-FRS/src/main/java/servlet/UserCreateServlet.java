package servlet;

import data.db.UserConnector;
import security.TokenAuthenticator;
import security.CryptoHash;
import security.SecurityUtility;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * A {@code UserLoginServlet} registers new users and logs users in.
 */
public class UserCreateServlet extends HttpServlet {

    private final UserConnector userConnector;
    private final TokenAuthenticator authenticator;

    /**
     * Creates a new UserLoginServlet with the UserConnector
     *
     * @param userConnector Module for database communication
     */
    public UserCreateServlet(UserConnector userConnector, TokenAuthenticator authenticator) {
        this.userConnector = userConnector;
        this.authenticator = authenticator;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // Make sure we are authenticated as an admin (since only the admin can create new accounts).
        if(!SecurityUtility.isAuthenticatedAsAdmin(request, authenticator, userConnector))
        {
            response.setStatus(SC_UNAUTHORIZED);
            return;
        }
        // Make sure we have a login string.
        String[] logIn = SecurityUtility.getLoginString(request);
        if(logIn == null) {
            response.setStatus(SC_BAD_REQUEST);
            return;
        }

        // Add the new user.
        if(userConnector.addNewUser(logIn[0], CryptoHash.createFromPassword(logIn[1]))) {
            response.setStatus(SC_OK);
        } else {
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
