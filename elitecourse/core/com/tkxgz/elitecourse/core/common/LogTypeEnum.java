package com.tkxgz.elitecourse.core.common;

/**
 * @author Soyi Yao
 */
public enum LogTypeEnum {
	LOG_TYPE_INSERT("insert");

	private String value;

	private LogTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
