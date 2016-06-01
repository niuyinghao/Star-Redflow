package my.model.wrapper;


import my.model.persist.spirit.Stone;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */

@Component
public class Hillock extends Place {
	List<Stone> stones;

    @Override
    public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
        List load = super.load(first, pageSize, sortField, sortOrder, filters);
        if (load == null || load.size() == 0) {
            ArrayList arrayList = new ArrayList();
            Stone e = new Stone();
//            e.setPlaceHolder(true);
            arrayList.add(e);
            setRowCount(1);
            return arrayList;
        }
        return load;
    }

    public List<Stone> getStones() {
		return stones;
	}

	public void setStones(List<Stone> stones) {
		this.stones = stones;
	}
}
