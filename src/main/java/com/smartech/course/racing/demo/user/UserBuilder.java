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
	 * @return the user name
	 */
	UserBuilder name();
	
	/**
	 * Constructs user birthday
	 * @return the user birthday
	 */
	UserBuilder birthday();
	
	/**
	 * Builds the user from the specified data
	 * @return the user
	 */
	User build();
}
