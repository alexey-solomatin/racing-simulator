/**
 * 
 */
package com.smartech.course.racing.simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Movable;

/**
 * Simulation of a racing of some vehicles 
 * @author Alexey Solomatin
 *
 */
public class SingleThreadRacingSimulation extends AbstractRacingSimulation {

	/**
	 * Creates the racing simulating with specified racing and time step.
	 * Vehicles should be registered separately.
	 * @param racing the racing for the simulation
	 * @param timeStep the time step for the simulation
	 */
	public SingleThreadRacingSimulation(Racing racing, double timeStep) {
		super(racing, timeStep);
	}	

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.simulation.RacingSimulation#run()
	 */
	@Override
	public void run() throws MovingVehicleException {
		log.info("Starting the racing simulation.");
		log.info("Start racers' state: {}.", racers);		
		activeRacers = new ArrayList<>(racers);
		double printStateTimeStep = 0;
		double time = 0;
		while (!activeRacers.isEmpty()) {
			Collection<Raceable> finished = new ArrayList<>();
			synchronized (lock) {
				for (Raceable racer : activeRacers) {
					racer.move(timeStep);
					if (racer.isFinished()) {
						log.debug("{} finished!", racer);
						finished.add(racer);
					}
				}							
			}			
			for (Raceable racer : finished)
				activeRacers.remove(racer);
			
			// printing the state of the race
			if (log.isDebugEnabled()) {
				printStateTimeStep += timeStep;
				time += timeStep;
				if (printStateTimeStep >= RACING_LOGGING_TIME_STEP && !activeRacers.isEmpty()) {
					printStateTimeStep = 0;				
					log.debug("Racers' state at {} s: {}", time, racers);				
				}
			}			
			
			try {
				Thread.sleep((long)(1000*timeStep));
			} catch (InterruptedException e) {
				log.error("Error during the racing simulation.", e);
			}
		}		
		log.info("Finish racers' state: {}.", racers);
		log.info("Racing simulation ended.");
	}
}
