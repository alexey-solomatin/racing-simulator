/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Car;
import com.smartech.course.racing.vehicle.payload.CarTrailer;

/**
 * @author Alexey Solomatin
 *
 */
public class CarBuilderImpl extends AbstractVehicleBuilder<Car, CarBuilder> implements CarBuilder {

	private CarTrailer trailer;

	/**
	 * 
	 */
	public CarBuilderImpl() {
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public Car get() {
		try {
			Car car = new Car(name, weight, maxSpeed, acceleration);
			if (trailer != null)
				car.addTrailer(trailer);
			return car;
		} catch (CreatingVehicleException e) {
			log.error("Error during creation of a car.", e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.CarBuilder#trailer(com.smartech.course.racing.vehicle.payload.CarTrailer)
	 */
	@Override
	public CarBuilder trailer(CarTrailer carTrailer) throws CreatingVehicleException {
		this.trailer = carTrailer;
		return this;
	}

}
