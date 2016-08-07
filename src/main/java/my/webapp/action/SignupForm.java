package my.webapp.action;

import my.model.persist.User;
import my.service.RoleManager;
import my.service.UserExistsException;
import my.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * JSF Page class to handle signing up a new user.
 *
 * @author mraible
 */
@Component
@Scope("request")
public class SignupForm extends BasePage implements Serializable {
    User user;
    @Autowired
    private UserManager userManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleManager roleManager;
    public static final String ROLE_USER = "ROLE_USER";


    // this is executed severial times
    @PostConstruct
    public void init() {
        if (user == null) {
            user = new User();
//        user.setUsername("STAR-" + DateFormatUtils.format(new Date(), "yyMMdd-hhmmss-") + UUID.randomUUID());
            user.setEnabled(true);
            user.setAccountExpired(false);
            user.setAccountLocked(false);
            user.setCredentialsExpired(false);

		/*  @deprecated No need;

        Role role = roleManager.getRole(ROLE_USER);
		Set roles = new HashSet<>();
        if (role == null) {
        }
        else{
            roles.add(role);
        }
		user.setRoles(roles);

		*/
        }
    }


    public void assignName() {
        user.setUsername("STAR-" + userManager.getUserSequence());
    }

    public String save() throws InvocationTargetException, NoSuchMethodException, UserExistsException, IllegalAccessException {
        User userByUsername = userManager.getUserByUsername(user.getUsername());
        if (userByUsername != null) {
            addError("用户名已存在");
            return "/signup.xhtml";
        }
        userManager.saveUser(user);
        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities());
        auth.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "/home.xhtml";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
