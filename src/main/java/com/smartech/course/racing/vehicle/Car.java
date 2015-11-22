/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
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
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Car(String name, double weight, double maxSpeed, double acceleration) throws CreatingVehicleException {
		super(name, weight, maxSpeed, acceleration);
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
