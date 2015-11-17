/**
 * 
 */
package com.smartech.course.racing.vehicle;

/**
 * Basic vehicle
 * @author Alexey Solomatin
 *
 */
public abstract class Vehicle {
	protected String name;
	protected long weight;
	protected double maxSpeed;	
	
	public Vehicle() {
		
	}

	public Vehicle(String name, long weight, double maxSpeed) {
		super();
		this.name = name;
		this.weight = weight;
		this.maxSpeed = maxSpeed;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
}
