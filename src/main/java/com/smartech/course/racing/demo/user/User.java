/**
 * 
 */
package com.smartech.course.racing.demo.user;

import java.time.LocalDate;
import java.time.Period;

import org.apache.commons.lang3.StringUtils;

/**
 * User to be greeted
 * @author Alexey Solomatin
 *
 */
public class User {
	private String name;
	private LocalDate birthday;
	private UserType type;
	
	
	public User(String name, LocalDate birthday, UserType type) throws IllegalArgumentException {
		if (StringUtils.isBlank(name))
			throw new IllegalArgumentException("The user name is illegal.");
		if (birthday == null)
			throw new IllegalArgumentException("The user birthday is illegal.");
		if (type == null)
			throw new IllegalArgumentException("The user type is illegal.");
		this.name = name;
		this.birthday = birthday;
		this.type = type;
	}
	
	/**
	 * Gets the user age
	 * @param date the date to which the age is calculated
	 * @return the user age
	 */
	public Period getAge(LocalDate date) {
		if (date == null)
			throw new IllegalArgumentException("The specified date is illegal.");
		return birthday.until(date).normalized();
	}
	
	/**
	 * Gets current user age
	 * @return the user age
	 */
	public Period getAge() {
		return getAge(LocalDate.now());
	}
	
	/**
	 * Checks if the user celebrates his or her birthday today
	 * @return
	 */
	public boolean isCelebratingToday() {		
		return birthday.getMonthValue() == LocalDate.now().getMonthValue() 
			&& birthday.getDayOfMonth() == LocalDate.now().getDayOfMonth();  
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", birthday=" + birthday + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
}
