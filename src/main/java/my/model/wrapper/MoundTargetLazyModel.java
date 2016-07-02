package my.model.wrapper;

import my.dao.MiscDao;
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
    @Autowired
    private CommonContext context;

    @Override
    public List load(int first, int pageSize, List multiSortMeta, Map filters) {
        return super.load(first, pageSize, multiSortMeta, filters);
    }


    static ThreadLocal<Long> count = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return 0l;
        }
    };

    public static Long getCount() {
        return count.get();
    }

    public static void setCount(Long count) {
        MoundTargetLazyModel.count.set(count);
    }

    @Override
    public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
        sortField = "createTime";
        sortOrder = SortOrder.ASCENDING;
        List notBuriedTarget = miscDao.getNotBuriedTarget(first,pageSize,sortField,sortOrder,context.getCreator());
        setRowCount(count.get().intValue());
        return notBuriedTarget;
    }
}
