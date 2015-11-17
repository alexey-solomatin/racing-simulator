/**
 * 
 */
package com.smartech.course.racing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.smartech.course.racing.exception.MovingException;
import com.smartech.course.racing.vehicle.BusRacer;
import com.smartech.course.racing.vehicle.CarRacer;
import com.smartech.course.racing.vehicle.Racer;
import com.smartech.course.racing.vehicle.TruckRacer;

/**
 * Racing Simulator application
 * @author Alexey Solomatin
 *
 */
public class RacingSimulator {

	/**
	 * Entry point to the application
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("Hello! This is Racing Simulator!");
		/*
		 * TODO:
		 * 1) Show the information about the application.
		 * 2) Create racers from the console.
		 * 3) Create a race.
		 * 4) Run simulation.
		 * 5) Print results to the screen.
		 */
		try {
			Collection<Racer> racers = createRacers();
			Racing racing = createRacing(racers);
			racing.run();
		} catch (MovingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Collection<Racer> createRacers() {
		List<Racer> racers = new ArrayList<>();
		
		CarRacer carRacer = new CarRacer();
		racers.add(carRacer);
		
		BusRacer busRacer = new BusRacer();
		racers.add(busRacer);
		
		TruckRacer truckRacer = new TruckRacer();
		racers.add(truckRacer);
		
		return racers;
	}
	
	private static Racing createRacing(Collection<Racer> racers) {
		Racing racing = new Racing();
		return racing;
	}

}
