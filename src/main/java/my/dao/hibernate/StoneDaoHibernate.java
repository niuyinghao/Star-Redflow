package my.dao.hibernate;

import my.dao.StoneDao;
import my.model.persist.spirit.Stone;
import org.springframework.stereotype.Repository;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Repository("stoneDao")
public class StoneDaoHibernate extends GenericDaoHibernate<Stone,Long> implements StoneDao {
	public StoneDaoHibernate() {
		super(Stone.class);
	}
}
