/**
 * 
 */
package com.smartech.course.racing.demo.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;

/**
 * Builder for building a user from the console
 * @author Alexey Solomatin
 *
 */
public class ConsoleUserBuilder extends AbstractUserBuilder {
	
	public ConsoleUserBuilder() {
		super();
		type = UserType.GUEST;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.demo.user.UserBuilder#name()
	 */
	@Override
	public UserBuilder name() {
		// getting user name
		name = null;
		while (StringUtils.isBlank(name)) {
			name = System.console().readLine("Please enter your name: ");
			if (StringUtils.isBlank(name))
				System.console().printf("Ooops! Looks like you've entered the incorrect name.\nPlease try again.\n");					
		}				
		return this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.demo.user.UserBuilder#birthday()
	 */
	@Override
	public UserBuilder birthday() {
		// getting user birthday					
		birthday = null;
		do {
			String strBirthday = System.console().readLine("Please enter your birthday in the yyyy-mm-dd format, e.g. 1990-01-01: ");			
			try {
				// only since Java 8
				birthday = LocalDate.parse(strBirthday, DateTimeFormatter.ISO_LOCAL_DATE);
			} catch (DateTimeParseException e) {
				System.console().printf("Ooops! Looks like you've entered the incorrect birthday.\nPlease try again.\n");
				birthday = null;
			} catch (Exception e) {
				System.console().printf("Ooops! Looks like you've entered the birthday in the wrong format.\nPlease try again.\n");
				birthday = null;
			}
		} while (birthday == null);		
		return this;
	}

	@Override
	public UserBuilder type() {
		type = UserType.GUEST;
		return this;
	}

}
