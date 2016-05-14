package my.dao;

import my.model.persist.place.Mound;

import java.util.List;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
public interface MoundDao extends GenericDao<Mound,Long>{
	List getTargets(Mound mound);
}
