/**
 * 
 */
package com.smartech.course.racing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.utils.MockUtils;
import com.smartech.course.racing.vehicle.Movable;
import com.smartech.course.racing.vehicle.Vehicle.VehicleState;

/**
 * Tests for {@link Racer}
 * @author Alexey Solomatin
 *
 */
public class RacerTest {

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
	 * Test method for {@link com.smartech.course.racing.Racer#move(double)}.
	 * @throws MovingVehicleException 
	 */
	@Test
	public void testMove() throws MovingVehicleException {		
		Racing racing = new Racing("Racing #1", 4.5);
		Movable vehicle = MockUtils.mockVehicle();
		Racer racer = new Racer(vehicle, racing);
		assertNotNull(racer.getVehicleState());
		VehicleState beginVehicleState = new VehicleState(0, 0, 0);
		assertEquals(beginVehicleState, racer.getVehicleState());
		racer.move(1);
		assertNotNull(racer.getVehicleState());
		VehicleState endVehicleState = new VehicleState(1, 1, 1);
		assertEquals(endVehicleState, racer.getVehicleState());
	}

	/**
	 * Test method for {@link com.smartech.course.racing.Racer#isFinished()}.
	 * @throws MovingVehicleException 
	 */
	@Test
	public void testIsFinished() throws MovingVehicleException {		
		Racing racing = new Racing("Racing #1", 4.5);
		Movable vehicle = MockUtils.mockVehicle();
		Racer racer = new Racer(vehicle, racing);
		assertFalse(racer.isFinished());
		racer.move(4);
		assertFalse(racer.isFinished());
		racer.move(1);
		assertTrue(racer.isFinished());		
	}
	
}
