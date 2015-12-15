/**
 * 
 */
package com.smartech.course.racing.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.simulation.Racer;
import com.smartech.course.racing.simulation.SingleThreadRacingSimulation;
import com.smartech.course.racing.vehicle.event.VehicleEvent;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingSimulationConsole {
	private Logger log = LoggerFactory.getLogger(getClass());

	private static volatile RacingSimulationConsole instance;
	
	private RacingSimulationWriter consoleWriter;

	/**
	 * 
	 */
	private RacingSimulationConsole() {
		consoleWriter = new RacingSimulationWriter(System.console().writer());
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
	
	public synchronized void printRacingSimulationBriefState(SingleThreadRacingSimulation simulation) {
		consoleWriter.printRacingSimulationBriefState(simulation);
	}
	
	public synchronized void printRacingSimulationFinalState(SingleThreadRacingSimulation simulation) {
		consoleWriter.printRacingSimulationFinalState(simulation);
	}
	
	public synchronized void onRacerEvent(Racer racer, Object event) {
		log.debug("onRacerEvent({}, {})", racer, event);
		if (event instanceof VehicleEvent)
			System.console().printf("%.1f s: %s - %s\n", racer.getVehicleState().getTime(), racer.getName(), ((VehicleEvent)event).getMessage());
	}		

}
