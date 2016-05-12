package my.dao.hibernate;

import my.dao.FeedbackDao;
import my.model.persist.board.Feedback;
import org.springframework.stereotype.Repository;

/**
 * Created by niuyinghao on 2016/5/12 for project.
 */
@Repository("feedbackDao")
public class FeedbackDaoHibernate extends GenericDaoHibernate<Feedback, Long> implements FeedbackDao {
    public FeedbackDaoHibernate() {
        super(Feedback.class);
    }
}
