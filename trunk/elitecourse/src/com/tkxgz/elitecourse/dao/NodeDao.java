package com.tkxgz.elitecourse.dao;

import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkxgz.elitecourse.bean.Node;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.dao.base.NodeBaseDao;
import com.tkxgz.elitecourse.mapper.NodeMapper;

/**
 * 节点Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class NodeDao extends NodeBaseDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 节点树列表
	 * 
	 * @author Soyi Yao
	 * @param parentId
	 *        父节点id
	 * @return 节点树数据
	 */
	public List<Node> listNodeTreeByParentId(String parentId) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_node t ");
		sql.append(" where 1 = 1  ");
		if (!"0".equalsIgnoreCase(parentId)) {
			sql.append(" and t.full_path_id  like '" + parentId + "%'  ");
		}
		sql.append(" order by t.full_path_id asc, t.order_number desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new NodeMapper());
	}

	/**
	 * 根据节点id删除节点，连同子节点一起删除
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        节点id
	 * @return 影响行数
	 */
	@Override
	public int deleteNodeById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_node ");
		sql.append(" where full_path_id like'%" + id + "%'  ");

		logger.info(sql.toString());

		return this.insert(sql.toString());
	}

	/**
	 * 添加节点
	 * 
	 * @author Soyi Yao
	 * @param node
	 *        节点bean
	 * @return 影响行数
	 */
	@Override
	public int addNode(Node node) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_node (");
		sql.append(" id , ");
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
		sql.append(" ? , ");
		sql.append(" now() , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { node.getId(), node.getName(), node.getParentId(),
				node.getContent(), node.getIsLeaf(), node.getFullPathName(),
				node.getOrderNumber(), node.getType(), node.getFullPathId(),
				node.getLink(), node.getLevel(), node.getCreateUserId(),
				node.getCreateUserName() };
		int[] valueTypes = { Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 获取当前最大id值
	 * 
	 * @author Soyi Yao
	 * @return
	 */
	public int getCurrentMaxId() {
		StringBuilder sql = new StringBuilder();
		sql.append("select max(id) from  t_node  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

	/**
	 * @author Soyi Yao
	 * @param parentId
	 * @return
	 */
	public List<Node> listChildNodesByParentId(String parentId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_node t ");
		sql.append(" where 1 = 1  ");
		sql.append(" and t.parent_id = ?  ");

		sql.append(" order by t.full_path_id asc, t.order_number desc ");
		Object[] values = { parentId };
		int[] valueTypes = { Types.VARCHAR, };

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new NodeMapper());
	}

	/**
	 * @author Soyi Yao
	 * @param originFullPathId
	 * @return
	 */
	public List<Node> listNodesByFullPathId(String originFullPathId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_node t ");
		sql.append(" where 1 = 1  ");
		sql.append(" and t.full_path_id like '" + originFullPathId + "%'  ");
		sql.append(" order by t.full_path_id asc, t.order_number desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new NodeMapper());
	}

}
