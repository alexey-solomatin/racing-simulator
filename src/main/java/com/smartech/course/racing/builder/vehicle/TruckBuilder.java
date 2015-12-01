/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import java.util.function.Supplier;

import com.smartech.course.racing.vehicle.Truck;

/**
 * @author Alexey Solomatin
 *
 */
public interface TruckBuilder extends VehicleBuilder<Truck> {
	TruckBuilder maxPayloadWeight(double maxPayloadWeight);
	default TruckBuilder maxPayloadWeight(Supplier<Double> maxPayloadWeightSupplier) {
		return maxPayloadWeight(maxPayloadWeightSupplier.get());
	}
	TruckBuilder payloadWeight(double payloadWeight);
	default TruckBuilder payloadWeight(Supplier<Double> payloadWeightSupplier) {
		return payloadWeight(payloadWeightSupplier.get());
	}
}
