package my.model.persist.place;


import my.model.persist.BaseLog;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by yinghao_niu on 2016/3/23 for Project.
 */
@Entity
public class Flower extends BaseLog {
// members
	List<Wish> wishes;
	Mound mound;
	Date moundTime;

//getter and setter
	@ManyToOne
	public Mound getMound() {
		return mound;
	}

	public void setMound(Mound mound) {
		this.mound = mound;
	}

	public Date getMoundTime() {
		return moundTime;
	}

	public void setMoundTime(Date moundTime) {
		this.moundTime = moundTime;
	}

	@OneToMany(mappedBy = "belongFlower")
	public List<Wish> getWishes() {
		return wishes;
	}

	public void setWishes(List<Wish> wishes) {
		this.wishes = wishes;
	}
}
