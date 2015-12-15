/**
 * 
 */
package com.smartech.course.racing.simulation;

import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * Interface that represents basic racer functionality
 * @author Alexey Solomatin
 *
 */
public interface Raceable {	
	void move(double time) throws MovingVehicleException;
	boolean isFinished();
	double getAverageSpeed();
	long getSpeedLosingNumber();
}
