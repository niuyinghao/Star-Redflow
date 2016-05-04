package my.webapp.action;

import com.thoughtworks.selenium.DefaultSelenium;
import my.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;

/**
 * Created by niuyinghao on 2016/3/30 for brandweb.
 */
public class SignupFormTest extends BaseTest {

    @Autowired
    private SignupForm signupForm;
    @Qualifier("passwordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Test
    public void testSave() throws Exception {
        signupForm.save();
    }

    @Test
    public void printPasswd() {
        System.out.println(passwordEncoder.encode("p"));
    }

    @Test
    public void testS() {
        DefaultSelenium defaultSelenium = new DefaultSelenium("test.niu.dhgate.com", 8080, "iexplore"
                , "http://www.baidu.com");
        defaultSelenium.start();
    }
}
