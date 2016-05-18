package my.dao.hibernate;

import my.dao.SettingDao;
import my.model.persist.Context;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
public class SettingDaoHibernate extends GenericDaoHibernate<Context,Long> implements SettingDao {
	public SettingDaoHibernate() {
		super(Context.class);
	}
}
