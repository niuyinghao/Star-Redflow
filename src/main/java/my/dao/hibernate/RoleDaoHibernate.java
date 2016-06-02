package my.dao.hibernate;

import my.dao.RoleDao;
import my.model.persist.project.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

/**
 * Created by niuyinghao on 2016/6/2 for project.
 */
@Repository("roleDao")
public class RoleDaoHibernate implements RoleDao {
    @Override
    public Role getRoleByName(String rolename) {
        return (Role) getSession().createQuery(" from Role  where name=:name")
                .setParameter("name", rolename)
                .uniqueResult();
    }

    @Override
    public Set<Role> getRolesByUserId(Long userId) {
        return null;   //todo_need
    }

    @Override
    public void removeRole(String rolename) {
        //todo_need
    }

    @Override
    public Serializable save(Role o) {
        return null;   //todo_need
    }

    @Override
    public void saveOrUpdate(Role o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //todo_need
    }

    @Override
    public Role get(Serializable key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return null;   //todo_need
    }

    @Override
    public List<Role> getByCriteria(Object criterion) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        return null;   //todo_need
    }

    @Override
    public List<Role> getPagedList(Object context) throws NoSuchFieldException {
        return null;   //todo_need
    }

    @Override
    public void remove(Role object) {
        //todo_need
    }

    @Override
    public int getCount() {
        return 0;   //todo_need
    }

    @Override
    public Session getSession() {
        return null;   //todo_need
    }
}
