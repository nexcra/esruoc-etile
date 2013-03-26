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
import com.tkxgz.elitecourse.bean.Notice;
import com.tkxgz.elitecourse.mapper.NoticeMapper;
/**
 * 公告Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class NoticeBaseDao extends BaseDao<Notice> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询公告列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 公告分页数据
	 */
	public Page listNotice(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_notice t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加公告
	 * 
	 * @author Soyi Yao
	 * @param notice
	 *        公告bean
	 * @return 影响行数
	 */
	public int addNotice(Notice notice) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_notice (");
		   sql.append(" title , "); 
  sql.append(" content , "); 
  sql.append(" create_time , "); 
  sql.append(" create_user_name , "); 
  sql.append(" create_user_id  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" now() , "); 
  sql.append(" ? , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { notice.getTitle(), 
notice.getContent(), 
notice.getCreateUserName(), 
notice.getCreateUserId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据公告id删除公告
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 影响行数
	 */
	public int deleteNoticeById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_notice ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据公告查询公告信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告Map
	 */
	public Map getNoticeById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_notice t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新公告
	 * 
	 * @author Soyi Yao
	 * @param notice
	 *        公告bean
	 * @return 影响行数
	 */
	public int updateNotice(Notice notice) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_notice t");
		sql.append(" set   ");
		 sql.append(" t.title = ? , "); 
  sql.append(" t.content = ? , "); 
  sql.append(" t.create_time = ? , "); 
  sql.append(" t.create_user_name = ? , "); 
  sql.append(" t.create_user_id = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { notice.getTitle(), 
notice.getContent(), 
notice.getCreateTime(), 
notice.getCreateUserName(), 
notice.getCreateUserId(), 
 notice.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.INTEGER };
		
		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}


	/**
	 * 分页搜索公告列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param notice
	 *        公告bean
	 * @return 分页公告列表
	 */
	public Page searchNotice(Page page, Notice notice) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_notice t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(notice.getTitle())) {sql.append( "and t.title like '%"+notice.getTitle()+"%'");}if (StringUtils.isNotEmpty(notice.getContent())) {sql.append( "and t.content like '%"+notice.getContent()+"%'");}if (StringUtils.isNotEmpty(notice.getCreateTime())) {sql.append( "and t.create_time like '%"+notice.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(notice.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+notice.getCreateUserName()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索公告列表
	 * 
	 * @author Soyi Yao
	 * @param notice
	 *        Notice实体
	 * @return 公告列表
	 */
	public List<Notice> searchNoticeForList(Notice notice) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_notice t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(notice.getTitle())) {sql.append( "and t.title like '%"+notice.getTitle()+"%'");}if (StringUtils.isNotEmpty(notice.getContent())) {sql.append( "and t.content like '%"+notice.getContent()+"%'");}if (StringUtils.isNotEmpty(notice.getCreateTime())) {sql.append( "and t.create_time like '%"+notice.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(notice.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+notice.getCreateUserName()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new NoticeMapper());
	}
	
	/**
	 * 根据公告id获取公告实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return Notice实体
	 */
	public Notice findNoticeById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_notice t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Notice notice = this.queryForObject(sql.toString(), values, valueTypes,
				new NoticeMapper());
		
		logger.info(sql.toString());

		return notice;
	}


	/**
	 * 列出所有公告
	 * 
	 * @author Soyi Yao
	 * @return 公告列表
	 */
	public List<Notice> listAllNotice() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_notice t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new NoticeMapper());
	}
	
    /**
	 * 获取t_notice表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_notice表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_notice  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
