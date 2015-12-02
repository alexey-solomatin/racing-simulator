/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import java.util.function.Supplier;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Car;
import com.smartech.course.racing.vehicle.payload.CarTrailer;

/**
 * @author Alexey Solomatin
 *
 */
public interface CarBuilder extends VehicleBuilder<Car, CarBuilder> {
	CarBuilder trailer(CarTrailer carTrailer) throws CreatingVehicleException;
	default CarBuilder trailer(Supplier<CarTrailer> carTrailerSupplier) throws CreatingVehicleException {
		return trailer(carTrailerSupplier.get());
	}
}
