package my.model.persist.project;

import my.model.persist.BaseLog;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Created by niuyinghao on 2016/6/2 for project.
 */
@Entity
public class HeartSymbol {
    private Long id;
    int age;

    BaseLog belong;

    @OneToOne
    public BaseLog getBelong() {
        return belong;
    }

    public void setBelong(BaseLog belong) {
        this.belong = belong;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public enum Kind {wave, flower, stone}
}

