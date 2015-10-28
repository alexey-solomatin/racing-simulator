/**
 * 
 */
package com.smartech.course.racing.demo.user;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * User builder from the console which distinguishes user's type
 * @author Alexey Solomatin
 *
 */
public class ConsoleDistinguishingUserBuilder extends ConsoleUserBuilder {
	private Map<String, UserType> loginTypes;	
	
	public ConsoleDistinguishingUserBuilder(Map<String, UserType> loginTypes) throws IllegalArgumentException {
		if (loginTypes == null)
			throw new IllegalArgumentException("The login types are not specified.");
		this.loginTypes = loginTypes;
	}


	/* (non-Javadoc)
	 * @see com.smartech.course.racing.demo.user.UserBuilder#type()
	 */
	@Override
	public UserBuilder type() {
		// getting a user login and determining the type
		String login = null;
		while (StringUtils.isBlank(login)) {
			login = System.console().readLine("Please enter your login: ");
			if (StringUtils.isBlank(login))
				System.console().printf("Ooops! Looks like you've entered the incorrect login.\nPlease try again.\n");					
		}
		type = loginTypes.get(login);
		if (type == null)
			type = UserType.GUEST;
		return this;		
	}

}
