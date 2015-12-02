/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import java.util.function.Supplier;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public interface VehicleBuilder<VehicleType extends Vehicle, VehicleBuilderType extends VehicleBuilder<?, ?>> extends Supplier<VehicleType> {
	VehicleBuilderType name(String name) throws CreatingVehicleException;
	default VehicleBuilderType name(Supplier<String> nameSupplier) throws CreatingVehicleException {
		return name(nameSupplier.get());
	}
	VehicleBuilderType weight(double weight) throws CreatingVehicleException;
	default VehicleBuilderType weight(Supplier<Double> weightSupplier) throws CreatingVehicleException {
		return weight(weightSupplier.get());
	}
	VehicleBuilderType maxSpeed(double maxSpeed) throws CreatingVehicleException;
	default VehicleBuilderType maxSpeed(Supplier<Double> maxSpeedSupplier) throws CreatingVehicleException {
		return maxSpeed(maxSpeedSupplier.get());
	}
	VehicleBuilderType acceleration(double acceleration) throws CreatingVehicleException;
	default VehicleBuilderType acceleration(Supplier<Double> accelerationSupplier) throws CreatingVehicleException {
		return acceleration(accelerationSupplier.get());
	}	
}
