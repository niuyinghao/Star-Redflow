package my.model.wrapper;


import my.model.persist.place.Mound;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Component
public class Plain extends Place {
	List<Mound> mounds;

	public List<Mound> getMounds() {
		return mounds;
	}

	@Override
	public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
		List loaded = super.load(first, pageSize, sortField, sortOrder, filters);
		if (loaded == null) {
			loaded = new ArrayList();
		}
		loaded.add(new Mound());
		setRowCount(getRowCount()+1);
		return loaded;
	}

	public void setMounds(List<Mound> mounds) {
		this.mounds = mounds;
	}
}
