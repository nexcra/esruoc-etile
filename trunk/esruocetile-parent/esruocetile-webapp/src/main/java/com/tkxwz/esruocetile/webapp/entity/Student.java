package com.tkxwz.esruocetile.webapp.entity;

import java.io.Serializable;


/**
 * 
 * @author Po Kong 
 * @since 2012-7-5 下午8:15:58
 */
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String idNo;
	private String studentNo;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIdNo() {
		return idNo;
	}
	
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public String getStudentNo() {
		return studentNo;
	}
	
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	
}
