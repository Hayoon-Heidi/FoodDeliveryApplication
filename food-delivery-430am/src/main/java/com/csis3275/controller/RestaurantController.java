package com.csis3275.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csis3275.dao.CustomerDAOImpl;
import com.csis3275.dao.OrderMenuDAOImpl;
import com.csis3275.dao.RestaurantDAOImpl;
import com.csis3275.dao.RestaurantMenuDAOImpl;
import com.csis3275.model.Customer;
import com.csis3275.model.Category;
import com.csis3275.model.FileInfo;
import com.csis3275.model.Login;
import com.csis3275.model.Order;
import com.csis3275.model.OrderMenu;
import com.csis3275.model.Restaurant;
import com.csis3275.model.RestaurantMenu;
import com.csis3275.service.FileUploadService;

@Controller
public class RestaurantController {
	
	@Autowired
	RestaurantDAOImpl restaurantDAOImp; 
	@Autowired
	RestaurantMenuDAOImpl restaurantMenuDAOImp;
	@Autowired
	OrderMenuDAOImpl orderMenuDAOImp;
	@Autowired
	CustomerDAOImpl customerDAOImp;
	@Autowired
	FileUploadService fileUploadService;
	
	@ModelAttribute("restaurant")
	public Restaurant setupAddForm() {
		return new Restaurant();
	}
	
	@ModelAttribute("restaurantMenu")
	public RestaurantMenu setUpAddForm() {
		return new RestaurantMenu();
	}
	
	@ModelAttribute("order")
	public Order setUpAddForm1() {
		return new Order();
	}
	
	/**
	 * Model about category which will be used to categories the restaurant. 
	 * @return category list for restaurant table.
	 */
	@ModelAttribute("category")
	public List<Category> initializeCategory() {
		List<Category> category = new ArrayList<>();
		List<Map<String,Object>> list = new ArrayList<>();
		list = restaurantDAOImp.getCategories();
		for (int i=0; i<list.size(); i++) {
			Category c = new Category((int) list.get(i).get("catId"),(String) list.get(i).get("catName"));
			category.add(c);
		}
		return category;
	}
	
	/**
	 * Test page.
	 * @param session
	 * @param model
	 * @return restaurant_info
	 */
	@GetMapping("/restaurant/restaurant_info")
	public String test_showRestaurant(HttpSession session, Model model, int id, HttpServletRequest request) {
		session = request.getSession();
		session.setAttribute("restId", id);
		Restaurant restaurant = (Restaurant) session.getAttribute("signedInUser");
		
		System.out.println("Restaurnat id : " + id);
		
		//Restaurant restaurant = restaurantDAOImp.getRestById(id);
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("signedInUser", restaurant);
		return "/restaurant/restaurant_info";			
	}	
	
	/**
	 * Registration page for restaurant users. 
	 * They can create a new account from here. 
	 * @param session
	 * @param model
	 * @return restaurant registration page
	 */
	@GetMapping("/restaurant/restaurant_registration")
	public String restaurant_registration(HttpSession session, Model model) {
		return "/restaurant/restaurant_registration";
	}
	
	/**
	 * Restaurant users can check and add their menu here. 
	 * @param id
	 * @param session
	 * @param model
	 * @param request
	 * @return restaurant menu page
	 */
	@GetMapping("/restaurant/restaurant_menu")
	public String restaurant_menu(@RequestParam(required=true) int id, 
			HttpSession session, Model model, HttpServletRequest request) {
		
		//put restaurant Id into session
		session = request.getSession();
		session.setAttribute("restId", id);
		
		List<Restaurant> restaurant = restaurantDAOImp.getAllRestaurant();
		model.addAttribute("restaurantList", restaurant);
		List<RestaurantMenu> restaurantMenu = restaurantMenuDAOImp.getAllRestMenu(id);
		model.addAttribute("restaurantMenuList", restaurantMenu);
		
		return "/restaurant/restaurant_menu";
	}	
	
