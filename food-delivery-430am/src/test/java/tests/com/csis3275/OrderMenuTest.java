/**
 * 
 */
package tests.com.csis3275;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.csis3275.model.OrderMenu;

/**
 * @author Yerin Shin
 *
 */
public class OrderMenuTest {

	OrderMenu orderMenu = new OrderMenu();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		orderMenu = new OrderMenu(1,1,3,"menu",4.99);
	}

	@Test
	public void test() {
		double expected = 3*4.99;
		assertEquals(expected, orderMenu.getSubtotal(), 3);
	}

}
