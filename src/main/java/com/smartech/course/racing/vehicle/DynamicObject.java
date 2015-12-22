/**
 * 
 */
package com.smartech.course.racing.vehicle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.dao.Persistable;
import com.smartech.course.racing.exception.CreatingVehicleException;

/**
 * Dynamic object
 * @author Alexey Solomatin
 *
 */
public class DynamicObject implements Persistable<Long> {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	protected final Long id;
	protected String name;
	protected double weight;
	protected double maxSpeed;

	/**
	 * 
	 */
	public DynamicObject(Long id, String name, double weight, double maxSpeed) throws CreatingVehicleException {
		if (name == null ||
			weight < 0 ||
			maxSpeed < 0)
			throw new CreatingVehicleException("Cannot create DynamicObject with the specified parameters.");
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.maxSpeed = maxSpeed;
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
	
	protected double calculateCurrentMaxSpeed() {
		return maxSpeed;
	}

	@Override
	public Long getId() {
		return id;
	}

}
