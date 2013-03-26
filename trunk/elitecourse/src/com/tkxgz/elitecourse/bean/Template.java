package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* Template类
* 
* @author Soyi Yao
*/
public class Template implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String templateName;

	private String description;

	private String createUserId;

	private String createUserName;

	private String createTime;

	private String fileName;

	private String templateCode;

	private String templateContent;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getTemplateName(){
		return templateName;
	}

	public void setTemplateName(String templateName){
		this.templateName = templateName;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
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

	public String getFileName(){
		return fileName;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	public String getTemplateCode(){
		return templateCode;
	}

	public void setTemplateCode(String templateCode){
		this.templateCode = templateCode;
	}

	public String getTemplateContent(){
		return templateContent;
	}

	public void setTemplateContent(String templateContent){
		this.templateContent = templateContent;
	}

}