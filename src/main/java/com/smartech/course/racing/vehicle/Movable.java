/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.MovingException;

/**
 * Interface that movable vehicles should implement
 * @author Alexey Solomatin
 *
 */
public interface Movable {	
	void move(double time) throws MovingException;	
}
