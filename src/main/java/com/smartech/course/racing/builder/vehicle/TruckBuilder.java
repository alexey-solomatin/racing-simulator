/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import java.util.function.Supplier;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Truck;

/**
 * @author Alexey Solomatin
 *
 */
public interface TruckBuilder extends VehicleBuilder<Truck, TruckBuilder> {
	TruckBuilder maxPayloadWeight(double maxPayloadWeight) throws CreatingVehicleException;
	default TruckBuilder maxPayloadWeight(Supplier<Double> maxPayloadWeightSupplier) throws CreatingVehicleException {
		return maxPayloadWeight(maxPayloadWeightSupplier.get());
	}
	TruckBuilder payloadWeight(double payloadWeight) throws CreatingVehicleException;
	default TruckBuilder payloadWeight(Supplier<Double> payloadWeightSupplier) throws CreatingVehicleException {
		return payloadWeight(payloadWeightSupplier.get());
	}
}
