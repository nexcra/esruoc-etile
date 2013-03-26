package com.tkxgz.elitecourse.bean;

import  java.sql.Timestamp ;
import java.io.Serializable;
/**
* ExamClass类
* 
* @author Soyi Yao
*/
public class ExamClass implements Serializable  {

private static final long serialVersionUID = 1L;	private String id;

	private String name;

	private String orderNumber;

	private String totalMark;

	private String radioSubjectNum;

	private String status;

	private String radioSubjectMark;

	private String checkboxSubjectNumber;

	private String checkboxSubjectMark;

	private String judgeSubjectNumber;

	private String judgeSubjectMark;

	private String blankSubjectNumber;

	private String blankSubjectMark;

	private String createTime;

	private String createUserId;

	private String createUserName;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getOrderNumber(){
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
	}

	public String getTotalMark(){
		return totalMark;
	}

	public void setTotalMark(String totalMark){
		this.totalMark = totalMark;
	}

	public String getRadioSubjectNum(){
		return radioSubjectNum;
	}

	public void setRadioSubjectNum(String radioSubjectNum){
		this.radioSubjectNum = radioSubjectNum;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getRadioSubjectMark(){
		return radioSubjectMark;
	}

	public void setRadioSubjectMark(String radioSubjectMark){
		this.radioSubjectMark = radioSubjectMark;
	}

	public String getCheckboxSubjectNumber(){
		return checkboxSubjectNumber;
	}

	public void setCheckboxSubjectNumber(String checkboxSubjectNumber){
		this.checkboxSubjectNumber = checkboxSubjectNumber;
	}

	public String getCheckboxSubjectMark(){
		return checkboxSubjectMark;
	}

	public void setCheckboxSubjectMark(String checkboxSubjectMark){
		this.checkboxSubjectMark = checkboxSubjectMark;
	}

	public String getJudgeSubjectNumber(){
		return judgeSubjectNumber;
	}

	public void setJudgeSubjectNumber(String judgeSubjectNumber){
		this.judgeSubjectNumber = judgeSubjectNumber;
	}

	public String getJudgeSubjectMark(){
		return judgeSubjectMark;
	}

	public void setJudgeSubjectMark(String judgeSubjectMark){
		this.judgeSubjectMark = judgeSubjectMark;
	}

	public String getBlankSubjectNumber(){
		return blankSubjectNumber;
	}

	public void setBlankSubjectNumber(String blankSubjectNumber){
		this.blankSubjectNumber = blankSubjectNumber;
	}

	public String getBlankSubjectMark(){
		return blankSubjectMark;
	}

	public void setBlankSubjectMark(String blankSubjectMark){
		this.blankSubjectMark = blankSubjectMark;
	}

	public String getCreateTime(){
		return createTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
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

}