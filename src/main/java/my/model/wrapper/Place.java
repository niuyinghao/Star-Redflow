package my.model.wrapper;

import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
public abstract class Place<T> extends AreaLazyModel {
    // members
    protected boolean editMode = false;
    int currentIndex;

    @Autowired
    private CommonContext ctx;

    //methods
    @Override
    public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {

        SimpleExpression creator = Restrictions.eq("creator", ctx.getCreator());
        SimpleExpression notBuried = Restrictions.eq("buried", false);
        LogicalExpression expression;
        if ("zh_CN".equals(ctx.getLocale())) {
            expression = Restrictions.or(Restrictions.and(creator, notBuried), Restrictions.eq("flag", 1));
        }
        else {
            expression = Restrictions.or(Restrictions.and(creator, notBuried), Restrictions.eq("flag", 2));
        }
        this.setCriterion(expression);
        sortField = "createTime";
        sortOrder = SortOrder.DESCENDING;
        List loaded = super.load(first, pageSize, sortField, sortOrder, filters);
        return loaded;
    }

    @Override
    public List load(int first, int pageSize, List multiSortMeta, Map filters) {
        return super.load(first, pageSize, multiSortMeta, filters);
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

}
