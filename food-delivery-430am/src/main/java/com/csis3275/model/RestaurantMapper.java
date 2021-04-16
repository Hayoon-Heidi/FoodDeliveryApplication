package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RestaurantMapper implements RowMapper<Restaurant>{
	
	public Restaurant mapRow(ResultSet r, int i) throws SQLException {
		Restaurant restaurant = new Restaurant();
		restaurant.setRestId(r.getInt("id"));
		restaurant.setPassword(r.getNString("password"));
		restaurant.setEmail(r.getNString("email"));
		restaurant.setRestName(r.getNString("restName"));
		restaurant.setAddress(r.getNString("address"));
		restaurant.setPhoneNumber(r.getString("phoneNumber"));
		restaurant.setClose(r.getString("close"));
		restaurant.setOpen(r.getString("open"));
		restaurant.setDescription(r.getNString("Description"));
		restaurant.setCategory(r.getString("category"));
		restaurant.setRestImg(r.getNString("restImg"));
		return restaurant;
	}

}
