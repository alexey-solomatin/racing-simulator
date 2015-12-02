/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import java.util.function.Supplier;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Bus;

/**
 * @author Alexey Solomatin
 *
 */
public interface BusBuilder extends VehicleBuilder<Bus, BusBuilder> {
	BusBuilder maxNumberOfPassengers(long maxNumberOfPassengers) throws CreatingVehicleException;
	default BusBuilder maxNumberOfPassengers(Supplier<Long> maxNumberOfPassengersSupplier) throws CreatingVehicleException {
		return maxNumberOfPassengers(maxNumberOfPassengersSupplier.get());
	}
	BusBuilder numberOfPassengers(long numberOfPassengers) throws CreatingVehicleException;
	default BusBuilder numberOfPassengers(Supplier<Long> numberOfPassengersSupplier) throws CreatingVehicleException {
		return numberOfPassengers(numberOfPassengersSupplier.get());
	}
}
