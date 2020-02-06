package servlet;

import data.db.UserConnector;
import security.SecurityUtility;
import security.TokenAuthenticator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * A {@code UserLoginServlet} registers new users and logs users in.
 */
public class UserLoginServlet extends HttpServlet {

    private final UserConnector userConnector;
    private final TokenAuthenticator authenticator;

    /**
     * Creates a new UserLoginServlet with the UserConnector
     *
     * @param userConnector Module for database communication
     * @param authenticator authenticates tokens
     */
    public UserLoginServlet(UserConnector userConnector, TokenAuthenticator authenticator) {
        this.userConnector = userConnector;
        this.authenticator = authenticator;
    }

    /**
     * Creates a cookie for the token and assigns it to the response.
     *
     * @param response The response that will get the cookie
     * @param username The user for which this cookie is being assigned.
     * @param token    The token to add.
     */
    private void assignToken(HttpServletResponse response, String username, String token) {
        Cookie cookie = new Cookie("authorization", username + ":" + token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Make sure we are not authenticated (authenticating is redundant then)
        if (SecurityUtility.isAuthenticated(request, authenticator)) {
            response.setStatus(SC_BAD_REQUEST);
            return;
        }
        // Make sure we have a login string.
        String[] logIn = SecurityUtility.getLoginString(request);
        if (logIn == null) {
            response.setStatus(SC_BAD_REQUEST);
            return;
        }

        // Try to authenticate the user
        String user = logIn[0];
        String pass = logIn[1];
        if (userConnector.authenticateUser(user, pass)) {
            // If we were able to, create and return a new token.
            assignToken(response, user, authenticator.createTokenFor(user));
            response.setStatus(SC_OK);
        } else {
            response.sendError(SC_UNAUTHORIZED, "Unauthorized user");
        }
    }
}
