package my.model.persist.spirit;


import my.model.persist.BaseLog;
import my.model.persist.BaseObj;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by yinghao_niu on 2016/3/23 for Project.
 */
@Entity
public class Mound extends BaseObj{
// members
	List<BaseLog> targets;
    int toolIndex=0;


    public int getToolIndex() {
        return toolIndex;
    }

    public void setToolIndex(int toolIndex) {
        this.toolIndex = toolIndex;
    }

    //getter and setter
//	@OneToMany
    @Transient
	public List<BaseLog> getTargets() {
		return targets;
	}

	public void setTargets(List<BaseLog> targets) {
		this.targets = targets;
	}
}

