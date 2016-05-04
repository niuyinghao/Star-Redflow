package my.dao;

import my.model.persist.User;

import java.lang.reflect.InvocationTargetException;

/**
 * User Data Access Object (GenericDao) interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface UserDao extends GenericDao<User, Long>  {

	User getUserByUsername(String username) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

	void saveUser(User user) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

}
