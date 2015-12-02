/**
 * 
 */
package com.smartech.course.racing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Movable;

/**
 * Simulation of a racing of some vehicles
 * @author Alexey Solomatin
 *
 */
public class RacingSimulation implements Observer {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static final double RACING_LOGGING_TIME_STEP = 10;
	
	private final Racing racing;
	private Map<Movable, Racer> racers = new ConcurrentHashMap<>(10, 0.9f, 1);
	private double timeStep = 1; // in seconds
	private List<BiConsumer<Racer, Object>> racerEventCallbacks = new ArrayList<>(); 

	/**
	 * Creates the racing simulating with specified racing and time step.
	 * Vehicles should be registered separately.
	 * @param racing the racing for the simulation
	 * @param timeStep the time step for the simulation
	 */
	public RacingSimulation(Racing racing, double timeStep) {
		log.debug("Creating the racing simulation with {} and time step {} s.", racing, timeStep);
		this.racing = racing;
		this.timeStep = timeStep;
	}
	
	public void register(Movable vehicle) {
		log.debug("Registering the vehicle {}.", vehicle);
		Racer racer = new Racer(vehicle, racing);
		racer.addObserver(this);
		racers.put(vehicle, racer);
	}
	
	public void deregister(Movable vehicle) {
		log.debug("Deregistering the vehicle {}.", vehicle);
		Racer racer = racers.get(vehicle);
		if (racer != null)
			racer.deleteObserver(this);
		racers.remove(vehicle);
	}
	
	public Collection<Raceable> listRacers() {
		return racers != null ? Collections.unmodifiableCollection(racers.values()) : null;
	}
	
	// TODO: implement test
	public void addRacerEventCallback(BiConsumer<Racer, Object> callback) {
		racerEventCallbacks.add(callback);
	}
	
	// TODO: implement test
	public void removeRacerEventCallback(BiConsumer<Racer, Object> callback) {
		racerEventCallbacks.remove(callback);
	}

	public void run() throws MovingVehicleException {
		log.info("Starting the racing simulation.");
		log.info("Start racers' state: {}.", racers.values());		
		Collection<Raceable> activeRacers = new ArrayList<>(racers.values());
		double printStateTimeStep = 0;
		double time = 0;
		while (!activeRacers.isEmpty()) {
			Collection<Raceable> finished = new ArrayList<>();
			for (Raceable racer : activeRacers) {
				racer.move(timeStep);
				if (racer.isFinished()) {
					log.debug("{} finished!", racer);
					finished.add(racer);
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
					log.debug("Racers' state at {} s: {}", time, racers.values());				
				}
			}			
			
			try {
				Thread.sleep((long)(1000*timeStep));
			} catch (InterruptedException e) {
				log.error("Error during the racing simulation.", e);
			}
		}		
		log.info("Finish racers' state: {}.", racers.values());
		log.info("Racing simulation ended.");
	}

	/**
	 * @return the racing
	 */
	public Racing getRacing() {
		return racing;
	}
	
	/**
	 * @return the timeStep
	 */
	public double getTimeStep() {
		return timeStep;
	}

	/**
	 * @param timeStep the timeStep to set
	 */
	public void setTimeStep(double timeStep) {
		this.timeStep = timeStep;
	}

	// TODO: implement test
	@Override
	public void update(Observable racer, Object event) {
		for (BiConsumer<Racer, Object> callback : racerEventCallbacks)
			callback.accept((Racer)racer, event);
	}
}
