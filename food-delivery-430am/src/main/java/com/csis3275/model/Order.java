package com.csis3275.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Order {

	private int orderId;
	private int customerId;
	private int driverId;
	private Timestamp orderedTime;
	private String orderedTimeString;
	private String orderedTimeStringNew;
	private String orderStatus;
	private int orderStatusId;
	
	public int getOrderStatusId() {
		return orderStatusId;
	}
	
	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public Timestamp getOrderedTime() {
		return orderedTime;
	}
	public void setOrderedTime(Timestamp orderedTime) {
		
		this.orderedTime = orderedTime;
	}
	public String getOrderedTimeString() {
		return orderedTimeString;
	}
	public void setOrderedTimeString(String orderedTimeString) {
		this.orderedTimeString = orderedTimeString;
	}
	
	public String getOrderedTimeStringNew() {
		return orderedTimeStringNew;
	}
	public void setOrderedTimeStringNew(String orderedTimeStringNew) {
		this.orderedTimeStringNew = orderedTimeStringNew;
	}
	
	public void setOrderedTimeStringNew(Timestamp orderedTime) {
		String result = "error getting time:(";
		//Object currentTime = new java.sql.Timestamp(date.getTime());
		
		DateFormat f = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
		result = f.format(orderedTime);

		this.orderedTimeStringNew = result;
	}
	
	
	//get a string value of time difference between now and orderedTime
	public void setOrderedTimeString(Timestamp orderedTime) {
		String result = "error getting time:(";
		Date date = new Date();
		//Object currentTime = new java.sql.Timestamp(date.getTime());
		long diff = date.getTime() - orderedTime.getTime();
		
		long diffSec = TimeUnit.MILLISECONDS.toSeconds(diff);
		long diffMin = TimeUnit.MILLISECONDS.toMinutes(diff);
		long diffHour = TimeUnit.MILLISECONDS.toHours(diff);
		long diffDay = TimeUnit.MILLISECONDS.toDays(diff);
		
		if (diffDay >= 50) {
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			result = f.format(orderedTime);
		} else if (diffDay >= 30) {
			result = "A month ago";
		} else if (diffDay > 1) {
			result = diffDay+" days ago";
		} else if (diffDay == 1) {
			result = "Yesterday";
		} else if (diffMin > 59) {
			result = diffHour + " hours ago";
		} else if (diffMin >= 1) {
			result = diffMin + " minutes ago";
		} else {
			result = diffSec + " seconds ago";
		}
		
		//System.out.println(String.valueOf(diff)+" in seconds: "+diffSec);
		//System.out.println(result);
		this.orderedTimeString = result;
	}
	
}
