package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* Video类
* 
* @author Soyi Yao
*/
public class Video implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String name;

	private String source;

	private String type;

	private String description;

	private String path;

	private String createUserId;

	private String createUserName;

	private String createTime;
	
	private String randomName;

	
	public String getRandomName() {
		return randomName;
	}

	
	public void setRandomName(String randomName) {
		this.randomName = randomName;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public String getSource(){
		return source;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getPath(){
		return path;
	}

	public void setPath(String path){
		this.path = path;
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

}