package my.model.persist;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by yinghao_niu on 2016/3/23 for Project.
 */


/*
 * generated sql id not auto increment
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
*/

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISCRIMINATOR_COLUMN")
public class BaseLog extends BaseObj implements Serializable {
// members

    /**
     * 标识字串,如 STAR#..
     */
    String sign;

    /**
     * 标识符
     * 0 系统创建
     */
    int flag;

    //getter and setter
    @Column(columnDefinition = " INT DEFAULT 0 ")
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Lob
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
