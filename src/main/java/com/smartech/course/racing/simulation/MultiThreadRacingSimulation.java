/**
 * 
 */
package com.smartech.course.racing.simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * @author Alexey Solomatin
 *
 */
public class MultiThreadRacingSimulation extends AbstractRacingSimulation {

	/**
	 * Creates the racing simulating with specified racing and time step.
	 * Vehicles should be registered separately.
	 * @param racing the racing for the simulation
	 * @param timeStep the time step for the simulation
	 */
	public MultiThreadRacingSimulation(Racing racing, double timeStep) {
		super(racing, timeStep);		
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.simulation.AbstractRacingSimulation#run()
	 */
	@Override
	public void run() throws MovingVehicleException {
		log.info("Starting the racing simulation.");
		log.info("Start racers' state: {}.", racers);
		CountDownLatch countDownLatch = new CountDownLatch(racers.size());
		activeRacers = new ArrayList<>(racers);		
		activeRacers.stream().map((racer) -> {
			return new Thread(()-> {
				try {
					double printStateTimeStep = 0;
					double time = 0;
					while (!racer.isFinished()) {
						synchronized (lock) { // TODO: refactor
							racer.move(timeStep);
							if (racer.isFinished()) {								
								log.debug("{} finished!", racer);								
								activeRacers.remove(racer);
							}
						}						
						// printing the state of the racer
						if (log.isDebugEnabled()) {
							printStateTimeStep += timeStep;
							time += timeStep;
							if (printStateTimeStep >= RACING_LOGGING_TIME_STEP) {
								printStateTimeStep = 0;				
								log.debug("Racer's state at {} s: {}", time, racer);				
							}
						}	
						try {
							Thread.sleep((long)(1000*timeStep));
						} catch (InterruptedException e) {
							log.error("Error during the racing simulation.", e);
						}
					}
				} catch (MovingVehicleException e) {
					log.error("Error during racer moving.", e);					
				} finally {
					countDownLatch.countDown();
				}
			});
		}).forEach((t)->t.start());	
		
		try {
			log.info("Waiting for the end of the simulation.");
			countDownLatch.await();
		} catch (InterruptedException e) {
			log.error("Waiting for the end of the simulation was interrupted.", e);			
		}	
		log.info("Finish racers' state: {}.", racers);
		log.info("Racing simulation ended.");
	}

}
