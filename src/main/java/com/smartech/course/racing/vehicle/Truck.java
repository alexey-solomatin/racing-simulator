/**
 * 
 */
package com.smartech.course.racing.vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public class Truck extends Transport {
	private double maxPayload;	
	private double payload;

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Truck(String name, double weight, double maxSpeed, double acceleration, double maxPayload) {
		super(name, weight, maxSpeed, acceleration);
		this.maxPayload = maxPayload;
	}

	public double getPayload() {
		return payload;
	}

	public void setPayload(double payload) {
		this.payload = payload;
	}

	@Override
	protected double getPayloadWeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
