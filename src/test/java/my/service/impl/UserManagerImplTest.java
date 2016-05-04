package my.service.impl;

import my.BaseTest;
import my.model.persist.User;
import my.service.UserManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by yinghao_niu on 2016/3/27 for Project.
 */
public class UserManagerImplTest extends BaseTest {


	@Autowired
	@Qualifier("userMgr")
	private UserManager userManager;

	@Test
	public void testSave() throws Exception {
		User user = new User();
		user.setEmail("niuyinghao@126.com");
		user.setEnabled(true);
		user.setAccountExpired(true);
		user.setAccountLocked(false);
		user.setCredentialsExpired(false);
		user.setPassword("testpasswd");
		user.setUsername("name2");
		user.setVersion(1);

		userManager.saveOrUpdate(user);

	}
}
