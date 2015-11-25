/**
 * 
 */
package com.smartech.course.racing.exception;

/**
 * Basic racing exception
 * @author Alexey Solomatin
 *
 */
public class RacingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5169344918041985529L;

	/**
	 * 
	 */
	public RacingException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public RacingException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public RacingException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RacingException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RacingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
