package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Group;

/**
	 * 组映射器内部类，用于返回范型的组实体
	 * 
	 * @author Soyi Yao
	 */
	public class GroupMapper implements RowMapper<Group> {

		public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
			Group group = new Group();
			
			group.setId(rs.getString("id".toLowerCase())); 
group.setName(rs.getString("name".toLowerCase())); 
group.setOrderNumber(rs.getString("order_number".toLowerCase())); 
group.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 
group.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
group.setCreateTime(rs.getString("create_time".toLowerCase())); 
group.setRemark(rs.getString("remark".toLowerCase())); 

			return group;
		}
	}
