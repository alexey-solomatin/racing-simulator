/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.payload.TruckPayload;

/**
 * Truck for carrying a payload
 * @author Alexey Solomatin
 *
 */
public class Truck extends Transport {	

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Truck(String name, double weight, double maxSpeed, double acceleration, double maxPayloadWeight, double payloadWeight) throws CreatingVehicleException {
		super(name, weight, maxSpeed, acceleration, new TruckPayload(maxPayloadWeight, payloadWeight));		
	}
	
}
