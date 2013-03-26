package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* Node类
* 
* @author Soyi Yao
*/
public class Node implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String name;

	private String parentId;

	private String content;

	private String isLeaf;

	private String fullPathName;

	private String orderNumber;

	private String type;

	private String fullPathId;

	private String link;

	private String createTime;

	private String level;

	private String createUserId;

	private String createUserName;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getParentId(){
		return parentId;
	}

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getIsLeaf(){
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf){
		this.isLeaf = isLeaf;
	}

	public String getFullPathName(){
		return fullPathName;
	}

	public void setFullPathName(String fullPathName){
		this.fullPathName = fullPathName;
	}

	public String getOrderNumber(){
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getFullPathId(){
		return fullPathId;
	}

	public void setFullPathId(String fullPathId){
		this.fullPathId = fullPathId;
	}

	public String getLink(){
		return link;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getCreateTime(){
		return createTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getLevel(){
		return level;
	}

	public void setLevel(String level){
		this.level = level;
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

}