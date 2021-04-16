package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowMapper;

public class OrderMapper implements RowMapper<Order>{

	public Order mapRow(ResultSet r, int i) throws SQLException {
		Order o = new Order();
		o.setOrderId(r.getInt("orderId"));
		o.setCustomerId(r.getInt("customerId"));
		o.setDriverId(r.getInt("driverId"));
		o.setOrderedTime(r.getTimestamp("orderedTime"));
		o.setOrderedTimeString(r.getTimestamp("orderedTime"));
		o.setOrderedTimeStringNew(r.getTimestamp("orderedTime"));
		o.setOrderStatus(r.getNString("orderStatus"));
		o.setOrderStatusId(r.getInt("orderStatusId"));
		return o;
	}
}
