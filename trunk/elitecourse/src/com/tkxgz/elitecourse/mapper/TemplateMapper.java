package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Template;

/**
	 * 模板映射器内部类，用于返回范型的模板实体
	 * 
	 * @author Soyi Yao
	 */
	public class TemplateMapper implements RowMapper<Template> {

		public Template mapRow(ResultSet rs, int rowNum) throws SQLException {
			Template template = new Template();
			
			template.setId(rs.getString("id".toLowerCase())); 
template.setTemplateName(rs.getString("template_name".toLowerCase())); 
template.setDescription(rs.getString("description".toLowerCase())); 
template.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
template.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 
template.setCreateTime(rs.getString("create_time".toLowerCase())); 
template.setFileName(rs.getString("file_name".toLowerCase())); 
template.setTemplateCode(rs.getString("template_code".toLowerCase())); 
template.setTemplateContent(rs.getString("template_content".toLowerCase())); 

			return template;
		}
	}
