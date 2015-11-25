/**
 * 
 */
package com.smartech.course.racing.vehicle.payload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link CarTrailer}
 * @author Alexey Solomatin
 *
 */
public class CarTrailerTest {
	private static final double MAX_PAYLOAD_WEIGHT = 100;
	private static final double PAYLOAD_WEIGHT = 50;
	private static final double TRAILER_WEIGHT = 100;
	private static final double TRAILER_MAX_SPEED = 20;
	private static final double COMPARISION_DELTA = 0.001;
	
	private CarTrailer payload;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		payload = new CarTrailer("CarTrailer", TRAILER_WEIGHT, TRAILER_MAX_SPEED, MAX_PAYLOAD_WEIGHT, PAYLOAD_WEIGHT);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.CarTrailer#CarTrailer(java.lang.String, double, double, double, double)}.
	 */
	@Test
	@Ignore
	public void testCarTrailer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.CarTrailer#getMaxPayloadWeight()}.
	 */
	@Test
	public void testGetMaxPayloadWeight() {		
		assertEquals(MAX_PAYLOAD_WEIGHT, payload.getMaxPayloadWeight(), COMPARISION_DELTA);
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.CarTrailer#getPayloadWeight()}.
	 */
	@Test
	public void testGetPayloadWeight() {		
		assertEquals(PAYLOAD_WEIGHT, payload.getPayloadWeight(), COMPARISION_DELTA);
	}

}
