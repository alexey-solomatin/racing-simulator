/**
 * 
 */
package com.smartech.course.racing.vehicle.builder.racing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.Racing;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractRacingBuilder implements RacingBuilder {
	protected Logger log = LoggerFactory.getLogger(getClass());
	protected String name;
	protected double distance;

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.builder.racing.RacingBuilder#build()
	 */
	@Override
	public Racing build() {
		// TODO Auto-generated method stub
		return new Racing(name, distance);
	}

}
