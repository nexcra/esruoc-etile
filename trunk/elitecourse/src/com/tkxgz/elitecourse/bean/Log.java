package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* Log类
* 
* @author Soyi Yao
*/
public class Log implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String logType;

	private String userId;

	private String userName;

	private String module;

	private String description;

	private String ipAddress;

	private String createTime;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getLogType(){
		return logType;
	}

	public void setLogType(String logType){
		this.logType = logType;
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

	public String getModule(){
		return module;
	}

	public void setModule(String module){
		this.module = module;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getIpAddress(){
		return ipAddress;
	}

	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}

	public String getCreateTime(){
		return createTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
 @Override 
         public  int  hashCode()  { 
                 final  int  prime  =  31; 
                 int  result  =  1; 
                 result  =  prime  *  result 
 +  ((id  ==  null)  ?  0  :  id.hashCode()); 
                 result  =  prime  *  result 
 +  ((logType  ==  null)  ?  0  :  logType.hashCode()); 
                 result  =  prime  *  result 
 +  ((userId  ==  null)  ?  0  :  userId.hashCode()); 
                 result  =  prime  *  result 
 +  ((userName  ==  null)  ?  0  :  userName.hashCode()); 
                 result  =  prime  *  result 
 +  ((module  ==  null)  ?  0  :  module.hashCode()); 
                 result  =  prime  *  result 
 +  ((description  ==  null)  ?  0  :  description.hashCode()); 
                 result  =  prime  *  result 
 +  ((ipAddress  ==  null)  ?  0  :  ipAddress.hashCode()); 
                 result  =  prime  *  result 
 +  ((createTime  ==  null)  ?  0  :  createTime.hashCode()); 
 return result;
 }
 @Override 
         public  boolean  equals(Object  obj)  { 
                 if  (this  ==  obj)                          return  true;                  if  (obj  ==  null)                          return  false;                  if  (getClass()  !=  obj.getClass())                          return  false;  Log  other  =  (Log)  obj;                  if  (id  ==  null)  {                          if  (other.id   !=  null)                                  return  false;                  }  else  if  (!id .equals(other.id ))                          return  false;                  if  (logType  ==  null)  {                          if  (other.logType   !=  null)                                  return  false;                  }  else  if  (!logType .equals(other.logType ))                          return  false;                  if  (userId  ==  null)  {                          if  (other.userId   !=  null)                                  return  false;                  }  else  if  (!userId .equals(other.userId ))                          return  false;                  if  (userName  ==  null)  {                          if  (other.userName   !=  null)                                  return  false;                  }  else  if  (!userName .equals(other.userName ))                          return  false;                  if  (module  ==  null)  {                          if  (other.module   !=  null)                                  return  false;                  }  else  if  (!module .equals(other.module ))                          return  false;                  if  (description  ==  null)  {                          if  (other.description   !=  null)                                  return  false;                  }  else  if  (!description .equals(other.description ))                          return  false;                  if  (ipAddress  ==  null)  {                          if  (other.ipAddress   !=  null)                                  return  false;                  }  else  if  (!ipAddress .equals(other.ipAddress ))                          return  false;                  if  (createTime  ==  null)  {                          if  (other.createTime   !=  null)                                  return  false;                  }  else  if  (!createTime .equals(other.createTime ))                          return  false; return true;
} @Override 
         public  String  toString()  { 
 return "Log [id="+id+", logType="+logType+", userId="+userId+", userName="+userName+", module="+module+", description="+description+", ipAddress="+ipAddress+", createTime="+createTime +"]"; }

}