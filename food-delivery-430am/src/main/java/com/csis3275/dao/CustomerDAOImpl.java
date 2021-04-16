package com.csis3275.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.Customer;
import com.csis3275.model.CustomerMapper;
import com.csis3275.model.Login;
import com.csis3275.model.OrderMenuMapper;
import com.csis3275.model.RestaurantMapper;

@Component
public class CustomerDAOImpl {
	JdbcTemplate j;
	
	private final String SQL_GET_ALL = "SELECT * FROM customers";
	private final String SQL_CREATE_CUSTOMER = 
			"INSERT INTO customers(email, password, firstName, lastName, address, phoneNumber) "
			+ "VALUES (?,?,?,?,?,?)";
	private final String SQL_GET_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE Id = ?";
	
	@Autowired
	public CustomerDAOImpl(DataSource dataSource) {
		j = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Get the customer's information from the database.
	 * @return customers information from customers table.
	 */
	public List<Customer> getAllCustomer() {
		return j.query(SQL_GET_ALL, new CustomerMapper());
	}
	
	public Customer getCustomerById(int id) {
		return j.queryForObject(SQL_GET_CUSTOMER_BY_ID, new Object[] { id }, new CustomerMapper());
	}
	
	/**
	 * Get input from the user to create a new customer account.
	 * @param newCustomer
	 * @return New value from the input. Customer's information.
	 */
	public boolean createCustomer(Customer newCustomer) {
		return j.update(SQL_CREATE_CUSTOMER, 
				newCustomer.getEmail(), newCustomer.getPassword(),
				newCustomer.getFirstName(), newCustomer.getLastName(),
				newCustomer.getAddress(), newCustomer.getPhoneNumber()) > 0;
	}
	
	/**
	 * Customer validation.
	 * @param login
	 * @return customer.
	 */
	public Customer validateCustomer(Login login) {
		String sql = "SELECT * FROM customers WHERE email = '" + login.getEmail() +
				"' AND password = '" + login.getPassword() + "'";
		List<Customer> customers = j.query(sql, new CustomerMapper());
		return customers.size() > 0 ? customers.get(0) : null;
	}

}
