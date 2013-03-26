package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* QuestionAnswer类
* 
* @author Soyi Yao
*/
public class QuestionAnswer implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String title;

	private String content;

	private String createUserId;

	private String createUserName;

	private String createTime;

	private String createUserIp;

	private String reContent;

	private String reUserId;

	private String reUserName;

	private String reTime;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
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

	public String getCreateUserIp(){
		return createUserIp;
	}

	public void setCreateUserIp(String createUserIp){
		this.createUserIp = createUserIp;
	}

	public String getReContent(){
		return reContent;
	}

	public void setReContent(String reContent){
		this.reContent = reContent;
	}

	public String getReUserId(){
		return reUserId;
	}

	public void setReUserId(String reUserId){
		this.reUserId = reUserId;
	}

	public String getReUserName(){
		return reUserName;
	}

	public void setReUserName(String reUserName){
		this.reUserName = reUserName;
	}

	public String getReTime(){
		return reTime;
	}

	public void setReTime(String reTime){
		this.reTime = reTime;
	}

}