	/**
	 * When the restaurant user add new menu from the restaurant_menu page, this will triggered. 
	 * addMenu class will send a new menu to the database and get a list of menus from the controller. 
	 * @param menu
	 * @param model
	 * @param request
	 * @return restaurant menu page
	 */
	@PostMapping("/addMenu")
	public String addMenu(@RequestParam("menuImgFile") MultipartFile file, @ModelAttribute("restaurantMenu") RestaurantMenu menu, 
			Model model, HttpServletRequest request)	{
		
		//get restaurant Id from session
		HttpSession session = request.getSession();
		int restId = (Integer) session.getAttribute("restId");
		menu.setRestId(restId);
		
		String url = fileUploadService.restore(file);
		menu.setMenuImg(url);
		restaurantMenuDAOImp.addMenu(menu);
		
		//Get a list of menus from the controller
		List<RestaurantMenu> menuList = restaurantMenuDAOImp.getAllRestMenu(restId);
		model.addAttribute("restaurantMenuList", menuList);
		
		return "/restaurant/restaurant_menu";
	}
	
	/**
	 * This page will appeared for the users who made their account successfully. 
	 * They can go back to the home page and log in with their new account.
	 * @param newRestaurant
	 * @param model
	 * @return registration success page.
	 */
	@PostMapping("/restaurant/restaurant_registration")
	public String restaurant_registration(@ModelAttribute("restaurant")
	Restaurant newRestaurant, Model model) {
		restaurantDAOImp.createRestaurant(newRestaurant);
		
		List<RestaurantMenu> restaurantMenu = restaurantMenuDAOImp.getAllRestMenu();
		model.addAttribute("restMenuList", restaurantMenu);
		return "registrationSuccess";
	}
	
	/**
	 *  User can edit their restaurant's menu from here. 
	 * @param id
	 * @param model
	 * @return restaurant edit menu page
	 */
	@GetMapping("/restaurant/restaurant_editMenu")
	public String editMenu(@RequestParam(required = true) int id, Model model) {
		RestaurantMenu menu = restaurantMenuDAOImp.getMenuById(id);
		model.addAttribute("menu", menu);
		return "/restaurant/restaurant_editMenu";
	}
	
	/**
	 * After user changed the menu from edit menu page, it will return to restaurant menu page with changed list.
	 * @param menu
	 * @param model
	 * @param request
	 * @return restaurant menu page
	 */
	@PostMapping("/restaurant/restaurant_editMenu")
	public String updateMenu(@RequestParam("menuImgFile") MultipartFile file, @ModelAttribute("menu") RestaurantMenu menu, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Restaurant restaurant = (Restaurant) session.getAttribute("signedInUser");
		session.setAttribute("signedInUser", restaurant);
		int restId = (Integer) session.getAttribute("restId");
		
		String url = fileUploadService.restore(file);
		menu.setMenuImg(url);
		restaurantMenuDAOImp.editMenu(menu);
		//model.addAttribute("loggedInRest", loggedInRestaurant);
		
		List<RestaurantMenu> menuList = restaurantMenuDAOImp.getAllRestMenu(restId);
		model.addAttribute("restaurantMenuList", menuList);
		return "/restaurant/restaurant_menu";
	}
	
	/**
	 * Log in page for restaurant users. 
	 * @param session
	 * @param model
	 * @return restaurant login page
	 */
	@GetMapping("/restaurant/restaurant_login")
	public String restaurant_login(HttpSession session, Model model) {
		
		return "/restaurant/restaurant_login";	
	}
	
