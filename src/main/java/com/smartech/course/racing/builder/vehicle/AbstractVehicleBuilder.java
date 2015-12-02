/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.vehicle.Vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractVehicleBuilder<VehicleType extends Vehicle, VehicleBuilderType extends VehicleBuilder<?, ?>> implements VehicleBuilder<VehicleType, VehicleBuilderType> {
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
	public VehicleBuilderType name(String name) {
		this.name = name;
		return (VehicleBuilderType) this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.VehicleBuilder#weight(double)
	 */
	@Override
	public VehicleBuilderType weight(double weight) {
		this.weight = weight;
		return (VehicleBuilderType) this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.VehicleBuilder#maxSpeed(double)
	 */
	@Override
	public VehicleBuilderType maxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
		return (VehicleBuilderType) this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.vehicle.VehicleBuilder#acceleration(double)
	 */
	@Override
	public VehicleBuilderType acceleration(double acceleration) {
		this.acceleration = acceleration;
		return (VehicleBuilderType) this;
	}	

}
