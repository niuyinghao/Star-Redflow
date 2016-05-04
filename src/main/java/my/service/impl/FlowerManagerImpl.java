package my.service.impl;

import my.dao.FlowerDao;
import my.model.persist.place.Flower;
import my.service.FlowerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
@Service("flowerManager")
public class FlowerManagerImpl extends GenericManagerImpl<Flower,Long> implements FlowerManager {

     @Autowired
     public FlowerManagerImpl(FlowerDao dao) {
        super(dao);
    }

    @Override
    public Serializable addFlower(Flower flower) {
        return dao.save(flower);
    }
}
