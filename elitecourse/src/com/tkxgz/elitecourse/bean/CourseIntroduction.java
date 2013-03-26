package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* CourseIntroduction类
* 
* @author Soyi Yao
*/
public class CourseIntroduction implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String courseHostName;

	private String introContent;

	private String pic;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getCourseHostName(){
		return courseHostName;
	}

	public void setCourseHostName(String courseHostName){
		this.courseHostName = courseHostName;
	}

	public String getIntroContent(){
		return introContent;
	}

	public void setIntroContent(String introContent){
		this.introContent = introContent;
	}

	public String getPic(){
		return pic;
	}

	public void setPic(String pic){
		this.pic = pic;
	}

}