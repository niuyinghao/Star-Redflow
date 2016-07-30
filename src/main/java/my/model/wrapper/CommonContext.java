package my.model.wrapper;

import my.model.persist.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by yinghao_niu on 2016/4/24 for Project.
 */

@Component
public class CommonContext implements Serializable {
    User creator; // members
    String locale;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }


}
