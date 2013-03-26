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
import com.tkxgz.elitecourse.bean.Group;
import com.tkxgz.elitecourse.mapper.GroupMapper;
/**
 * 组Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class GroupBaseDao extends BaseDao<Group> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询组列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 组分页数据
	 */
	public Page listGroup(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_group t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加组
	 * 
	 * @author Soyi Yao
	 * @param group
	 *        组bean
	 * @return 影响行数
	 */
	public int addGroup(Group group) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_group (");
		   sql.append(" name , "); 
  sql.append(" order_number , "); 
  sql.append(" create_user_name , "); 
  sql.append(" create_user_id , "); 
  sql.append(" create_time , "); 
  sql.append(" remark  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" now() , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { group.getName(), 
group.getOrderNumber(), 
group.getCreateUserName(), 
group.getCreateUserId(), 
group.getRemark()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据组id删除组
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        组id
	 * @return 影响行数
	 */
	public int deleteGroupById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_group ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据组查询组信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        组id
	 * @return 组Map
	 */
	public Map getGroupById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_group t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新组
	 * 
	 * @author Soyi Yao
	 * @param group
	 *        组bean
	 * @return 影响行数
	 */
	public int updateGroup(Group group) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_group t");
		sql.append(" set   ");
		 sql.append(" t.name = ? , "); 
  sql.append(" t.order_number = ? , "); 
  sql.append(" t.create_user_name = ? , "); 
  sql.append(" t.create_user_id = ? , "); 
  sql.append(" t.create_time = ? , "); 
  sql.append(" t.remark = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { group.getName(), 
group.getOrderNumber(), 
group.getCreateUserName(), 
group.getCreateUserId(), 
group.getCreateTime(), 
group.getRemark(), 
 group.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
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
	 * 分页搜索组列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param group
	 *        组bean
	 * @return 分页组列表
	 */
	public Page searchGroup(Page page, Group group) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_group t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(group.getName())) {sql.append( "and t.name like '%"+group.getName()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(group.getOrderNumber()))&& (!"all".equalsIgnoreCase(group.getOrderNumber()))){		sql.append(" and t.order_number = ? ");	valuesList.add(Integer.valueOf(group.getOrderNumber()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(group.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+group.getCreateUserName()+"%'");}if (StringUtils.isNotEmpty(group.getCreateTime())) {sql.append( "and t.create_time like '%"+group.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(group.getRemark())) {sql.append( "and t.remark like '%"+group.getRemark()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索组列表
	 * 
	 * @author Soyi Yao
	 * @param group
	 *        Group实体
	 * @return 组列表
	 */
	public List<Group> searchGroupForList(Group group) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_group t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(group.getName())) {sql.append( "and t.name like '%"+group.getName()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(group.getOrderNumber()))&& (!"all".equalsIgnoreCase(group.getOrderNumber()))){		sql.append(" and t.order_number = ? ");	valuesList.add(Integer.valueOf(group.getOrderNumber()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(group.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+group.getCreateUserName()+"%'");}if (StringUtils.isNotEmpty(group.getCreateTime())) {sql.append( "and t.create_time like '%"+group.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(group.getRemark())) {sql.append( "and t.remark like '%"+group.getRemark()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new GroupMapper());
	}
	
	/**
	 * 根据组id获取组实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        组id
	 * @return Group实体
	 */
	public Group findGroupById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_group t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Group group = this.queryForObject(sql.toString(), values, valueTypes,
				new GroupMapper());
		
		logger.info(sql.toString());

		return group;
	}


	/**
	 * 列出所有组
	 * 
	 * @author Soyi Yao
	 * @return 组列表
	 */
	public List<Group> listAllGroup() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_group t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new GroupMapper());
	}
	
    /**
	 * 获取t_group表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_group表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_group  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
