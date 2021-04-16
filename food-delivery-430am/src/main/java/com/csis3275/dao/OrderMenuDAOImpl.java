package com.csis3275.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.Customer;
import com.csis3275.model.Order;
import com.csis3275.model.OrderMapper;
import com.csis3275.model.OrderMenu;
import com.csis3275.model.OrderMenuMapper;
import com.csis3275.model.RestaurantMenu;

@Component
public class OrderMenuDAOImpl {

	JdbcTemplate j;
	
	private final String SQL_GET_SUBTOTAL = "SELECT orderId, restMenu.menuId, quantity, menuName, menuPrice, \r\n" + 
			"restMenu.menuPrice * order_menu.quantity AS subtotal \r\n" + 
			"FROM order_menu\r\n" + 
			"INNER JOIN restMenu ON order_menu.menuId = restMenu.menuId;";
	private final String SQL_INSERT_ORDER = "INSERT INTO orders(customerId, driverId, orderedTime, orderStatus, orderStatusId)"
			+ "VALUES (?,?,?,?,?);";
	private final String SQL_GET_CURRENT_ORDER = "SELECT * FROM orders\r\n" + 
			"WHERE customerId=? AND driverId IS NULL AND orderedTime=?;";
	private final String SQL_INSERT_ORDERMENU = "INSERT INTO order_menu(menuId, orderId, quantity)"
			+ "VALUES (?,?,?)";
	private final String SQL_GET_ORDER_DETAILS = "";

	private final String SQL_GET_CUSTOMER_ORDER_HISTORY = "SELECT orders.orderId AS orderId, customerId, restMenu.menuId AS menuId, \r\n" + 
			"quantity, menuName, menuPrice, \r\n" + 
			"restMenu.menuPrice * order_menu.quantity AS subtotal \r\n" + 
			"FROM order_menu\r\n" + 
			"INNER JOIN restMenu ON order_menu.menuId = restMenu.menuId\r\n" + 
			"INNER JOIN orders ON order_menu.orderId = orders.orderId\r\n" + 
			"WHERE customerId = ? AND orders.orderId = ?;";
	private final String SQL_GET_ORDERS_BY_CUSTOMER = "SELECT * FROM orders WHERE customerId = ? ORDER BY orderedTime DESC;";

	private final String SQL_GET_ORDER = "SELECT orders.orderId, orders.orderedtime, orders.customerId, orders.driverId, orders.orderStatus, orders.orderStatusId "
			+ "FROM order_menu INNER JOIN orders ON order_menu.orderId = orders.orderId INNER JOIN restMenu ON order_menu.menuId = restmenu.menuId  "
			+ "WHERE restId = ? AND NOT orderstatusID=5  GROUP BY orders.orderId ORDER BY orders.orderStatusId";
	
	private final String SQL_GET_ORDER_D = "SELECT orders.orderId, orders.orderedtime, orders.customerId, orders.driverId, orders.orderStatus, orders.orderStatusId "
			+ "FROM order_menu INNER JOIN orders ON order_menu.orderId = orders.orderId INNER JOIN restMenu ON order_menu.menuId = restmenu.menuId  "
			+ "WHERE restId = ? AND orderstatusID=5  GROUP BY orders.orderId ORDER BY orders.orderStatusId";
	
	private final String SQL_GET_ORDER_BY_ID = "SELECT order_menu.orderid as orderid, order_menu.menuid as menuid, restmenu.menuname as menuname, order_menu.quantity as quantity, restmenu.menuprice as menuprice, restmenu.menudesc as menudesc, restMenu.menuPrice * order_menu.quantity AS subtotal FROM restmenu \r\n" + 
			"INNER JOIN order_menu ON restmenu.menuid = order_menu.menuid WHERE orderid =?";

	private final String SQL_DELETE_ORDERMENU = "DELETE FROM order_menu WHERE menuId = ? AND orderId = ?;";
	
	private final String SQL_UPDATE_STATUS_2 = "UPDATE orders SET orderStatusID = 2, orderStatus='Start Delivery' WHERE orderId =?";
	
	private final String SQL_UPDATE_STATUS_3= "UPDATE orders SET orderStatusID = 3, orderStatus='Finish Delivery' WHERE orderId =?";
	
	private final String SQL_UPDATE_STATUS_4= "UPDATE orders SET orderStatusID = 4, orderStatus='Delete' WHERE orderId =?";
	
	private final String SQL_UPDATE_STATUS_5= "UPDATE orders SET orderStatusID = 5, orderStatus='Done' WHERE orderId =?";
	

	
	
	

	private Object orderedTime;
	
