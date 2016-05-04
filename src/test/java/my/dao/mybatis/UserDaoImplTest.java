package my.dao.mybatis;

import my.BaseTest;
import my.model.persist.User;
import my.service.UserManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by niuyinghao on 2016/3/29 for brandweb.
 */
public class UserDaoImplTest extends BaseTest{


    @Autowired
    private UserManager userManager;

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setEnabled(true);

        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setEmail("e");
        user.setId(1l);
        user.setPassword("p");
        user.setUsername("u");
        user.setVersion(1);
        userManager.saveOrUpdate(user);
    }

    @Test
    public void testLogin() throws IOException {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.setMethod("POST");
//        request.setRequestURI("http://192.168.33.10:8080" +
//                "/j_security_check");
//        request.setParameter("j_username","u");
//        request.setParameter("j_password","$2a$10$vJuVTX7SiMDgKCpjFaQFAengCucQeMeR4w5mJGH6Z20G3FXYmtMBG");
//        request.startAsync();

        super.doPost("http://192.168.33.10:8080" +
                "/j_security_check", "j_username=u;j_password=$2a$10$vJuVTX7SiMDgKCpjFaQFAengCucQeMeR4w5mJGH6Z20G3FXYmtMBG");

    }
}
