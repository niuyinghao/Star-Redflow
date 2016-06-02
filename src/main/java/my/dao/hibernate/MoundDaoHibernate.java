package my.dao.hibernate;

import my.dao.MoundDao;
import my.model.persist.spirit.Mound;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Repository("moundDao")
public class MoundDaoHibernate extends GenericDaoHibernate<Mound,Long> implements MoundDao {
	public MoundDaoHibernate() {
		super(Mound.class);
	}

	@Override
	public List getTargets(Mound mound) {
		return getSession().createQuery(" select m.targets from Mound m where m=:m")
				.setParameter("m", mound)
				.list();
	}
}
