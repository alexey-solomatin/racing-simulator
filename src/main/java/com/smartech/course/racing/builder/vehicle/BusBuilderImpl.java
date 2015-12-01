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
public class BusBuilderImpl extends AbstractVehicleBuilder<Bus> implements BusBuilder {

	private long maxNumberOfPassengers;
	private long numberOfPassengers;

	/**
	 * 
	 */
	public BusBuilderImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public Bus get() {
		try {
			return new Bus(name, weight, maxSpeed, acceleration, maxNumberOfPassengers, numberOfPassengers);
		} catch (CreatingVehicleException e) {
			log.error("Error during creation of a bus.", e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.BusBuilder#maxNumberOfPassengers(long)
	 */
	@Override
	public BusBuilder maxNumberOfPassengers(long maxNumberOfPassengers) {
		this.maxNumberOfPassengers = maxNumberOfPassengers;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.BusBuilder#numberOfPassengers(long)
	 */
	@Override
	public BusBuilder numberOfPassengers(long numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
		return this;
	}

}
