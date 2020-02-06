package servlet;

import security.TokenAuthenticator;
import security.SecurityUtility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * A {@code UserLoginServlet} registers new users and logs users in.
 */
public class UserLogoutServlet extends HttpServlet {

    private final TokenAuthenticator authenticator;

    /**
     * Creates a new UserLoginServlet with the authenticator
     *
     * @param authenticator The authenticator to use.
     */
    public UserLogoutServlet(TokenAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    /**
     * Clears a token
     * @param response The response that will get the cookie erased.
     */
    private void clearToken(HttpServletResponse response)
    {
        Cookie cookie = new Cookie("authorization", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // Make sure we are authenticated (can't log out if not logged in)
        if(!SecurityUtility.isAuthenticated(request, authenticator))
        {
            response.setStatus(SC_BAD_REQUEST);
            return;
        }
        String[] details = SecurityUtility.getAuthenticationDetails(request);
        // Since we are authenticated we know that details will not be null.
        authenticator.deleteToken(details[1]);
        clearToken(response);
        response.setStatus(SC_OK);
    }
}
