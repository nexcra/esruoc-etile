package com.tkxwz.esruocetile.webapp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.dao.ArticleDao;
import com.tkxwz.esruocetile.webapp.entity.Article;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:14:39
 */
@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	public Page listArticle(Page page) {
		return this.articleDao.listArticle(page);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 12:51:56
	 * @param article
	 */
	public int addArticle(Article article) {
		return this.articleDao.addArticle(article);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:12:54
	 * @param ids
	 */
	public int deleteArticleById(String id) {
		return this.articleDao.deleteArticleById(id);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:14:08
	 * @param ids
	 */
	public int batchDeleteArticle(String[] ids) {
		int result = 0;
		for (String id : ids) {
			this.deleteArticleById(id);
			result++;
		}
		return result;

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:55:43
	 * @param id
	 */
	public Map getArticleById(String id) {
		return this.articleDao.getArticleById(id);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 23:25:01
	 * @param article
	 */
	public int updateArticle(Article article) {
		return this.articleDao.updateArticle(article);

	}

}
