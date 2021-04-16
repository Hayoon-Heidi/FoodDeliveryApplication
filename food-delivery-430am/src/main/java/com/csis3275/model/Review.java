package com.csis3275.model;

import java.sql.Date;

public class Review {
	
	private int reviewId;
	private int orderId;
	private String reviewContent;
	private int rate;
	private Date orderedTime;
	private int restId;
	private String menuName;
	private int starRating;
	
	public void calcStarRating() {
		this.starRating = rate * 20;
	}
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public Date getOrderedTime() {
		return orderedTime;
	}
	public void setOrderedTime(Date orderedTime) {
		this.orderedTime = orderedTime;
	}
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getStarRating() {
		return starRating;
	}
	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
	
	public Review() {
		
	}
	public Review(int reviewId, int orderId, String reviewContent, int rate) {
		super();
		this.reviewId = reviewId;
		this.orderId = orderId;
		this.reviewContent = reviewContent;
		this.rate = rate;
	}

}
