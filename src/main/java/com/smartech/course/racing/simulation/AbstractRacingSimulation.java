/**
 * 
 */
package com.smartech.course.racing.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Movable;

/**
 * Basic implementation of a racing simulation
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractRacingSimulation implements RacingSimulation, Observer {
	protected Logger log = LoggerFactory.getLogger(getClass());	
	protected static final double RACING_LOGGING_TIME_STEP = 1;
	
	protected final Racing racing;
	protected List<Racer> racers = new CopyOnWriteArrayList<>();
	protected double timeStep = 1; // in seconds
	protected List<BiConsumer<Racer, Object>> racerEventCallbacks = new ArrayList<>();
	protected List<Raceable> activeRacers;
	private Semaphore racersSemaphore;

	/**
	 * Creates the racing simulating with specified racing and time step.
	 * Vehicles should be registered separately.
	 * @param racing the racing for the simulation
	 * @param timeStep the time step for the simulation
	 */
	public AbstractRacingSimulation(Racing racing, double timeStep) {
		log.debug("Creating the racing simulation with {} and time step {} s.", racing, timeStep);
		this.racing = racing;
		this.timeStep = timeStep;		
	}		

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.simulation.RacingSimulation#register(java.lang.String, com.smartech.course.racing.vehicle.Movable)
	 */
	@Override
	public void register(String racerName, Movable vehicle) {
		if (vehicle == null)
			throw new IllegalArgumentException("Cannot register the null vehicle.");
		log.debug("Registering the vehicle {} for racer {}.", vehicle, racerName);		
		Racer racer = new Racer(racerName, vehicle, racing);				
		racers.add(racer);
		racer.addObserver(this);				
	}
	
	protected Semaphore getRacersSemaphore() {
		Semaphore localInstance = racersSemaphore;
		if (localInstance == null) {
			synchronized (getClass()) {
				localInstance = racersSemaphore;
				if (localInstance == null) {
					racersSemaphore = localInstance = new Semaphore(racers.size());
				}
			}
		}
		return localInstance;
	}
	
	@Override
	public List<Racer> getRacerSnapshots() {
		log.debug("Retrieving the racer snapshots.");
		if (racers != null) {
			try {				
				log.debug("Acquiring the racers semaphore for {} permits.", racers.size());				
				getRacersSemaphore().acquire(racers.size());
				log.debug("The racers semaphore is acquired for {} permits.", racers.size());
				return racers.stream().map((racer)->racer.createSnapshot()).collect(Collectors.toList());
			} catch (InterruptedException e) {
				log.error("Error during getting the racer snapshots.", e);
				return null;
			} finally {
				getRacersSemaphore().release(racers.size());
				log.debug("The racers semaphore is released for {} permits.", racers.size());
				log.debug("The racer snapshots are retrieved.");
			}			
		} else
			return null;		
	}
	
	@Override
	public List<Racer> getRacers() {		
		return Collections.unmodifiableList(racers);		
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.simulation.RacingSimulation#addRacerEventCallback(java.util.function.BiConsumer)
	 */
	@Override
	public void addRacerEventCallback(BiConsumer<Racer, Object> callback) {
		racerEventCallbacks.add(callback);
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.simulation.RacingSimulation#removeRacerEventCallback(java.util.function.BiConsumer)
	 */
	@Override
	public void removeRacerEventCallback(BiConsumer<Racer, Object> callback) {
		racerEventCallbacks.remove(callback);
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.simulation.RacingSimulation#run()
	 */
	@Override
	public abstract void run() throws MovingVehicleException;
	
	/**
	 * @return the racing
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.simulation.RacingSimulation#isRunning()
	 */
	@Override
	public boolean isRunning() {
		return activeRacers != null && !activeRacers.isEmpty();
	}

	@Override
	public void update(Observable racer, Object event) {
		for (BiConsumer<Racer, Object> callback : racerEventCallbacks)
			callback.accept((Racer)racer, event);
	}

}
