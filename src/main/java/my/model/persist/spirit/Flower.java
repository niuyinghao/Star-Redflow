package my.model.persist.spirit;


import my.model.persist.BaseLog;
import my.model.persist.project.HeartSymbol;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
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
    HeartSymbol heartSymbol;

    @OneToOne(fetch = FetchType.EAGER,mappedBy = "belong")
    @Fetch(FetchMode.JOIN)
    public HeartSymbol getHeartSymbol() {
        return heartSymbol;
    }

    public void setHeartSymbol(HeartSymbol heartSymbol) {
        this.heartSymbol = heartSymbol;
    }

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

//	@OneToMany(mappedBy = "belongFlower")
    @Transient
	public List<Wish> getWishes() {
		return wishes;
	}

	public void setWishes(List<Wish> wishes) {
		this.wishes = wishes;
	}
}
