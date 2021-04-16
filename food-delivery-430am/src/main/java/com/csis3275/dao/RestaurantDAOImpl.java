package com.csis3275.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.Customer;
import com.csis3275.model.CustomerMapper;
import com.csis3275.model.Login;
import com.csis3275.model.Order;
import com.csis3275.model.Restaurant;
import com.csis3275.model.RestaurantMapper;


@Component
public class RestaurantDAOImpl {

JdbcTemplate j;
	
	private final String SQL_GET_ALL = "SELECT * FROM restaurants";
	private final String SQL_CREATE_RESTAURANT = 
			"INSERT INTO restaurants(email, password, restName, open, close, description, address, phoneNumber, category) "
			+ "VALUES (?,?,?,?,?,?,?,?,?)";
	private final String SQL_FIND_REST = "SELECT * FROM restaurants WHERE id = ?";
	private final String SQL_GET_REST_BY_MENUID = "SELECT * FROM restaurants INNER JOIN restMenu "
			+ "ON restaurants.id=restMenu.restId WHERE restMenu.restId = ?;";
	private final String SQL_UPDATE_REST = "UPDATE restaurants SET restImg=? WHERE id = ?;";

	private final String SQL_GET_CATEGORIES = "SELECT * FROM category";
	private final String SQL_GET_CATNAME_BY_CATID = "SELECT catName FROM category WHERE catId = ?";
	private final String SQL_GET_REST_BY_CATID = "SELECT * FROM RESTAURANTS WHERE category = ?";
	
	private final String SQL_SEARCH_RESTNAME = "SELECT * FROM restaurants WHERE restName LIKE ?;";
	private final String SQL_SEARCH_RESTNAME_WITH_CATEGORY = "SELECT * FROM restaurants WHERE category = ? AND restName LIKE ?;";
	
	@Autowired
	public RestaurantDAOImpl(DataSource dataSource) {
		j = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Get the restaurant informations from the database as list.
	 * @return restaurant list.
	 */
	public List<Restaurant> getAllRestaurant() {
		return j.query(SQL_GET_ALL, new RestaurantMapper());
	}
	
	public List<Restaurant> getRestaurantsByCategory(int catId) {
		return j.query(SQL_GET_REST_BY_CATID, new Object[] {catId}, new RestaurantMapper());
	}
	
	public List<Restaurant> searchRestaurantsByName(String name) {
		return j.query(SQL_SEARCH_RESTNAME, new Object[] {"%"+name+"%"}, new RestaurantMapper());
	}
	
	public List<Restaurant> searchRestaurantsByNameWithCategory(int catId, String name) {
		return j.query(SQL_SEARCH_RESTNAME_WITH_CATEGORY, new Object[] {catId, "%"+name+"%"}, new RestaurantMapper());
	}
	
	/**
	 * Create new restaurant account. Insert the value into the database.
	 * @param newRestaurant
	 * @return Informations about new restaurant.
	 */
	public boolean createRestaurant(Restaurant newRestaurant) {
		return j.update(SQL_CREATE_RESTAURANT, 
				newRestaurant.getEmail(), newRestaurant.getPassword(),
				newRestaurant.getRestName(), newRestaurant.getOpen(),
				newRestaurant.getClose(), newRestaurant.getDescription(),
				newRestaurant.getAddress(), newRestaurant.getPhoneNumber(), newRestaurant.getCategory()) > 0;
	}

	/**
	 * Get the restaurant information by id.
	 * @param id
	 * @return Restaurant by restaurant id.
	 */
	public Restaurant getRestById(int id) {
		return j.queryForObject(SQL_FIND_REST, new Object[] { id }, new RestaurantMapper());
	}
	
	/**
	 * Restaurant account validation.
	 * @param login
	 * @return Restaurant validity.
	 */
	public Restaurant validateRestaurant(Login login) {
		String sql = "SELECT * FROM restaurants WHERE email = '" + login.getEmail() +
				"' AND password = '" + login.getPassword() + "'";
		List<Restaurant> restaurants = j.query(sql, new RestaurantMapper());
		return restaurants.size() > 0 ? restaurants.get(0) : null;
	}
	
	/**
	 * Get the restaurant by menuId. 
	 * @param menuId
	 * @return restaurant by menuid.
	 */
	public Restaurant getRestByMenuId(int menuId) {
		return j.queryForObject(SQL_GET_REST_BY_MENUID, new Object[] {menuId}, new RestaurantMapper());
	}
	
	public boolean editRestaurant(Restaurant rest) {
		return j.update(SQL_UPDATE_REST, rest.getRestImg(), rest.getRestId()) > 0;
	}
	
	public List<Map<String, Object>> getCategories() {
		return j.queryForList(SQL_GET_CATEGORIES);
	}
	
	public List<Map<String, Object>> getCatNameByCatId(int catId) {
		return j.queryForList(SQL_GET_CATNAME_BY_CATID, catId);
	}
}
