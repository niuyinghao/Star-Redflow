package my.service.impl;

import my.dao.FeedbackDao;
import my.model.persist.board.Feedback;
import my.service.FeedbackManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by niuyinghao on 2016/5/12 for project.
 */
@Service("feedbackManager")
public class FeedbackManagerImpl extends GenericManagerImpl<Feedback, Long> implements FeedbackManager {
    @Autowired
    public FeedbackManagerImpl(FeedbackDao dao) {
        super(dao);
    }
}
