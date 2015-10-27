/**
 * 
 */
package com.smartech.course.racing.demo.greeting;

import com.smartech.course.racing.demo.user.User;

/**
 * Racing Simulator user greeter
 * @author Alexey Solomatin
 *
 */
public class RacingSimulatorUserGreeter implements UserGreeter {
	@Override
	public void greet(User user) throws IllegalArgumentException {
		if (user == null)
			throw new IllegalArgumentException("User cannot be null.");
		System.console().printf("Hello %s! This is Racing Simulator!\n", user.getName());
		if (user.isCelebratingToday())
			System.console().printf("Congratulations! Happy birthday!\n");
	}
}
