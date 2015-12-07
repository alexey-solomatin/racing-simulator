package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * Movable vehicles should implement this interface
 * @author Alexey Solomatin
 *
 */
public interface Movable {

	/**
	 * Calculates the new state of a vehicle from the previous state
	 * @param curState the current state of a vehicle
	 * @param time the moving time
	 * @return the new state of a vehicle
	 */
	VehicleState move(VehicleState curState, double time) throws MovingVehicleException;
	
	String getDescription();
}