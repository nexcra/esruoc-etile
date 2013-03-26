package com.tkxgz.elitecourse.dao.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.ListUtil;
import com.tkxgz.elitecourse.bean.Video;
import com.tkxgz.elitecourse.mapper.VideoMapper;

/**
 * 视频Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class VideoBaseDao extends BaseDao<Video> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询视频列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 视频分页数据
	 */
	public Page listVideo(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_video t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加视频
	 * 
	 * @author Soyi Yao
	 * @param video
	 *        视频bean
	 * @return 影响行数
	 */
	public int addVideo(Video video) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_video (");
		sql.append(" name , ");
		sql.append(" source , ");
		sql.append(" type , ");
		sql.append(" description , ");
		sql.append(" path , ");
		sql.append(" create_user_id , ");
		sql.append(" create_user_name , ");
		sql.append(" create_time  ");
		sql.append(" ) ");
		sql.append(" values ( ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" now()  ");

		sql.append(" ) ");
		Object[] values = { video.getName(), video.getSource(),
				video.getType(), video.getDescription(), video.getPath(),
				video.getCreateUserId(), video.getCreateUserName() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据视频id删除视频
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        视频id
	 * @return 影响行数
	 */
	public int deleteVideoById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_video ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据视频查询视频信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        视频id
	 * @return 视频Map
	 */
	public Map getVideoById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_video t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新视频
	 * 
	 * @author Soyi Yao
	 * @param video
	 *        视频bean
	 * @return 影响行数
	 */
	public int updateVideo(Video video) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_video t");
		sql.append(" set   ");
		sql.append(" t.name = ? , ");
		sql.append(" t.source = ? , ");
		sql.append(" t.type = ? , ");
		sql.append(" t.description = ? , ");
		sql.append(" t.path = ? , ");
		sql.append(" t.create_user_id = ? , ");
		sql.append(" t.create_user_name = ? , ");
		sql.append(" t.create_time = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { video.getName(), video.getSource(),
				video.getType(), video.getDescription(), video.getPath(),
				video.getCreateUserId(), video.getCreateUserName(),
				video.getCreateTime(), video.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER };

		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * 分页搜索视频列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param video
	 *        视频bean
	 * @return 分页视频列表
	 */
	public Page searchVideo(Page page, Video video) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_video t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(video.getName())) {
			sql.append("and t.name like '%" + video.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(video.getSource())) {
			sql.append("and t.source like '%" + video.getSource() + "%'");
		}
		if (StringUtils.isNotEmpty(video.getType())) {
			sql.append("and t.type like '%" + video.getType() + "%'");
		}
		if (StringUtils.isNotEmpty(video.getDescription())) {
			sql.append("and t.description like '%" + video.getDescription()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(video.getPath())) {
			sql.append("and t.path like '%" + video.getPath() + "%'");
		}
		if (StringUtils.isNotEmpty(video.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ video.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(video.getCreateTime())) {
			sql.append("and t.create_time like '%" + video.getCreateTime()
					+ "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * 搜索视频列表
	 * 
	 * @author Soyi Yao
	 * @param video
	 *        Video实体
	 * @return 视频列表
	 */
	public List<Video> searchVideoForList(Video video) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_video t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(video.getName())) {
			sql.append("and t.name like '%" + video.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(video.getSource())) {
			sql.append("and t.source like '%" + video.getSource() + "%'");
		}
		if (StringUtils.isNotEmpty(video.getType())) {
			sql.append("and t.type like '%" + video.getType() + "%'");
		}
		if (StringUtils.isNotEmpty(video.getDescription())) {
			sql.append("and t.description like '%" + video.getDescription()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(video.getPath())) {
			sql.append("and t.path like '%" + video.getPath() + "%'");
		}
		if (StringUtils.isNotEmpty(video.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ video.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(video.getCreateTime())) {
			sql.append("and t.create_time like '%" + video.getCreateTime()
					+ "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new VideoMapper());
	}

	/**
	 * 根据视频id获取视频实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        视频id
	 * @return Video实体
	 */
	public Video findVideoById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_video t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Video video = this.queryForObject(sql.toString(), values, valueTypes,
				new VideoMapper());

		logger.info(sql.toString());

		return video;
	}

	/**
	 * 列出所有视频
	 * 
	 * @author Soyi Yao
	 * @return 视频列表
	 */
	public List<Video> listAllVideo() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_video t  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new VideoMapper());
	}

	/**
	 * 获取t_video表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_video表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_video  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

	/**
	 * 获取t_video当前最大id值
	 * 
	 * @author Soyi Yao
	 * @return
	 */
	public int getCurrentMaxId() {
		StringBuilder sql = new StringBuilder();
		sql.append("select max(id) from  t_video  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

}
