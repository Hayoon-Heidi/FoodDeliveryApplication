package com.csis3275.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.csis3275.dao.CustomerDAOImpl;
import com.csis3275.dao.OrderMenuDAOImpl;
import com.csis3275.dao.RestaurantDAOImpl;
import com.csis3275.dao.RestaurantMenuDAOImpl;
import com.csis3275.dao.ReviewDAOImpl;
import com.csis3275.model.Customer;
import com.csis3275.model.Order;
import com.csis3275.model.OrderMenu;
import com.csis3275.model.Restaurant;
import com.csis3275.model.RestaurantMenu;
import com.csis3275.model.Review;
import com.csis3275.model.SortRestaurant;

@Controller
public class OrderController {
	private List<OrderMenu> cart = new ArrayList<>();
	private double total = 0;
	
	@Autowired
	CustomerController customerController;
	
	@Autowired
	CustomerDAOImpl customerDAOImp;
	
	@Autowired
	RestaurantDAOImpl restaurantDAOImp; 
	
	@Autowired
	RestaurantMenuDAOImpl restaurantMenuDAOImp;
	
	@Autowired
	OrderMenuDAOImpl orderMenuDAOImp;

	@Autowired
	ReviewDAOImpl reviewDAOImp;
	
	@ModelAttribute("customer")
	public Customer setupAddForm() {
		return new Customer();
	}
	
	@ModelAttribute("restaurant")
	public Restaurant setAddForm() {
		return new Restaurant();
	}
	
	@ModelAttribute("/restaurant/restaurantMenu")
	public RestaurantMenu setUpAddForm() {
		return new RestaurantMenu();
	}
	
	@ModelAttribute("orderMenu")
	public OrderMenu setUpAdd() {
		return new OrderMenu();
	}
	
	@ModelAttribute("review")
	public Review setupReviewAdd() {
		return new Review();
	}
	
	@ModelAttribute("sortRestaurant")
	public SortRestaurant setupSort() {
		return new SortRestaurant();
	}
	
	/**
	 * Verifies that customer is logged in
	 * @param session
	 * @param model
	 * @return boolean
	 */
	private boolean verifyCustomerLogin(HttpSession session, Model model) {
		return session.getAttribute("signedInUser") != null;
	}
	
	/**
	 * Get logged in customer
	 * @param session
	 * @param model
	 * @return customer
	 */
	private Customer getLoggedInCustomer(HttpSession session, Model model) {
		Customer customer = (Customer) session.getAttribute("signedInUser");		
		model.addAttribute("loggedInCustomer", customer);
		return customer;
	}
	
	/**
	 * Links to login page
	 * @param session
	 * @param model
	 * @return login page
	 */
	private String redirectToLogin(HttpSession session, Model model) {
		return customerController.customer_login(session, model);
	}
	
	/**
	 * This page will show the list of the restaurant where customer can order food.
	 * @param session
	 * @param model
	 * @param request
	 * @return order restaurant list page
	 */
	@GetMapping("/order/order_restaurantList")
	public String restaurantList(@RequestParam(required=false) Integer cat, HttpSession session, Model model, HttpServletRequest request) {
		session = request.getSession();
		
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		getLoggedInCustomer(session, model);
		
		//get restaurant list based on each category
		List<Restaurant> restaurant = new ArrayList<>();
		if (cat == null) {
			restaurant = restaurantDAOImp.getAllRestaurant();
			
		} else {
			restaurant = restaurantDAOImp.getRestaurantsByCategory(cat);
		}
		
		//put category id to session
		session.setAttribute("category", cat);
			

		List<String> starRatings = new ArrayList<>();
		List<String> categoryNames = new ArrayList<>();
		for (int i=0; i<restaurant.size(); i++) {
			starRatings.add(getStarRatingValue(getTotalRatings(restaurant.get(i).getRestId())));
			
			if (getTotalRatings(restaurant.get(i).getRestId()).equals("No reviews yet")) {
				restaurant.get(i).setRating(0);
			} else {
				restaurant.get(i).setRating(Double.valueOf(getTotalRatings(restaurant.get(i).getRestId())));	
			}
			restaurant.get(i).setStarRating(getStarRatingValue(getTotalRatings(restaurant.get(i).getRestId())));
			
			String catName = (String) restaurantDAOImp.getCatNameByCatId(Integer.valueOf(restaurant.get(i).getCategory())).get(0).get("catName");
			categoryNames.add(catName);
			restaurant.get(i).setCatName(catName);
			setNumOfReviews(restaurant.get(i));
			
		}
		
		model.addAttribute("restaurantList", restaurant);
		//model.addAttribute("starRatings", starRatings);
		//model.addAttribute("categories", categoryNames);
		
		return "/order/order_restaurantList";
	}
	
