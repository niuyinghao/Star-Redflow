package my.dao.mybatis;

import my.dao.GenericDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.Session;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GenericDaoMybatisImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

// members
	MapperFactoryBean factory = new MapperFactoryBean();
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	protected Mapper mapper;
	Class mapperClass;
	Class exampleClass;


	public static final String SAVE_METHOD = "insert";


	//constructs

	public GenericDaoMybatisImpl(Class mapperClass, Class exampleClass) {
//		SqlSession sqlSession = this.getSqlSession();
//		if (sqlSession != null) {
//			mapper = (T) sqlSession.getMapper(interfaceMap.get(persistClass));
//		}
		this.factory.setMapperInterface(mapperClass);
		this.mapperClass = mapperClass;
		this.exampleClass = exampleClass;
	}

    @Override
    public Serializable save(T o) {
        return null;
    }

    //overridden
	@Override
	public void saveOrUpdate(T o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class mapperInterface = factory.getMapperInterface();
		 mapperInterface.getDeclaredMethod(SAVE_METHOD, o.getClass()).invoke(mapper, o);
	}

	@Override
	public T get(Serializable key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class mapperInterface = factory.getMapperInterface();
		return (T) mapperInterface.getDeclaredMethod("selectByPrimaryKey", Long.class).invoke(mapper, key);
	}

	@Override
	public List<T> getByCriteria(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class mapperInterface = factory.getMapperInterface();
		List selectByExample = (List) mapperInterface.getDeclaredMethod("selectByExample", exampleClass).invoke(mapper, o);
		if (selectByExample == null || selectByExample.size() == 0) {
			return null;
		}
		return selectByExample;
	}

	@Override
	public List<T> getPagedList( Object example) {
		return mapper.selectByExample();
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Session getSession() {
		return null;
	}

	//method
	@PostConstruct
	public void init() {
		this.factory.setSqlSessionFactory(sqlSessionFactory);
		this.factory.setMapperInterface(mapperClass);
		mapper = (Mapper) factory.getSqlSession().getMapper(mapperClass);
	}
}
