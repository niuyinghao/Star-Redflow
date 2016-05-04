package my.model.persist.project;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by yinghao_niu on 2016/3/23 for Project.
 */
@Entity
public class Music {
    @Id
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
