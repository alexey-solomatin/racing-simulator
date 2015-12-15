/**
 * 
 */
package com.smartech.course.racing.vehicle.event;

/**
 * @author Alexey Solomatin
 *
 */
public class VehicleEvent {
	private final String message;	

	/**
	 * 
	 */
	public VehicleEvent(String message) {		
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
