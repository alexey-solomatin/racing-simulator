/**
 * 
 */
package com.smartech.course.racing.simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Semaphore;

import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * Single thread implementation of a racing simulation 
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
			try {
				log.debug("Updating the racers state.");
				log.debug("Acquiring the racers semaphore for {} permits.", racers.size());
				getRacersSemaphore().acquire(racers.size());
				log.debug("The racers semaphore is acquired for {} permits.", racers.size());
				for (Raceable racer : activeRacers) {
					racer.move(timeStep);
					if (racer.isFinished()) {
						log.debug("{} finished!", racer);
						finished.add(racer);
					}
				}				
			} catch (InterruptedException e) {
				log.error("Error during updating the racer states.", e);
			} finally {
				getRacersSemaphore().release(racers.size());
				log.debug("The racers semaphore is released for {} permits.", racers.size());
				log.debug("The racers state updated.");
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
