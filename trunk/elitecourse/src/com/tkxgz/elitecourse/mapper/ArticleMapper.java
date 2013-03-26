package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tkxgz.elitecourse.bean.Article;

/**
 * 文章映射器内部类，用于返回范型的文章实体
 * 
 * @author Soyi Yao
 */
public class ArticleMapper implements RowMapper<Article> {

	public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
		Article article = new Article();

		article.setId(rs.getString("id".toLowerCase()));
		article.setTitle(rs.getString("title".toLowerCase()));
		article.setColumnId(rs.getString("column_id".toLowerCase()));
		article.setSubTitle(rs.getString("sub_title".toLowerCase()));
		article.setType(rs.getString("type".toLowerCase()));
		article.setContent(rs.getString("content".toLowerCase()));
		article.setOnTop(rs.getString("on_top".toLowerCase()));
		article.setRecommend(rs.getString("recommend".toLowerCase()));
		article.setCreateUserId(rs.getString("create_user_id".toLowerCase()));
		article.setCreateUserName(rs.getString("create_user_name".toLowerCase()));
		article.setCreateTime(rs.getString("create_time".toLowerCase()));
		article.setName(rs.getString("name".toLowerCase()));
		article.setFullPathName(rs.getString("full_path_name".toLowerCase()));
		article.setColumnFullPathName(rs.getString("full_path_name".toLowerCase()));
		article.setColumnName(rs.getString("name".toLowerCase()));

		return article;
	}
}
