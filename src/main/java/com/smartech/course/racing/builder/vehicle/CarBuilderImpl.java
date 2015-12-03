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
	
	@Override
	public Car build() throws CreatingVehicleException {
		Car car = new Car(name, weight, maxSpeed, acceleration);
		if (trailer != null)
			car.addTrailer(trailer);
		return car;
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
