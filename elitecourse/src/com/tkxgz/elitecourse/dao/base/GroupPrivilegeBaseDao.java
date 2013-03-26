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
import com.tkxgz.elitecourse.bean.GroupPrivilege;
import com.tkxgz.elitecourse.mapper.GroupPrivilegeMapper;
/**
 * 组权限关联Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class GroupPrivilegeBaseDao extends BaseDao<GroupPrivilege> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询组权限关联列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 组权限关联分页数据
	 */
	public Page listGroupPrivilege(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_group_privilege t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加组权限关联
	 * 
	 * @author Soyi Yao
	 * @param groupPrivilege
	 *        组权限关联bean
	 * @return 影响行数
	 */
	public int addGroupPrivilege(GroupPrivilege groupPrivilege) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_group_privilege (");
		   sql.append(" group_id , "); 
  sql.append(" privilege_id  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { groupPrivilege.getGroupId(), 
groupPrivilege.getPrivilegeId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据组权限关联id删除组权限关联
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        组权限关联id
	 * @return 影响行数
	 */
	public int deleteGroupPrivilegeById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_group_privilege ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据组权限关联查询组权限关联信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        组权限关联id
	 * @return 组权限关联Map
	 */
	public Map getGroupPrivilegeById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_group_privilege t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新组权限关联
	 * 
	 * @author Soyi Yao
	 * @param groupPrivilege
	 *        组权限关联bean
	 * @return 影响行数
	 */
	public int updateGroupPrivilege(GroupPrivilege groupPrivilege) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_group_privilege t");
		sql.append(" set   ");
		 sql.append(" t.group_id = ? , "); 
  sql.append(" t.privilege_id = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { groupPrivilege.getGroupId(), 
groupPrivilege.getPrivilegeId(), 
 groupPrivilege.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.INTEGER };
		
		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}


	/**
	 * 分页搜索组权限关联列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param groupPrivilege
	 *        组权限关联bean
	 * @return 分页组权限关联列表
	 */
	public Page searchGroupPrivilege(Page page, GroupPrivilege groupPrivilege) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_group_privilege t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

			if (StringUtils.isNotEmpty(String.valueOf(groupPrivilege.getGroupId()))&& (!"all".equalsIgnoreCase(groupPrivilege.getGroupId()))){		sql.append(" and t.group_id = ? ");	valuesList.add(Integer.valueOf(groupPrivilege.getGroupId()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(groupPrivilege.getPrivilegeId()))&& (!"all".equalsIgnoreCase(groupPrivilege.getPrivilegeId()))){		sql.append(" and t.privilege_id = ? ");	valuesList.add(Integer.valueOf(groupPrivilege.getPrivilegeId()));	valueTypesList.add(Types.INTEGER);}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索组权限关联列表
	 * 
	 * @author Soyi Yao
	 * @param groupPrivilege
	 *        GroupPrivilege实体
	 * @return 组权限关联列表
	 */
	public List<GroupPrivilege> searchGroupPrivilegeForList(GroupPrivilege groupPrivilege) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_group_privilege t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

			if (StringUtils.isNotEmpty(String.valueOf(groupPrivilege.getGroupId()))&& (!"all".equalsIgnoreCase(groupPrivilege.getGroupId()))){		sql.append(" and t.group_id = ? ");	valuesList.add(Integer.valueOf(groupPrivilege.getGroupId()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(groupPrivilege.getPrivilegeId()))&& (!"all".equalsIgnoreCase(groupPrivilege.getPrivilegeId()))){		sql.append(" and t.privilege_id = ? ");	valuesList.add(Integer.valueOf(groupPrivilege.getPrivilegeId()));	valueTypesList.add(Types.INTEGER);}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new GroupPrivilegeMapper());
	}
	
	/**
	 * 根据组权限关联id获取组权限关联实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        组权限关联id
	 * @return GroupPrivilege实体
	 */
	public GroupPrivilege findGroupPrivilegeById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_group_privilege t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		GroupPrivilege groupPrivilege = this.queryForObject(sql.toString(), values, valueTypes,
				new GroupPrivilegeMapper());
		
		logger.info(sql.toString());

		return groupPrivilege;
	}


	/**
	 * 列出所有组权限关联
	 * 
	 * @author Soyi Yao
	 * @return 组权限关联列表
	 */
	public List<GroupPrivilege> listAllGroupPrivilege() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_group_privilege t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new GroupPrivilegeMapper());
	}
	
    /**
	 * 获取t_group_privilege表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_group_privilege表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_group_privilege  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
