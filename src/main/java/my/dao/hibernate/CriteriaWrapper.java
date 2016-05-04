package my.dao.hibernate;

import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/4 for Project.
 */
public class CriteriaWrapper {
	int start;
	int pageSize;
	List orders;
	Criterion criterion;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getOrders() {
		return orders;
	}

	public void setOrders(List orders) {
		this.orders = orders;
	}

	public Criterion getCriterion() {
		return criterion;
	}

	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}
}
