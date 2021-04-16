package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReviewMapper implements RowMapper<Review>{

	public Review mapRow(ResultSet r, int i) throws SQLException {
		Review rv = new Review();
		rv.setReviewId(r.getInt("reviewId"));
		rv.setOrderId(r.getInt("orderId"));
		rv.setReviewContent(r.getString("reviewContent"));
		rv.setRate(r.getInt("rate"));
		
		// TODO Auto-generated method stub
		return rv;
	}

}
