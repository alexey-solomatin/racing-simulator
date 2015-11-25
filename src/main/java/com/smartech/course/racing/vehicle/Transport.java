/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.payload.PayloadCarriable;

/**
 * Vehicle for carrying payloads
 * @author Alexey Solomatin
 *
 */
public abstract class Transport extends Vehicle {
	protected PayloadCarriable payload;

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed 
	 * @param acceleration
	 */
	public Transport(String name, double weight, double maxSpeed, double acceleration, PayloadCarriable payload) throws CreatingVehicleException {
		super(name, weight, maxSpeed, acceleration);
		this.payload = payload;
	}	
	
	@Override
	protected double calculateCurrentAcceleration() {
		return payload != null
			? acceleration * weight / (weight + payload.getPayloadWeight())
			: acceleration;		
	}		

}
