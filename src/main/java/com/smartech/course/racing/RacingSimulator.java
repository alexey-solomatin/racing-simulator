/**
 * 
 */
package com.smartech.course.racing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Bus;
import com.smartech.course.racing.vehicle.Car;
import com.smartech.course.racing.vehicle.Movable;
import com.smartech.course.racing.vehicle.Truck;
import com.smartech.course.racing.vehicle.builder.racing.ConsoleRacingBuilder;
import com.smartech.course.racing.vehicle.builder.racing.RacingBuilder;

/**
 * Racing Simulator application
 * @author Alexey Solomatin
 *
 */
public class RacingSimulator {
	private final int PRINTING_THREAD_TIME_STEP = 5000;
	private Logger log = LoggerFactory.getLogger(RacingSimulator.class);

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
		new RacingSimulator().run();		
	}
	
	private void run() {
		try {
			log.info("Starting Racing Simulator.");
			showInfo();						
			Collection<Movable> vehicles = createVehicles();
			Racing racing = createRacing();
			RacingSimulation simulation = createRacingSimulation(racing, vehicles);
			startPrintingThread(simulation);
			simulation.run();			
		} catch (Throwable e) {
			log.error("Fatal error.", e);
			System.err.println("Fatal error: " + e.getMessage());
		} finally {
			log.info("Racing Simulator stopped.");
		}
	}
	
	private void showInfo() {
		System.console().printf("Racing Simulator is an application for simulation of races.\n\n");
		System.console().printf("Create a new simulation, specify the race details, add some racers, start the simulation, and enjoy the process!\n\n");
	}
	
	
	private void startPrintingThread(final RacingSimulation simulation) {
		Thread printingThread = new Thread(()->{
			while (true) {
				System.out.println(simulation.listRacers());
				try {
					Thread.sleep(PRINTING_THREAD_TIME_STEP);
				} catch (Exception e) {
					log.error("Error in the printing thread.", e);
				}
			}
		});
		printingThread.setDaemon(true);
		printingThread.start();
	}
	
	private Collection<Movable> createVehicles() throws CreatingVehicleException {
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
	
	private Racing createRacing() {
		log.debug("Creating a new racing.");
		System.console().printf("Please specify the details about the racing you want to simulate.\n");
		RacingBuilder racingBuilder = new ConsoleRacingBuilder();
		Racing racing = racingBuilder.name().distance().build();
		log.debug("Created racing: {}.", racing);
		return racing;
	}
	
	private RacingSimulation createRacingSimulation(Racing racing, Collection<Movable> vehicles) {
		RacingSimulation simulation = new RacingSimulation(racing, 1);
		vehicles.stream().forEach(simulation::register);
		simulation.addRacerEventCallback((racer, event) -> System.out.println("Racer " + racer + " finished!"));
		return simulation;
	}

}
