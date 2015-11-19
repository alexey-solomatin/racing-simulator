/**
 * 
 */
package com.smartech.course.racing.vehicle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alexey Solomatin
 *
 */
public class DynamicObject {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	protected String name;
	protected double weight;
	protected double maxSpeed;

	/**
	 * 
	 */
	public DynamicObject(String name, double weight, double maxSpeed) {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the maxSpeed
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @param maxSpeed the maxSpeed to set
	 */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

}
