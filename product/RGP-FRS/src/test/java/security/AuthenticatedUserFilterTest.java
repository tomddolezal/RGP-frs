package security;

import org.junit.Test;
import servlet.AuthenticatedUserFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

@SuppressWarnings("NewClassNamingConvention")
public class AuthenticatedUserFilterTest {

    @Test
    public void shouldNotAllowRequestThroughIfNoCookies() throws Exception {
        TokenAuthenticator util = mock(TokenAuthenticator.class);
        AuthenticatedUserFilter filter = new AuthenticatedUserFilter(util);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getCookies()).thenReturn(null);

        filter.doFilter(request, response, filterChain);

        verifyZeroInteractions(filterChain);
    }
}
