/**
 * 
 */
package com.smartech.course.racing.vehicle.extension;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.DynamicObject;

/**
 * @author Alexey Solomatin
 *
 */
public class CarTrailer extends DynamicObject {	
	private double maxPayloadWeight;	
	private double payloadWeight;

	/**
	 * @throws CreatingVehicleException 
	 * 
	 */
	public CarTrailer(String name, double weight, double maxSpeed, double maxPayloadWeight) throws CreatingVehicleException {		
		super(name, weight, maxPayloadWeight);
		this.maxPayloadWeight = maxPayloadWeight;		
	}
	
	/**
	 * @return the maxPayload
	 */
	public double getMaxPayload() {
		return 0;
	}

	/**
	 * @param maxPayload the maxPayload to set
	 */
	public void setMaxPayload(double maxPayload) {
		//this.maxPayload = maxPayload;
	}

	/**
	 * @return the payload
	 */
	public double getPayload() {
		return 0;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(double payload) {
		//this.payload = payload;
	}

}
