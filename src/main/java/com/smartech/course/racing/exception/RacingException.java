/**
 * 
 */
package com.smartech.course.racing.exception;

/**
 * Basic racing exception
 * @author Alexey Solomatin
 *
 */
public abstract class RacingException extends Exception {
	public RacingException(String message) {
		super(message);
	}
}
