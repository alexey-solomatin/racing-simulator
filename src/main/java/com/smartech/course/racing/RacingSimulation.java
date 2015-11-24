/**
 * 
 */
package com.smartech.course.racing;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Movable;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingSimulation {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private Racing racing;
	private Collection<Movable> racers = new ArrayList<Movable>();
	private double timeStep = 1; // in seconds
	

	/**
	 * 
	 */
	public RacingSimulation() {
		// TODO Auto-generated constructor stub
	}
	
	public void register(Movable racer) {
		racers.add(racer);
	}
	
	public void deregister(Movable racer) {
		racers.remove(racer);
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
	 * @param racing the racing to set
	 */
	public void setRacing(Racing racing) {
		this.racing = racing;
	}
	
	private Collection<Raceable> createRacers() {
		// TODO: create racers from the specified vehicles and the racing
		return null;
	}
}
