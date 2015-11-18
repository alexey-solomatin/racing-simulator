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
	 * @param acceleration
	 * @param maxSpeed
	 */
	public Bus(String name, double weight, double acceleration, double maxSpeed, long maxNumberOfPassengers) {
		super(name, weight, acceleration, maxSpeed);		
	}

	@Override
	protected double getPayloadWeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}
