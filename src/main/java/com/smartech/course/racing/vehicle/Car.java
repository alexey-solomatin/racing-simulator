/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.payload.CarTrailer;

/**
 * Car which can also have a {@link CarTrailer}
 * @author Alexey Solomatin
 *
 */
public class Car extends Transport {	

	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Car(String name, double weight, double maxSpeed, double acceleration) throws CreatingVehicleException {
		super(name, weight, maxSpeed, acceleration, null);
	}

	public void addTrailer(CarTrailer trailer) {
		this.payload = trailer;
	}
	
	public void removeTrailer(CarTrailer trailer) {
		this.payload = null;
	}
	
	@Override
	protected double calculateCurrentAcceleration() {
		// TODO Auto-generated method stub
		return super.calculateCurrentAcceleration();
	}
	
	@Override
	protected double calculateCurrentMaxSpeed() {
		// TODO Auto-generated method stub
		return super.calculateCurrentMaxSpeed();
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
