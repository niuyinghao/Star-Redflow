package my.model.persist.place;


import my.model.persist.BaseLog;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created by yinghao_niu on 2016/3/23 for Project.
 */
@Entity
public class Stone extends BaseLog {
    boolean isPlaceHolder;

    @Basic
    public boolean isPlaceHolder() {
        return isPlaceHolder;
    }

    public void setPlaceHolder(boolean placeHolder) {
        isPlaceHolder = placeHolder;
    }
}
