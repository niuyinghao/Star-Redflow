package my.service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Generic Manager that talks to GenericDao to CRUD POJOs.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) managers
 * for your domain objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Updated by jgarcia: added full text search + reindexing
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public interface GenericManager<T, PK extends Serializable> {

    Serializable save(T o);

    /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @return List of populated objects
     */
    List<T> getAll();

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    /**
     * Checks for existence of an object of type T using the id arg.
     * @param id the identifier (primary key) of the object to get
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an object - handles both update and insert.
     * @param object the object to save
     * @return the updated object
     */
    void saveOrUpdate(T object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    /**
     * Generic method to delete an object
     * @param object the object to remove
     */
    void remove(T object);

    /**
     * Generic method to delete an object based on class and id
     * @param id the identifier (primary key) of the object to remove
     */
    void remove(PK id);


	/**
	 *
	 /**
	 * Generic method to get paged list of objects
	 * @param start    the row to begin
	 * @param pagesize the number of each page
	 * @param order    to control sort
	 * @param searchTerm
	 * @return paged list of object
	 */
	List<T> getPagedList(int start, int pagesize, Map order, String searchTerm);


	/**
	 *
	 * @return number of all entity
	 */
	int count();


}
