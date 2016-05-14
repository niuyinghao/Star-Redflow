package my.dao.hibernate;

import my.dao.WishDao;
import my.model.persist.place.Stone;
import my.model.persist.place.Wish;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
@Repository("wishDao")
public class WishDaoHibernate extends GenericDaoHibernate<Wish,Long> implements WishDao {
	public WishDaoHibernate() {
		super(Wish.class);
	}



	@Override
	public List<Stone> getStones(Wish wish) {
		return getSession().createQuery(" select w.stoneList from Wish w where w=:w")
				.setParameter("w", wish)
				.list();
	}
}
