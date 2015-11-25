/**
 * 
 */
package com.smartech.course.racing.vehicle.payload;

/**
 * Interface for object which can carry payload
 * @author Alexey Solomatin
 *
 */
public interface PayloadCarriable {	
	/**
	 * Returns the maximal payload weight which can be carried
	 * @return the maximal payload weight
	 */
	double getMaxPayloadWeight();

	/**
	 * Returns the current payload weight
	 * @return the current payload weight
	 */
	double getPayloadWeight();

}
