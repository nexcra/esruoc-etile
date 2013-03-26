package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Privilege;

/**
	 * 权限映射器内部类，用于返回范型的权限实体
	 * 
	 * @author Soyi Yao
	 */
	public class PrivilegeMapper implements RowMapper<Privilege> {

		public Privilege mapRow(ResultSet rs, int rowNum) throws SQLException {
			Privilege privilege = new Privilege();
			
			privilege.setId(rs.getString("id".toLowerCase())); 
privilege.setPrivilegeCode(rs.getString("privilege_code".toLowerCase())); 
privilege.setName(rs.getString("name".toLowerCase())); 
privilege.setStatus(rs.getString("status".toLowerCase())); 
privilege.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 
privilege.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
privilege.setCreateTime(rs.getString("create_time".toLowerCase())); 
privilege.setRemark(rs.getString("remark".toLowerCase())); 

			return privilege;
		}
	}
