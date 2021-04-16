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

import com.csis3275.dao.CustomerDAOImpl;
import com.csis3275.dao.RestaurantDAOImpl;
import com.csis3275.model.Category;
import com.csis3275.model.Customer;
import com.csis3275.model.Login;
import com.csis3275.service.UserServiceImpl;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerDAOImpl customerDAOImp; 
	@Autowired
	RestaurantDAOImpl restaurantDAOImp;

	@ModelAttribute("customer")
	public Customer setupAddForm() {
		return new Customer();
	}
	
	/**
	 * Test page for seeing all registered customers
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/customer/test_showCustomers")
	public String test_showCustomers(HttpSession session, Model model) {
		List<Customer> customers = customerDAOImp.getAllCustomer();
		model.addAttribute("customerList", customers);
		return "/customer/test_showCustomers";	
	}
	

	/**
	 * Registration page for customer user. They can create a new account.
	 * @param session
	 * @param model
	 * @return customer registration page
	 */
	@GetMapping("/customer/registration")
	public String customer_registration(HttpSession session, Model model) {
		return "/customer/registration";
	}
	
	/**
	 * Test page to check the informations of customer registration.
	 * @param newCustomer
	 * @param model
	 * @return test show customer page. 
	 */
	@PostMapping("/customer/registered")
	public String customer_registration(@ModelAttribute("customer")
	Customer newCustomer, Model model) {
		customerDAOImp.createCustomer(newCustomer);
		
		model.addAttribute("registerSuccess", "Successfully registered. Please sign in to continue.");
		return "/customer/customer_login";
	}
	
	/**
	 * Log in page for customer users.
	 * @param session
	 * @param model customer login page.
	 * @return customer login page
	 */
	@GetMapping("/customer/customer_login")
	public String customer_login(HttpSession session, Model model) {
		//List<Customer> customers = customerDAOImp.getAllCustomer();
		//model.addAttribute("customerList", customers);
		return "/customer/customer_login";	
	}
	
	/**
	 * Customer log in validation.
	 * @param login
	 * @param model
	 * @param request
	 * @return welcome page or customer log in page.
	 */
	@PostMapping("/customer/customer_login")
	public String customer_login(@ModelAttribute("login")
	Login login, Model model, HttpServletRequest request) {
		Customer customer = customerDAOImp.validateCustomer(login);
		
		if (customer != null) {
			HttpSession session = request.getSession();
			session.setAttribute("signedInUser", customer);
			model.addAttribute("loggedInCustomer", customer);
			model.addAttribute("welcomeMessage","Login Success!");
			model.addAttribute("categories", getAllCategories());
			return "welcome";
		} else {
			model.addAttribute("message", "Login fail:(");
			return "/customer/customer_login";
		}	
	}
	
	public List<Category> getAllCategories() {
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
	 * Find restaurant by location. Get Mapping.
	 * @param session
	 * @param model
	 * @param request
	 * @return welcome page. (main page for customer)
	 */
	@GetMapping("/findRestaurants")
	public String findRestaurants(HttpSession session, Model model, HttpServletRequest request) {
		session = request.getSession();
		Customer customer = (Customer) session.getAttribute("signedInUser");		
		model.addAttribute("loggedInCustomer", customer);
		model.addAttribute("categories", getAllCategories());
		
		return "welcome";
	}
	
	/*
	//this is just trash..
	@GetMapping("/customer_login/findRestaurants")
	public String welcome(@RequestParam(required=true) Login login, Model model) {
		Customer loggedIn = customerDAOImp.validateCustomer(login);
		System.out.println("1: "+loggedIn.getFirstName());
		model.addAttribute("loggedInCustomer", loggedIn);
		return "welcome";
	}
	
	@PostMapping("/customer_login/findRestaurants")
	public String welcome(@ModelAttribute("customer") Customer customer, Model model) {
		//model.addAttribute("address", model.getAttribute("address"));
		System.out.println( model.getAttribute("loggedInCustomer"));
		System.out.println(customer.getCurrentAddress());
		model.addAttribute("loggedInCustomer", model.getAttribute("loggedInCustomer"));
		return "welcome";
	}*/
	
	/**
	 * Find restaurant by location. Post Mapping.
	 * @param session
	 * @param model
	 * @param request
	 * @return welcom page. (main page for customer)
	 */
	@PostMapping("/findRestaurants")
	public String findRestaurants(@ModelAttribute("customer") 
	Customer loggedinUser, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("signedInUser");
		customer.setCurrentAddress(loggedinUser.getCurrentAddress());
		System.out.println(customer.getCurrentAddress());
		model.addAttribute("loggedInCustomer", customer);
		model.addAttribute("categories", getAllCategories());

		return "welcome";
		
	}
	
	/**
	 * Customer's my page. user can see their information of account. 
	 * @param request
	 * @param model
	 * @return customer mypage page. 
	 */
	@GetMapping("/customer/customer_mypage")
	public String customer_login(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("signedInUser");
		System.out.println(customer.getFirstName());

		model.addAttribute("loggedInCustomer", customer);
		return "/customer/customer_mypage";
	}
	
	/**
	 * Customer account log out.
	 * @param customer
	 * @param request
	 * @return logout page.
	 */
	@GetMapping("/logout")
	public String logout(Customer customer, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "logout";
	}
	
	
	
}
