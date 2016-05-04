package my.service.impl;

import my.dao.StoneDao;
import my.model.persist.place.Stone;
import my.service.StoneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */

@Service("stoneManager")
public class StoneManagerImpl extends GenericManagerImpl<Stone,Long> implements StoneManager {

    @Autowired
    public StoneManagerImpl(StoneDao dao) {
        super(dao);
    }

    @Override
    public Serializable addStone(Stone stone) {
        return dao.save(stone);
    }
}
