/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.payload.Payloadable;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class Transport extends Vehicle {
	protected Payloadable payload;

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed 
	 * @param acceleration
	 */
	public Transport(String name, double weight, double maxSpeed, double acceleration, Payloadable payload) throws CreatingVehicleException {
		super(name, weight, maxSpeed, acceleration);
		this.payload = payload;
	}	
	
	@Override
	protected double calculateCurrentAcceleration() {
		// TODO Auto-generated method stub
		return super.calculateCurrentAcceleration();
	}	
	
	/*
	@Override
	protected double calculateAcceleration() {
		// TODO Auto-generated method stub
		return super.calculateAcceleration();
	}
	*/

}