	/**
	 * This page shows the order description for restaurant users. They can check the menu, quatity, and the price of the order. 
	 * @param id
	 * @param model
	 * @return restaurant order page
	 */
	@GetMapping("/restaurant/order")
	public String order(@RequestParam(required = true) int id, Model model,  HttpServletRequest request) {


		System.out.println("Order ID : " + id);
				
		HttpSession session = request.getSession();
		Restaurant restaurant = (Restaurant) session.getAttribute("signedInUser");
		session.setAttribute("signedInUser", restaurant);
		
		int restId = restaurant.getRestId();
		
		System.out.println("Restaurant Id : " +restId);
		
		List<Order> orderList = orderMenuDAOImp.getAllOrders(restId);
		model.addAttribute("orderList", orderList);
		
		
		List<OrderMenu> orderMenuList = orderMenuDAOImp.getOrderById(id);
		model.addAttribute("orderedMenuList", orderMenuList);
		model.addAttribute("orderId", id);
		
		double total = 0;
		int[] customerId = new int[orderList.size()];
		int[] ordersOrder = new int[orderList.size()];
		int customerIdInt = 0;
		
		for(int i=0; i<orderMenuList.size(); i++)  {
			total += orderMenuList.get(i).getSubtotal(); 
		}
		
		for (int i=0; i<orderList.size(); i++) {
			customerId[i] = orderList.get(i).getCustomerId();
			ordersOrder[i] = orderList.get(i).getOrderId();
			System.out.println("Customer Id : " + customerId[i]);
			System.out.println("order Id : " + ordersOrder[i]);
		}
		
		model.addAttribute("Total", total);
		
		for (int i=0; i<orderList.size(); i++) {
			
			if (ordersOrder[i] == id) {
				customerIdInt = customerId[i];
			}
			
		};
		
		
		System.out.println("customer Id : " + customerIdInt);		
		
		Customer customer = (Customer) customerDAOImp.getCustomerById(customerIdInt);
		String customerName = customer.getFirstName() + " " + customer.getLastName();
		String customerContact = customer.getPhoneNumber();
		String customerAddress = customer.getAddress();
		

		System.out.println(customerName);
		
		model.addAttribute("customerName", customerName);
		model.addAttribute("customerContact", customerContact);
		model.addAttribute("customerAddress", customerAddress);

		
		return "/restaurant/order";
		
	}
	
	
	@GetMapping("/restaurant/order_deleted")
	public String order_deleted(@RequestParam(required = true) int id, Model model,  HttpServletRequest request) {


		System.out.println("Order ID : " + id);
				
		HttpSession session = request.getSession();
		Restaurant restaurant = (Restaurant) session.getAttribute("signedInUser");
		session.setAttribute("signedInUser", restaurant);
		
		int restId = restaurant.getRestId();
		
		System.out.println("Restaurant Id : " +restId);
		
		List<Order> orderList = orderMenuDAOImp.getDOrders(restId);
		model.addAttribute("orderList", orderList);
		
		
		List<OrderMenu> orderMenuList = orderMenuDAOImp.getOrderById(id);
		model.addAttribute("orderedMenuList", orderMenuList);
		model.addAttribute("orderId", id);
		
		double total = 0;
		int[] customerId = new int[orderList.size()];
		int[] ordersOrder = new int[orderList.size()];
		int customerIdInt = 0;
		
		for(int i=0; i<orderMenuList.size(); i++)  {
			total += orderMenuList.get(i).getSubtotal(); 
		}
		
		for (int i=0; i<orderList.size(); i++) {
			customerId[i] = orderList.get(i).getCustomerId();
			ordersOrder[i] = orderList.get(i).getOrderId();
			System.out.println("Customer Id : " + customerId[i]);
			System.out.println("order Id : " + ordersOrder[i]);
		}
		
		model.addAttribute("Total", total);
		
		for (int i=0; i<orderList.size(); i++) {
			
			if (ordersOrder[i] == id) {
				customerIdInt = customerId[i];
			}
			
		};
		
		
		System.out.println("customer Id : " + customerIdInt);		
		
		Customer customer = (Customer) customerDAOImp.getCustomerById(customerIdInt);
		String customerName = customer.getFirstName() + " " + customer.getLastName();
		String customerContact = customer.getPhoneNumber();
		String customerAddress = customer.getAddress();
		

		System.out.println(customerName);
		
		model.addAttribute("customerName", customerName);
		model.addAttribute("customerContact", customerContact);
		model.addAttribute("customerAddress", customerAddress);

		
		return "/restaurant/order_deleted";
		
	}
	
	/**
	 * Log ing validation for restaurant users. 
	 * @param login
	 * @param model
	 * @param request
	 * @return restaurant login
	 */
	@PostMapping("/restaurant/restaurant_login")
	public String restaurant_login(@ModelAttribute("login")
	Login login, Model model, HttpServletRequest request) {
		Restaurant restaurant = restaurantDAOImp.validateRestaurant(login);
		
		if (restaurant != null) {
			HttpSession session = request.getSession();
			session.setAttribute("signedInUser", restaurant);
			model.addAttribute("loggedInRest", restaurant);
			model.addAttribute("welcomeMessage","Login Success!");					
			int restId = restaurant.getRestId();

			List<Order> orderList = orderMenuDAOImp.getAllOrders(restId);
			model.addAttribute("orderList", orderList);
			
			return "/restaurant/restaurant_mypage";
		} else {
			model.addAttribute("message", "Login fail:(");
			return "/restaurant/restaurant_login";
		}	
	}
	
