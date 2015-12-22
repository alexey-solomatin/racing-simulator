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
	public CarTrailer(Long id, String name, double weight, double maxSpeed, double maxPayloadWeight, double payloadWeight) throws CreatingVehicleException {		
		super(id, name, weight, maxSpeed);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CarTrailer [id=" + id + ", name=" + name + ", weight=" + weight + ", maxSpeed=" + maxSpeed + ", payload=" + payload
				+ "]";
	}
	
	public PayloadCarriable getPayload() {
		return payload;
	}
	
}
