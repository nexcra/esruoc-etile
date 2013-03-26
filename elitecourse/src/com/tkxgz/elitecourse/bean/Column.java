package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* Column类
* 
* @author Soyi Yao
*/
public class Column implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String parentId;

	private String name;

	private String code;

	private String isLeaf;

	private String level;

	private String fullPathId;

	private String fullPathName;

	private String createUserId;

	private String createUserName;

	private String createTime;

	private String orderNumber;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getParentId(){
		return parentId;
	}

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getIsLeaf(){
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf){
		this.isLeaf = isLeaf;
	}

	public String getLevel(){
		return level;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getFullPathId(){
		return fullPathId;
	}

	public void setFullPathId(String fullPathId){
		this.fullPathId = fullPathId;
	}

	public String getFullPathName(){
		return fullPathName;
	}

	public void setFullPathName(String fullPathName){
		this.fullPathName = fullPathName;
	}

	public String getCreateUserId(){
		return createUserId;
	}

	public void setCreateUserId(String createUserId){
		this.createUserId = createUserId;
	}

	public String getCreateUserName(){
		return createUserName;
	}

	public void setCreateUserName(String createUserName){
		this.createUserName = createUserName;
	}

	public String getCreateTime(){
		return createTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getOrderNumber(){
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
	}

}