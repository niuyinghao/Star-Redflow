package my.service.impl;

import my.dao.MoundDao;
import my.model.persist.place.Mound;
import my.service.MoundManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
@Service("moundManager")
public class MoundManagerImpl extends GenericManagerImpl<Mound,Long> implements MoundManager {

    @Autowired
    MoundManagerImpl(MoundDao dao) {
        super(dao);
    }

    @Override
    public Serializable addMound(Mound mound) {
        return dao.save(mound);
    }
}
