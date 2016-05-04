package my.model.wrapper;


import my.model.persist.place.Stone;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */

@Component
public class Hillock extends Context{
	List<Stone> stones;

	public List<Stone> getStones() {
		return stones;
	}

	public void setStones(List<Stone> stones) {
		this.stones = stones;
	}
}