	@GetMapping("/restaurant/restaurant_mypage")
	public String restaurant_mypage(HttpSession session, Model model, HttpServletRequest request) {
		
		session = request.getSession();
		Restaurant restaurant = (Restaurant) session.getAttribute("signedInUser");
		session.setAttribute("signedInUser", restaurant);
		model.addAttribute("loggedInRest", restaurant);
		model.addAttribute("welcomeMessage","Login Success!");					
		int restId = restaurant.getRestId();

		List<Order> orderList = orderMenuDAOImp.getAllOrders(restId);
		model.addAttribute("orderList", orderList);
		
		
		return "/restaurant/restaurant_mypage";
		
	}
	
	@GetMapping("/restaurant/restaurant_deleted")
	public String restaurant_deleted(HttpSession session, Model model, HttpServletRequest request) {
		
		session = request.getSession();
		Restaurant restaurant = (Restaurant) session.getAttribute("signedInUser");
		session.setAttribute("signedInUser", restaurant);
		model.addAttribute("loggedInRest", restaurant);
		model.addAttribute("welcomeMessage","Login Success!");					
		int restId = restaurant.getRestId();

		List<Order> orderList = orderMenuDAOImp.getDOrders(restId);
		model.addAttribute("orderList", orderList);
		
		
		return "/restaurant/restaurant_deleted";
		
	}
	
	
	@PostMapping("/restaurant/restaurant_mypage")
	public String restaurant_mypage(@ModelAttribute("order") Order order, Model model, HttpServletRequest request) {
		

		int orderId = order.getOrderId();
//Order myOrder = (Order) orderMenuDAOImp.getOrderById(orderId);
		
		System.out.println("Order Id : " + orderId);
		
		orderMenuDAOImp.updateOrderStatus(order, orderId);
		
		HttpSession session = request.getSession();
		Restaurant restaurant = (Restaurant) session.getAttribute("signedInUser");
		session.setAttribute("signedInUser", restaurant);
		model.addAttribute("loggedInRest", restaurant);
		model.addAttribute("welcomeMessage","Login Success!");					
		int restId = restaurant.getRestId();

		List<Order> orderList = orderMenuDAOImp.getAllOrders(restId);
		model.addAttribute("orderList", orderList);
		
		
		return "/restaurant/restaurant_mypage";
		

	}
	
	@PostMapping("/checkOrder")
	public String checkOrder(@ModelAttribute("orderedMenuList") RestaurantMenu menu, 
			Model model, HttpServletRequest request)	{
		
		//get restaurant Id from session
		HttpSession session = request.getSession();
		int restId = (Integer) session.getAttribute("restId");
		
		//Get a list of menus from the controller
		List<RestaurantMenu> menuList = restaurantMenuDAOImp.getAllRestMenu(restId);
		model.addAttribute("restaurantMenuList", menuList);
		List<Order> orderList = orderMenuDAOImp.getAllOrders(restId);
		model.addAttribute("orderList", orderList);
		
		return "/restaurant/restaurant_menu";
	}
		
	@PostMapping("/editRestInfo")
	public String editRestInfo(Model model, @RequestParam("file1") MultipartFile file, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		Restaurant loggedInRestaurant = (Restaurant) session.getAttribute("signedInUser");
		
		String url = fileUploadService.restore(file);
		loggedInRestaurant.setRestImg(url);
		restaurantDAOImp.editRestaurant(loggedInRestaurant);
		model.addAttribute("signedInUser", loggedInRestaurant);
		
		List<Order> orderList = orderMenuDAOImp.getAllOrders(loggedInRestaurant.getRestId());
		model.addAttribute("orderList", orderList);
		
		return "/restaurant/restaurant_info";
	}
	
	
}
