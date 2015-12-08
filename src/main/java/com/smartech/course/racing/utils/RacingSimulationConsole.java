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
	
	public synchronized void printRacingSimulationBriefState(RacingSimulation simulation) {
		System.console().printf("-------------------------------------------------------------\n");
		simulation.getRacerSnapshots().stream().forEachOrdered(this::printRacerBriefState);
		System.console().printf("-------------------------------------------------------------\n");
	}
	
	public synchronized void printRacingSimulationFinalState(RacingSimulation simulation) {
		System.console().printf("-------------------------------------------------------------\n");
		System.console().printf("Racing: %s, %.1f meters\n", simulation.getRacing().getName(), simulation.getRacing().getDistance());
		System.console().printf("Racers:\n");		
		simulation
			.getRacers()
			.stream()
			.sorted((r1, r2)->Double.compare(r1.getVehicleState().getTime(), r2.getVehicleState().getTime()))
			.forEachOrdered(this::printRacerFinalState);
		System.console().printf("-------------------------------------------------------------\n");
	}
	
	public synchronized void onRacerEvent(Racer racer, Object event) {
		log.debug("onRacerEvent({}, {})", racer, event);
		System.console().printf("%.1f s: %s finished!\n", racer.getVehicleState().getTime(), racer.getName());
	}		

	private void printRacerBriefState(Racer racer) {		
		System.console().printf("%.1f s: %-10s in %-10s: \t%.1f/%.1f meters, speed: %.1f m/s\n", 
			racer.getVehicleState().getTime(), 
			racer.getName(),
			racer.getVehicle().getDescription(),
			racer.getVehicleState().getPosition(), 
			racer.getRacing().getDistance(),
			racer.getVehicleState().getSpeed());
	}
	
	private void printRacerFinalState(Racer racer) {		
		System.console().printf("%.1f s - %-10s in %-10s, avg. speed: %.1f m/s\n",			
			racer.getVehicleState().getTime(), 
			racer.getName(),
			racer.getVehicle().getDescription(),
			racer.getAverageSpeed());
	}

}
