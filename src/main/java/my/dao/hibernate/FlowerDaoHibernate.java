package my.dao.hibernate;

import my.dao.FlowerDao;
import my.model.persist.spirit.Flower;
import org.springframework.stereotype.Repository;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Repository("flowerDao")
public class FlowerDaoHibernate extends GenericDaoHibernate<Flower,Long> implements FlowerDao {
	public FlowerDaoHibernate() {
		super(Flower.class);
	}
}
