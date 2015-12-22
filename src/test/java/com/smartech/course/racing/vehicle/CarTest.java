/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;

/**
 * Tests for {@link Car}
 * @author Alexey Solomatin
 *
 */
public class CarTest extends AbstractVehicleTest {
	protected final String CAR_NAME = "Car";
	protected final double CAR_WEIGHT = 500;
	protected final double CAR_MAX_SPEED = 55;
	protected final double CAR_ACCELERATION = 10;
	
	@Override
	protected Vehicle createVehicle() throws CreatingVehicleException {
		return new Car(1L, CAR_NAME, CAR_WEIGHT, CAR_MAX_SPEED, CAR_ACCELERATION);
	}
	
	@Override
	protected double getVehicleAcceleration() {
		return CAR_ACCELERATION;
	}
	
	@Override
	protected double getVehicleMaxSpeed() {
		return CAR_MAX_SPEED;
	}

}
