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
public class AreaViewScopeSea implements Serializable {

    DataTable waveDataTable;

    @PostConstruct
    public void init() {
    }

    public DataTable getWaveDataTable() {
        return waveDataTable;
    }

    public void setWaveDataTable(DataTable waveDataTable) {
        this.waveDataTable = waveDataTable;
    }

}

