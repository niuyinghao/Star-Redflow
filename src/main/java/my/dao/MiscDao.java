package my.dao;

import my.model.persist.BaseLog;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
public interface MiscDao  {

	BaseLog getBaseLogById(Serializable id);

    void update(Object baseLog);

    List getNotBuriedTarget();

    Session getSession();

	List getAllWaveOrFlowerNotMound();

    List getMoundTarget();
}

