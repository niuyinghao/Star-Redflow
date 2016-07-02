package my.model.persist.project;

import my.model.persist.spirit.Wish;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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
