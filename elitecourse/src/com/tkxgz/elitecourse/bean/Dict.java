package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* Dict类
* 
* @author Soyi Yao
*/
public class Dict implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String dictCode;

	private String dictName;

	private String dictDesc;

	private String dictValue;

	private String status;

	private String isApplicationLevel;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getDictCode(){
		return dictCode;
	}

	public void setDictCode(String dictCode){
		this.dictCode = dictCode;
	}

	public String getDictName(){
		return dictName;
	}

	public void setDictName(String dictName){
		this.dictName = dictName;
	}

	public String getDictDesc(){
		return dictDesc;
	}

	public void setDictDesc(String dictDesc){
		this.dictDesc = dictDesc;
	}

	public String getDictValue(){
		return dictValue;
	}

	public void setDictValue(String dictValue){
		this.dictValue = dictValue;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getIsApplicationLevel(){
		return isApplicationLevel;
	}

	public void setIsApplicationLevel(String isApplicationLevel){
		this.isApplicationLevel = isApplicationLevel;
	}

}