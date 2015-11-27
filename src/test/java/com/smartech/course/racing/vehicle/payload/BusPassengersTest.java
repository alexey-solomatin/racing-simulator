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
 * Tests for {@link BusPassengers}
 * @author Alexey Solomatin
 *
 */
public class BusPassengersTest {
	private static final long MAX_NUMBER_OF_PASSENGERS = 20;
	private static final long NUMBER_OF_PASSENGERS = 10;
	private static final double COMPARISION_DELTA = 0.001;
	
	private BusPassengers payload;

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
		payload = new BusPassengers(MAX_NUMBER_OF_PASSENGERS, NUMBER_OF_PASSENGERS);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.BusPassengers#BusPassengers(long, long)}.
	 * @throws CreatingVehicleException 
	 */
	@Test
	public void testBusPassengersWithCorrectParameters() throws CreatingVehicleException {		
		new BusPassengers(1, 1);
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.BusPassengers#BusPassengers(long, long)}.
	 * @throws CreatingVehicleException 
	 */
	@Test(expected=CreatingVehicleException.class)
	public void testBusPassengersWithNotCorrectMaxNumberOfPassengers() throws CreatingVehicleException {		
		new BusPassengers(-1, 1);
		fail("BusPassengers cannot be created with the negative maxNumberOfPassengers value");
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.BusPassengers#BusPassengers(long, long)}.
	 * @throws CreatingVehicleException 
	 */
	@Test(expected=CreatingVehicleException.class)
	public void testBusPassengersWithNotCorrectNumberOfPassengers() throws CreatingVehicleException {		
		new BusPassengers(1, -1);
		fail("BusPassengers cannot be created with the negative numberOfPassengers value");
	}
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.BusPassengers#BusPassengers(long, long)}.
	 * @throws CreatingVehicleException 
	 */
	@Test(expected=CreatingVehicleException.class)
	public void testBusPassengersWithMaxNumberOfPassengersLessThanNumberOfPassengers() throws CreatingVehicleException {		
		new BusPassengers(1, 2);
		fail("BusPassengers cannot be created with the maxNumberOfPassengers value less that the numberOfPassengers value");
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.BusPassengers#getMaxPayloadWeight()}.
	 */
	@Test
	public void testGetMaxPayloadWeight() {
		assertEquals(MAX_NUMBER_OF_PASSENGERS*BusPassengers.PASSENGER_WEIGHT, payload.getMaxPayloadWeight(), COMPARISION_DELTA);
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.BusPassengers#getPayloadWeight()}.
	 */
	@Test
	public void testGetPayloadWeight() {
		assertEquals(NUMBER_OF_PASSENGERS*BusPassengers.PASSENGER_WEIGHT, payload.getPayloadWeight(), COMPARISION_DELTA);
	}

}
