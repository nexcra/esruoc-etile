package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* Faq类
* 
* @author Soyi Yao
*/
public class Faq implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String question;

	private String answer;

	private String status;

	private String reference;

	private String createUserId;

	private String createUserName;

	private String createTime;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getQuestion(){
		return question;
	}

	public void setQuestion(String question){
		this.question = question;
	}

	public String getAnswer(){
		return answer;
	}

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getReference(){
		return reference;
	}

	public void setReference(String reference){
		this.reference = reference;
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