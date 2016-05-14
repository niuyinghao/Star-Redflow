package my.service.impl;

import my.dao.WishDao;
import my.model.persist.place.Stone;
import my.model.persist.place.Wish;
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
}
