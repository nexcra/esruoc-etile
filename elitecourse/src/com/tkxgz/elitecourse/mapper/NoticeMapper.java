package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Notice;

/**
	 * 公告映射器内部类，用于返回范型的公告实体
	 * 
	 * @author Soyi Yao
	 */
	public class NoticeMapper implements RowMapper<Notice> {

		public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
			Notice notice = new Notice();
			
			notice.setId(rs.getString("id".toLowerCase())); 
notice.setTitle(rs.getString("title".toLowerCase())); 
notice.setContent(rs.getString("content".toLowerCase())); 
notice.setCreateTime(rs.getString("create_time".toLowerCase())); 
notice.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 
notice.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 

			return notice;
		}
	}
