/**
 * 
 */
package com.smartech.course.racing.exception;

/**
 * @author Alexey Solomatin
 *
 */
public class DataAccessOperationErrorException extends DataAccessException {

	/**
	 * 
	 */
	public DataAccessOperationErrorException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DataAccessOperationErrorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DataAccessOperationErrorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataAccessOperationErrorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DataAccessOperationErrorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
