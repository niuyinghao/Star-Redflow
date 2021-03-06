package my.service;

import my.model.persist.User;
import my.model.persist.spirit.Stone;
import my.model.persist.spirit.Wish;

import java.util.List;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
public interface WishManager extends GenericManager<Wish,Long> {
	List<Stone> getStones(Wish wish);

	List getMenuedWishes(User currentUser);
}
