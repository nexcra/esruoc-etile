package com.tkxwz.esruocetile.webapp.dao;

import java.sql.Types;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.entity.Article;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:12:25
 */
@Repository
public class ArticleDao extends BaseDao<Article> {

	public Page listArticle(Page page) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.id, ");
		sql.append(" a.title, ");
		sql.append(" a.sub_title, ");
		sql.append(" b.column_name, ");
		sql.append(" a.insert_time, ");
		sql.append(" a.hit_count ");
		sql.append(" FROM t_article a, t_column b ");
		sql.append(" WHERE a.column_id = b.id ");

		return this.queryForPage(sql.toString(), page);
	}

	public int addArticle(Article article) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_article  ");
		sql.append(" ( title, sub_title, column_id, content,author, keywords, hit_count,insert_time) ");
		sql.append(" values ");
		sql.append(" ( ?, ?, ?, ?, ?, ? ,now()) ");
		Object[] values = { article.getTitle(), article.getSubTitle(), article.getColumnId(),
				article.getContent(), article.getAuthor(),
				article.getKeywords(), article.getHitCount() };
		int[] valueTypes = { Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:13:11
	 * @param ids
	 */
	public int deleteArticleById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_article ");
		sql.append(" where id = ?  ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:56:00
	 * @param i
	 */
	public Map getArticleById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.id, t.title, t.content,t.author, t.keywords, t.hit_count ");
		sql.append(" from t_article t where t.id = ? ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForMap(sql.toString(), values, valueTypes);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 23:25:24
	 * @param article
	 * @return
	 */
	public int updateArticle(Article article) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_article t");
		sql.append(" set t.title = ? , ");
		sql.append("     t.content = ? ,  ");
		sql.append("     t.author = ? ,  ");
		sql.append("     t.keywords = ? ,  ");
		sql.append("     t.hit_count = ?   ");
		sql.append(" where t.id = ? ");

		Object[] values = { article.getTitle(), article.getContent(),
				article.getAuthor(), article.getKeywords(),
				article.getHitCount(), article.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER, Types.INTEGER };
		return this.update(sql.toString(), values, valueTypes);
	}

}
