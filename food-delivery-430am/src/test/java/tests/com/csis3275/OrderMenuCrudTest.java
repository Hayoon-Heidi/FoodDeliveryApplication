/**
 * 
 */
package tests.com.csis3275;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csis3275.config.AppConfig;
import com.csis3275.dao.OrderMenuDAOImpl;
import com.csis3275.model.OrderMenu;

/**
 * @author Yerin Shin
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes={AppConfig.class})
public class OrderMenuCrudTest {
	
	@Autowired
	OrderMenuDAOImpl dao;
	private OrderMenu orderMenu = new OrderMenu();
	
	@Autowired
	DataSource dataSource;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		/**
		 * menuName and menuPrice should be taken from the database
		 */
		orderMenu = new OrderMenu(1,1,1,"example",7.25);
	}

	@Test
	public void test() {
		assertEquals(0,0);
	}
	
	@Test
	public void daoTest() {
		assertNotNull(dao);
		assertNotNull(dataSource);
	}
	
	@Test
	@Order(3)
	public void insertOrderMenu() {
		assertEquals(true, dao.addOrderMenu(orderMenu , 1));
	}
	
	@Test
	@Order(4)
	public void testSubtotal() {
		double subtotal = orderMenu.getSubtotal();
		double testSubtotal = dao.getAllSubtotals().get(dao.getAllSubtotals().size()-1).getSubtotal();
		assertEquals(subtotal, testSubtotal,3);
	}
	
	@Test
	@Order(5)
	public void updateOrderMenu() {
		assertEquals(true, dao.updateOrderMenu(orderMenu));
	}

	@Test
	@Order(2)
	public void getOrderMenu() {
		assertNotNull(dao.getAllSubtotals());
	}

	@Test
	@Order(1)
	public void deleteOrderMenu() {
		assertEquals(true, dao.deleteOrderMenu(orderMenu.getMenuId(), orderMenu.getOrderId()));
	}
	
	@Test
	public void connectToDatabaseWithURL() throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test");
		connection.setAutoCommit(false);
	}
	
	@Test
	public void connectToDatabaseWithUsername() throws SQLException {
		dataSource.toString();
		dataSource.getConnection("sa", "");
	}

}
