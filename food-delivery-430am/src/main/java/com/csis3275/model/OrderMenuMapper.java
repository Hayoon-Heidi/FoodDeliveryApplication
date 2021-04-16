package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderMenuMapper implements RowMapper<OrderMenu>{

	public OrderMenu mapRow(ResultSet r, int i) throws SQLException {
		OrderMenu om = new OrderMenu();
		om.setOrderId(r.getInt("orderId"));
		om.setMenuId(r.getInt("menuId"));
		om.setQuantity(r.getInt("quantity"));
		om.setMenuName(r.getNString("menuName"));
		om.setMenuPrice(r.getDouble("menuPrice"));
		om.setSubtotal(r.getDouble("subtotal"));
		return om;
	}
}
