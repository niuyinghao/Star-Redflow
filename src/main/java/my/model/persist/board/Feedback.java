package my.model.persist.board;

import my.model.persist.BaseLog;

import javax.persistence.Entity;

/**
 * Created by yinghao_niu on 2016/4/17 for Project.
 */

@Entity
public class Feedback extends BaseLog {
    String contactInfo;

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
