/**
 * 
 */
package com.smartech.course.racing.demo.user;

/**
 * General builder for {@link User}
 * @author Alexey Solomatin
 *
 */
public interface UserBuilder {
	/**
	 * Constructs user name
	 * @return the user builder
	 */
	UserBuilder name();
	
	/**
	 * Constructs user birthday
	 * @return the user builder
	 */
	UserBuilder birthday();
	
	/**
	 * Constructs user type
	 * @return the user builder
	 */
	UserBuilder type();
	
	/**
	 * Builds the user from the specified data
	 * @return the user
	 */
	User build() throws IllegalArgumentException;
}
