package servlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import security.TokenAuthenticator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static security.SecurityUtility.isAuthenticated;

/**
 * A {@code AuthenticatedUserFilter} filters requests where a user should be logged in already.
 * They authenticate by checking the cookie has been added.
 */
public class AuthenticatedUserFilter implements Filter {

    private final TokenAuthenticator tokenAuthenticator;

    /**
     * @param tokenAuthenticator authenticates requests
     */
    public AuthenticatedUserFilter(TokenAuthenticator tokenAuthenticator) {
        this.tokenAuthenticator = tokenAuthenticator;
    }

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticatedUserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        if (isAuthenticated((HttpServletRequest) request, tokenAuthenticator)) {
            try {
                chain.doFilter(request, response);
            } catch (ServletException e) {
                LOG.error("Internal Server Error during authentication", e);
            }
        } else {
            ((HttpServletResponse) response).sendError(SC_UNAUTHORIZED, "Please log in to ask for this");
        }
    }

    @Override
    public void destroy() {
    }
}
