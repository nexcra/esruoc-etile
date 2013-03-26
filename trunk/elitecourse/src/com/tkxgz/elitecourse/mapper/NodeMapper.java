package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Node;

/**
	 * 节点映射器内部类，用于返回范型的节点实体
	 * 
	 * @author Soyi Yao
	 */
	public class NodeMapper implements RowMapper<Node> {

		public Node mapRow(ResultSet rs, int rowNum) throws SQLException {
			Node node = new Node();
			
			node.setId(rs.getString("id".toLowerCase())); 
node.setName(rs.getString("name".toLowerCase())); 
node.setParentId(rs.getString("parent_id".toLowerCase())); 
node.setContent(rs.getString("content".toLowerCase())); 
node.setIsLeaf(rs.getString("is_leaf".toLowerCase())); 
node.setFullPathName(rs.getString("full_path_name".toLowerCase())); 
node.setOrderNumber(rs.getString("order_number".toLowerCase())); 
node.setType(rs.getString("type".toLowerCase())); 
node.setFullPathId(rs.getString("full_path_id".toLowerCase())); 
node.setLink(rs.getString("link".toLowerCase())); 
node.setCreateTime(rs.getString("create_time".toLowerCase())); 
node.setLevel(rs.getString("level".toLowerCase())); 
node.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
node.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 

			return node;
		}
	}
