/**
 * 
 */
package com.smartech.course.racing.vehicle.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.smartech.course.racing.vehicle.Car;
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
		// TODO: implement proper creation
		super(properties, Car::new, new HashMap<>());
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.factory.AbstractVehicleFactory#createVehicleInstance()
	 */
	@Override
	protected Vehicle createVehicleInstance() {
		// TODO Auto-generated method stub
		return new Car();
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.factory.AbstractVehicleFactory#initializeVehicle(com.smartech.course.racing.vehicle.Vehicle)
	 */
	@Override
	protected void initializeVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}

}
