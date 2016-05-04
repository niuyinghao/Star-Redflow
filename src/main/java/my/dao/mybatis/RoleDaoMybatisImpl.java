package my.dao.mybatis;

import my.dao.RoleDao;
import my.dao.mybatis.mi.RoleMapper;
import my.model.b_example.RoleExample;
import my.model.persist.project.Role;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by yinghao_niu on 2015/12/5 0005 for Project.
 */
@Repository("roleDao")
public class RoleDaoMybatisImpl extends GenericDaoMybatisImpl<Role, Long> implements RoleDao {

	//constructs
	@Autowired
	RoleDaoMybatisImpl(SqlSessionFactory sqlSessionFactory) {
		super(RoleMapper.class, RoleExample.class);
	}

	//overridden
	@Override
	public Role getRoleByName(String rolename) {
		return null;   //todo_need
	}

	@Override
	public Set<Role> getRolesByUserId(Long userId) {
		return ((RoleMapper) mapper).getRolesByUserId(userId);
	}

	@Override
	public void removeRole(String rolename) {
		//todo_need
	}

}
