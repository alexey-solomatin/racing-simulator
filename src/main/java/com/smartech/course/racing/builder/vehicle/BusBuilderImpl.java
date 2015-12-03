/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Bus;

/**
 * @author Alexey Solomatin
 *
 */
public class BusBuilderImpl extends AbstractVehicleBuilder<Bus, BusBuilder> implements BusBuilder {

	private long maxNumberOfPassengers;
	private long numberOfPassengers;

	/**
	 * 
	 */
	public BusBuilderImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Bus build() throws CreatingVehicleException {
		return new Bus(name, weight, maxSpeed, acceleration, maxNumberOfPassengers, numberOfPassengers);
	}	

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.BusBuilder#maxNumberOfPassengers(long)
	 */
	@Override
	public BusBuilder maxNumberOfPassengers(long maxNumberOfPassengers) throws CreatingVehicleException {
		this.maxNumberOfPassengers = maxNumberOfPassengers;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.BusBuilder#numberOfPassengers(long)
	 */
	@Override
	public BusBuilder numberOfPassengers(long numberOfPassengers) throws CreatingVehicleException {
		this.numberOfPassengers = numberOfPassengers;
		return this;
	}

}
