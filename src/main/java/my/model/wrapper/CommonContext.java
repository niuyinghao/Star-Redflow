package my.model.wrapper;

import my.model.persist.User;
import org.springframework.stereotype.Component;

/**
 * Created by yinghao_niu on 2016/4/24 for Project.
 */

@Component
public class CommonContext  {
	User creator; // members

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}
