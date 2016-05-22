package my.model.persist.place;


import my.model.persist.BaseLog;

import javax.persistence.Entity;

/**
 * Created by yinghao_niu on 2016/3/23 for Project.
 */
@Entity
public class Stone extends BaseLog {
    String flag;

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }
}
