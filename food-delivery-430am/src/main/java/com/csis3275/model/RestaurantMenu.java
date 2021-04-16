package com.csis3275.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class RestaurantMenu {
	
	private int menuId;
	private int restId;
	private String menuName;
	private double menuPrice;
	private String menuDesc;
	private String menuImg;
	private CommonsMultipartFile menuImgFile;
	
	public int getRestId() {
		return restId;
	}
	public void setRestId (int restId) {
		this.restId = restId;
	}	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public double getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(double menuPrice) {
		this.menuPrice = menuPrice;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getMenuImg() {
		return menuImg;
	}
	public void setMenuImg(String menuImg) {
		if (menuImg == "" || menuImg == null) {
			this.menuImg = "/static/img/svg_icon.png";
		} else {
			this.menuImg = menuImg;
		}
	}
	public CommonsMultipartFile getMenuImgFile() {
		return menuImgFile;
	}
	public void setMenuImgFile(CommonsMultipartFile menuImgFile) {
		this.menuImgFile = menuImgFile;
	}
	
	

}
