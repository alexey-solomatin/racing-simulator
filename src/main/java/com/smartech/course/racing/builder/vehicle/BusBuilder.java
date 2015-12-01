/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import java.util.function.Supplier;

import com.smartech.course.racing.vehicle.Bus;

/**
 * @author Alexey Solomatin
 *
 */
public interface BusBuilder extends VehicleBuilder<Bus> {
	VehicleBuilder<Bus> maxNumberOfPassengers(long maxNumberOfPassengers);
	default VehicleBuilder<Bus> maxNumberOfPassengers(Supplier<Long> maxNumberOfPassengersSupplier) {
		return maxNumberOfPassengers(maxNumberOfPassengersSupplier.get());
	}
	VehicleBuilder<Bus> numberOfPassengers(long numberOfPassengers);
	default VehicleBuilder<Bus> numberOfPassengers(Supplier<Long> numberOfPassengersSupplier) {
		return numberOfPassengers(numberOfPassengersSupplier.get());
	}
}
