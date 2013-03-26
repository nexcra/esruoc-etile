package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.UserGroup;

/**
	 * 用户组映射器内部类，用于返回范型的用户组实体
	 * 
	 * @author Soyi Yao
	 */
	public class UserGroupMapper implements RowMapper<UserGroup> {

		public UserGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserGroup userGroup = new UserGroup();
			
			userGroup.setId(rs.getString("id".toLowerCase())); 
userGroup.setUserId(rs.getString("user_id".toLowerCase())); 
userGroup.setGroupId(rs.getString("group_id".toLowerCase())); 

			return userGroup;
		}
	}