	/**
	 * Sort Restaurants By selected criteria
	 * @param sort
	 * @param session
	 * @param model
	 * @param request
	 * @return order_restaurantList.jsp
	 */
	@PostMapping("/sort")
	public String sortRestaurant(@ModelAttribute("sortRestaurant") SortRestaurant sort, HttpSession session, Model model, HttpServletRequest request) {
		session = request.getSession();
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		getLoggedInCustomer(session, model);
		//get restaurant list based on each category
		List<Restaurant> restaurant = new ArrayList<>();
		if (session.getAttribute("category") == null) {
			restaurant = restaurantDAOImp.getAllRestaurant();
		} else {
			restaurant = restaurantDAOImp.getRestaurantsByCategory((int) session.getAttribute("category"));
		}
		
		for (int i=0; i<restaurant.size(); i++) {			
			if (getTotalRatings(restaurant.get(i).getRestId()).equals("No reviews yet")) {
				restaurant.get(i).setRating(0);
			} else {
				restaurant.get(i).setRating(Double.valueOf(getTotalRatings(restaurant.get(i).getRestId())));	
			}
			restaurant.get(i).setStarRating(getStarRatingValue(getTotalRatings(restaurant.get(i).getRestId())));
			
			String catName = (String) restaurantDAOImp.getCatNameByCatId(Integer.valueOf(restaurant.get(i).getCategory())).get(0).get("catName");
			restaurant.get(i).setCatName(catName);
			setNumOfReviews(restaurant.get(i));
		}
		
		if (sort.getSortBy().equals("Sort By")) {
			
		} else if (sort.getSortBy().equals("Ratings")) {
			Collections.sort(restaurant, new Comparator<Restaurant>() {
			    public int compare(Restaurant left, Restaurant right)  {
			        //return (int) (right.getRating() - left.getRating()); // The order depends on the direction of sorting.
			    	return Double.compare(right.getRating(), left.getRating());
			    	//return left.getDescription().compareTo(right.getDescription());
			    }
			});
		} else if (sort.getSortBy().equals("NumOfReviews")) {
			Collections.sort(restaurant, new Comparator<Restaurant>() {
			    public int compare(Restaurant left, Restaurant right)  {
			        return right.getNumOfReviews() - left.getNumOfReviews(); // The order depends on the direction of sorting.
			    	//return Double.compare(right.getNumOfReviews(), left.getRating());
			    	//return left.getDescription().compareTo(right.getDescription());
			    }
			});
		}
		
		model.addAttribute("restaurantList", restaurant);
		
		return "/order/order_restaurantList";
	}
	
	@PostMapping("/search")
	public String searchRestByName(@ModelAttribute("restaurant") Restaurant searchRest, Model model, HttpSession session,
			HttpServletRequest request) {
		session = request.getSession();
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		getLoggedInCustomer(session, model);
		//get restaurant list based on each category
		List<Restaurant> restaurant = new ArrayList<>();
		if (session.getAttribute("category") == null) {
			restaurant = restaurantDAOImp.searchRestaurantsByName(searchRest.getRestName());
		} else {
			restaurant = restaurantDAOImp.searchRestaurantsByNameWithCategory((int) session.getAttribute("category"), searchRest.getRestName());
		}
		
		for (int i=0; i<restaurant.size(); i++) {			
			if (getTotalRatings(restaurant.get(i).getRestId()).equals("No reviews yet")) {
				restaurant.get(i).setRating(0);
			} else {
				restaurant.get(i).setRating(Double.valueOf(getTotalRatings(restaurant.get(i).getRestId())));	
			}
			restaurant.get(i).setStarRating(getStarRatingValue(getTotalRatings(restaurant.get(i).getRestId())));
			
			String catName = (String) restaurantDAOImp.getCatNameByCatId(Integer.valueOf(restaurant.get(i).getCategory())).get(0).get("catName");
			restaurant.get(i).setCatName(catName);
			setNumOfReviews(restaurant.get(i));
		}
		
		model.addAttribute("restaurantList", restaurant);
		return "/order/order_restaurantList";
	}
	
