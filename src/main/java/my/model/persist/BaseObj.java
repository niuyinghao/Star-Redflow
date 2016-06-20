package my.model.persist;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by niuyinghao on 2016/4/25 for project.
 */
@MappedSuperclass
public class BaseObj {
    private Long id;
    /**
     * 内容
     */
    String content;
    /**
     * 创建时间
     */
    Date createTime;
    /**
     * 创建人
     */
    User creator;


    @Deprecated
    int buryDepth;

    @Transient
    @Deprecated
    @Column(columnDefinition = " number(2,0) default 0")
    public int getBuryDepth() {
        return buryDepth;
    }

    public void setBuryDepth(int buryDepth) {
        this.buryDepth = buryDepth;
    }

    @ManyToOne
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Id
    // @issue strategy
    // identity when table per class cause :
//    Cannot use identity column key generation with <union-subclass> ( TABLE_PER_CLASS )
//    @GeneratedValue(generator = "g")
//    @GenericGenerator(name = "g", strategy = "identity")

    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
