package my.model.wrapper;

import org.hibernate.criterion.Restrictions;
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
		this.setCriterion(Restrictions.eq("creator", ctx.getCreator()));
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
