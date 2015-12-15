/**
 * 
 */
package com.smartech.course.racing.simulation;

import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * @author Alexey Solomatin
 *
 */
public class MultiThreadRacingSimulation extends AbstractRacingSimulation {

	/**
	 * @param racing
	 * @param timeStep
	 */
	public MultiThreadRacingSimulation(Racing racing, double timeStep) {
		super(racing, timeStep);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.simulation.AbstractRacingSimulation#run()
	 */
	@Override
	public void run() throws MovingVehicleException {
		// TODO Auto-generated method stub

	}

}
