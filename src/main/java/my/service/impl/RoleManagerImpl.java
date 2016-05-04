package my.service.impl;

import my.dao.RoleDao;
import my.model.persist.project.Role;
import my.service.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/3/26 for Project.
 */
@Service("roleMgr")
public class RoleManagerImpl  extends GenericManagerImpl<Role,Long> implements RoleManager {


    @Autowired
    RoleManagerImpl(RoleDao dao) {
        super(dao);
    }

    @Override
    public List getRoles(Role role) {
        return null;
    }

    @Override
    public Role getRole(String rolename) {
        return null;
    }

    @Override
    public Role saveRole(Role role) {
        return null;
    }

    @Override
    public void removeRole(String rolename) {

    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }


    @Override
    public void remove(Role object) {

    }

    @Override
    public void remove(Long id) {

    }

	@Override
    public List<Role> getPagedList(int start, int pagesize, Map order, String searchTerm) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}
