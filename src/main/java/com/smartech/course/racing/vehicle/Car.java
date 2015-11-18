/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.vehicle.extension.CarTrailer;

/**
 * Car
 * @author Alexey Solomatin
 *
 */
public class Car extends Vehicle {
	private CarTrailer trailer;

	/**
	 * @param name
	 * @param weight
	 * @param force
	 * @param maxSpeed
	 */
	public Car(String name, double weight, double force, double maxSpeed) {
		super(name, weight, force, maxSpeed);
	}

	public void addTrailer(CarTrailer trailer) {
		this.trailer = trailer;
	}
	
	public void removeTrailer(CarTrailer trailer) {
		this.trailer = null;
	}
	
	/*
	@Override
	protected double calculateMaxSpeed() {
		if (trailer != null)
			return Math.max(trailer.getMaxSpeed(), super.calculateMaxSpeed());
		else
			return super.calculateMaxSpeed();
	}
	
	@Override
	protected double calculateAcceleration() {
		if (trailer != null)
			return super.calculateAcceleration() * (weight / (weight + trailer.getWeight() + trailer.getPayload()));
		else
			return super.calculateAcceleration();
	}
	*/
}
