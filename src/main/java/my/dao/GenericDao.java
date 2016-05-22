package my.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) DAO's for your
 * domain objects.
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @author jgarcia (update: added full text search + reindexing)
 *
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public interface GenericDao <T, PK extends Serializable> {

    Serializable save(T o);

    void saveOrUpdate(T o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    T get(Serializable key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    List<T> getByCriteria(Object criterion) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException;

	List<T> getPagedList(Object context) throws NoSuchFieldException;

    void remove(T object);

    int getCount();

	org.hibernate.Session getSession();
}
