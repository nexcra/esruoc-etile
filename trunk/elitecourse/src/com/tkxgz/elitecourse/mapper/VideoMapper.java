package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tkxgz.elitecourse.bean.Video;

/**
 * 视频映射器内部类，用于返回范型的视频实体
 * 
 * @author Soyi Yao
 */
public class VideoMapper implements RowMapper<Video> {

	public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
		Video video = new Video();

		video.setId(rs.getString("id".toLowerCase()));
		video.setName(rs.getString("name".toLowerCase()));
		video.setSource(rs.getString("source".toLowerCase()));
		video.setType(rs.getString("type".toLowerCase()));
		video.setDescription(rs.getString("description".toLowerCase()));
		video.setPath(rs.getString("path".toLowerCase()));
		video.setCreateUserId(rs.getString("create_user_id".toLowerCase()));
		video.setCreateUserName(rs.getString("create_user_name".toLowerCase()));
		video.setCreateTime(rs.getString("create_time".toLowerCase()));
		video.setRandomName(rs.getString("random_name".toLowerCase()));

		return video;
	}
}
