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
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractRacingSimulation implements RacingSimulation, Observer {
	protected Logger log = LoggerFactory.getLogger(getClass());	
	protected static final double RACING_LOGGING_TIME_STEP = 1;
	
	private final Racing racing;
	protected List<Racer> racers = new ArrayList<>();
	protected double timeStep = 1; // in seconds
	private List<BiConsumer<Racer, Object>> racerEventCallbacks = new ArrayList<>();
	protected Collection<Raceable> activeRacers;
	protected Object lock = new Object();

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
		racer.addObserver(this);
		synchronized (lock) {
			racers.add(racer);
		}
	}
	
	@Override
	public List<Racer> getRacerSnapshots() {
		synchronized (lock) { 
			return racers != null 
				? racers.stream().map((racer)->racer.createSnapshot()).collect(Collectors.toList())
				: null;
		}		
	}
	
	@Override
	public List<Racer> getRacers() {
		synchronized (lock) {
			return Collections.unmodifiableList(racers);
		}
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
