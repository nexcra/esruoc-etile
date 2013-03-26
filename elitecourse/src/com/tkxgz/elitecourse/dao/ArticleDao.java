package com.tkxgz.elitecourse.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkxgz.elitecourse.bean.Article;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.ListUtil;
import com.tkxgz.elitecourse.dao.base.ArticleBaseDao;
import com.tkxgz.elitecourse.mapper.ArticleMapper;

/**
 * 文章Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class ArticleDao extends ArticleBaseDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 根据文章查询文章信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        文章id
	 * @return 文章Map
	 */
	@Override
	public Map getArticleById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.*, a.name, a.full_path_name from  t_article t , t_column a   ");
		sql.append("where 1=1   ");
		sql.append("and t.column_id = a.id ");
		sql.append("and t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 分页查询文章列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 文章分页数据
	 */
	public Page listArticle(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* , a.name, a.full_path_name from  t_article t , t_column a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and t.column_id = a.id ");

		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 分页搜索文章列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param article
	 *        文章bean
	 * @return 分页文章列表
	 */
	@Override
	public Page searchArticle(Page page, Article article) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* , a.name, a.full_path_name from  t_article t , t_column a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and t.column_id = a.id ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(article.getTitle())) {
			sql.append("and t.title like '%" + article.getTitle() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(article.getColumnId()))
				&& (!"all".equalsIgnoreCase(article.getColumnId()))) {
			sql.append(" and t.column_id = ? ");
			valuesList.add(Integer.valueOf(article.getColumnId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(article.getSubTitle())) {
			sql.append("and t.sub_title like '%" + article.getSubTitle() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(article.getType()))
				&& (!"all".equalsIgnoreCase(article.getType()))) {
			sql.append(" and t.type = ? ");
			valuesList.add(article.getType());
			valueTypesList.add(Types.VARCHAR);
		}
		if (StringUtils.isNotEmpty(article.getContent())) {
			sql.append("and t.content like '%" + article.getContent() + "%'");
		}
		if (StringUtils.isNotEmpty(article.getOnTop())) {
			sql.append("and t.on_top like '%" + article.getOnTop() + "%'");
		}
		if (StringUtils.isNotEmpty(article.getRecommend())) {
			sql.append("and t.recommend like '%" + article.getRecommend()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(article.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ article.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(article.getCreateTime())) {
			sql.append("and t.create_time like '%" + article.getCreateTime()
					+ "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * 搜索文章列表
	 * 
	 * @author Soyi Yao
	 * @param article
	 *        Article实体
	 * @return 文章列表
	 */
	public List<Article> searchArticleForList(Article article) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* , a.name, a.full_path_name from  t_article t , t_column a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and t.column_id = a.id ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(article.getTitle())) {
			sql.append("and t.title like '%" + article.getTitle() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(article.getColumnId()))
				&& (!"all".equalsIgnoreCase(article.getColumnId()))) {
			sql.append(" and t.column_id = ? ");
			valuesList.add(Integer.valueOf(article.getColumnId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(article.getSubTitle())) {
			sql.append("and t.sub_title like '%" + article.getSubTitle() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(article.getType()))
				&& (!"all".equalsIgnoreCase(article.getType()))) {
			sql.append(" and t.type = ? ");
			valuesList.add(article.getType());
			valueTypesList.add(Types.VARCHAR);
		}
		if (StringUtils.isNotEmpty(article.getContent())) {
			sql.append("and t.content like '%" + article.getContent() + "%'");
		}
		if (StringUtils.isNotEmpty(article.getOnTop())) {
			sql.append("and t.on_top like '%" + article.getOnTop() + "%'");
		}
		if (StringUtils.isNotEmpty(article.getRecommend())) {
			sql.append("and t.recommend like '%" + article.getRecommend()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(article.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ article.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(article.getCreateTime())) {
			sql.append("and t.create_time like '%" + article.getCreateTime()
					+ "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new ArticleMapper());
	}

	/**
	 * @author Soyi Yao
	 * @param columnId
	 * @return
	 */
	public Page listArticleByColumnId(String columnId, Page page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* , a.name, a.full_path_name from  t_article t , t_column a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and t.column_id = a.id ");
		sql.append(" and t.column_id = ? ");

		sql.append(" order by t.id desc ");

		Object[] values = { Integer.parseInt(columnId) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
}
