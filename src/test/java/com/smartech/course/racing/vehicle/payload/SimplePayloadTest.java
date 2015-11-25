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
 * Tests for {@link SimplePayload}
 * @author Alexey Solomatin
 *
 */
public class SimplePayloadTest {
	private static final double MAX_PAYLOAD_WEIGHT = 100;
	private static final double PAYLOAD_WEIGHT = 50;
	private static final double COMPARISION_DELTA = 0.001;
	
	private SimplePayload payload = new SimplePayload(MAX_PAYLOAD_WEIGHT, PAYLOAD_WEIGHT); 

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
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.SimplePayload#SimplePayload(double, double)}.
	 */
	@Test
	@Ignore
	public void testSimplePayload() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.SimplePayload#getMaxPayloadWeight()}.
	 */
	@Test
	public void testGetMaxPayloadWeight() {		
		assertEquals(MAX_PAYLOAD_WEIGHT, payload.getMaxPayloadWeight(), COMPARISION_DELTA);
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.payload.SimplePayload#getPayloadWeight()}.
	 */
	@Test
	public void testGetPayloadWeight() {
		assertEquals(PAYLOAD_WEIGHT, payload.getPayloadWeight(), COMPARISION_DELTA);
	}

}
