package my.model.persist.place;


import my.model.persist.BaseLog;
import my.model.persist.project.Pray;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/3 for Project.
 */
@Entity
public class Wish extends BaseLog {

    Wave belongWave;
    Flower belongFlower;
    public List<Stone> stoneList;
    List<Pray> prays;

    @OneToMany(mappedBy = "wish")
    public List<Pray> getPrays() {
        return prays;
    }

    public void setPrays(List<Pray> prays) {
        this.prays = prays;
    }

    @ManyToOne
    @JoinTable(name = "wish_flower")
    public Flower getBelongFlower() {
        return belongFlower;
    }

    public void setBelongFlower(Flower belongFlower) {
        this.belongFlower = belongFlower;
    }

    @ManyToOne
    @JoinTable(name = "wish_wave")
    public Wave getBelongWave() {
        return belongWave;
    }

    public void setBelongWave(Wave belong) {
        this.belongWave = belong;
    }

    @OneToMany
    public List<Stone> getStoneList() {
        return stoneList;
    }

    public void setStoneList(List<Stone> stoneList) {
        this.stoneList = stoneList;
    }
}
