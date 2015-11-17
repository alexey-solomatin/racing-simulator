/**
 * 
 */
package com.smartech.course.racing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.smartech.course.racing.exception.MovingException;
import com.smartech.course.racing.vehicle.Racer;

/**
 * Racing represents the racing track and the racers.
 * @author Alexey Solomatin
 *
 */
public class Racing {
	private double raceLength;
	private Collection<Racer> racers;
	private double timeStep;
	
	public Racing() {
		racers = new ArrayList<>();
	}
	
	public void register(Racer racer) {
		racers.add(racer);
	}
	
	public void deregister(Racer racer) {
		racers.remove(racer);
	}
	
	public void run() throws MovingException {		
		for (Racer racer : racers) {
			racer.move(timeStep);
			if (racer.getPosition() >= raceLength) {
				// TODO: implement finishing
			}
		}
	}
	
}
