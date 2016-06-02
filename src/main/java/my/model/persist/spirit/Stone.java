package my.model.persist.spirit;


import my.model.persist.BaseLog;
import my.model.persist.project.HeartSymbol;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by yinghao_niu on 2016/3/23 for Project.
 */
@Entity
public class Stone extends BaseLog {
    boolean isPlaceHolder;
    HeartSymbol heartSymbol;

    @OneToOne
    public HeartSymbol getHeartSymbol() {
        return heartSymbol;
    }

    public void setHeartSymbol(HeartSymbol heartSymbol) {
        this.heartSymbol = heartSymbol;
    }

    @Basic
    public boolean isPlaceHolder() {
        return isPlaceHolder;
    }

    public void setPlaceHolder(boolean placeHolder) {
        isPlaceHolder = placeHolder;
    }
}
