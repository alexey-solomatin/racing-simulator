/**
 * 
 */
package com.smartech.course.racing.vehicle;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Vehicle.VehicleState;

/**
 * Basic class for tests for vehicles
 * @author Alexey Solomatin
 *
 */
abstract class AbstractVehicleTest {
	protected static final double COMPARISON_DELTA = 0.001;

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
	
	protected abstract Vehicle createVehicle() throws CreatingVehicleException;
	
	protected abstract double getVehicleMaxSpeed();
	
	protected abstract double getVehicleAcceleration();
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#getMaxSpeed()}.
	 * @throws CreatingVehicleException 
	 */
	@Test
	public void testGetMaxSpeed() throws CreatingVehicleException {
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		assertEquals(getVehicleMaxSpeed(), vehicle.getMaxSpeed(), COMPARISON_DELTA);
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#move(com.smartech.course.racing.vehicle.Vehicle.VehicleState, double)}.
	 * @throws CreatingVehicleException 
	 */
	@Test(expected=MovingVehicleException.class)
	public void testMoveWithNegativeTime() throws CreatingVehicleException {
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		vehicle.move(new VehicleState(), -1);
		fail("Vehicle should not move with a negative time parameter, but it did.");
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#move(com.smartech.course.racing.vehicle.Vehicle.VehicleState, double)}.
	 * @throws CreatingVehicleException 
	 */
	@Test(expected=MovingVehicleException.class)
	public void testMoveWithNullVehicleState() throws CreatingVehicleException {
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		vehicle.move(null, 1);
		fail("Vehicle should not move with a not specified vehicle state, but it did.");
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#move(com.smartech.course.racing.vehicle.Vehicle.VehicleState, double)}.
	 * @throws CreatingVehicleException 
	 */
	@Test
	public void testMoveWithoutAcceleration() throws CreatingVehicleException {		
		double time = 1;
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		VehicleState curState = new VehicleState(0, getVehicleMaxSpeed(), 0);
		VehicleState newState = vehicle.move(curState, time);
		assertNotNull(newState);
		assertEquals(curState.getTime()+time, newState.getTime(), COMPARISON_DELTA);
		assertEquals(curState.getSpeed(), newState.getSpeed(), COMPARISON_DELTA);
		assertEquals(curState.getPosition()+time*getVehicleMaxSpeed(), newState.getPosition(), COMPARISON_DELTA);
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#move(com.smartech.course.racing.vehicle.Vehicle.VehicleState, double)}.
	 * @throws CreatingVehicleException 
	 */
	@Test
	public void testMoveWithAcceleration() throws CreatingVehicleException {
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		VehicleState curState = new VehicleState(0, 0, 0);
		// calculate time for moving with acceleration
		double time = getVehicleAcceleration()/2;
		VehicleState newState = vehicle.move(curState, time);
		assertNotNull(newState);
		assertEquals(curState.getTime()+time, newState.getTime(), COMPARISON_DELTA);
		assertEquals(curState.getSpeed()+time*getVehicleAcceleration(), newState.getSpeed(), COMPARISON_DELTA);
		assertEquals(curState.getPosition()+time*curState.getSpeed()+getVehicleAcceleration()*time*time/2, newState.getPosition(), COMPARISON_DELTA);
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#move(com.smartech.course.racing.vehicle.Vehicle.VehicleState, double)}.
	 * @throws CreatingVehicleException 
	 */
	@Test
	public void testMoveWithAccelerationAndWithoutAcceleration() throws CreatingVehicleException {
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		VehicleState curState = new VehicleState(0, 0, 0);
		// calculate time for moving with acceleration
		double timeOfAcceleratedMoving = getVehicleAcceleration();
		// and without acceleration, just a half of the previous time
		double timeOfNotAcceleratedMoving = timeOfAcceleratedMoving/2;
		VehicleState newState = vehicle.move(curState, timeOfAcceleratedMoving + timeOfNotAcceleratedMoving);
		assertNotNull(newState);
		assertEquals(curState.getTime()+timeOfAcceleratedMoving+timeOfNotAcceleratedMoving, newState.getTime(), COMPARISON_DELTA);
		assertEquals(getVehicleMaxSpeed(), newState.getSpeed(), COMPARISON_DELTA);
		assertEquals(curState.getPosition()+timeOfAcceleratedMoving*curState.getSpeed()+getVehicleAcceleration()*timeOfAcceleratedMoving*timeOfAcceleratedMoving/2+timeOfNotAcceleratedMoving*getVehicleMaxSpeed(), newState.getPosition(), COMPARISON_DELTA);
	}
	
}
