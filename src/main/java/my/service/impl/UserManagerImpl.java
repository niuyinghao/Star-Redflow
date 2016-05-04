package my.service.impl;

import my.dao.UserDao;
import my.model.persist.User;
import my.service.UserExistsException;
import my.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/3/26 for Project.
 */
@Service("userMgr")
@Scope("session")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager,Serializable {

	// members
	UserDao userDao;

	//constructs
	@Autowired
	UserManagerImpl(@Qualifier("userDao") UserDao userDao) {
        super(userDao);
		this.userDao = userDao;
	}

	@Override
	public User getUser(String userId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return userDao.get(userId);
	}

	@Override
	public User getUserByUsername(String username) throws UsernameNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return userDao.getUserByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		return null;
	}

	@Override
	public void saveUser(User user) throws UserExistsException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		 userDao.saveUser(user);
	}

	@Override
	public void removeUser(User user) {

	}

	@Override
	public void removeUser(String userId) {

	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public User get(Long id) {
		return null;
	}

	@Override
	public boolean exists(Long id) {
		return false;
	}



	@Override
	public void remove(User object) {

	}

	@Override
	public void remove(Long id) {

	}

	@Override
	public List<User> getPagedList(int start, int pagesize, Map order, String searchTerm) {
		return null;
	}

	@Override
	public int count() {
		return 0;
	}
}
