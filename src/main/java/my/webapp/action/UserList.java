package my.webapp.action;

import my.webapp.action.BasePage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("userList")
@Scope("session")
public class UserList extends BasePage implements Serializable {
}