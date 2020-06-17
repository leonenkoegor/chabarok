package codes.purple.chabarok;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class AuthSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            try {
                if (authority.getAuthority().equals("CASHIER")) {
                    redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/cashier");
                } else if (authority.getAuthority().equals("COOK")) {
                    redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/cook");
                } else if (authority.getAuthority().equals("ADMIN")) {
                    redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/admin");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
