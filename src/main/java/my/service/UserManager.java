package my.service;

import my.model.persist.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */

@WebService
@Path("/user")
public interface UserManager extends GenericManager<User, Long> {


    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    @Path("{id}")
    @GET
    User getUser(@PathParam("id") String userId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    /**
     * Finds a user by their username.
     *
     * @param username the user's username used to login
     * @return User a populated user object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException exception thrown when user not found
     */
    User getUserByUsername(String username) throws UsernameNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @return user the updated user object
     * @throws UserExistsException thrown when user already exists
     */
    void saveUser(User user) throws UserExistsException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeUser(User user);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeUser(String userId);

    /**
     * Retrieves a list of all users.
     *
     * @return List
     */
    List<User> getUsers();

    String getUserSequence();
}
