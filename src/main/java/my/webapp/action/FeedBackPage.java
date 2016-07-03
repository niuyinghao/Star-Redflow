package my.webapp.action;

import com.sun.jndi.cosnaming.RemoteToCorba;
import my.model.persist.board.Feedback;
import my.model.wrapper.CommonContext;
import my.service.FeedbackManager;
import my.service.impl.FeedbackManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yinghao_niu on 2016/7/3 for project.
 */
@Component
@Scope("request")
public class FeedBackPage implements Serializable{

    Feedback feedback = new Feedback();

    @Autowired
    private FeedbackManager feedbackManager;
    @Autowired
    private CommonContext context;

    String retCode;

    public void saveFeedBack(Feedback feedback) {
        try {
            if (feedback == null) {
                retCode = "0";
                return;
            }
            if ((feedback.getContent() == null || feedback.getContent().equals(""))
                    &&
                    (feedback.getContactInfo() == null || feedback.getContactInfo().equals(""))) {
                retCode = "0";
                return;
            }
            feedback.setCreateTime(new Date());
            if (context != null && context.getCreator() != null) {
                feedback.setCreator(context.getCreator());
            }
            feedbackManager.save(feedback);
            retCode = "1";
            return;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            retCode = "-1";
            return;
        }
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
