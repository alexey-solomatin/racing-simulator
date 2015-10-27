/**
 * 
 */
package com.smartech.course.racing.demo.greeting.simple;

/**
 * Greeter interface
 * @author Alexey Solomatin
 *
 */
public interface Greeter {
	// Only since Java 8!
	default void greet(String username) {
		System.out.println("Hi " + username);
	}
}
