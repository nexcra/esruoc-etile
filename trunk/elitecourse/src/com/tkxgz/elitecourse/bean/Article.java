package com.tkxgz.elitecourse.bean;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * Article类
 * 
 * @author Soyi Yao
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String title;

	private String columnId;

	private String subTitle;

	private String type;

	private String content;

	private String onTop;

	private String recommend;

	private String createUserId;

	private String createUserName;

	private String createTime;

	private String name;

	private String fullPathName;

	private String columnName;

	private String columnFullPathName;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnFullPathName() {
		return columnFullPathName;
	}

	public void setColumnFullPathName(String columnFullPathName) {
		this.columnFullPathName = columnFullPathName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullPathName() {
		return fullPathName;
	}

	public void setFullPathName(String fullPathName) {
		this.fullPathName = fullPathName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOnTop() {
		return onTop;
	}

	public void setOnTop(String onTop) {
		this.onTop = onTop;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
