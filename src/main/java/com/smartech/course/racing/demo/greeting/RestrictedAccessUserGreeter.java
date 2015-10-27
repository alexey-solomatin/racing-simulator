/**
 * 
 */
package com.smartech.course.racing.demo.greeting;

import com.smartech.course.racing.demo.user.User;

/**
 * User greeter when the access is resticted.
 * @author Alexey Solomatin
 *
 */
public class RestrictedAccessUserGreeter implements UserGreeter {
	@Override
	public void greet(User user) throws IllegalArgumentException {
		System.console().printf("Hello %s! YOU DO NOT HAVE ACCESS TO THIS APPLICATION!\n", user.getName());
	}
}
