/**
 * 
 */
package com.smartech.course.racing.builder.simulation;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingSimulationBuilderFactory {
	public static enum RacingSimulationType {
		SINGLE_THREAD,
		MULTI_THREAD
	}
	
	private RacingSimulationBuilderFactory() {
		
	}
	
	public static RacingSimulationBuilder newRacingSimulationBuilder(RacingSimulationType type) {
		switch (type) {
			case SINGLE_THREAD:
				return new SingleThreadRacingSimulationBuilder();
			case MULTI_THREAD:
				return new MultiThreadRacingSimulationBuilder();
			default:
				throw new IllegalArgumentException("Cannot create a builder for type " + type);
		}
	}

}
