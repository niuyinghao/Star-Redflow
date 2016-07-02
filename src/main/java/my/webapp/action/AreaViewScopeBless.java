package my.webapp.action;

import org.primefaces.component.datalist.DataList;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */


@Component
@Scope("view")
public class AreaViewScopeBless implements Serializable {


    DataList wishDataList;

    public DataList getWishDataList() {
        return wishDataList;
    }

    public void setWishDataList(DataList wishDataList) {
        this.wishDataList = wishDataList;
    }


}

