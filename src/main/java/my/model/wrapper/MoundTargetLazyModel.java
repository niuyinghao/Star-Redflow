package my.model.wrapper;

import my.dao.MiscDao;
import my.dao.hibernate.MiscDaoHibernate;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/6/19 for project.
 */
@Component
public class MoundTargetLazyModel extends LazyDataModel {
    @Autowired
    private MiscDao miscDao;

    @Override
    public List load(int first, int pageSize, List multiSortMeta, Map filters) {
        return super.load(first, pageSize, multiSortMeta, filters);
    }



    @Override
    public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
        List notBuriedTarget = miscDao.getNotBuriedTarget();
        setRowCount(notBuriedTarget.size());
        return notBuriedTarget;
    }
}
