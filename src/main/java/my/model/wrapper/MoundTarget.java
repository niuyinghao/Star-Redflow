package my.model.wrapper;

import my.model.persist.BaseLog;
import my.service.AreaManager;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by niuyinghao on 2016/4/22 for project.
 */
@Component
public class MoundTarget extends AreaLazyModel implements SelectableDataModel {

    @Autowired
    private AreaManager areaManager;


    @Override
    public Object getRowData(String rowKey) {
        return areaManager.getBaseLogById(rowKey);
    }

    @Override
    public Object getRowKey(Object object) {
        if (object == null) {
            return null;
        }
        return ((BaseLog) object).getId();
    }

    @Override
    public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
        List list = areaManager.getMoundTarget();
        setRowCount(list.size());
        return list;
    }

    @Override
    public List load(int first, int pageSize, List multiSortMeta, Map filters) {
        return super.load(first, pageSize, multiSortMeta, filters);
    }

    public List getAllWaveOrFlowerNotMound() {
        return areaManager.getAllWaveOrFlowerNotMound();
    }
}
