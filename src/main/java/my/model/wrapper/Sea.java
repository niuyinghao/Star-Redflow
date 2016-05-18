package my.model.wrapper;


import my.model.persist.place.Wave;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Component
public class Sea  extends Place {

	@Override
	public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
		return super.load(first, pageSize, sortField, sortOrder, filters);
	}

	@Override
	public List load(int first, int pageSize, List multiSortMeta, Map filters) {
		return super.load(first, pageSize, multiSortMeta, filters);
	}

	List<Wave> waves;

	public List<Wave> getWaves() {
		return waves;
	}

	public void setWaves(List<Wave> waves) {
		this.waves = waves;
	}
}
