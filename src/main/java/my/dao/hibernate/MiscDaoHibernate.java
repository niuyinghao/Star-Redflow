package my.dao.hibernate;

import my.dao.MiscDao;
import my.model.persist.BaseLog;
import my.model.persist.User;
import my.model.wrapper.MoundTargetLazyModel;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/23 for Project.
 */
@Repository("miscDao")
public class MiscDaoHibernate extends GenericDaoHibernate implements MiscDao {

    public MiscDaoHibernate() {
        super(null);
    }

    @Override
    public BaseLog getBaseLogById(Serializable id) {
        return (BaseLog) getSession().createQuery("from BaseLog  where id=:id")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public void update(Object baseLog) {
        getSession().update(baseLog);
    }

    @Override
    public void doMound(String id, String entity) {
        /**
         * update BaseLog :
         *  org.postgresql.util.PSQLException: 错误: 关系 "ht_base_log" 不存在
         */

        if ("class my.model.persist.spirit.Flower".equals(entity)) {
            getSession().createQuery("update Flower set buried=true where id=:id")
                    .setLong("id", Long.parseLong(id))
                    .executeUpdate();
        }
        else if ("class my.model.persist.spirit.Wave".equals(entity)) {
            getSession().createQuery("update Wave set buried=true where id=:id")
                    .setLong("id", Long.parseLong(id))
                    .executeUpdate();
        }
        else {
            return;
        }
    }

    @Override
    public List getNotBuriedTarget(int first, int pageSize, String sortField, SortOrder sortOrder, User creator) {
        String hqlBase = " from BaseLog b where buried=false and creator=:creator";
        String hql = hqlBase + "  order by  " + sortField;
        if (sortOrder == SortOrder.ASCENDING) {
            hql += " asc ";
        }
        else if (sortOrder == SortOrder.DESCENDING) {
            hql +=  " desc ";
        }
        else {
        }

        Query query = getSession().createQuery(hql);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        query.setParameter("creator", creator);

        String hql4Count = "select count(b)  " + hqlBase;

        Query queryCount = getSession().createQuery(hql4Count).setParameter("creator", creator);


        MoundTargetLazyModel.setCount((Long) queryCount.uniqueResult());

        return query.list();
    }

    @Override
    public List getAllWaveOrFlowerNotMound() {
        SQLQuery sqlQuery = getSession().createSQLQuery(" SELECT   targets from MOUND_TARGETS ");
        List<BigInteger> list = sqlQuery.list();
        if (list == null || list.size() == 0) {
            // todo restrict
            return getSession().createQuery(" from  BaseLog  ")
                    .list();
        }
        List l_idList = new ArrayList();
        for (BigInteger bigInteger : list) {
            l_idList.add(Long.valueOf(bigInteger.longValue()));
        }
        return getSession().createQuery(" from BaseLog b where b.id not in (:list)" +
                "  ")
                .setParameterList("list", l_idList)
                .list()
                ;
    }

    @Override
    public List getMoundTarget() {
        Query query = getSession().createQuery("from BaseLog where id in (select id from Wave w where w.mound is null )" +
                " or id in (select id from Flower f where f.mound is null)");
        return query.list();

    }

    @Override
    public Long getUserSequence() {
        BigInteger seq = (BigInteger) getSession().createSQLQuery(" SELECT nextval('register_sequence')").uniqueResult();
        Long registerSeq = seq.longValue();
        return registerSeq;
    }

    //methods
    public List x_getMoundTarget() {
        String columns = "create_time as createTime,id as id , sign as sign";
        SQLQuery sqlQuery = getSession().createSQLQuery(" select " +
                columns +
                " from wave " +
                " UNION ALL SELECT " +
                columns +
                " from flower");
        sqlQuery.addScalar("id", StandardBasicTypes.LONG);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(BaseLog.class));
        List list = sqlQuery.list();
        if (list != null) {
            return list;
        }
        else {
            return null;
        }
    }
}
