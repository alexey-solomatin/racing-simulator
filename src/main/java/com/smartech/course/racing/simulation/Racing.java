/**
 * 
 */
package com.smartech.course.racing.simulation;

import com.smartech.course.racing.dao.Persistable;

/**
 * Racing represents the racing track and the racers.
 * @author Alexey Solomatin
 *
 */
public class Racing implements Persistable<Long> {
	private Long id;
	private String name;
	private double distance;
	
	
	public Racing(Long id) {
		this.id = id;
	}
	
	public Racing(Long id, String name, double distance) {
		this.id = id;
		this.name = name;
		this.distance = distance;
	}
	
	public Racing(Racing racing) {
		this.name = racing.name;
		this.distance = racing.distance;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Racing [id=" + id + ", name=" + name + ", distance=" + distance + "]";
	}

	@Override
	public Long getId() {
		return id;
	}
	
}
