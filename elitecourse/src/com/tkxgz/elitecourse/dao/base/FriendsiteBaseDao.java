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
import com.tkxgz.elitecourse.bean.Friendsite;
import com.tkxgz.elitecourse.mapper.FriendsiteMapper;
/**
 * 友情链接Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class FriendsiteBaseDao extends BaseDao<Friendsite> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询友情链接列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 友情链接分页数据
	 */
	public Page listFriendsite(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_friendsite t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加友情链接
	 * 
	 * @author Soyi Yao
	 * @param friendsite
	 *        友情链接bean
	 * @return 影响行数
	 */
	public int addFriendsite(Friendsite friendsite) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_friendsite (");
		   sql.append(" name , "); 
  sql.append(" type , "); 
  sql.append(" link , "); 
  sql.append(" description , "); 
  sql.append(" create_time , "); 
  sql.append(" pic_path , "); 
  sql.append(" order_number , "); 
  sql.append(" create_user_id , "); 
  sql.append(" create_user_name  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" now() , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { friendsite.getName(), 
friendsite.getType(), 
friendsite.getLink(), 
friendsite.getDescription(), 
friendsite.getPicPath(), 
friendsite.getOrderNumber(), 
friendsite.getCreateUserId(), 
friendsite.getCreateUserName()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据友情链接id删除友情链接
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        友情链接id
	 * @return 影响行数
	 */
	public int deleteFriendsiteById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_friendsite ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据友情链接查询友情链接信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        友情链接id
	 * @return 友情链接Map
	 */
	public Map getFriendsiteById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_friendsite t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新友情链接
	 * 
	 * @author Soyi Yao
	 * @param friendsite
	 *        友情链接bean
	 * @return 影响行数
	 */
	public int updateFriendsite(Friendsite friendsite) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_friendsite t");
		sql.append(" set   ");
		 sql.append(" t.name = ? , "); 
  sql.append(" t.type = ? , "); 
  sql.append(" t.link = ? , "); 
  sql.append(" t.description = ? , "); 
  sql.append(" t.create_time = ? , "); 
  sql.append(" t.pic_path = ? , "); 
  sql.append(" t.order_number = ? , "); 
  sql.append(" t.create_user_id = ? , "); 
  sql.append(" t.create_user_name = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { friendsite.getName(), 
friendsite.getType(), 
friendsite.getLink(), 
friendsite.getDescription(), 
friendsite.getCreateTime(), 
friendsite.getPicPath(), 
friendsite.getOrderNumber(), 
friendsite.getCreateUserId(), 
friendsite.getCreateUserName(), 
 friendsite.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.INTEGER };
		
		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}


	/**
	 * 分页搜索友情链接列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param friendsite
	 *        友情链接bean
	 * @return 分页友情链接列表
	 */
	public Page searchFriendsite(Page page, Friendsite friendsite) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_friendsite t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(friendsite.getName())) {sql.append( "and t.name like '%"+friendsite.getName()+"%'");}if (StringUtils.isNotEmpty(friendsite.getType())) {sql.append( "and t.type like '%"+friendsite.getType()+"%'");}if (StringUtils.isNotEmpty(friendsite.getLink())) {sql.append( "and t.link like '%"+friendsite.getLink()+"%'");}if (StringUtils.isNotEmpty(friendsite.getDescription())) {sql.append( "and t.description like '%"+friendsite.getDescription()+"%'");}if (StringUtils.isNotEmpty(friendsite.getCreateTime())) {sql.append( "and t.create_time like '%"+friendsite.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(friendsite.getPicPath())) {sql.append( "and t.pic_path like '%"+friendsite.getPicPath()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(friendsite.getOrderNumber()))&& (!"all".equalsIgnoreCase(friendsite.getOrderNumber()))){		sql.append(" and t.order_number = ? ");	valuesList.add(Integer.valueOf(friendsite.getOrderNumber()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(friendsite.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+friendsite.getCreateUserName()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索友情链接列表
	 * 
	 * @author Soyi Yao
	 * @param friendsite
	 *        Friendsite实体
	 * @return 友情链接列表
	 */
	public List<Friendsite> searchFriendsiteForList(Friendsite friendsite) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_friendsite t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(friendsite.getName())) {sql.append( "and t.name like '%"+friendsite.getName()+"%'");}if (StringUtils.isNotEmpty(friendsite.getType())) {sql.append( "and t.type like '%"+friendsite.getType()+"%'");}if (StringUtils.isNotEmpty(friendsite.getLink())) {sql.append( "and t.link like '%"+friendsite.getLink()+"%'");}if (StringUtils.isNotEmpty(friendsite.getDescription())) {sql.append( "and t.description like '%"+friendsite.getDescription()+"%'");}if (StringUtils.isNotEmpty(friendsite.getCreateTime())) {sql.append( "and t.create_time like '%"+friendsite.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(friendsite.getPicPath())) {sql.append( "and t.pic_path like '%"+friendsite.getPicPath()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(friendsite.getOrderNumber()))&& (!"all".equalsIgnoreCase(friendsite.getOrderNumber()))){		sql.append(" and t.order_number = ? ");	valuesList.add(Integer.valueOf(friendsite.getOrderNumber()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(friendsite.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+friendsite.getCreateUserName()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new FriendsiteMapper());
	}
	
	/**
	 * 根据友情链接id获取友情链接实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        友情链接id
	 * @return Friendsite实体
	 */
	public Friendsite findFriendsiteById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_friendsite t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Friendsite friendsite = this.queryForObject(sql.toString(), values, valueTypes,
				new FriendsiteMapper());
		
		logger.info(sql.toString());

		return friendsite;
	}


	/**
	 * 列出所有友情链接
	 * 
	 * @author Soyi Yao
	 * @return 友情链接列表
	 */
	public List<Friendsite> listAllFriendsite() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_friendsite t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new FriendsiteMapper());
	}
	
    /**
	 * 获取t_friendsite表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_friendsite表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_friendsite  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
