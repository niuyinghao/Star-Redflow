package my.model.wrapper;


import my.model.persist.board.Article;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */

@Component
public class Board extends AreaLazyModel{
	List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
