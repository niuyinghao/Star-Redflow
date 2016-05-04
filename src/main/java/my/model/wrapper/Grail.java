package my.model.wrapper;

import my.model.persist.project.Pray;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Component
public class Grail extends Context {
	List<Pray> prays;

	public List<Pray> getPrays() {
		return prays;
	}

	public void setPrays(List<Pray> prays) {
		this.prays = prays;
	}
}
