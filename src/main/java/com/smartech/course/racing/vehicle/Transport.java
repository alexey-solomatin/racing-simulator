/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class Transport extends Vehicle {

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed 
	 * @param acceleration
	 */
	public Transport(String name, double weight, double maxSpeed, double acceleration) throws CreatingVehicleException {
		super(name, weight, maxSpeed, acceleration);
		// TODO Auto-generated constructor stub
	}
	/*
	@Override
	protected double calculateAcceleration() {
		// TODO Auto-generated method stub
		return super.calculateAcceleration();
	}
	*/
	
	protected abstract double getPayloadWeight();

}
