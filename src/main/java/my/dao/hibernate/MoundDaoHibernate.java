package my.dao.hibernate;

import my.dao.MoundDao;
import my.model.persist.place.Mound;
import org.springframework.stereotype.Repository;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Repository("moundDao")
public class MoundDaoHibernate extends GenericDaoHibernate<Mound,Long> implements MoundDao {
	public MoundDaoHibernate() {
		super(Mound.class);
	}
}
