/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.payload.BusPassengers;

/**
 * Bus for carrying passengers
 * @author Alexey Solomatin
 *
 */
public class Bus extends Transport<BusPassengers> {

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Bus(Long id, String name, double weight, double maxSpeed, double acceleration, long maxNumberOfPassengers, long numberOfPassengers) throws CreatingVehicleException {
		super(id, name, weight, maxSpeed, acceleration, new BusPassengers(maxNumberOfPassengers, numberOfPassengers));		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bus [id=" + id + ", name=" + name + ", weight=" + weight + ", acceleration=" + acceleration
				+ ", maxSpeed=" + maxSpeed + ", payload=" + payload + "]";
	}


	@Override
	public BusPassengers getPayload() {
		return super.getPayload();
	}
}
