/**
 * 
 */
package com.smartech.course.racing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		simulation.listRacers().stream().forEach(this::printRacerPosition);
		System.console().printf("------------------------------------\n");
	}
	
	public synchronized void onRacerEvent(Racer racer, Object event) {
		log.debug("onRacerEvent({}, {})", racer, event);
		System.console().printf("%s finished!\n", racer.getVehicle().getName());
	}		

	private void printRacerPosition(Racer racer) {		
		System.console().printf("%-10s at position: \t%.1f/%.1f meters\n", racer.getVehicle().getName(),
				racer.getVehicleState().getPosition(), racer.getRacing().getDistance());
	}
		

}
