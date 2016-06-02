package my.model.wrapper;


import my.model.persist.spirit.Flower;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Component
public class Swamp extends Place {
	List<Flower> flowers;

	public List<Flower> getFlowers() {
		return flowers;
	}

	public void setFlowers(List<Flower> flowers) {
		this.flowers = flowers;
	}
}
