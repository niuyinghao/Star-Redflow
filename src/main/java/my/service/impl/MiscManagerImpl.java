package my.service.impl;

import my.dao.MiscDao;
import my.service.MiscManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yinghao_niu on 2016/6/29 for project.
 */

@Service("miscManager")
public class MiscManagerImpl extends GenericManagerImpl implements MiscManager {

    @Autowired
    public MiscManagerImpl(MiscDao miscDao) {
        super(miscDao);
    }

}
