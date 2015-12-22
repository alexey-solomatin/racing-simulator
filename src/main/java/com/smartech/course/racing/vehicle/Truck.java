/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.payload.SimplePayload;

/**
 * Truck for carrying a payload
 * @author Alexey Solomatin
 *
 */
public class Truck extends Transport<SimplePayload> {

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Truck(Long id, String name, double weight, double maxSpeed, double acceleration, double maxPayloadWeight, double payloadWeight) throws CreatingVehicleException {
		super(id, name, weight, maxSpeed, acceleration, new SimplePayload(maxPayloadWeight, payloadWeight));		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Truck [id=" + id + ", name=" + name + ", weight=" + weight + ", acceleration=" + acceleration + ", maxSpeed="
				+ maxSpeed + ", payload=" + payload + "]";
	}
	
	@Override
	public SimplePayload getPayload() {
		// TODO Auto-generated method stub
		return super.getPayload();
	}
	
}
