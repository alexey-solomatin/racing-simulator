/**
 * 
 */
package com.smartech.course.racing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Movable;

/**
 * Simulation of a racing of some vehicles
 * @author Alexey Solomatin
 *
 */
public class RacingSimulation {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private final Racing racing;
	private Map<Movable, Raceable> racers = new HashMap<>();
	private double timeStep = 1; // in seconds

	/**
	 * Creates the racing simulating with specified racing and time step.
	 * Vehicles should be registered separately.
	 * @param racing the racing for the simulation
	 * @param timeStep the time step for the simulation
	 */
	public RacingSimulation(Racing racing, double timeStep) {
		// TODO Auto-generated constructor stub
		this.racing = racing;
		this.timeStep = timeStep;
	}
	
	public void register(Movable vehicle) {
		racers.put(vehicle, new Racer(vehicle, racing));
	}
	
	public void deregister(Movable vehicle) {
		racers.remove(vehicle);
	}
	
	public Collection<Raceable> listRacers() {
		return racers != null ? Collections.unmodifiableCollection(racers.values()) : null;
	}

	public void run() throws MovingVehicleException {
		// TODO: create racers
		// TODO: perform racing simulation
		/*
		Collection<Vehicle> activeRacers = new ArrayList<>(racers);
		double printStateTimeStep = 0;
		while (!activeRacers.isEmpty()) {
			Collection<Vehicle> finished = new ArrayList<>();
			for (Vehicle racer : activeRacers) {
				racer.move(timeStep);
				if (racer.getPosition() >= raceLength)
					finished.add(racer);				
			}
			printStateTimeStep += timeStep;
			for (Vehicle racer : finished)
				activeRacers.remove(racer);
			
			// printing the state of the race
			if (printStateTimeStep >= 10) {
				System.out.println("==============================================");
				System.out.println(racers);
				System.out.println("==============================================");
			}
			
			try {
				Thread.sleep((long)(1000*timeStep));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/		
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
}
