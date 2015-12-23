/**
 * 
 */
package com.smartech.course.racing.simulation;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;

import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * Multi thread implementation of a racing simulation
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
		CountDownLatch activeRacersLatch = new CountDownLatch(racers.size());
		activeRacers = new ArrayList<>(racers);
		Phaser simulationPhaser = new Phaser();
		activeRacers.stream().map((racer) -> {
			return new Thread(()-> {
				try {
					log.debug("Registering to the phaser.");
					simulationPhaser.register();
					log.debug("Simulation phaser after registration: {}.", simulationPhaser);
					double printStateTimeStep = 0;
					double time = 0;
					while (!racer.isFinished()) {
						try {
							log.debug("Updating the state of racer {}.", racer);
							log.debug("Simulation phase: {}.", simulationPhaser.getPhase());
							log.debug("Acquiring the racers semaphore for racer {}.", racer);
							getRacersSemaphore().acquire();
							log.debug("The racers semaphore is acquired for racer {}.", racer);
							racer.move(timeStep);
							if (racer.isFinished()) {								
								log.debug("{} finished!", racer);								
								activeRacers.remove(racer);
							}
							log.debug("The state of racer {} is updated.", racer);
						} catch (InterruptedException e1) {
							log.error("Error during updating the racer state.", e1);
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
							log.debug("Waiting for other racer threads.");
							log.debug("Simulation phaser: {}.", simulationPhaser);
							simulationPhaser.arriveAndAwaitAdvance();
							log.debug("Releasing the racers semaphore for racer {}.", racer);
							getRacersSemaphore().release();
							log.debug("The racers semaphore is released for racer {}.", racer);
							Thread.sleep((long)(1000*timeStep));							
						} catch (InterruptedException e) {
							log.error("Error during the racing simulation.", e);
						}
					}
				} catch (MovingVehicleException e) {
					log.error("Error during racer moving.", e);					
				} finally {					
					simulationPhaser.arriveAndDeregister();
					activeRacersLatch.countDown();
				}
			});
		}).forEach((t)->t.start());	
		
		try {
			log.info("Waiting for the end of the simulation.");
			activeRacersLatch.await();
		} catch (InterruptedException e) {
			log.error("Waiting for the end of the simulation was interrupted.", e);			
		}	
		log.info("Finish racers' state: {}.", racers);
		log.info("Racing simulation ended.");
	}

}
