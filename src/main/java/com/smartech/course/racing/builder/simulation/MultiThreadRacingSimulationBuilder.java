/**
 * 
 */
package com.smartech.course.racing.builder.simulation;

import com.smartech.course.racing.simulation.MultiThreadRacingSimulation;
import com.smartech.course.racing.simulation.RacingSimulation;

/**
 * @author Alexey Solomatin
 *
 */
public class MultiThreadRacingSimulationBuilder extends AbstractRacingSimulationBuilder {

	/**
	 * 
	 */
	public MultiThreadRacingSimulationBuilder() {		
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.simulation.AbstractRacingSimulationBuilder#createSimulation()
	 */
	@Override
	protected RacingSimulation createSimulation() {
		return new MultiThreadRacingSimulation(racing, timeStep);
	}

}
