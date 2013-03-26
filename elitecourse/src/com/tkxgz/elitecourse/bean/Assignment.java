package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* Assignment类
* 
* @author Soyi Yao
*/
public class Assignment implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String userId;

	private String userName;

	private String assignmentClassId;

	private String submitTime;

	private String classesId;

	private String description;

	private String content;

	private String path;

	private String grade;

	private String reContent;

	private String reTime;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getAssignmentClassId(){
		return assignmentClassId;
	}

	public void setAssignmentClassId(String assignmentClassId){
		this.assignmentClassId = assignmentClassId;
	}

	public String getSubmitTime(){
		return submitTime;
	}

	public void setSubmitTime(String submitTime){
		this.submitTime = submitTime;
	}

	public String getClassesId(){
		return classesId;
	}

	public void setClassesId(String classesId){
		this.classesId = classesId;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getPath(){
		return path;
	}

	public void setPath(String path){
		this.path = path;
	}

	public String getGrade(){
		return grade;
	}

	public void setGrade(String grade){
		this.grade = grade;
	}

	public String getReContent(){
		return reContent;
	}

	public void setReContent(String reContent){
		this.reContent = reContent;
	}

	public String getReTime(){
		return reTime;
	}

	public void setReTime(String reTime){
		this.reTime = reTime;
	}

}