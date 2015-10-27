/**
 * 
 */
package com.smartech.course.racing.demo.greeting.simple;

/**
 * Greeter when the access is restricted
 * @author Alexey Solomatin
 *
 */
public class RestrictedAccessGreeter implements Greeter {
	@Override
	public void greet(String username) {
		System.console().printf("Hello %s! YOU DO NOT HAVE ACCESS TO THIS APPLICATION!\n", username);
	}
}
