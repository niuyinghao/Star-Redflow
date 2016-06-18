package my.model.persist.spirit;


import my.model.persist.BaseLog;
import my.model.persist.project.HeartSymbol;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by yinghao_niu on 2016/3/20 for Project.
 */
@Entity
public class Wave extends BaseLog {

    List<Wish> wishes;
    Mound mound;
    Date moundTime;
    HeartSymbol heartSymbol;

    @OneToMany(mappedBy = "belongWave", fetch = FetchType.EAGER)
    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wish> wishList) {
        this.wishes = wishList;
    }

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

    @OneToOne(mappedBy = "belong")
    public HeartSymbol getHeartSymbol() {
        return heartSymbol;
    }

    public void setHeartSymbol(HeartSymbol heartSymbol) {
        this.heartSymbol = heartSymbol;
    }

}
