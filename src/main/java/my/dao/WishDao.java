package my.dao;

import my.model.persist.place.Stone;
import my.model.persist.place.Wish;

import java.util.List;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
public interface WishDao extends GenericDao<Wish,Long>{
	List<Stone> getStones(Wish wish);
}
