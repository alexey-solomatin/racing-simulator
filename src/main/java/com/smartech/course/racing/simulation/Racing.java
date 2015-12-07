/**
 * 
 */
package com.smartech.course.racing.simulation;

/**
 * Racing represents the racing track and the racers.
 * @author Alexey Solomatin
 *
 */
public class Racing {	
	private String name;
	private double distance;
	
	
	public Racing() {
		
	}
	
	public Racing(String name, double distance) {
		this.name = name;
		this.distance = distance;
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
		return "Racing [name=" + name + ", distance=" + distance + "]";
	}
	
}
