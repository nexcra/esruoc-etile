package com.tkxwz.esruocetile.webapp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:12:51
 */
public class TestBooking implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String description;

	private String bookingBeginTime;

	private String bookingEndTime;

	private int maxBookingNum;

	private int currentBookingNum;

	private int status;

	private Date insertTime;

	private String campus;

	
	public String getCampus() {
		return campus;
	}

	
	public void setCampus(String campus) {
		this.campus = campus;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBookingBeginTime() {
		return bookingBeginTime;
	}

	public void setBookingBeginTime(String bookingBeginTime) {
		this.bookingBeginTime = bookingBeginTime;
	}

	public String getBookingEndTime() {
		return bookingEndTime;
	}

	public void setBookingEndTime(String bookingEndTime) {
		this.bookingEndTime = bookingEndTime;
	}

	public int getMaxBookingNum() {
		return maxBookingNum;
	}

	public void setMaxBookingNum(int maxBookingNum) {
		this.maxBookingNum = maxBookingNum;
	}

	public int getCurrentBookingNum() {
		return currentBookingNum;
	}

	public void setCurrentBookingNum(int currentBookingNum) {
		this.currentBookingNum = currentBookingNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
