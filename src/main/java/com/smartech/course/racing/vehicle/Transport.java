/**
 * 
 */
package com.smartech.course.racing.vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class Transport extends Vehicle {

	/**
	 * @param name
	 * @param weight
	 * @param acceleration
	 * @param maxSpeed
	 */
	public Transport(String name, double weight, double acceleration, double maxSpeed) {
		super(name, weight, acceleration, maxSpeed);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected double calculateAcceleration() {
		// TODO Auto-generated method stub
		return super.calculateAcceleration();
	}
	
	protected abstract double getPayloadWeight();

}
