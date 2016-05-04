package my.service.impl;

import my.dao.WishDao;
import my.model.persist.place.Wish;
import my.service.WishManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
@Service("wishManger")
public class WishManagerImpl extends GenericManagerImpl<Wish,Long> implements WishManager {

    @Autowired
    public WishManagerImpl(WishDao dao) {
        super(dao);
    }
}
