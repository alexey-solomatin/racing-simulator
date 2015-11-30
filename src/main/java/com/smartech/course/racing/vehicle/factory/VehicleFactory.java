/**
 * 
 */
package com.smartech.course.racing.vehicle.factory;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public interface VehicleFactory {
	Vehicle createVehicle() throws CreatingVehicleException;
}
