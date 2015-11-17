/**
 * 
 */
package com.smartech.course.racing.exception;

/**
 * Exception connected with moving a vehicle
 * @author Alexey Solomatin
 *
 */
public class MovingException extends RacingException {	
	private static final long serialVersionUID = -7170400079894046377L;

	public MovingException(String message) {
		super(message);
	}
}
