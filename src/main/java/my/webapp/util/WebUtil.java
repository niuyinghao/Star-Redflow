package my.webapp.util;

import my.model.persist.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by yinghao_niu on 8/1 for project.
 */
public class WebUtil {

	public static boolean isFromDesktopClient(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		if (agent.indexOf("JavaFX") != -1) {
			return true;
		}
		return false;
	}
	public static User getCurrentUser(HttpServletRequest request) {
		 AbstractAuthenticationToken principal = (AbstractAuthenticationToken) request.getUserPrincipal();
		if (principal == null) {
			return null;
		}
		return (User) (principal.getPrincipal()  );
	}

	public boolean isLogin(HttpServletRequest request) {
		if (getCurrentUser(request) == null) {
			return false;
		}
		return true;
	}

	public void handleDirect(HttpServletRequest request) throws IOException {
		FacesContext ctx = FacesContext.getCurrentInstance();
//		@mark NavigationHandler.handleNavigation not work
//		NavigationHandler navigationHandler = ctx.getApplication().getNavigationHandler();
		if (isLogin(request)) {
			ctx.getExternalContext().redirect("/home.xhtml");
		}  else{
			ctx.getExternalContext().redirect("/login.xhtml");
		}
	}
}

