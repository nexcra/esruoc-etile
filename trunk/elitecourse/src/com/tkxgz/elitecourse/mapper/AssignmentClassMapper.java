package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.AssignmentClass;

/**
	 * 作业分类映射器内部类，用于返回范型的作业分类实体
	 * 
	 * @author Soyi Yao
	 */
	public class AssignmentClassMapper implements RowMapper<AssignmentClass> {

		public AssignmentClass mapRow(ResultSet rs, int rowNum) throws SQLException {
			AssignmentClass assignmentClass = new AssignmentClass();
			
			assignmentClass.setId(rs.getString("id".toLowerCase())); 
assignmentClass.setName(rs.getString("name".toLowerCase())); 
assignmentClass.setDescription(rs.getString("description".toLowerCase())); 
assignmentClass.setOrderNumber(rs.getString("order_number".toLowerCase())); 
assignmentClass.setClassesId(rs.getString("classes_id".toLowerCase())); 
assignmentClass.setClassesName(rs.getString("classes_name".toLowerCase())); 
assignmentClass.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
assignmentClass.setStartTime(rs.getString("start_time".toLowerCase())); 
assignmentClass.setEndTime(rs.getString("end_time".toLowerCase())); 
assignmentClass.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 
assignmentClass.setCreateTime(rs.getString("create_time".toLowerCase())); 

			return assignmentClass;
		}
	}
