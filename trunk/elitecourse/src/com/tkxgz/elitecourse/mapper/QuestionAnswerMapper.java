package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.QuestionAnswer;

/**
	 * 答疑映射器内部类，用于返回范型的答疑实体
	 * 
	 * @author Soyi Yao
	 */
	public class QuestionAnswerMapper implements RowMapper<QuestionAnswer> {

		public QuestionAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
			QuestionAnswer questionAnswer = new QuestionAnswer();
			
			questionAnswer.setId(rs.getString("id".toLowerCase())); 
questionAnswer.setTitle(rs.getString("title".toLowerCase())); 
questionAnswer.setContent(rs.getString("content".toLowerCase())); 
questionAnswer.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
questionAnswer.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 
questionAnswer.setCreateTime(rs.getString("create_time".toLowerCase())); 
questionAnswer.setCreateUserIp(rs.getString("create_user_ip".toLowerCase())); 
questionAnswer.setReContent(rs.getString("re_content".toLowerCase())); 
questionAnswer.setReUserId(rs.getString("re_user_id".toLowerCase())); 
questionAnswer.setReUserName(rs.getString("re_user_name".toLowerCase())); 
questionAnswer.setReTime(rs.getString("re_time".toLowerCase())); 

			return questionAnswer;
		}
	}