	/**
	 * When customer choose a restaurant to order, this page will show the list of the menu of the restaurant. 
	 * Customer can add menu in the cart and proceed order. 
	 * @param id
	 * @param session
	 * @param model
	 * @param request
	 * @return menu list of restaurant page.
	 */
	@GetMapping("/order/menuList")
	public String menuList(@RequestParam(required=true) int id, HttpSession session, 
			Model model, HttpServletRequest request) {
		session = request.getSession();
		//get login info
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		getLoggedInCustomer(session, model);
		//put restaurant Id into session
		session.setAttribute("restId", id);
		if (id == 0) {
			id = (Integer) session.getAttribute("restId");
		}
		
		//get menus by restaurant id
		Restaurant restaurant = restaurantDAOImp.getRestById(id);
		model.addAttribute("restaurant", restaurant);
		List<RestaurantMenu> restaurantMenu = restaurantMenuDAOImp.getAllRestMenu(id);
		model.addAttribute("restaurantMenuList", restaurantMenu);
		
		//get all order history (for debugging)
		List<OrderMenu> orderedMenus = orderMenuDAOImp.getAllSubtotals();
		model.addAttribute("orderedMenuList", orderedMenus);
		
		cart.clear();
		total = 0;
		
		//Get Reviews for the restaurant
		getReviewsForRestaurant(model, id);
		
		model.addAttribute("cart", cart);
		model.addAttribute("cartTotal", total);
		model.addAttribute("count", cart.size());
		//put star rating to model here!!!!
		model.addAttribute("starRating", getStarRatingValue(getTotalRatings(id)));
		
		
		return "/order/menuList";
	}
	
	private void getReviewsForRestaurant(Model model, int restId) {
		List<Review> reviews = reviewDAOImp.getReviewByRestaurant(restId);
		Collections.reverse(reviews);
		
		for (int i=0; i<reviews.size(); i++) {
			String c = reviews.get(i).getReviewContent();
			System.out.println(c);
		}
		
		if (!reviews.isEmpty()) {
			//오더 아이디가 같은 리뷰끼리 묶은 리뷰리스트들을 묶은 리스트 ㅋㅋ //아니 이게 뭐얔ㅋㅋㅋㅋㅋㅋㅋ
			List<List<Review>> resultReviewList = new ArrayList<>();
			List<Review> reviewsForOneOrder = new ArrayList<>();
			int orderId = reviews.get(0).getOrderId();
			for(int i=0; i<reviews.size(); i++) {
				reviews.get(i).calcStarRating();
				if (orderId == reviews.get(i).getOrderId()) {
					reviewsForOneOrder.add(reviews.get(i));
					if (i == reviews.size()-1) {
						resultReviewList.add(reviewsForOneOrder);
					}
				} else {
					resultReviewList.add(reviewsForOneOrder);
					orderId = reviews.get(i).getOrderId();
					reviewsForOneOrder = new ArrayList<>();
					reviewsForOneOrder.add(reviews.get(i));
					if (i == reviews.size()-1) {
						resultReviewList.add(reviewsForOneOrder);
					}
				}
			}
			
			System.out.println("ResultReview size:" +resultReviewList.size());

			/*
			for(int i=0; i<resultReviewList.size(); i++) {
				System.out.println(resultReviewList.get(i).get(i).getOrderId());
			}*/
			
			model.addAttribute("ratings", getTotalRatings(restId));
			model.addAttribute("review", reviews);
			model.addAttribute("reviewList", resultReviewList);
			model.addAttribute("reviewExist", true);
		} else {
			System.out.println("no review");
			model.addAttribute("reviewExist", false);
		}
	}
	
