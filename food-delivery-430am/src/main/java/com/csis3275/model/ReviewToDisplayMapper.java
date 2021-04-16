package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReviewToDisplayMapper implements RowMapper<Review>{

	public Review mapRow(ResultSet r, int i) throws SQLException {
		Review rv = new Review();
		rv.setReviewId(r.getInt("reviewId"));
		rv.setOrderId(r.getInt("orderId"));
		rv.setReviewContent(r.getString("reviewContent"));
		rv.setRate(r.getInt("rate"));
		rv.setRestId(r.getInt("restId"));
		rv.setMenuName(r.getNString("menuName"));
		
		// TODO Auto-generated method stub
		return rv;
	}

}
