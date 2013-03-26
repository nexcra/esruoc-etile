package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* Group类
* 
* @author Soyi Yao
*/
public class Group implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String name;

	private String orderNumber;

	private String createUserName;

	private String createUserId;

	private String createTime;

	private String remark;


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

	public String getOrderNumber(){
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
	}

	public String getCreateUserName(){
		return createUserName;
	}

	public void setCreateUserName(String createUserName){
		this.createUserName = createUserName;
	}

	public String getCreateUserId(){
		return createUserId;
	}

	public void setCreateUserId(String createUserId){
		this.createUserId = createUserId;
	}

	public String getCreateTime(){
		return createTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

}