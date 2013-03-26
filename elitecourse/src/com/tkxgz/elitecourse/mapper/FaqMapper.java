package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Faq;

/**
	 * 常见问题映射器内部类，用于返回范型的常见问题实体
	 * 
	 * @author Soyi Yao
	 */
	public class FaqMapper implements RowMapper<Faq> {

		public Faq mapRow(ResultSet rs, int rowNum) throws SQLException {
			Faq faq = new Faq();
			
			faq.setId(rs.getString("id".toLowerCase())); 
faq.setQuestion(rs.getString("question".toLowerCase())); 
faq.setAnswer(rs.getString("answer".toLowerCase())); 
faq.setStatus(rs.getString("status".toLowerCase())); 
faq.setReference(rs.getString("reference".toLowerCase())); 
faq.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
faq.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 
faq.setCreateTime(rs.getString("create_time".toLowerCase())); 

			return faq;
		}
	}
