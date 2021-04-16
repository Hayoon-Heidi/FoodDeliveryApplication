package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer>{
	public Customer mapRow(ResultSet r, int i) throws SQLException {
		Customer customer = new Customer();
		customer.setId(r.getInt("id"));
		customer.setPassword(r.getNString("password"));
		customer.setEmail(r.getNString("email"));
		customer.setFirstName(r.getNString("firstName"));
		customer.setLastName(r.getNString("lastName"));
		customer.setAddress(r.getNString("address"));
		customer.setPhoneNumber(r.getString("phoneNumber"));
		return customer;
	}
}
