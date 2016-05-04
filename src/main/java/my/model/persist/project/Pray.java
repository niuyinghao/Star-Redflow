package my.model.persist.project;

import my.model.persist.place.Wish;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by yinghao_niu on 2016/3/23 for Project.
 */
@Entity
public class Pray {
    // members
    @Id
    @GeneratedValue
    Long id;

    Date createTime;


    Wish wish;

    @ManyToOne
    @JoinColumn
    public Wish getWish() {
        return wish;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }

    //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
