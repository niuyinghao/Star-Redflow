package my.webapp.action;

import my.service.WishManager;
import org.primefaces.component.datatable.DataTable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import java.io.Serializable;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */


@Component
@Scope("view")
public class AreaViewScopeHillock implements Serializable {


    DataTable stoneDataTable;

    public DataTable getStoneDataTable() {
        return stoneDataTable;
    }

    public void setStoneDataTable(DataTable stoneDataTable) {
        this.stoneDataTable = stoneDataTable;
    }


}

