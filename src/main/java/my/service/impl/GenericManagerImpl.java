package my.service.impl;

import my.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/3/27 for Project.
 */
public abstract class GenericManagerImpl<T, PK extends Serializable> implements my.service.GenericManager<T, PK> {
// members
	GenericDao dao;

//constructs
	GenericManagerImpl(GenericDao dao) {
		this.dao = dao;
	}

//methods
	@Override
	public Serializable save(T o) {
		return dao.save(o);
	}

	@Override
	public List<T> getAll() {
		return null;
	}

	@Override
	public T get(PK id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return (T) dao.get(id);
	}

	@Override
	public boolean exists(PK id) {
		return false;
	}

	@Override
	public void saveOrUpdate(T object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		dao.saveOrUpdate(object);
	}

	@Override
	public void remove(T object) {
        dao.remove(object);
    }

	@Override
	public void remove(PK id) {

	}

	@Override
	public List<T> getPagedList(int start, int pagesize, Map order, String searchTerm) {
		return null;
	}

	@Override
	public int count() {
		return 0;
	}
}
