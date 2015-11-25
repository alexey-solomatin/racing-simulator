/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;

/**
 * Tests for {@link Truck}
 * @author Alexey Solomatin
 *
 */
public class TruckTest extends AbstractVehicleTest {
	protected final String TRUCK_NAME = "Truck";
	protected final double TRUCK_WEIGHT = 1500;
	protected final double TRUCK_MAX_SPEED = 40;
	protected final double TRUCK_ACCELERATION = 7;
	protected final long TRUCK_PAYLOAD_WEIGHT = 1000;

	@Override
	protected Vehicle createVehicle() throws CreatingVehicleException {
		Truck truck = new Truck(TRUCK_NAME, TRUCK_WEIGHT, TRUCK_MAX_SPEED, TRUCK_ACCELERATION, TRUCK_PAYLOAD_WEIGHT, TRUCK_PAYLOAD_WEIGHT);		
		return truck;
	}

	@Override
	protected double getVehicleMaxSpeed() {
		return TRUCK_MAX_SPEED;
	}

	@Override
	protected double getVehicleAcceleration() {
		return TRUCK_ACCELERATION * TRUCK_WEIGHT / (TRUCK_WEIGHT + TRUCK_PAYLOAD_WEIGHT);
	}

}
