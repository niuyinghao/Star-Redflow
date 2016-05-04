package my.service;

import my.model.persist.place.Flower;

import java.io.Serializable;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
public interface FlowerManager extends GenericManager<Flower,Long> {
    Serializable addFlower(Flower flower);
}
