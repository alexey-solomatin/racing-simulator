/**
 * 
 */
package com.smartech.course.racing.demo.greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.demo.user.User;

/**
 * Racing Simulator user greeter
 * @author Alexey Solomatin
 *
 */
public class RacingSimulatorUserGreeter implements UserGreeter {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void greet(User user) throws IllegalArgumentException {
		if (user == null)
			throw new IllegalArgumentException("User cannot be null.");
		log.debug("Greeting user {}.", user);
		System.console().printf("Hello %s! This is Racing Simulator!\n", user.getName());
		if (user.isCelebratingToday())
			System.console().printf("Congratulations! Happy birthday!\n");
	}
}