	/**
	 * This class is for add menu into the cart function of the ordering feature. 
	 * @param addedMenu
	 * @param model
	 * @param request
	 * @param session
	 * @return add menu to the cart. (still in the same page.)
	 */
	@PostMapping("/addCart")
	public String addCart(@ModelAttribute("orderMenu") OrderMenu addedMenu,
			Model model, HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		//get login info
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		getLoggedInCustomer(session, model);
		//put restaurant Id into session
		int id = (Integer) session.getAttribute("restId");
		session.setAttribute("restId", id);
				
		//get menus by restaurant id
		Restaurant restaurant = restaurantDAOImp.getRestById(id);
		model.addAttribute("restaurant", restaurant);
		List<RestaurantMenu> restaurantMenu = restaurantMenuDAOImp.getAllRestMenu(id);
		model.addAttribute("restaurantMenuList", restaurantMenu);
		
		/*
		//get cart info if it exists in the session --test- ----test-----------------------!!!
		if (session.getAttribute("cart") != null) {
			cart = (List<OrderMenu>) session.getAttribute("cart");
			total = (double) session.getAttribute("cartTotal");
		}*/
		
		//initialize total to prevent from being doubled every time the user updates the cart
		total = 0;
		
		boolean sameMenuExists = false;
		for (int i=0; i<cart.size(); i++) {
			if (cart.get(i).getMenuId() == addedMenu.getMenuId()) {
				sameMenuExists = true;
				cart.get(i).setQuantity(cart.get(i).getQuantity() + addedMenu.getQuantity());
				cart.get(i).calcSubtotal();
			}
		}
		
		if (!sameMenuExists) {
			addedMenu.calcSubtotal();
			cart.add(addedMenu);
		}
		
		for(int i=0; i<cart.size(); i++)  {
			total += cart.get(i).getSubtotal(); 
			System.out.println("cartsize:"+cart.size()+" "+i+"; Now total is: "+total);
		}
		
		//System.out.println(addedMenu.getMenuName());
		//System.out.println(addedMenu.getMenuPrice());
		
		//Get Reviews for the restaurant
		getReviewsForRestaurant(model, id);

		model.addAttribute("cart", cart);
		model.addAttribute("cartTotal", total);
		model.addAttribute("count", cart.size());
		//put star rating to model here!!!!
		model.addAttribute("starRating", getStarRatingValue(getTotalRatings(id)));
		//TODO: To maintain the shopping cart among the pages, work with session
		session.setAttribute("cart", cart);
		session.setAttribute("cartTotal", total);
		
		return "/order/menuList";
	}
	
	/**
	 * Delete menu from the cart function used in order page. 
	 * @param index
	 * @param model
	 * @param request
	 * @param session
	 * @return delete menu from the cart. (still in the same page)
	 */
	@GetMapping("/deleteCart")
	public String deleteCart(@RequestParam(required=true) int index, Model model, HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		//get login info
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		getLoggedInCustomer(session, model);
		//put restaurant Id into session
		int id = (Integer) session.getAttribute("restId");
		session.setAttribute("restId", id);
				
		//get menus by restaurant id
		Restaurant restaurant = restaurantDAOImp.getRestById(id);
		model.addAttribute("restaurant", restaurant);
		List<RestaurantMenu> restaurantMenu = restaurantMenuDAOImp.getAllRestMenu(id);
		model.addAttribute("restaurantMenuList", restaurantMenu);
		
		total = 0;
		cart.remove(index);
		for(int i=0; i<cart.size(); i++)  {
			total += cart.get(i).getSubtotal(); 
			System.out.println("cartsize:"+cart.size()+" "+i+"; Now total is: "+total);
		}
		model.addAttribute("cart", cart);
		model.addAttribute("cartTotal", total);
		model.addAttribute("count", cart.size());
		//TODO: To maintain the shopping cart among the pages, work with session
		session.setAttribute("cart", cart);
		session.setAttribute("cartTotal", total);
		
		//Get Reviews for the restaurant
		getReviewsForRestaurant(model, id);
		//put star rating to model here!!!!
		model.addAttribute("starRating", getStarRatingValue(getTotalRatings(id)));
		
		return "/order/menuList";
	}
	
	/**
	 * Informations to complete order. (name, address, payment method)
	 * @param orderMenu
	 * @param model
	 * @param request
	 * @param session
	 * @return checkout page 
	 */
	@PostMapping("/checkout")
	public String checkout(@ModelAttribute("orderMenu") OrderMenu orderMenu, 
			Model model, HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		//get login info
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		getLoggedInCustomer(session, model);
		//put restaurant Id into session
		int id = (Integer) session.getAttribute("restId");
		session.setAttribute("restId", id);
		//get restaurant info
		Restaurant restaurant = restaurantDAOImp.getRestById(id);
		model.addAttribute("restaurant", restaurant);
		//get cart info
		List<OrderMenu> cart = (List<OrderMenu>) session.getAttribute("cart");
		total = (double) session.getAttribute("cartTotal");
		
		if (!cart.isEmpty()) {
			model.addAttribute("cart", cart);
			model.addAttribute("cartTotal", total);
			model.addAttribute("count", cart.size());
			
			return "/order/checkout";
		} else {
			return "/order/menuList";
		}
		
	}
	
