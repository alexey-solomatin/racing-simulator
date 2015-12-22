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
public class Car extends Transport<CarTrailer> {	
	
	/**
	 * @param name
	 * @param weight
	 * @param maxSpeed
	 * @param acceleration
	 */
	public Car(Long id, String name, double weight, double maxSpeed, double acceleration) throws CreatingVehicleException {
		super(id, name, weight, maxSpeed, acceleration, null);
	}

	public void addTrailer(CarTrailer trailer) {
		this.payload = trailer;
	}
	
	public void removeTrailer(CarTrailer trailer) {
		this.payload = null;
	}
	
	@Override
	protected double calculateCurrentAcceleration() {
		return payload != null 
			? acceleration * weight / (weight + ((CarTrailer)payload).getWeight() + payload.getPayloadWeight())
			: acceleration;
	}
	
	@Override
	protected double calculateCurrentMaxSpeed() {
		return payload != null ? Math.min(((CarTrailer)payload).getMaxSpeed(), maxSpeed) : maxSpeed;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", weight=" + weight + ", acceleration=" + acceleration + ", maxSpeed=" + maxSpeed
				+ ", payload=" + payload + "]";
	}
	
	@Override
	public CarTrailer getPayload() {
		return super.getPayload();
	}
}
