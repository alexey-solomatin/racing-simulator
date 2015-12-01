/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.vehicle.Vehicle;
import com.smartech.course.racing.vehicle.payload.PayloadCarriable;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractVehicleBuilder<VehicleType extends Vehicle> implements VehicleBuilder<VehicleType> {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	protected String name;
	protected double weight;
	protected double maxSpeed;
	protected double acceleration;

	/**
	 * 
	 */
	public AbstractVehicleBuilder() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.VehicleBuilder#name(java.lang.String)
	 */
	@Override
	public VehicleBuilder<VehicleType> name(String name) {
		this.name = name;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.VehicleBuilder#weight(double)
	 */
	@Override
	public VehicleBuilder<VehicleType> weight(double weight) {
		this.weight = weight;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.VehicleBuilder#maxSpeed(double)
	 */
	@Override
	public VehicleBuilder<VehicleType> maxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.VehicleBuilder#acceleration(double)
	 */
	@Override
	public VehicleBuilder<VehicleType> acceleration(double acceleration) {
		this.acceleration = acceleration;
		return this;
	}	

}
