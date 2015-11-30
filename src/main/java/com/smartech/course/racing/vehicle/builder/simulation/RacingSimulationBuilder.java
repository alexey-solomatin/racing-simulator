/**
 * 
 */
package com.smartech.course.racing.vehicle.builder.simulation;

import com.smartech.course.racing.RacingSimulation;

/**
 * @author Alexey Solomatin
 *
 */
public interface RacingSimulationBuilder {
	RacingSimulationBuilder racing();
	RacingSimulationBuilder timeStep();
	RacingSimulationBuilder vehicles();
	RacingSimulation build();
}
