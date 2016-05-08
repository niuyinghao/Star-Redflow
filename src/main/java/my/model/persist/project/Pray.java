package my.model.persist.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yinghao_niu on 2016/3/23 for Project.
 */
@Entity
public class Pray implements Serializable{
    // members
    @Id
    @GeneratedValue
    Long id;

    Date createTime;


//    Wish wish;

//    @ManyToOne
//    @JoinTable
//    public Wish getWish() {
//        return wish;
//    }
//
//    public void setWish(Wish wish) {
//        this.wish = wish;
//    }

    //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
