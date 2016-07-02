package my.webapp.util;

import my.model.persist.User;
import my.model.persist.spirit.Flower;
import my.model.persist.spirit.Wave;
import my.service.UserManager;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yinghao_niu on 8/1 for project.
 */
public class WebUtil {
    SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

    public String formatTime(Date date) {
        return dateFormat.format(date);
    }

    public String getClassSign(Object o) {
        if (o instanceof Wave) {
            return "WAVE";
        }
        else if (o instanceof Flower) {
            return "FLOWER";
        }
        else return "";
    }

    public boolean isBlank(String s) {
        return s == null ? true : s.equals("") ? true : false;
    }
    public void validateEmail(FacesContext context, UIComponent component,Object value) throws ValidatorException {
        Pattern p = Pattern.compile("[a-zA-z0-9_-]+@[.a-zA-Z0-9_-]+");
        Matcher matcher = p.matcher((CharSequence) value);
        if (matcher.matches()) {

        }
        else {
            throw new ValidatorException(new FacesMessage());
        }
    }


    public static boolean isFromDesktopClient(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        if (agent.indexOf("JavaFX") != -1) {
            return true;
        }
        return false;
    }

    public void handleDirect(HttpServletRequest request) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        FacesContext ctx = FacesContext.getCurrentInstance();
//		@mark NavigationHandler.handleNavigation not work
//		NavigationHandler navigationHandler = ctx.getApplication().getNavigationHandler();
        if (isLogin(request)) {
            ctx.getExternalContext().redirect("/home.xhtml");
        }
        else {
            ctx.getExternalContext().redirect("/login.xhtml");
        }
    }

    public boolean isLogin(HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (getCurrentUser(request) == null) {
            return false;
        }
        return true;
    }

    public static User getCurrentUser(HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object principal = request.getUserPrincipal();
        if (principal == null) {
            return null;
        }

        if (principal instanceof String) {
            WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            UserManager userManager = (UserManager) ctx.getBean("userManager");
            return userManager.getUserByUsername((String) principal);
        }

        return (User) (((AbstractAuthenticationToken) principal).getPrincipal());
    }
}

