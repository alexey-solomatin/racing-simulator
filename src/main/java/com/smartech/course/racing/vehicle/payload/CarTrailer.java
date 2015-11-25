/**
 * 
 */
package com.smartech.course.racing.vehicle.payload;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.DynamicObject;

/**
 * Car trailer, a special form of a payload
 * @author Alexey Solomatin
 *
 */
public class CarTrailer extends DynamicObject implements PayloadCarriable {	
	private PayloadCarriable payload;

	/**
	 * @throws CreatingVehicleException 
	 * 
	 */
	public CarTrailer(String name, double weight, double maxSpeed, double maxPayloadWeight, double payloadWeight) throws CreatingVehicleException {		
		super(name, weight, maxSpeed);
		this.payload = new SimplePayload(maxPayloadWeight, payloadWeight);		
	}

	@Override
	public double getMaxPayloadWeight() {
		return payload != null ? payload.getMaxPayloadWeight() : 0;
	}

	@Override
	public double getPayloadWeight() {
		return payload != null ? payload.getPayloadWeight() : 0;
	}
	
}
