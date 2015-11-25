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
		this.payload = new TruckPayload(maxPayloadWeight, payloadWeight);		
	}

	@Override
	public double getMaxPayloadWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPayloadWeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
