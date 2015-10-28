/**
 * 
 */
package com.smartech.course.racing.demo.greeting;

import com.smartech.course.racing.demo.user.User;

/**
 * Racing Simulator greeter distinguishing users of several types
 * @author Alexey Solomatin
 *
 */
public class RacingSimulatorDistinguishingUserGreeter extends RacingSimulatorUserGreeter {
	@Override
	public void greet(User user) throws IllegalArgumentException {		
		super.greet(user);
		switch (user.getType()) {
		case GUEST:
			System.console().printf("You logged in as a guest.\n");
			break;
		case USER:
			System.console().printf("You logged in as an ordinary user.\n");
			break;
		case ADMINISTRATOR:
			System.console().printf("You logged in as an administrator.\n");
			break;
		default:
			throw new IllegalArgumentException("User of unknown type!");			
		}
	}
}
