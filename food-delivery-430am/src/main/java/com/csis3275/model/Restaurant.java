package com.csis3275.model;

import java.sql.Time;
import java.time.format.DateTimeFormatter;

public class Restaurant {

	private int restId;
	private String email;
	private String password;
	private String restName;
	private String open;
	private String close;
	private String description;
	private String phoneNumber;
	private String address;
	private String category;
	private String restImg;
	private double rating;
	private int numOfReviews;
	private String numOfReviewsString;
	private String starRating;
	private String catName;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;		
	}	
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {		
		this.open = open;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRestImg() {
		return restImg;
	}
	public void setRestImg(String restImg) {
		if (restImg == "" || restImg == null) {
			this.restImg = "/static/img/svg_icon.png";
		} else {
			this.restImg = restImg;
		}
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getNumOfReviews() {
		return numOfReviews;
	}
	public void setNumOfReviews(int numOfReviews) {
		this.numOfReviews = numOfReviews;
	}
	public String getNumOfReviewsString() {
		return numOfReviewsString;
	}
	public void setNumOfReviewsString(String numOfReviews) {
		this.numOfReviewsString = numOfReviews;
	}
	public String getStarRating() {
		return starRating;
	}
	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	
	
}
