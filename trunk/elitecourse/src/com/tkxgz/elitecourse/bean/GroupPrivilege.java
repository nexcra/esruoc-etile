package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* GroupPrivilege类
* 
* @author Soyi Yao
*/
public class GroupPrivilege implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String groupId;

	private String privilegeId;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getGroupId(){
		return groupId;
	}

	public void setGroupId(String groupId){
		this.groupId = groupId;
	}

	public String getPrivilegeId(){
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId){
		this.privilegeId = privilegeId;
	}

}