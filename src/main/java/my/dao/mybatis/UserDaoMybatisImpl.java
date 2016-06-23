package my.dao.mybatis;

import my.dao.UserDao;
import my.dao.mybatis.mi.AppUserMapper;
import my.model.b_example.AppUserExample;
import my.model.persist.User;
import my.model.persist.project.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

/**
 * Created by yinghao_niu on 2015/12/5 0005 for Project.
 */

@Repository("userDaoMb")
public class UserDaoMybatisImpl extends GenericDaoMybatisImpl<User, Long> implements UserDetailsService, UserDao {
	//constructs
	public UserDaoMybatisImpl() {
		super(AppUserMapper.class, AppUserExample.class);
	}

	;

	//overridden
	//methods
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		User user = null;

		try {
			user = getUserByUsername(s);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return (UserDetails) user;
	}


	@Override
	public User getUserByUsername(String username) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		AppUserExample e = new AppUserExample();
		e.createCriteria().andUsernameEqualTo(username);
		List<User> users = getByCriteria(e);
		if (users == null || users.size() == 0) {
			return null;
		}
		User user = users.get(0);
//		Set<Role> roles = ((AppUserMapper) mapper).selectRoles(user.getId());
//		user.setRoles(roles);
		return user;
	}

	@Override
	public void saveUser(User user) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		super.saveOrUpdate(user);
		((AppUserMapper) mapper).insertRoles(user.getRoles(), user.getId());
	}

}
