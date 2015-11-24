/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.payload.CarTrailer;

/**
 * @author Alexey Solomatin
 *
 */
public class CarWithTrailerTest extends CarTest {
	private final String TRAILER_NAME = "Trailer"; 
	private final double TRAILER_WEIGHT = 100;
	private final double TRAILER_PAYLOAD_WEIGHT = 70;
	private final double TRAILER_MAX_SPEED = 100;

	@Override
	protected Vehicle createVehicle() throws CreatingVehicleException {
		Car car = (Car) super.createVehicle();
		CarTrailer trailer = new CarTrailer(TRAILER_NAME, TRAILER_WEIGHT, TRAILER_MAX_SPEED, TRAILER_PAYLOAD_WEIGHT, TRAILER_PAYLOAD_WEIGHT);		
		car.addTrailer(trailer);
		return car;
	}
	
	@Override
	protected double getVehicleAcceleration() {
		return super.getVehicleAcceleration() * CAR_WEIGHT / (CAR_WEIGHT + TRAILER_WEIGHT + TRAILER_PAYLOAD_WEIGHT);
	}
	
	@Override
	protected double getVehicleMaxSpeed() {
		return Math.max(super.getVehicleMaxSpeed(), TRAILER_MAX_SPEED);
	}

}
