package my.webapp.action;

import my.model.persist.User;
import my.model.persist.project.Role;
import my.service.UserExistsException;
import my.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * JSF Page class to handle signing up a new user.
 *
 * @author mraible
 */
@Component
public class SignupForm extends BasePage implements Serializable {
    User user;
    @Autowired
    private UserManager userManager;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@PostConstruct
    public void init() {
        user = new User();
        user.setUsername("STAR-" +   UUID.randomUUID());
//        user.setUsername("STAR-" + DateFormatUtils.format(new Date(), "yyMMdd-hhmmss-") + UUID.randomUUID());
        user.setEnabled(true);
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setEmail("e" +   UUID.randomUUID());
        user.setPassword(passwordEncoder.encode("p"));
		Role role = new Role();
		role.setDescription("normal");
		role.setName("ROLE_USER");
		Set roles = new HashSet<>();
		roles.add(role);

		user.setRoles(roles);
	}

    public void save() throws InvocationTargetException, NoSuchMethodException, UserExistsException, IllegalAccessException {
        userManager.saveUser(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
