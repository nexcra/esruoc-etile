package com.tkxgz.elitecourse.dao.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.ListUtil;
import com.tkxgz.elitecourse.bean.Article;
import com.tkxgz.elitecourse.mapper.ArticleMapper;

/**
 * 文章Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class ArticleBaseDao extends BaseDao<Article> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

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
		sql.append(" select t.* ");
		sql.append(" from t_article t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加文章
	 * 
	 * @author Soyi Yao
	 * @param article
	 *        文章bean
	 * @return 影响行数
	 */
	public int addArticle(Article article) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_article (");
		sql.append(" title , ");
		sql.append(" column_id , ");
		sql.append(" sub_title , ");
		sql.append(" type , ");
		sql.append(" content , ");
		sql.append(" on_top , ");
		sql.append(" recommend , ");
		sql.append(" create_user_id , ");
		sql.append(" create_user_name , ");
		sql.append(" create_time  ");
		sql.append(" ) ");
		sql.append(" values ( ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" now()  ");

		sql.append(" ) ");
		Object[] values = { article.getTitle(), article.getColumnId(),
				article.getSubTitle(), article.getType(), article.getContent(),
				article.getOnTop(), article.getRecommend(),
				article.getCreateUserId(), article.getCreateUserName() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据文章id删除文章
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        文章id
	 * @return 影响行数
	 */
	public int deleteArticleById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_article ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据文章查询文章信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        文章id
	 * @return 文章Map
	 */
	public Map getArticleById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_article t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新文章
	 * 
	 * @author Soyi Yao
	 * @param article
	 *        文章bean
	 * @return 影响行数
	 */
	public int updateArticle(Article article) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_article t");
		sql.append(" set   ");
		sql.append(" t.title = ? , ");
		sql.append(" t.column_id = ? , ");
		sql.append(" t.sub_title = ? , ");
		sql.append(" t.type = ? , ");
		sql.append(" t.content = ? , ");
		sql.append(" t.on_top = ? , ");
		sql.append(" t.recommend = ? , ");
		sql.append(" t.create_user_id = ? , ");
		sql.append(" t.create_user_name = ? , ");
		sql.append(" t.create_time = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { article.getTitle(), article.getColumnId(),
				article.getSubTitle(), article.getType(), article.getContent(),
				article.getOnTop(), article.getRecommend(),
				article.getCreateUserId(), article.getCreateUserName(),
				article.getCreateTime(), article.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };

		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
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
	public Page searchArticle(Page page, Article article) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_article t ");
		sql.append(" where 1 = 1 ");

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
		if (StringUtils.isNotEmpty(article.getType())) {
			sql.append("and t.type like '%" + article.getType() + "%'");
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
		sql.append(" select t.* from  t_article t ");
		sql.append(" where 1 = 1 ");

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
		if (StringUtils.isNotEmpty(article.getType())) {
			sql.append("and t.type like '%" + article.getType() + "%'");
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
	 * 根据文章id获取文章实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        文章id
	 * @return Article实体
	 */
	public Article findArticleById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_article t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Article article = this.queryForObject(sql.toString(), values,
				valueTypes, new ArticleMapper());

		logger.info(sql.toString());

		return article;
	}

	/**
	 * 列出所有文章
	 * 
	 * @author Soyi Yao
	 * @return 文章列表
	 */
	public List<Article> listAllArticle() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, a.name, a.id, a.full_path_name ");
		sql.append(" from t_article t , t_column a  ");
		sql.append(" where t.column_id = a.id  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new ArticleMapper());
	}

	/**
	 * 获取t_article表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_article表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_article  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

}
