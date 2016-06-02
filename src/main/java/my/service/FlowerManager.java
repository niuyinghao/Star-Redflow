package my.service;

import my.model.persist.spirit.Flower;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
public interface FlowerManager extends GenericManager<Flower,Long> {
    void addFlower(Flower flower);
}
