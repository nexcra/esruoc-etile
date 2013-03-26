package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Assignment;

/**
	 * 作业映射器内部类，用于返回范型的作业实体
	 * 
	 * @author Soyi Yao
	 */
	public class AssignmentMapper implements RowMapper<Assignment> {

		public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Assignment assignment = new Assignment();
			
			assignment.setId(rs.getString("id".toLowerCase())); 
assignment.setUserId(rs.getString("user_id".toLowerCase())); 
assignment.setUserName(rs.getString("user_name".toLowerCase())); 
assignment.setAssignmentClassId(rs.getString("assignment_class_id".toLowerCase())); 
assignment.setSubmitTime(rs.getString("submit_time".toLowerCase())); 
assignment.setClassesId(rs.getString("classes_id".toLowerCase())); 
assignment.setDescription(rs.getString("description".toLowerCase())); 
assignment.setContent(rs.getString("content".toLowerCase())); 
assignment.setPath(rs.getString("path".toLowerCase())); 
assignment.setGrade(rs.getString("grade".toLowerCase())); 
assignment.setReContent(rs.getString("re_content".toLowerCase())); 
assignment.setReTime(rs.getString("re_time".toLowerCase())); 

			return assignment;
		}
	}
