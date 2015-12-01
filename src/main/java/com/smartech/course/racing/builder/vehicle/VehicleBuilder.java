/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import java.util.function.Supplier;

import com.smartech.course.racing.vehicle.Vehicle;
import com.smartech.course.racing.vehicle.payload.PayloadCarriable;

/**
 * @author Alexey Solomatin
 *
 */
public interface VehicleBuilder<VehicleType extends Vehicle> extends Supplier<VehicleType> {
	VehicleBuilder<VehicleType> name(String name);
	default VehicleBuilder<VehicleType> name(Supplier<String> nameSupplier) {
		return name(nameSupplier.get());
	}
	VehicleBuilder<VehicleType> weight(double weight);
	default VehicleBuilder<VehicleType> weight(Supplier<Double> weightSupplier) {
		return weight(weightSupplier.get());
	}
	VehicleBuilder<VehicleType> maxSpeed(double maxSpeed);
	default VehicleBuilder<VehicleType> maxSpeed(Supplier<Double> maxSpeedSupplier) {
		return maxSpeed(maxSpeedSupplier.get());
	}
	VehicleBuilder<VehicleType> acceleration(double acceleration);
	default VehicleBuilder<VehicleType> acceleration(Supplier<Double> accelerationSupplier) {
		return acceleration(accelerationSupplier.get());
	}	
}
