package com.csis3275.model;

import java.sql.Date;
import java.sql.Timestamp;

public class OrderMenu {

	private int menuId;
	private int orderId;
	private int quantity;
	private String menuName;
	private double menuPrice;
	private double subtotal;
	private Timestamp orderedTime;
	
	public OrderMenu() {
		
	}
	public OrderMenu(int menuId, int orderId, 
			int quantity, String menuName, double menuPrice) {
		super();
		this.menuId = menuId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		calcSubtotal();
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public void calcSubtotal() {
		this.subtotal = menuPrice * quantity;
	}
	public double getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(double menuPrice) {
		this.menuPrice = menuPrice;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Timestamp getOrderedTime() {
		return orderedTime;
	}
	public void setOrderedTime(Date orderedTime) {
		Timestamp timestamp = new java.sql.Timestamp(orderedTime.getTime());
		this.orderedTime = timestamp;
	}
	
	
	
	
	
	
}
