package my.service.impl;

import my.model.persist.User;
import my.model.persist.project.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yinghao_niu on 2016/3/26 for Project.
 */
@Service
public class AuthenticationSucessHandlerImpl implements AuthenticationSuccessHandler{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User principal = (User) authentication.getPrincipal();
        Role role = new Role();
        role.setName("ROLE_USER");
        principal.addRole(role);
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/home");
    }
}
