package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Friendsite;

/**
	 * 友情链接映射器内部类，用于返回范型的友情链接实体
	 * 
	 * @author Soyi Yao
	 */
	public class FriendsiteMapper implements RowMapper<Friendsite> {

		public Friendsite mapRow(ResultSet rs, int rowNum) throws SQLException {
			Friendsite friendsite = new Friendsite();
			
			friendsite.setId(rs.getString("id".toLowerCase())); 
friendsite.setName(rs.getString("name".toLowerCase())); 
friendsite.setType(rs.getString("type".toLowerCase())); 
friendsite.setLink(rs.getString("link".toLowerCase())); 
friendsite.setDescription(rs.getString("description".toLowerCase())); 
friendsite.setCreateTime(rs.getString("create_time".toLowerCase())); 
friendsite.setPicPath(rs.getString("pic_path".toLowerCase())); 
friendsite.setOrderNumber(rs.getString("order_number".toLowerCase())); 
friendsite.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
friendsite.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 

			return friendsite;
		}
	}
