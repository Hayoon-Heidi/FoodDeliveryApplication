package com.csis3275.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.RestaurantMenu;
import com.csis3275.model.RestaurantMenuMapper;

@Component
public class RestaurantMenuDAOImpl {
	
JdbcTemplate j;
	
	private final String SQL_GET_ALL_MENU = "SELECT * FROM restMenu";
	private final String SQL_GET_MENU_BY_RESTAURANT = "SELECT * FROM restMenu WHERE restID = ?";
	private final String SQL_INSERT_RESTMENU = 
			"INSERT INTO restMenu(restId, menuName, menuDesc, menuPrice, menuImg) "
			+ "VALUES (?,?,?,?,?)";
	//private final String SQL_FIND_REST = "SELECT * FROM restMenu WHERE menuId = ?";
	private final String SQL_EDIT_MENU = "UPDATE restMenu SET menuName =?, menuDesc =?, menuPrice =?, menuImg =? WHERE menuId =?";
	private final String SQL_FIND_MENU = "SELECT * FROM restMenu WHERE menuId =?";
	
	
	@Autowired
	public RestaurantMenuDAOImpl(DataSource dataSource) {
		j = new JdbcTemplate(dataSource);
	}
	
	/*
	public RestaurantMenu getRestByID(int id) {
		return j.queryForObject(SQL_FIND_REST, new Object[] { id }, new RestaurantMenuMapper());
	}*/
	
	/**
	 * Insert new value into the restMenu.
	 * @param restMenu
	 * @return New value for restaurant menu.
	 */
	public boolean addMenu(RestaurantMenu restMenu) {
		return j.update(SQL_INSERT_RESTMENU, restMenu.getRestId(), restMenu.getMenuName(), restMenu.getMenuDesc(), restMenu.getMenuPrice(), restMenu.getMenuImg()) > 0;
	}
	
	//for testing
	public List<RestaurantMenu> getAllRestMenu() {
		return j.query(SQL_GET_ALL_MENU, new RestaurantMenuMapper());
	}
	
	/**
	 * Get the list of menu by restaurant id.
	 * @param restId
	 * @return List of menu by restaurant id.
	 */
	public List<RestaurantMenu> getAllRestMenu(int restId) {
		return j.query(SQL_GET_MENU_BY_RESTAURANT, new Object[] {restId}, new RestaurantMenuMapper());
		//return j.queryForList(SQL_GET_ALL_MENU, new Object[] {restId}, new RestaurantMenuMapper());
		//return j.queryForObject(SQL_GET_ALL_MENU, new Object[] {restId}, new RestaurantMenuMapper());
	}
	
	/**
	 * Update the database with new information of menu
	 * @param menu
	 * @return new values for menu by menuid.
	 */
	public boolean editMenu(RestaurantMenu menu) {
		return j.update(SQL_EDIT_MENU, menu.getMenuName(), menu.getMenuDesc(), menu.getMenuPrice(), menu.getMenuImg(), menu.getMenuId()) > 0;
	}
	
	/**
	 * Get the menu by menuid.
	 * @param id
	 * @return Menu information by menuid
	 */
	public RestaurantMenu getMenuById(int id) {
		return j.queryForObject(SQL_FIND_MENU, new Object[] {id}, new RestaurantMenuMapper());
	}
	
	
	
	

}
