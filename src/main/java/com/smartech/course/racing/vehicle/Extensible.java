/**
 * 
 */
package com.smartech.course.racing.vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public interface Extensible {
	void connect(VehicleExtension vehicleExtension);
	void remove(VehicleExtension vehicleExtension);
}
