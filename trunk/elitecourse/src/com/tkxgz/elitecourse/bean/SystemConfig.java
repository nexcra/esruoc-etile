package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* SystemConfig类
* 
* @author Soyi Yao
*/
public class SystemConfig implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String siteName;

	private String copyright;

	private String siteOwner;

	private String contactPhone;

	private String contactEmail;

	private String status;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getSiteName(){
		return siteName;
	}

	public void setSiteName(String siteName){
		this.siteName = siteName;
	}

	public String getCopyright(){
		return copyright;
	}

	public void setCopyright(String copyright){
		this.copyright = copyright;
	}

	public String getSiteOwner(){
		return siteOwner;
	}

	public void setSiteOwner(String siteOwner){
		this.siteOwner = siteOwner;
	}

	public String getContactPhone(){
		return contactPhone;
	}

	public void setContactPhone(String contactPhone){
		this.contactPhone = contactPhone;
	}

	public String getContactEmail(){
		return contactEmail;
	}

	public void setContactEmail(String contactEmail){
		this.contactEmail = contactEmail;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

}