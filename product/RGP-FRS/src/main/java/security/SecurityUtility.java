package security;

import data.db.UserConnector;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SecurityUtility {
    /**
     * Gets a login string from a request. Returns null if the request does not have such a string.
     *
     * @param request The request to get the string from.
     * @return The login string, or null if none is found.
     */
    public static String[] getLoginString(ServletRequest request) {
        // Get the parameter.
        String auth = request.getParameter("auth");
        // Check if it is a non-empty string.
        if (auth == null || auth.equals("")) {
            return null;
        }
        String[] logIn = auth.split(":");
        // Make sure there are two components to the auth string.
        if (logIn.length != 2) {
            return null;
        }
        return logIn;
    }

    /**
     * Internal function to whether the request is authenticated and to return the authenticated username
     * if it is. This is so that we can have common code between isAuthenticated and isAuthenticatedAsAdmin.
     *
     * @param request The request to check.
     * @return If the request is authenticated, the username of the user. Otherwise, null.
     */
    public static String[] getAuthenticationDetails(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (!cookie.getName().equals("authorization")) {
                continue;
            }
            String[] details = cookie.getValue().split(":");
            return details.length == 2 ? details : null;
        }
        return null;
    }

    /**
     * Check if a request is authenticated.
     *
     * @param request       The request to check.
     * @param authenticator The authenticator to test against.
     * @return Whether the request is authenticated.
     */
    public static boolean isAuthenticated(HttpServletRequest request, TokenAuthenticator authenticator) {
        String[] details = getAuthenticationDetails(request);
        if (details == null) {
            return false;
        }
        return authenticator.verify(details[0], details[1]);
    }

    /**
     * Check if a request is authenticated and it is an admin.
     *
     * @param request       The request to check.
     * @param authenticator The authenticator to test against.
     * @param connector     The user connector that we use for data.
     * @return Whether the request is an authenticated admin.
     */
    public static boolean isAuthenticatedAsAdmin(HttpServletRequest request, TokenAuthenticator authenticator,
                                                 UserConnector connector) {
        String[] details = getAuthenticationDetails(request);
        if (details == null) {
            return false;
        }
        if (!authenticator.verify(details[0], details[1])) {
            return false;
        }
        return connector.isUserAdmin(details[0]);
    }
}
