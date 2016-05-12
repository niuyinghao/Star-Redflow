package my.service.impl;

import my.dao.FeedbackDao;
import my.model.persist.board.Feedback;
import my.service.FeedbackManager;
import org.springframework.stereotype.Service;

/**
 * Created by niuyinghao on 2016/5/12 for project.
 */
@Service("FeedbackManager")
public class FeedbackManagerImpl extends GenericManagerImpl<Feedback, Long> implements FeedbackManager {
    FeedbackManagerImpl(FeedbackDao dao) {
        super(dao);
    }
}
