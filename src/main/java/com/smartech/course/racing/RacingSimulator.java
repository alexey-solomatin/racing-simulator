/**
 * 
 */
package com.smartech.course.racing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Bus;
import com.smartech.course.racing.vehicle.Car;
import com.smartech.course.racing.vehicle.Vehicle;
import com.smartech.course.racing.vehicle.Truck;

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
		/*
		 * TODO:
		 * 1) Show the information about the application.
		 * 2) Create racers from the console.
		 * 3) Create a race.
		 * 4) Run simulation.
		 * 5) Print results to the screen.
		 */
		try {
			Collection<Raceable> racers = createRacers();
			Racing racing = createRacing(racers);
			racers.stream().forEach(racing::register);
			racing.run();
		} catch (MovingVehicleException e) {			
			e.printStackTrace();
		}
		
	}
	
	private static Collection<Raceable> createRacers() {
		List<Raceable> racers = new ArrayList<>();
		/*
		Car carRacer = new Car("Car", 200, 10, 30);
		racers.add(carRacer);
		
		Bus busRacer = new Bus("Bus", 200, 6, 30, 40);
		racers.add(busRacer);
		
		Truck truckRacer = new Truck("Track", 200, 7, 30, 100);
		racers.add(truckRacer);
		*/
		return racers;
	}
	
	private static Racing createRacing(Collection<Raceable> racers) {
		Racing racing = new Racing();
		return racing;
	}

}
