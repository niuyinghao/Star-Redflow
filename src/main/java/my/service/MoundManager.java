package my.service;

import my.model.persist.spirit.Mound;

import java.io.Serializable;
import java.util.List;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
public interface MoundManager extends GenericManager<Mound,Long> {
    Serializable addMound(Mound mound);

	List getTargets(Mound mound);
}
