/**
 * 
 */
package com.smartech.course.racing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.exception.MovingException;
import com.smartech.course.racing.vehicle.Vehicle;

/**
 * Racing represents the racing track and the racers.
 * @author Alexey Solomatin
 *
 */
public class Racing {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private double raceLength;
	private Collection<Vehicle> racers;
	private double timeStep = 1; // in seconds
	
	public Racing() {
		racers = new ArrayList<>();
	}
	
	public void register(Vehicle racer) {
		racers.add(racer);
	}
	
	public void deregister(Vehicle racer) {
		racers.remove(racer);
	}
	
	public void run() throws MovingException {
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
	
}
