package my.service;

import my.model.persist.place.Stone;

import java.io.Serializable;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
public interface StoneManager extends GenericManager<Stone,Long> {
    Serializable addStone(Stone stone);

}
