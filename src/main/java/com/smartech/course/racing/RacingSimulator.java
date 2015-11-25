/**
 * 
 */
package com.smartech.course.racing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Bus;
import com.smartech.course.racing.vehicle.Car;
import com.smartech.course.racing.vehicle.Movable;
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
		 * 1) Show the information about the application.
		 * 2) Create racers from the console.
		 * 3) Create a race.
		 * 4) Run simulation.
		 * 5) Print results to the screen.
		 */
		try {
			Collection<Movable> vehicles = createVehicles();
			Racing racing = createRacing();
			RacingSimulation simulation = new RacingSimulation(racing, 1);
			vehicles.stream().forEach(simulation::register);
			simulation.run();			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private static Collection<Movable> createVehicles() throws CreatingVehicleException {
		List<Movable> vehicles = new ArrayList<>();
		// 700 kg, 50 m/s, 10 m/s^2
		Car car = new Car("Car", 700, 50, 10); 
		vehicles.add(car);
		
		// 1000 kg, 30 m/s, 5 m/s^2, 0/40 passengers
		Bus bus = new Bus("Bus", 1000, 30, 5, 40, 0); 
		vehicles.add(bus);
		
		// 1500 kg, 40 m/s, 7 m/s^2, 0/500 kg
		Truck truck = new Truck("Truck", 1500, 40, 7, 500, 0);
		vehicles.add(truck);
		
		return vehicles;
	}
	
	private static Racing createRacing() {
		return new Racing("Racing #1", 500);				
	}

}
