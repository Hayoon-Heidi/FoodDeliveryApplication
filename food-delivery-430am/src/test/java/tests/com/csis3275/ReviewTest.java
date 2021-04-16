package tests.com.csis3275;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.csis3275.model.Review;

/**
 * 
 * @author Hayoon Kim
 *
 */
public class ReviewTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	//Set up the unit to test
	Review review = new Review();
	
	@BeforeEach
	void setUp() throws Exception {
		review = new Review(10, 4, "I like the food here", 4);
	}
	
	@Test
	public void getRevieId() {
		assertEquals(10, review.getReviewId());
	}

}
