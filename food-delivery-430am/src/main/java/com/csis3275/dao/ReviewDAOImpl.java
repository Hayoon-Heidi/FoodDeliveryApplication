package com.csis3275.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.OrderMapper;
import com.csis3275.model.Review;
import com.csis3275.model.ReviewMapper;
import com.csis3275.model.ReviewToDisplayMapper;

@Component
public class ReviewDAOImpl {
	JdbcTemplate j;
	
	private final String SQL_GET_REVIEW_BY_ORDER = "SELECT * FROM review WHERE orderId=?";
	private final String SQL_GET_REVIEW_BY_RESTAURANT = "SELECT * FROM review INNER JOIN order_menu\r\n" + 
			"ON review.orderId = order_menu.orderId\r\n" + 
			"INNER JOIN restmenu \r\n" + 
			"ON order_menu.menuId = restmenu.menuId\r\n" + 
			"WHERE restId = ?" + 
			"GROUP BY reviewId, order_menu.menuId;";
	private final String SQL_INSERT_REVIEW = "INSERT INTO review (orderId, reviewContent, rate) VALUES (?,?,?)";
	private final String SQL_GET_RATINGS = "SELECT SUM(sum / count) AS sum, COUNT(sum / count) AS count FROM\r\n" + 
			"(SELECT orders.orderId, restId, reviewId, SUM(rate) AS sum, COUNT(review.reviewId) AS count \r\n" + 
			"    FROM review INNER JOIN orders\r\n" + 
			"    ON review.orderId = orders.orderId\r\n" + 
			"    INNER JOIN order_menu\r\n" + 
			"    ON orders.orderId = order_menu.orderId\r\n" + 
			"    INNER JOIN restmenu \r\n" + 
			"    ON order_menu.menuId = restmenu.menuId\r\n" + 
			"    group by restId, reviewId, orders.orderId)\r\n" + 
			"WHERE restId = ?\r\n" + 
			"GROUP BY restId;";
	
	@Autowired
	public ReviewDAOImpl(DataSource dataSource) {
		j = new JdbcTemplate(dataSource);
	}
	
	/**
	 * List of review informations. Get the review by order id.
	 * @param id
	 * @return review list by order id.
	 */
	public List<Review> getReviewByOrder(int id) {
		return j.query(SQL_GET_REVIEW_BY_ORDER, new Object[] { id }, new ReviewMapper());
	}
	
	/**
	 * Insert new value about order to review table.
	 * @param review
	 * @return new review value for order.
	 */
	public boolean addReview(Review review) {
		return j.update(SQL_INSERT_REVIEW, review.getOrderId(), review.getReviewContent(), review.getRate()) > 0;
	}
	
	public List<Review> getReviewByRestaurant(int restId) {
		return j.query(SQL_GET_REVIEW_BY_RESTAURANT, new Object[] {restId}, new ReviewToDisplayMapper());
	}
	
	public List<Map<String, Object>> getRatings(int restId) {
		return j.queryForList(SQL_GET_RATINGS, restId);
	}
	
}
