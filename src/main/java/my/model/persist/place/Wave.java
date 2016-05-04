package my.model.persist.place;


import my.model.persist.BaseLog;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by yinghao_niu on 2016/3/20 for Project.
 */
@Entity
public class Wave extends BaseLog {

	List<Wish> wishes;

	@OneToMany (mappedBy = "belongWave",fetch = FetchType.EAGER)
	public List<Wish> getWishes() {
		return wishes;
	}

	public void setWishes(List<Wish> wishList) {
		this.wishes = wishList;
	}

	Mound mound;
	Date moundTime;

	public Date getMoundTime() {
		return moundTime;
	}

	public void setMoundTime(Date moundTime) {
		this.moundTime = moundTime;
	}

	@ManyToOne
	public Mound getMound() {
		return mound;
	}

	public void setMound(Mound bury) {
		this.mound = bury;
	}

}
