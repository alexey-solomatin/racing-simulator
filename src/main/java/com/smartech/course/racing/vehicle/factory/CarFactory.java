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
public class CarFactory extends AbstractVehicleFactory {

	/**
	 * @param properties
	 */
	public CarFactory(Properties properties) {
		super(properties);
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.factory.AbstractVehicleFactory#createVehicleInstance()
	 */
	@Override
	protected Vehicle createVehicleInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.factory.AbstractVehicleFactory#initializeVehicle(com.smartech.course.racing.vehicle.Vehicle)
	 */
	@Override
	protected void initializeVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub

	}

}
