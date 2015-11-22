/**
 * 
 */
package com.smartech.course.racing;

import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * Interface that represents basic racer functionality
 * @author Alexey Solomatin
 *
 */
public interface Raceable {	
	void move(double time) throws MovingVehicleException;
	boolean isFinished();
}
