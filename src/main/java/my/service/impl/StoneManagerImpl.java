package my.service.impl;

import my.dao.StoneDao;
import my.model.persist.spirit.Stone;
import my.service.StoneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */

@Service("stoneManager")
public class StoneManagerImpl extends GenericManagerImpl<Stone, Long> implements StoneManager {

    @Autowired
    public StoneManagerImpl(StoneDao dao) {
        super(dao);
    }

}
