/**
 * 
 */
package com.smartech.course.racing.vehicle.factory;

import java.util.Properties;

import com.smartech.course.racing.vehicle.Vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractVehicleFactory implements VehicleFactory {
	private Properties properties;

	/**
	 * 
	 */
	public AbstractVehicleFactory(Properties properties) {
		this.properties = properties;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.factory.VehicleFactory#createVehicle()
	 */
	@Override
	public Vehicle createVehicle() {
		Vehicle vehicle = createVehicleInstance();
		initializeVehicle(vehicle);
		return vehicle;
	}

	protected abstract Vehicle createVehicleInstance();
	
	protected abstract void initializeVehicle(Vehicle vehicle);

}
