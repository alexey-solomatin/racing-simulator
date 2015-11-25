/**
 * 
 */
package com.smartech.course.racing.exception;

/**
 * Exception for unsuccessful creating a vehicle of its parts
 * @author Alexey Solomatin
 *
 */
public final class CreatingVehicleException extends RacingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5136360304229418629L;

	/**
	 * 
	 */
	public CreatingVehicleException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CreatingVehicleException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CreatingVehicleException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CreatingVehicleException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CreatingVehicleException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
