package com.tkxwz.esruocetile.webapp.entity;

import java.io.Serializable;

/**
 * @author Po Kong
 * @since 2012-5-20 上午11:29:11
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
