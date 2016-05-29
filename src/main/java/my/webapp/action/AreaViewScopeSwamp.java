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
public class AreaViewScopeSwamp implements Serializable {
    DataTable flowDataTable;

    @PostConstruct
    public void init() {
    }

    public DataTable getFlowDataTable() {
        return flowDataTable;
    }

    public void setFlowDataTable(DataTable flowDataTable) {
        this.flowDataTable = flowDataTable;
    }

}

