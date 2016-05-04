package my.dao.hibernate;

import my.dao.WishDao;
import my.model.persist.place.Wish;
import org.springframework.stereotype.Repository;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
@Repository("wishDao")
public class WishDaoHibernate extends GenericDaoHibernate<Wish,Long> implements WishDao {
	public WishDaoHibernate() {
		super(Wish.class);
	}
}
