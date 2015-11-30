/**
 * 
 */
package com.smartech.course.racing.vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * Basic class for tests for vehicles
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractVehicleTest {
	protected static final double COMPARISON_DELTA = 0.001;
	
	protected abstract Vehicle createVehicle() throws CreatingVehicleException;
	
	protected abstract double getVehicleMaxSpeed();
	
	protected abstract double getVehicleAcceleration();
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#calculateCurrentMaxSpeed()}.
	 * @throws CreatingVehicleException 
	 */
	@Test
	public void testCalculateCurrentMaxSpeed() throws CreatingVehicleException {
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		assertEquals(getVehicleMaxSpeed(), vehicle.calculateCurrentMaxSpeed(), COMPARISON_DELTA);
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#move(com.smartech.course.racing.vehicle.Vehicle.VehicleState, double)}.
	 * @throws CreatingVehicleException 
	 * @throws MovingVehicleException 
	 */
	@Test(expected=MovingVehicleException.class)
	public void testMoveWithNegativeTime() throws CreatingVehicleException, MovingVehicleException {
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		vehicle.move(new VehicleState(), -1);
		fail("Vehicle should not move with a negative time parameter, but it did.");
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#move(com.smartech.course.racing.vehicle.Vehicle.VehicleState, double)}.
	 * @throws CreatingVehicleException 
	 * @throws MovingVehicleException 
	 */
	@Test(expected=MovingVehicleException.class)
	public void testMoveWithNullVehicleState() throws CreatingVehicleException, MovingVehicleException {
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		vehicle.move(null, 1);
		fail("Vehicle should not move with a not specified vehicle state, but it did.");
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#move(com.smartech.course.racing.vehicle.Vehicle.VehicleState, double)}.
	 * @throws CreatingVehicleException 
	 * @throws MovingVehicleException 
	 */
	@Test
	public void testMoveWithoutAcceleration() throws CreatingVehicleException, MovingVehicleException {		
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
	 * @throws MovingVehicleException 
	 */
	@Test
	public void testMoveWithAcceleration() throws CreatingVehicleException, MovingVehicleException {
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		VehicleState curState = new VehicleState(0, 0, 0);
		// calculate time for moving with acceleration, a half of max time
		double time = getVehicleMaxSpeed() / getVehicleAcceleration() / 2;
		VehicleState newState = vehicle.move(curState, time);
		assertNotNull(newState);
		assertEquals(curState.getTime()+time, newState.getTime(), COMPARISON_DELTA);
		assertEquals(curState.getSpeed()+time*getVehicleAcceleration(), newState.getSpeed(), COMPARISON_DELTA);
		assertEquals(curState.getPosition()+time*curState.getSpeed()+getVehicleAcceleration()*time*time/2, newState.getPosition(), COMPARISON_DELTA);
	}
	
	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#move(com.smartech.course.racing.vehicle.Vehicle.VehicleState, double)}.
	 * @throws CreatingVehicleException 
	 * @throws MovingVehicleException 
	 */
	@Test
	public void testMoveWithAccelerationAndWithoutAcceleration() throws CreatingVehicleException, MovingVehicleException {
		Vehicle vehicle = createVehicle();
		assertNotNull(vehicle);
		VehicleState curState = new VehicleState(0, 0, 0);
		// calculate time for moving with acceleration
		double timeOfAcceleratedMoving = getVehicleMaxSpeed() / getVehicleAcceleration();
		// and without acceleration, just a half of the previous time
		double timeOfNotAcceleratedMoving = timeOfAcceleratedMoving/2;
		VehicleState newState = vehicle.move(curState, timeOfAcceleratedMoving + timeOfNotAcceleratedMoving);
		assertNotNull(newState);
		assertEquals(curState.getTime()+timeOfAcceleratedMoving+timeOfNotAcceleratedMoving, newState.getTime(), COMPARISON_DELTA);
		assertEquals(getVehicleMaxSpeed(), newState.getSpeed(), COMPARISON_DELTA);
		assertEquals(curState.getPosition()+timeOfAcceleratedMoving*curState.getSpeed()+getVehicleAcceleration()*timeOfAcceleratedMoving*timeOfAcceleratedMoving/2+timeOfNotAcceleratedMoving*getVehicleMaxSpeed(), newState.getPosition(), COMPARISON_DELTA);
	}
	
}
