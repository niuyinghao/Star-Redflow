package my.dao.hibernate;

import my.dao.GenericDao;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.ComponentType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="gdms.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="gdms.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 *         Updated by jgarcia: update hibernate3 to hibernate4
 * @author jgarcia (update: added full text search + reindexing)
 */
@SuppressWarnings("unchecked")
public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {

    // members
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;
    @Resource
    protected SessionFactory sessionFactory;
    private int searchedSize = 0;
    private boolean needFilter = true;
    ClassMetadata classMetadata;
    int count;

//constructs


    public GenericDaoHibernate() {

    }

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
    }

    //methods
    @Override
    public Serializable save(T o) {
        return getSession().save(o);
    }

    @Override
    public void saveOrUpdate(T o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        getSession().saveOrUpdate(o);
    }

    @Override
    public T get(Serializable key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (T) getSession().get(persistentClass, key);
    }

    @Override
    public List<T> getByCriteria(Object criterion) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        return getPagedList(criterion);
    }

    @Override
    public List<T> getPagedList(Object context) throws NoSuchFieldException {
        CriteriaWrapper criteria = (CriteriaWrapper) context;
        return doGetPagedList(criteria.getStart(), criteria.getPageSize(), criteria.getOrders(), null, criteria.getCriterion());
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public Session getSession() throws HibernateException {
        Session sess = null;
        sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }

    /**
     * 分页
     *
     * @param start      起始�     * @param pageSize 没页显示的条�     * @param order 排序方式
     * @param searchTerm
     * @param criterion
     * @return
     * @throws NoSuchFieldException
     */
    private List<T> doGetPagedList(int start, int pageSize, List order, String searchTerm, Criterion criterion) throws NoSuchFieldException {


        if (searchTerm != null) {
            searchTerm = searchTerm.trim();
        }

        Criteria criteria = getSession().createCriteria(persistentClass);
        if (criterion != null) {
            criteria.add(criterion);
        }

        //Search
        if (StringUtils.equals(searchTerm, "") || StringUtils.equals(searchTerm, "*") || searchTerm == null) {
        }
        else {
    /*		FullTextSession txtSession = Search.getFullTextSession(sess);
            SearchFactory searchFactory = txtSession.getSearchFactory();
			IndexReaderAccessor readerAccessor = searchFactory.getIndexReaderAccessor();
			IndexReader reader = readerAccessor.open(this.persistentClass);
			Collection<String> fieldNames = new HashSet<>();
			for (FieldInfo fieldInfo : ReaderUtil.getMergedFieldInfos(reader)) {
				if (fieldInfo.isIndexed) {

				}
			}*/

            Disjunction search = Restrictions.disjunction();

            if (classMetadata.getIdentifierType().getClass() == StringType.class) {
                search.add(Restrictions.or(Restrictions.like(classMetadata.getIdentifierPropertyName(), searchTerm, MatchMode.ANYWHERE)));
            }

            for (String fieldName : classMetadata.getPropertyNames()) {

                Field field = persistentClass.getDeclaredField(fieldName);
                if (field.getType() == String.class) {
                    search.add(Restrictions.or(Restrictions.like(fieldName, searchTerm, MatchMode.ANYWHERE)));
                }
                else {
                    Class<? extends Type> aClass = classMetadata.getPropertyType(fieldName).getClass();
                    Class bClass = field.getType();
                    if (aClass == ComponentType.class) {
                        Field[] fields = bClass.getDeclaredFields();
                        for (Field f : fields) {
                            if (f.getName() != "paper") {
                                try {
                                    String subProperty = field.getName() + "." + f.getName();
                                    Type propertyType = classMetadata.getPropertyType(subProperty);

                                    if (propertyType.getClass() == StringType.class) {
                                        search.add(Restrictions.or(Restrictions.like(subProperty, searchTerm, MatchMode.ANYWHERE)));
                                    }
                                } catch (HibernateException e) {
//								e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }

            criteria.add(Restrictions.and(search));
        }


        this.searchedSize = ((Long) (criteria.setProjection(Projections.rowCount()).uniqueResult())).intValue();

        setCount(searchedSize);

        criteria.setProjection(null);

        // paging
        if (pageSize == -1) {

        }
        else {
            criteria.setFirstResult(start);
            criteria.setMaxResults(pageSize);
        }

        // sorts

        if (order == null) {
            //criteria.addOrder(Order.asc("status"));
        }
        else {
            for (Iterator<Map> iterator = order.iterator(); iterator.hasNext(); ) {
                Map<String, String> entry = iterator.next();
                String field = entry.keySet().iterator().next().toString();
                String orderType = entry.get(field);
                if (StringUtils.equals("desc", orderType)) {
                    criteria.addOrder(Order.desc(field));
                }
                else {
                    criteria.addOrder(Order.asc(field));
                }
            }

        }


        criteria.setProjection(Projections.id());
//		criteria.setResultTransformer(new AliasToBeanResultTransformer(persistentClass));
//		criteria.add(Subqueries.propertyIn("id", idsOnlyCriteria));
        List<Serializable> list = criteria.list();
        if (list.size() > 0) {

        }
        else {
            return null;
        }

        // not work
        /*Disjunction dis=Restrictions.disjunction();;
        for (Serializable s : list) {
			dis.add(Restrictions.or(Restrictions.eq("id", s)));
		}
		if (dis != null) {
			criteria1.add(dis);
		}*/
//		criteria1.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List result = new ArrayList();
/*
// show improve performance
		for (Serializable s : list) {
			Criteria criteria1 = getSession().createCriteria(persistentClass);
			criteria1.add(Restrictions.eq("id", s));
			result.add(criteria1.uniqueResult());
		}
*/
        Criteria criteria1 = getSession().createCriteria(persistentClass);
        criteria1.add(Restrictions.in("id", list));
        List unsortedResult = criteria1.list();

        // to sort as list
        try {
            result = sortedAsList(unsortedResult, list);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List sortedAsList(List unsortedResult, List<Serializable> list) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String idColumn = classMetadata.getIdentifierPropertyName();
        String getIdMethod = "get" + idColumn.substring(0, 1).toUpperCase() + idColumn.substring(1);

        List sortedList = new ArrayList();
        Map infoMap = new HashMap();
        int size = unsortedResult.size();
        for (Object o : unsortedResult) {
            infoMap.put(getIndex(o, list, getIdMethod), o);
        }

        for (int i = 0; i < size; i++) {
            sortedList.add(infoMap.get(i));
        }
        return sortedList;

    }

    private int getIndex(Object o, List<Serializable> list, String getIdMethod) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Serializable s1 = (Serializable) o.getClass().getMethod(getIdMethod).invoke(o);
        int i = 0;
        for (Serializable s2 : list) {
            if (s2.equals(s1)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        if (persistentClass != null) {
            classMetadata = getSessionFactory().getClassMetadata(persistentClass);
        }
    }

    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        return entity != null;
    }

    /**
     * 删除对象
     */
    public void remove(T object) {
        Session sess = getSession();
        sess.delete(object);
    }

    /**
     * 根据id删除对象
     */
    public void remove(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        sess.delete(entity);
    }

    //getter and setter
    public boolean isNeedFilter() {
        return needFilter;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session sess = getSession();
        return sess.createCriteria(persistentClass).list();
    }


}
