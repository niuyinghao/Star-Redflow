package my.service.impl;

import my.dao.SettingDao;
import my.model.persist.Context;
import my.service.SettingManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
public class SettingManagerImpl extends GenericManagerImpl<Context,Long> implements SettingManager {

    @Autowired
    SettingManagerImpl(SettingDao dao) {
        super(dao);
    }
}
