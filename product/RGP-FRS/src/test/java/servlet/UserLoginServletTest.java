package servlet;

import data.db.UserConnector;
import org.junit.Test;
import security.TokenAuthenticator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@SuppressWarnings("NewClassNamingConvention")
public class UserLoginServletTest {

    private final UserConnector userConnector = mock(UserConnector.class);

    @Test
    public void shouldNotReturnOKIfURLIsInvalid() throws Exception {
        when(userConnector.authenticateUser(any(), anyString())).thenReturn(false);
        TokenAuthenticator util = new TokenAuthenticator();
        UserLoginServlet servlet = new UserLoginServlet(userConnector, util);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/users/usernameandmalformedpassword");
        servlet.doPost(request, response);

        verify(response).setStatus(SC_BAD_REQUEST);
    }

    @Test
    public void shouldNotReturnOKIfLogInInfoIsInvalid() throws Exception {
        when(userConnector.authenticateUser(any(), anyString())).thenReturn(false);
        TokenAuthenticator util = new TokenAuthenticator();
        UserLoginServlet servlet = new UserLoginServlet(userConnector, util);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("auth")).thenReturn("username:password");

        servlet.doPost(request, response);

        verify(response).sendError(SC_UNAUTHORIZED, "Unauthorized user");
    }
}
