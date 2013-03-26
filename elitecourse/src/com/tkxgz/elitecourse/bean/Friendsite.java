package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* Friendsite类
* 
* @author Soyi Yao
*/
public class Friendsite implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String name;

	private String type;

	private String link;

	private String description;

	private String createTime;

	private String picPath;

	private String orderNumber;

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

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getLink(){
		return link;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getCreateTime(){
		return createTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getPicPath(){
		return picPath;
	}

	public void setPicPath(String picPath){
		this.picPath = picPath;
	}

	public String getOrderNumber(){
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
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