/**
 * 
 */
package com.smartech.course.racing.demo.greeting;

import com.smartech.course.racing.demo.user.User;

/**
 * Factory of greeters
 * @author Alexey Solomatin
 *
 */
public class UserGreeterFactory {
	private static final int RESTRICTED_ACCESS_YEARS = 18;
	
	/**
	 * Creates the greeter based on the user age
	 * @param user the user to be greeted
	 * @return the user greeter
	 * @throws IllegalArgumentException
	 */
	public static UserGreeter createUserGreeter(User user) throws IllegalArgumentException {
		if (user == null)
			throw new IllegalArgumentException("User is not specified.");
		if (user.getAge().getYears() < RESTRICTED_ACCESS_YEARS)			
			return new PoliteUserGreeterWrapper(new RestrictedAccessUserGreeter());
		
		return new PoliteUserGreeterWrapper(new RacingSimulatorDistinguishingUserGreeter());
	}
}
