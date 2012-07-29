package com.tkxwz.esruocetile.webapp.entity;

import java.io.Serializable;

/**
 * @author Po Kong
 * @since 2012-7-5 下午8:15:58
 */
public class StudentTestBooking implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String studentNo;

	private String name;

	private String college;

	private String grade;

	private String campus;

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getTestBookingName() {
		return testBookingName;
	}

	public void setTestBookingName(String testBookingName) {
		this.testBookingName = testBookingName;
	}

	private String testBookingName;

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	private String gender;

	private String notionalityCode;

	private String nationality;

	private String dateOfBirth;

	private String idNo;

	private String major;

	private String executiveClaas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNotionalityCode() {
		return notionalityCode;
	}

	public void setNotionalityCode(String notionalityCode) {
		this.notionalityCode = notionalityCode;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getExecutiveClaas() {
		return executiveClaas;
	}

	public void setExecutiveClaas(String executiveClaas) {
		this.executiveClaas = executiveClaas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
