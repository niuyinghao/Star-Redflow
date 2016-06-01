package my.dao.hibernate;

import my.dao.RoleDao;
import my.model.persist.project.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by niuyinghao on 2016/6/2 for project.
 */
@Repository("roleDao")
public class RoleDaoHibernate extends GenericDaoHibernate<Role, Long> implements RoleDao {
    public RoleDaoHibernate() {
        super(Role.class);
    }

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
}

