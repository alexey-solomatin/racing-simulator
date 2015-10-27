/**
 * 
 */
package com.smartech.course.racing.demo.greeting.simple;

/**
 * Greeter for Racing Siulator
 * @author Alexey Solomatin
 *
 */
public class RacingSimulatorGreeter implements Greeter {
	@Override
	public void greet(String username) {
		System.console().printf("Hello %s! This is Racing Simulator!\n", username);
	}
}
