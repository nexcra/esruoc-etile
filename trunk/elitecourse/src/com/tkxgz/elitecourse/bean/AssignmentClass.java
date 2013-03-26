package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* AssignmentClass类
* 
* @author Soyi Yao
*/
public class AssignmentClass implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String name;

	private String description;

	private String orderNumber;

	private String classesId;

	private String classesName;

	private String createUserId;

	private String startTime;

	private String endTime;

	private String createUserName;

	private String createTime;


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

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getOrderNumber(){
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
	}

	public String getClassesId(){
		return classesId;
	}

	public void setClassesId(String classesId){
		this.classesId = classesId;
	}

	public String getClassesName(){
		return classesName;
	}

	public void setClassesName(String classesName){
		this.classesName = classesName;
	}

	public String getCreateUserId(){
		return createUserId;
	}

	public void setCreateUserId(String createUserId){
		this.createUserId = createUserId;
	}

	public String getStartTime(){
		return startTime;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public String getEndTime(){
		return endTime;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
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

}