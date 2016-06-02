package my.service.impl;

import my.dao.WishDao;
import my.model.persist.User;
import my.model.persist.spirit.Stone;
import my.model.persist.spirit.Wish;
import my.service.WishManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
@Service("wishManger")
public class WishManagerImpl extends GenericManagerImpl<Wish, Long> implements WishManager {

//constructs
	@Autowired
	public WishManagerImpl(WishDao dao) {
		super(dao);
	}

//methods
	@Override
	public List<Stone> getStones(Wish wish) {
		return ((WishDao) dao).getStones(wish);
	}

	@Override
	public List getMenuedWishes(User currentUser) {
		if (currentUser == null) {
			return null;
		}
		return ((WishDao) dao).getMenuedWishes(currentUser);
	}
}
