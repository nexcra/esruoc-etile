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
import com.tkxgz.elitecourse.bean.Node;
import com.tkxgz.elitecourse.mapper.NodeMapper;

/**
 * 节点Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class NodeBaseDao extends BaseDao<Node> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询节点列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 节点分页数据
	 */
	public Page listNode(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_node t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.order_number desc,t.id desc ");

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加节点
	 * 
	 * @author Soyi Yao
	 * @param node
	 *        节点bean
	 * @return 影响行数
	 */
	public int addNode(Node node) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_node (");
		sql.append(" name , ");
		sql.append(" parent_id , ");
		sql.append(" content , ");
		sql.append(" is_leaf , ");
		sql.append(" full_path_name , ");
		sql.append(" order_number , ");
		sql.append(" type , ");
		sql.append(" full_path_id , ");
		sql.append(" link , ");
		sql.append(" create_time , ");
		sql.append(" level , ");
		sql.append(" create_user_id , ");
		sql.append(" create_user_name  ");
		sql.append(" ) ");
		sql.append(" values ( ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" now() , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { node.getName(), node.getParentId(),
				node.getContent(), node.getIsLeaf(), node.getFullPathName(),
				node.getOrderNumber(), node.getType(), node.getFullPathId(),
				node.getLink(), node.getLevel(), node.getCreateUserId(),
				node.getCreateUserName() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据节点id删除节点
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        节点id
	 * @return 影响行数
	 */
	public int deleteNodeById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_node ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据节点查询节点信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        节点id
	 * @return 节点Map
	 */
	public Map getNodeById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_node t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新节点
	 * 
	 * @author Soyi Yao
	 * @param node
	 *        节点bean
	 * @return 影响行数
	 */
	public int updateNode(Node node) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_node t");
		sql.append(" set   ");
		sql.append(" t.name = ? , ");
		sql.append(" t.parent_id = ? , ");
		sql.append(" t.content = ? , ");
		sql.append(" t.is_leaf = ? , ");
		sql.append(" t.full_path_name = ? , ");
		sql.append(" t.order_number = ? , ");
		sql.append(" t.type = ? , ");
		sql.append(" t.full_path_id = ? , ");
		sql.append(" t.link = ? , ");
		sql.append(" t.create_time = ? , ");
		sql.append(" t.level = ? , ");
		sql.append(" t.create_user_id = ? , ");
		sql.append(" t.create_user_name = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { node.getName(), node.getParentId(),
				node.getContent(), node.getIsLeaf(), node.getFullPathName(),
				node.getOrderNumber(), node.getType(), node.getFullPathId(),
				node.getLink(), node.getCreateTime(), node.getLevel(),
				node.getCreateUserId(), node.getCreateUserName(), node.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.INTEGER };

		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * 分页搜索节点列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param node
	 *        节点bean
	 * @return 分页节点列表
	 */
	public Page searchNode(Page page, Node node) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_node t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(node.getName())) {
			sql.append("and t.name like '%" + node.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(node.getParentId()))
				&& (!"all".equalsIgnoreCase(node.getParentId()))) {
			sql.append(" and t.parent_id = ? ");
			valuesList.add(Integer.valueOf(node.getParentId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(node.getContent())) {
			sql.append("and t.content like '%" + node.getContent() + "%'");
		}
		if (StringUtils.isNotEmpty(node.getIsLeaf())) {
			sql.append("and t.is_leaf like '%" + node.getIsLeaf() + "%'");
		}
		if (StringUtils.isNotEmpty(node.getFullPathName())) {
			sql.append("and t.full_path_name like '%" + node.getFullPathName()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(node.getOrderNumber()))
				&& (!"all".equalsIgnoreCase(node.getOrderNumber()))) {
			sql.append(" and t.order_number = ? ");
			valuesList.add(Integer.valueOf(node.getOrderNumber()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(node.getType())) {
			sql.append("and t.type like '%" + node.getType() + "%'");
		}
		if (StringUtils.isNotEmpty(node.getFullPathId())) {
			sql.append("and t.full_path_id like '%" + node.getFullPathId()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(node.getLink())) {
			sql.append("and t.link like '%" + node.getLink() + "%'");
		}
		if (StringUtils.isNotEmpty(node.getCreateTime())) {
			sql.append("and t.create_time like '%" + node.getCreateTime()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(node.getLevel()))
				&& (!"all".equalsIgnoreCase(node.getLevel()))) {
			sql.append(" and t.level = ? ");
			valuesList.add(Integer.valueOf(node.getLevel()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(node.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ node.getCreateUserName() + "%'");
		}

		sql.append(" order by t.order_number desc,t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * 搜索节点列表
	 * 
	 * @author Soyi Yao
	 * @param node
	 *        Node实体
	 * @return 节点列表
	 */
	public List<Node> searchNodeForList(Node node) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_node t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(node.getName())) {
			sql.append("and t.name like '%" + node.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(node.getParentId()))
				&& (!"all".equalsIgnoreCase(node.getParentId()))) {
			sql.append(" and t.parent_id = ? ");
			valuesList.add(Integer.valueOf(node.getParentId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(node.getContent())) {
			sql.append("and t.content like '%" + node.getContent() + "%'");
		}
		if (StringUtils.isNotEmpty(node.getIsLeaf())) {
			sql.append("and t.is_leaf like '%" + node.getIsLeaf() + "%'");
		}
		if (StringUtils.isNotEmpty(node.getFullPathName())) {
			sql.append("and t.full_path_name like '%" + node.getFullPathName()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(node.getOrderNumber()))
				&& (!"all".equalsIgnoreCase(node.getOrderNumber()))) {
			sql.append(" and t.order_number = ? ");
			valuesList.add(Integer.valueOf(node.getOrderNumber()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(node.getType())) {
			sql.append("and t.type like '%" + node.getType() + "%'");
		}
		if (StringUtils.isNotEmpty(node.getFullPathId())) {
			sql.append("and t.full_path_id like '%" + node.getFullPathId()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(node.getLink())) {
			sql.append("and t.link like '%" + node.getLink() + "%'");
		}
		if (StringUtils.isNotEmpty(node.getCreateTime())) {
			sql.append("and t.create_time like '%" + node.getCreateTime()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(node.getLevel()))
				&& (!"all".equalsIgnoreCase(node.getLevel()))) {
			sql.append(" and t.level = ? ");
			valuesList.add(Integer.valueOf(node.getLevel()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(node.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ node.getCreateUserName() + "%'");
		}

		sql.append(" order by t.order_number desc, t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new NodeMapper());
	}

	/**
	 * 根据节点id获取节点实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        节点id
	 * @return Node实体
	 */
	public Node findNodeById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_node t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Node node = this.queryForObject(sql.toString(), values, valueTypes,
				new NodeMapper());

		logger.info(sql.toString());

		return node;
	}

	/**
	 * 列出所有节点
	 * 
	 * @author Soyi Yao
	 * @return 节点列表
	 */
	public List<Node> listAllNode() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_node t  ");
		sql.append(" order by t.order_number desc,t.id desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new NodeMapper());
	}

	/**
	 * 获取t_node表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_node表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_node  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

}