	/**
	 * When the order checkout successes, page will show the message that order confirmed and returns to the main page. 
	 * @param orderMenu
	 * @param model
	 * @param request
	 * @param session
	 * @return welcome page. (main page for customer)
	 */
	@PostMapping("/checkoutSuccess")
	public String checkoutSuccess(@ModelAttribute("orderMenu") OrderMenu orderMenu,
			Model model, HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		//get login info
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		Customer customer = getLoggedInCustomer(session, model);
		//get cart info
		List<OrderMenu> cart = (List<OrderMenu>) session.getAttribute("cart");
		total  = (double) session.getAttribute("cartTotal");
		
		orderMenuDAOImp.addOrder(customer);
		int orderId = orderMenuDAOImp.getCurrentOrderId(customer.getId());

		//System.out.println("orderId: " + orderId);

		System.out.println("orderId: " + orderId);

		
		//System.out.println("cart size: "+cart.size());
		for (int i=0; i<cart.size(); i++) {
			orderMenuDAOImp.addOrderMenu(cart.get(i), orderId);
			int m= cart.get(i).getMenuId();
			int q= cart.get(i).getQuantity();
			//System.out.println("menuID & quantity: " + m +" & "+q);
		}
		
		cart.clear();
		total = 0;
		
		model.addAttribute("orderSuccessMessage", "Order Confirmed");
		model.addAttribute("categories", customerController.getAllCategories());
		return "welcome";
	}
	
	/**
	 * Check order history of the customer. It will aslo show the list of the menu for each order. 
	 * @param model
	 * @param request
	 * @param session
	 * @return customer's order history page
	 */
	@GetMapping("/orderHistory")
	public String orderHistory(Model model, HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		//get login info
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		Customer customer = getLoggedInCustomer(session, model);
		
		/*get order history */
		//get all orders that the customer made
		List<Order> orderHistory = orderMenuDAOImp.getOrdersByCustomer(customer.getId());
		
		//list for all order history that is indexed by orderId
		List<List<OrderMenu>> allOrders = new ArrayList<>();
		
		//list for relevant restaurants from the order history
		List<Restaurant> orderedRestList = new ArrayList<>();
		Restaurant orderedRest = null;

		for (int i=0; i<orderHistory.size(); i++) {
			int orderId = orderHistory.get(i).getOrderId();
			int orderStatus = orderHistory.get(i).getOrderStatusId();
			String orderStat = null;
			
			List<OrderMenu> orderedMenus = orderMenuDAOImp.getCustomerOrderHistory(customer.getId(), orderId);
			
			for (int j=0; j<orderedMenus.size(); j++) {
				//System.out.println(orderedMenus.get(j).getOrderId()+", "+orderedMenus.get(j).getMenuName());
				int menuId = orderedMenus.get(0).getMenuId();
				RestaurantMenu menu = restaurantMenuDAOImp.getMenuById(menuId);
				orderedRest = restaurantDAOImp.getRestById(menu.getRestId());
			}
			
			orderedRestList.add(orderedRest);
			//System.out.println(orderedRest.getRestName());
			
			allOrders.add(orderedMenus);
			//System.out.println(orderId);
			
			if (orderStatus == 1) {
				orderStat = "Waiting to be confirmed.";
			} else if (orderStatus == 2) {
				orderStat = "Restaurant is making your order!";
			} else if (orderStatus == 3) {
				orderStat = "Order is on the way!";
			} else {
				orderStat = "This order has been completed.";
			}
			
			System.out.println(orderStat);
			orderHistory.get(i).setOrderStatus(orderStat);
	
			
		}
		
		
		model.addAttribute("orderedList", orderHistory);
		model.addAttribute("orderedRestList", orderedRestList);
		model.addAttribute("orderedMenuList", allOrders);
				
		return "/customer/orderHistory";
	}
	
