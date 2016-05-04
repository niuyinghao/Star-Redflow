package my.service;

import my.model.persist.place.Mound;

import java.io.Serializable;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
public interface MoundManager extends GenericManager<Mound,Long> {
    Serializable addMound(Mound mound);
}
