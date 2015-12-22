/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.payload.BusPassengers;

/**
 * Tests for {@link Bus}
 * @author Alexey Solomatin
 *
 */
public class BusTest extends AbstractVehicleTest {
	protected final String BUS_NAME = "Bus";
	protected final double BUS_WEIGHT = 1000;
	protected final double BUS_MAX_SPEED = 30;
	protected final double BUS_ACCELERATION = 5;
	protected final long NUMBER_OF_PASSENGERS = 10;

	@Override
	protected Vehicle createVehicle() throws CreatingVehicleException {
		Bus bus = new Bus(1L, BUS_NAME, BUS_WEIGHT, BUS_MAX_SPEED, BUS_ACCELERATION, NUMBER_OF_PASSENGERS, NUMBER_OF_PASSENGERS);		
		return bus;
	}
	
	@Override
	protected double getVehicleAcceleration() {
		return BUS_ACCELERATION * BUS_WEIGHT / (BUS_WEIGHT + BusPassengers.PASSENGER_WEIGHT*NUMBER_OF_PASSENGERS);
	}
	
	@Override
	protected double getVehicleMaxSpeed() {
		return BUS_MAX_SPEED;
	}

}
