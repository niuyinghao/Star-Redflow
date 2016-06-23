package my.model.persist;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by yinghao_niu on 2016/3/23 for Project.
 */
@Entity
@Repository
@Scope("session")
public class Context implements Serializable {
    // members
    @Id
    @GeneratedValue
    Long id;
    @OneToOne
    @JoinColumn(name = "userx")
    User user;


    //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