	@Autowired
	public OrderMenuDAOImpl(DataSource dataSource) {
		j = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Calculate the subtotal of the ordering items.
	 * @return order information with subtotal.
	 */
	public List<OrderMenu> getAllSubtotals() {
		return j.query(SQL_GET_SUBTOTAL, new OrderMenuMapper());
	}
	
	/**
	 * Add order information into the order table when it's confirmed. 
	 * @param customer
	 * @return order information that confirmed.
	 */
	public boolean addOrder(Customer customer) {
		Date date = new Date();
		orderedTime = new java.sql.Timestamp(date.getTime());
		//System.out.println(j.update(SQL_INSERT_ORDER, customer.getId(), null, orderedTime) > 0);
		return j.update(SQL_INSERT_ORDER, customer.getId(), null, orderedTime, "Confirm the New order", 1) > 0;
	}
	
	/**
	 * Get the selected order by orderId.
	 * @param customerId
	 * @return Selected orderId.
	 */
	public int getCurrentOrderId(int customerId) {
		List<Order> list = j.query(SQL_GET_CURRENT_ORDER, new Object[] {customerId, orderedTime}, new OrderMapper());
		//System.out.println(list.get(list.size()-1).getOrderId());
		return list.get(list.size()-1).getOrderId();
	}
	
	
	/**
	 * Get the list of order by id.
	 * @param id
	 * @return list of orders.
	 */
	public List<Order> getAllOrders(int id) {
		return j.query(SQL_GET_ORDER, new Object[] { id }, new OrderMapper());
	}
	
	public List<Order> getDOrders(int id) {
		return j.query(SQL_GET_ORDER_D, new Object[] {id}, new OrderMapper());
	}
	
	/**
	 * Add order menu information into the order_menu table when it's confirmed. 
	 * @param orderMenu
	 * @param orderId
	 * @return order menu information that confirmed.
	 */
	public boolean addOrderMenu(OrderMenu orderMenu, int orderId) {
		return j.update(SQL_INSERT_ORDERMENU, orderMenu.getMenuId(), orderId, orderMenu.getQuantity())  > 0;
	}
	
	/**
	 * Get the order_menu history by customer id. It will show the orders by order id as well. 
	 * @param customerId
	 * @param orderId
	 * @return customer order_menu history by customer id.
	 */
	public List<OrderMenu> getCustomerOrderHistory(int customerId, int orderId) {
		return j.query(SQL_GET_CUSTOMER_ORDER_HISTORY, new Object[] {customerId, orderId}, new OrderMenuMapper());
	}
	
	/**
	 * Get the order history by customer id. It will show the orders by order id as well.
	 * @param customerId
	 * @return customer order history by customer id.
	 */
	public List<Order> getOrdersByCustomer(int customerId) {
		return j.query(SQL_GET_ORDERS_BY_CUSTOMER, new Object[] {customerId}, new OrderMapper());
	}
	
	/**
	 * Get the order_menu information by order id.  
	 * @param id
	 * @return order_menu information by order id.
	 */
	public List<OrderMenu> getOrderById(int id) {
		return j.query(SQL_GET_ORDER_BY_ID, new Object[] {id}, new OrderMenuMapper());
	}
		
	public boolean deleteOrderMenu(int menuId, int orderId) {
		return j.update(SQL_DELETE_ORDERMENU, menuId, orderId) > 0;
	}
	
	/**
	 * This method is for junit crud test
	 * @param orderMenu
	 * @return
	 */
	public boolean updateOrderMenu(OrderMenu orderMenu) {
		String sql = "UPDATE order_menu SET quantity = ? WHERE menuId = ? AND orderId = ?";
		return j.update(sql, orderMenu.getQuantity(), orderMenu.getMenuId(), orderMenu.getOrderId()) > 0;
	}
	
	public boolean updateOrderStatus(Order order, int orderId) {
		
		orderId = order.getOrderId();
		
		System.out.println(order.getOrderStatus());
		
		System.out.println(order.getOrderId());
		
		if (order.getOrderStatus().equals("Confirm the New order")) {
			System.out.println("1");
			return j.update(SQL_UPDATE_STATUS_2, orderId) > 0;
		} else if (order.getOrderStatus().equals("Start Delivery"))  {
			System.out.println("2");
			return j.update(SQL_UPDATE_STATUS_3, orderId) > 0;
		} else if (order.getOrderStatus().equals("Finish Delivery"))  {
			System.out.println("3");
			return j.update(SQL_UPDATE_STATUS_4, orderId) > 0;
		} else  {
			System.out.println("4");
			return j.update(SQL_UPDATE_STATUS_5, orderId) > 0;
		}
		
		
	}
	
}
