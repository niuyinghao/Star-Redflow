package my.service.impl;

import my.dao.ArticleDao;
import my.model.persist.board.Article;
import my.service.ArticleManager;
import org.springframework.stereotype.Service;

/**
 * Created by niuyinghao on 2016/5/12 for project.
 */
@Service("articleManager")
public class ArticleManagerImpl extends GenericManagerImpl<Article, Long> implements ArticleManager {
    ArticleManagerImpl(ArticleDao dao) {
        super(dao);
    }
}
