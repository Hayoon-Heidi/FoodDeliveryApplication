package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RestaurantMenuMapper implements RowMapper<RestaurantMenu>{
	
	public RestaurantMenu mapRow(ResultSet r, int i) throws SQLException {
		RestaurantMenu restaurantMenu = new RestaurantMenu();
		restaurantMenu.setMenuName(r.getString("menuName"));
		restaurantMenu.setMenuPrice(r.getDouble("menuPrice"));
		restaurantMenu.setMenuDesc(r.getNString("menuDesc"));
		restaurantMenu.setMenuId(r.getInt("menuId"));
		restaurantMenu.setRestId(r.getInt("restId"));
		restaurantMenu.setMenuImg(r.getNString("menuImg"));
		return restaurantMenu;
	}


}
