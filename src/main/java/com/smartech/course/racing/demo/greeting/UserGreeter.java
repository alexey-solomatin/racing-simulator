/**
 * 
 */
package com.smartech.course.racing.demo.greeting;

import com.smartech.course.racing.demo.user.User;

/**
 * User greeter interface
 * @author Alexey Solomatin
 *
 */
public interface UserGreeter {
	// Only since Java 8!
	default void greet(User user) throws IllegalArgumentException {
		if (user != null)
			System.out.println("Hi " + user.getName());
		else
			throw new IllegalArgumentException("User cannot be null.");
	}
}
