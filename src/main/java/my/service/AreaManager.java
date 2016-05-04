package my.service;

import my.model.persist.BaseLog;
import my.model.persist.BaseObj;

import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/24 for Project.
 */
public interface AreaManager {
	BaseLog getBaseLogById(String id);

	List getPagedList(Class type, Object context) throws NoSuchFieldException;

	int getCount(Class type);

    void updateBaseObj(BaseObj baseLog);

    List getMoundTarget();
}
