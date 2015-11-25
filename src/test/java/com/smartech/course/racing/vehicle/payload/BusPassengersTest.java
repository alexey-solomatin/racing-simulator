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
 * Tests for {@link BusPassengers}
 * @author Alexey Solomatin
 *
 */
public class BusPassengersTest {
	private static final long MAX_NUMBER_OF_PASSENGERS = 20;
	private static final long NUMBER_OF_PASSENGERS = 10;
	private static final double COMPARISION_DELTA = 0.001;
	
	private BusPassengers payload = new BusPassengers(MAX_NUMBER_OF_PASSENGERS, NUMBER_OF_PASSENGERS);

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.BusPassengers#BusPassengers(long, long)}.
	 */
	@Test
	@Ignore
	public void testBusPassengers() {
		fail("Not yet implemented");
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
