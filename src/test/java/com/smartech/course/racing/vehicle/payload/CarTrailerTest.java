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

import com.smartech.course.racing.exception.CreatingVehicleException;

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
	 * @throws CreatingVehicleException 
	 */
	@Test	
	public void testCarTrailerWithCorrectParameters() throws CreatingVehicleException {		
		new CarTrailer("name", 1, 1, 1, 1);
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.CarTrailer#CarTrailer(java.lang.String, double, double, double, double)}.
	 * @throws CreatingVehicleException 
	 */
	@Test(expected=CreatingVehicleException.class)
	@Ignore
	public void testCarTrailerWithNotCorrectName() throws CreatingVehicleException {		
		new CarTrailer(null, 1, 1, 1, 1);
		fail("CarTrailer cannot be created with the name value that equals to null");
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.CarTrailer#CarTrailer(java.lang.String, double, double, double, double)}.
	 * @throws CreatingVehicleException 
	 */
	@Ignore
	@Test(expected=CreatingVehicleException.class)	
	public void testCarTrailerWithNotCorrectWeight() throws CreatingVehicleException {		
		new CarTrailer("name", -1, 1, 1, 1);
		fail("CarTrailer cannot be created with the negative weight value");
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.CarTrailer#CarTrailer(java.lang.String, double, double, double, double)}.
	 * @throws CreatingVehicleException 
	 */
	@Ignore
	@Test(expected=CreatingVehicleException.class)	
	public void testCarTrailerWithNotCorrectMaxSpeed() throws CreatingVehicleException {		
		new CarTrailer("name", 1, -1, 1, 1);
		fail("CarTrailer cannot be created with the negative maxSpeed value");
	}
	
//	/**
//	 * Test method for {@link com.smartech.course.racing.vehicle.payload.CarTrailer#CarTrailer(java.lang.String, double, double, double, double)}.
//	 * @throws CreatingVehicleException 
//	 */
//	@Test	
//	public void testCarTrailerWithCorrectParameters() throws CreatingVehicleException {		
//		new CarTrailer("name", 1, 1, 1, 1);
//	}
//	
//	/**
//	 * Test method for {@link com.smartech.course.racing.vehicle.payload.CarTrailer#CarTrailer(java.lang.String, double, double, double, double)}.
//	 * @throws CreatingVehicleException 
//	 */
//	@Test	
//	public void testCarTrailerWithCorrectParameters() throws CreatingVehicleException {		
//		new CarTrailer("name", 1, 1, 1, 1);
//	}

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
