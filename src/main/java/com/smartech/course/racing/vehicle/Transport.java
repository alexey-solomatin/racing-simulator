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
public class Transport<PayloadType extends PayloadCarriable> extends Vehicle {
	protected PayloadType payload;

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed 
	 * @param acceleration
	 */
	public Transport(Long id, String name, double weight, double maxSpeed, double acceleration, PayloadType payload) throws CreatingVehicleException {
		super(id, name, weight, maxSpeed, acceleration);
		this.payload = payload;
	}	
	
	@Override
	protected double calculateCurrentAcceleration() {
		return payload != null
			? acceleration * weight / (weight + payload.getPayloadWeight())
			: acceleration;		
	}

	/**
	 * @return the payload
	 */
	protected PayloadType getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	protected void setPayload(PayloadType payload) {
		this.payload = payload;
	}

}
