/**
 * 
 */
package com.smartech.course.racing.exception;

/**
 * Exception for unsuccessful moving a vehicle
 * @author Alexey Solomatin
 *
 */
public final class MovingVehicleException extends RacingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1906766067394877029L;

	/**
	 * 
	 */
	public MovingVehicleException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MovingVehicleException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MovingVehicleException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MovingVehicleException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MovingVehicleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
