package my.dao.hibernate;

import my.dao.ArticleDao;
import my.model.persist.board.Article;
import org.springframework.stereotype.Repository;

/**
 * Created by niuyinghao on 2016/5/12 for project.
 */
@Repository("articleDao")
public class ArticleDaoHibernate extends GenericDaoHibernate<Article, Long> implements ArticleDao {
    public ArticleDaoHibernate() {
        super(Article.class);
    }
}
