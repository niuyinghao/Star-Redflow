package my.dao;

import my.model.persist.BaseLog;
import my.model.persist.User;
import org.hibernate.Session;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
public interface MiscDao extends GenericDao {

	BaseLog getBaseLogById(Serializable id);

    void update(Object baseLog);

    void doMound(String id, String entity);

    List getNotBuriedTarget(int first, int pageSize, String sortField, SortOrder sortOrder, User creator);

    Session getSession();

	List getAllWaveOrFlowerNotMound();

    List getMoundTarget();

    Long getUserSequence();
}

