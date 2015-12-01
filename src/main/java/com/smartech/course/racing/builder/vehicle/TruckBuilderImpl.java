/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Truck;
import com.smartech.course.racing.vehicle.Vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public class TruckBuilderImpl extends AbstractVehicleBuilder<Truck> implements TruckBuilder {

	private double maxPayloadWeight;
	private double payloadWeight;

	/**
	 * 
	 */
	public TruckBuilderImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public Truck get() {
		try {
			return new Truck(name, weight, maxSpeed, acceleration, maxPayloadWeight, payloadWeight);
		} catch (CreatingVehicleException e) {
			log.error("Error during creation of a truck", e);
			return null;
		}
	}

	@Override
	public TruckBuilder maxPayloadWeight(double maxPayloadWeight) {
		this.maxPayloadWeight = payloadWeight;
		return this;
	}

	@Override
	public TruckBuilder payloadWeight(double payloadWeight) {
		this.payloadWeight = payloadWeight;
		return this;
	}

}
