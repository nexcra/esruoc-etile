package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.GroupPrivilege;

/**
	 * 组权限关联映射器内部类，用于返回范型的组权限关联实体
	 * 
	 * @author Soyi Yao
	 */
	public class GroupPrivilegeMapper implements RowMapper<GroupPrivilege> {

		public GroupPrivilege mapRow(ResultSet rs, int rowNum) throws SQLException {
			GroupPrivilege groupPrivilege = new GroupPrivilege();
			
			groupPrivilege.setId(rs.getString("id".toLowerCase())); 
groupPrivilege.setGroupId(rs.getString("group_id".toLowerCase())); 
groupPrivilege.setPrivilegeId(rs.getString("privilege_id".toLowerCase())); 

			return groupPrivilege;
		}
	}