	/**
	 * Customers can write or check the review about each order. 
	 * @param id
	 * @param session
	 * @param model
	 * @param request
	 * @return customer review page
	 */
	@GetMapping("/customer/review")
	public String review(@RequestParam(required=true) int id, 
			HttpSession session, Model model, HttpServletRequest request) {
		
		session = request.getSession();
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		getLoggedInCustomer(session, model);
		System.out.println(verifyCustomerLogin(session,model));
		
		session.setAttribute("orderId", id);
		
		List<Review> review = reviewDAOImp.getReviewByOrder(id);
		if (!review.isEmpty()) {
			model.addAttribute("review", review);	
			model.addAttribute("reviewExist", true);
		} else {
			System.out.println("no review");
			model.addAttribute("reviewExist", false);
		}
		
		return "/customer/review";
	}
	
	/**
	 * Post a review
	 * @param newReview
	 * @param model
	 * @param request
	 * @return
	 */
	@PostMapping("/postReview")
	public String postReview(@ModelAttribute("review") Review newReview, 
			Model model, HttpServletRequest request)	{
		
		//get restaurant Id from session
		HttpSession session = request.getSession();
		int orderId = (Integer) session.getAttribute("orderId");
		
		//get login info
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		getLoggedInCustomer(session, model);
		
		model.addAttribute("orderId", orderId);
		
		//add a review to the database
		reviewDAOImp.addReview(newReview);		
		model.addAttribute("reviewExist", true);
		
		//Get a list of menus from the controller
		List<Review> reviewList = reviewDAOImp.getReviewByOrder(orderId);
		model.addAttribute("review", reviewList);
		
		return "/customer/review";
	}
	
	/**
	 * This method calculates the total ratings of a restaurant
	 * @param restId
	 * @return double rating
	 */
	private String getTotalRatings(int restId) {
		//get total ratings of the restaurant
		List<Map<String,Object>> rateAndCount = reviewDAOImp.getRatings(restId);
		/*
		System.out.println(rateAndCount.get(0).get("sum"));
		System.out.println(rateAndCount.get(0).get("count"));
		BigDecimal b = (BigDecimal) rateAndCount.get(0).get("sum");
		System.out.println("convert pleaseee "+ Integer.valueOf(b.intValue()));*/

		if (!rateAndCount.isEmpty()) {
			BigDecimal sumVal =  (BigDecimal) rateAndCount.get(0).get("sum");
			long countVal = (long) rateAndCount.get(0).get("count");
			int sum = Integer.valueOf(sumVal.intValue());
			//int count = Integer.valueOf(countVal);
			
			double sum2 = (double) sum;
			double rating = sum2 / countVal;
			DecimalFormat df = new DecimalFormat("0.00");
			
			System.out.println("sum: "+sum2+"count: "+countVal+"rating: "+rating);
			return df.format(rating);
		} else {
			return "No reviews yet";
		}
		
	}
	
	private void setNumOfReviews(Restaurant rest) {
		List<Map<String,Object>> rateAndCount = reviewDAOImp.getRatings(rest.getRestId());
		if (!rateAndCount.isEmpty()) {
			long count = (long) rateAndCount.get(0).get("count");
			rest.setNumOfReviews((int) count);
			rest.setNumOfReviewsString(String.valueOf(count) + " reviews");
			
		} else {
			rest.setNumOfReviews(0);
			rest.setNumOfReviewsString("No reviews yet");
		}
	}
	
	private String getStarRatingValue(String totalRatings) {
		try {
			double starRating = Double.parseDouble(totalRatings);
			starRating = Math.ceil(starRating * 2);
			String starRatingValue = String.valueOf((int)starRating*10);
			return starRatingValue;
		} catch (NumberFormatException e) {
			e.getMessage();
			return null;
		}
		
	}
	
	/**
	 * Shows reviews for a restaurant for customers 
	 * @param id
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping("/seeReviews")
	public String seeReviews(@RequestParam(required=true) int id, 
			HttpSession session, HttpServletRequest request, Model model) {
		session = request.getSession();
		//get login info
		if (!verifyCustomerLogin(session,model)) {
			return redirectToLogin(session, model);
		}
		getLoggedInCustomer(session, model);
		//get restaurant info
		Restaurant restaurant = restaurantDAOImp.getRestById(id);
		model.addAttribute("restaurant", restaurant);

		//Get Reviews for the restaurant
		getReviewsForRestaurant(model, id);
		
		return "/order/seeReviews";
	}
	
	
}
