/**
 * 
 */
package com.smartech.course.racing.builder.racing;

import java.util.function.Supplier;

import com.smartech.course.racing.Racing;

/**
 * @author Alexey Solomatin
 *
 */
public interface RacingBuilder {
	RacingBuilder name(String name);
	default RacingBuilder name(Supplier<String> nameSupplier) {
		return name(nameSupplier.get());
	}
	RacingBuilder distance(double distance);
	default RacingBuilder distance(Supplier<Double> distanceSupplier) {
		return distance(distanceSupplier.get());
	}
	Racing build();
}
