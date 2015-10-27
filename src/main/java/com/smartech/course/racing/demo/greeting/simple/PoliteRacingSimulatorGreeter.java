/**
 * 
 */
package com.smartech.course.racing.demo.greeting.simple;

/**
 * Polite {@link RacingSimulatorGreeter}
 * @author Alexey Solomatin
 *
 */
public class PoliteRacingSimulatorGreeter extends RacingSimulatorGreeter {
	@Override
	public void greet(String username) {
		super.greet(username);
		System.console().printf("Have a good day!\n");
	}
}
