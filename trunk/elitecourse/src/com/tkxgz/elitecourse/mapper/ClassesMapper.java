package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Classes;

/**
	 * 班级映射器内部类，用于返回范型的班级实体
	 * 
	 * @author Soyi Yao
	 */
	public class ClassesMapper implements RowMapper<Classes> {

		public Classes mapRow(ResultSet rs, int rowNum) throws SQLException {
			Classes classes = new Classes();
			
			classes.setId(rs.getString("id".toLowerCase())); 
classes.setName(rs.getString("name".toLowerCase())); 
classes.setOrderNumber(rs.getString("order_number".toLowerCase())); 
classes.setRemark(rs.getString("remark".toLowerCase())); 
classes.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 
classes.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
classes.setCreateTime(rs.getString("create_time".toLowerCase())); 

			return classes;
		}
	}
