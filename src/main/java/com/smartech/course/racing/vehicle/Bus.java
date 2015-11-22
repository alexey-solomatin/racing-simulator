/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;

/**
 * @author Alexey Solomatin
 *
 */
public class Bus extends Transport {
	public final static double PASSENGER_WEIGHT = 70;
	private long maxNumberOfPassengers;
	private long numberOfPassengers;

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Bus(String name, double weight, double maxSpeed, double acceleration, long maxNumberOfPassengers) throws CreatingVehicleException {
		super(name, weight, maxSpeed, acceleration);		
	}

	@Override
	protected double getPayloadWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(long numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
}
