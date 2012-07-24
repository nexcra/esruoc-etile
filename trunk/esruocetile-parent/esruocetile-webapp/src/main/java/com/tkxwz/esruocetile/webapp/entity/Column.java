package com.tkxwz.esruocetile.webapp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:12:51
 */
public class Column implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String columnName;

	private int columnType;

	private String columnContent;

	private int parentId;

	private String description;

	private int orderNum;

	private Date insertTime;

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getColumnType() {
		return columnType;
	}

	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}

	public String getColumnContent() {
		return columnContent;
	}

	public void setColumnContent(String columnContent) {
		this.columnContent = columnContent;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
}
