/**
 * 
 */
package com.smartech.course.racing.vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public class Bus extends Transport {
	private long maxNumberOfPassengers;
	private long numberOfPassengers;

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Bus(String name, double weight, double maxSpeed, double acceleration, long maxNumberOfPassengers) {
		super(name, weight, maxSpeed, acceleration);		
	}

	@Override
	protected double getPayloadWeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}
