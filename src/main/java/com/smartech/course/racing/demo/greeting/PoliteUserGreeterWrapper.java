/**
 * 
 */
package com.smartech.course.racing.demo.greeting;

import com.smartech.course.racing.demo.user.User;

/**
 * Polite wrapper for {@link UserGreeter}
 * @author Alexey Solomatin
 *
 */
public class PoliteUserGreeterWrapper implements UserGreeter {
	private final UserGreeter userGreeter;
	
	/**
	 * Created polite wrapper for {@link UserGreeter}
	 * @param userGreeter the user greeter to be wrapped
	 */
	public PoliteUserGreeterWrapper(UserGreeter userGreeter) {
		if (userGreeter == null)
			throw new IllegalArgumentException("User greeter cannot be null.");
		this.userGreeter = userGreeter;
	}
	
	@Override
	public void greet(User user) throws IllegalArgumentException {
		userGreeter.greet(user);
		bePolite();
	}
	
	private void bePolite() {
		System.console().printf("Have a good day!\n");
	}
}
