package my.model.wrapper;

import my.dao.hibernate.CriteriaWrapper;
import my.service.AreaManager;
import org.hibernate.criterion.Criterion;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/4/7 for Project.
 */
public abstract class AreaLazyModel extends LazyDataModel {

// members
	Class targetClass;
	@Autowired
	private AreaManager areaManager;
	Criterion criterion;

//methods
	@Override
	public int getRowCount() {
		return super.getRowCount();
	}

	@Override
	public void setRowCount(int rowCount) {
		super.setRowCount(rowCount);
	}

	@Override
	public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
		List sortList = null;
		if (sortField != null) {
			sortList = new ArrayList();
			Map m = new HashMap();
			if (sortOrder == SortOrder.ASCENDING) {
				m.put(sortField, "asc");
			} else if (sortOrder == SortOrder.DESCENDING) {
				m.put(sortField, "desc");
			}
			else{
				m.put(sortField, "desc");
			}
			sortList.add(m);
		}
		return getList(first, pageSize, sortList , criterion);
	}

	@Override
	public List load(int first, int pageSize, List multiSortMeta, Map filters) {
		return getList(first, pageSize, multiSortMeta, criterion);
	}

	private List getList(int first, int pageSize, List multiSortMeta, Criterion criterion) {
		CriteriaWrapper criteriaWrapper = new CriteriaWrapper();
		criteriaWrapper.setStart(first);
		criteriaWrapper.setPageSize(pageSize);
		criteriaWrapper.setOrders(multiSortMeta);
		criteriaWrapper.setCriterion(criterion);

		try {
			List pagedList = areaManager.getPagedList(targetClass, criteriaWrapper);
			setRowCount(areaManager.getCount(targetClass));
			return pagedList;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}

//getter and setter
	public Class getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class targetClass) {
		this.targetClass = targetClass;
	}

	public Criterion getCriterion() {
		return criterion;
	}

	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}
}
