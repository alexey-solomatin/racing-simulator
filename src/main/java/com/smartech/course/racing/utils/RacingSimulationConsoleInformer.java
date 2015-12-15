/**
 * 
 */
package com.smartech.course.racing.utils;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.simulation.SingleThreadRacingSimulation;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingSimulationConsoleInformer {
	private Logger log = LoggerFactory.getLogger(getClass());
	private static final int EXECUTOR_SERVICE_TERMINATION_TIMEOUT = 10;
	
	private SingleThreadRacingSimulation simulation;
	private Duration frequency;
	private ScheduledExecutorService executorService;

	/**
	 * 
	 */
	public RacingSimulationConsoleInformer(SingleThreadRacingSimulation simulation, Duration frequency) {
		this.simulation = simulation;
		this.frequency = frequency;
	}

	public void start() {
		log.info("Starting the racing simulation console informer.");
		if (executorService == null) {
			executorService = Executors.newSingleThreadScheduledExecutor();
			executorService.scheduleAtFixedRate(()-> {
				if (simulation.isRunning())
					RacingSimulationConsole.getInstance().printRacingSimulationBriefState(simulation);				
				},
				0,
				frequency.toMillis(), 
				TimeUnit.MILLISECONDS);
		}
	}

	public void stop() {
		log.info("Stopping the racing simulation console informer.");
		if (executorService != null) {			
			try {
				executorService.shutdownNow();
				// Wait a while for tasks to respond to being cancelled
				if (!executorService.awaitTermination(EXECUTOR_SERVICE_TERMINATION_TIMEOUT, TimeUnit.SECONDS)) {
					log.error("Executor service did not terminate");					
				}
			} catch (InterruptedException e) {
				// (Re-)Cancel if current thread also interrupted
				executorService.shutdownNow();
				// Preserve interrupt status
				Thread.currentThread().interrupt();
			}
			executorService = null;
		} else
			log.warn("The racing simulator console informer is not started.");
	}

	

}
