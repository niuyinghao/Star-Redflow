package my.dao.hibernate;

import my.dao.UserDao;
import my.model.persist.User;
import my.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/10 for Project.
 */


@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserService, UserDao,UserDetailsService {


	public UserDaoHibernate() {
		super(User.class);
	}

//methods
	@Override
	public User getUser(String userId) {
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		Object user = getSession().createQuery(" from User  where username=:username")
				.setParameter("username", username)
				.uniqueResult();
		return (User) user;
	}

	@Override
	public List<User> getUsers() {
		return null;
	}

	@Override
	public void saveUser(User user) {
		Serializable id = getSession().save(user);
		user.setId((Long) id);
	}

	@Override
	public void removeUser(String userId) {

	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return getUserByUsername(s);
	}
}
