package my.dao.hibernate;

import my.dao.WaveDao;
import my.model.persist.place.Wave;
import org.springframework.stereotype.Repository;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Repository("waveDao")
public class WaveDaoHibernate extends GenericDaoHibernate<Wave,Long> implements WaveDao {
	public WaveDaoHibernate() {
		super(Wave.class);
	}
}
