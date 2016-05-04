package my.model.wrapper;


import my.model.persist.place.Flower;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Component
public class Swamp extends Context{
	List<Flower> flowers;

	public List<Flower> getFlowers() {
		return flowers;
	}

	public void setFlowers(List<Flower> flowers) {
		this.flowers = flowers;
	}
}
