/**
 * 
 */
package com.smartech.course.racing.vehicle.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractVehicleFactory implements VehicleFactory {
	private Properties properties;
	private Supplier<Vehicle> vehicleSupplier;
	private Map<String, Object> vehicleFields;

	/**
	 * 
	 */
	public AbstractVehicleFactory(Properties properties, Supplier<Vehicle> vehicleSupplier, Map<String, Object> vehicleFields) {
		this.properties = properties;
		this.vehicleSupplier = vehicleSupplier;
		this.vehicleFields = vehicleFields;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.factory.VehicleFactory#createVehicle()
	 */
	@Override
	public Vehicle createVehicle() throws CreatingVehicleException {
		Vehicle vehicle = vehicleSupplier.get();
		vehicleFields.keySet().stream().forEach((fieldName) -> {
			try {
				Class<?> clazz = vehicle.getClass();
				Object fieldValue = vehicleFields.get(fieldName);
				Method fieldSetter = clazz.getMethod("set" + fieldName, fieldValue.getClass());
				fieldSetter.invoke(vehicle, fieldValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		initializeVehicle(vehicle);
		return vehicle;		
	}

	protected abstract Vehicle createVehicleInstance();
	
	protected abstract void initializeVehicle(Vehicle vehicle);

}
