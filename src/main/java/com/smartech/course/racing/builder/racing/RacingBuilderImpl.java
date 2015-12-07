/**
 * 
 */
package com.smartech.course.racing.builder.racing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.simulation.Racing;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingBuilderImpl implements RacingBuilder {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	protected String name;
	protected double distance;

	/**
	 * 
	 */
	public RacingBuilderImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public RacingBuilder name(String name) {
		this.name = name;
		return this;
	}

	@Override
	public RacingBuilder distance(double distance) {
		this.distance = distance;
		return this;
	}

	@Override
	public Racing build() {
		return new Racing(name, distance);
	}

}
