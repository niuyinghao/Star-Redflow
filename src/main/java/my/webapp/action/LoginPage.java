package my.webapp.action;

import org.springframework.stereotype.Component;

/**
 * Created by yinghao_niu on 2016/5/29 for project.
 */
@Component
public class LoginPage extends BasePage {
    public String register() {
        return "/signup.xhtml";
    }
}
