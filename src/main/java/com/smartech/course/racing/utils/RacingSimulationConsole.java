/**
 * 
 */
package com.smartech.course.racing.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.simulation.Racer;
import com.smartech.course.racing.simulation.RacingSimulation;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingSimulationConsole {
	private Logger log = LoggerFactory.getLogger(getClass());

	private static volatile RacingSimulationConsole instance;

	/**
	 * 
	 */
	private RacingSimulationConsole() {
		// TODO Auto-generated constructor stub
	}
	
	public static RacingSimulationConsole getInstance() {
		RacingSimulationConsole localInstance = instance;
		if (localInstance == null) {
			synchronized (RacingSimulationConsole.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new RacingSimulationConsole();
				}
			}
		}
		return localInstance;
	}
	
	public synchronized void printRacingSimulationState(RacingSimulation simulation) {
		System.console().printf("------------------------------------\n");
		simulation.listRacers().stream().forEach(this::printRacerState);
		System.console().printf("------------------------------------\n");
	}
	
	public synchronized void onRacerEvent(Racer racer, Object event) {
		log.debug("onRacerEvent({}, {})", racer, event);
		System.console().printf("%.1f s: %s finished!\n", racer.getVehicleState().getTime(), racer.getName());
	}		

	private void printRacerState(Racer racer) {		
		System.console().printf("%.1f s: %-10s at position: \t%.1f/%.1f meters, cur. speed: %f, avg. speed: %f\n", 
			racer.getVehicleState().getTime(), 
			racer.getName(),
			racer.getVehicleState().getPosition(), 
			racer.getRacing().getDistance(),
			racer.getVehicleState().getSpeed(),
			racer.getAverageSpeed());
	}
		

}
