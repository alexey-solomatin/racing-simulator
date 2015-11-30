/**
 * 
 */
package com.smartech.course.racing.vehicle.builder.racing;

import com.smartech.course.racing.Racing;

/**
 * @author Alexey Solomatin
 *
 */
public interface RacingBuilder {
	RacingBuilder name();
	RacingBuilder distance();
	Racing build();
}
