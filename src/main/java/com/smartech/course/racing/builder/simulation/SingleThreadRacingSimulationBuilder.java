/**
 * 
 */
package com.smartech.course.racing.builder.simulation;

import com.smartech.course.racing.simulation.RacingSimulation;
import com.smartech.course.racing.simulation.SingleThreadRacingSimulation;

/**
 * @author Alexey Solomatin
 *
 */
public class SingleThreadRacingSimulationBuilder extends AbstractRacingSimulationBuilder {

	/**
	 * 
	 */
	public SingleThreadRacingSimulationBuilder() {
		
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.simulation.AbstractRacingSimulationBuilder#createSimulation()
	 */
	@Override
	protected RacingSimulation createSimulation() {
		return new SingleThreadRacingSimulation(racing, timeStep);
	}

}
