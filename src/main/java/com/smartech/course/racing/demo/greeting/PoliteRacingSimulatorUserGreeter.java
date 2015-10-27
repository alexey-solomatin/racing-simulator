/**
 * 
 */
package com.smartech.course.racing.demo.greeting;

import com.smartech.course.racing.demo.user.User;

/**
 * Polite {@link RacingSimulatorUserGreeter}
 * @author Alexey Solomatin
 *
 */
@Deprecated
public class PoliteRacingSimulatorUserGreeter extends RacingSimulatorUserGreeter {
	@Override
	public void greet(User user) throws IllegalArgumentException {
		super.greet(user);
		System.console().printf("Have a good day!\n");
	}
}
