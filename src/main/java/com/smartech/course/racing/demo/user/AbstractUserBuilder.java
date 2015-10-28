/**
 * 
 */
package com.smartech.course.racing.demo.user;

import java.time.LocalDate;

/**
 * Abstract user builder
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractUserBuilder implements UserBuilder {
	protected String name;
	protected LocalDate birthday;
	protected UserType type;

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.demo.user.UserBuilder#build()
	 */
	@Override
	public User build() {
		return new User(name, birthday, type);
	}

}
