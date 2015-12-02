/**
 * 
 */
package com.smartech.course.racing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.builder.racing.RacingBuilderImpl;
import com.smartech.course.racing.builder.simulation.RacingSimulationBuilderImpl;
import com.smartech.course.racing.builder.vehicle.BusBuilderImpl;
import com.smartech.course.racing.builder.vehicle.CarBuilderImpl;
import com.smartech.course.racing.builder.vehicle.TruckBuilderImpl;
import com.smartech.course.racing.dialog.DoubleValueConsoleDialog;
import com.smartech.course.racing.dialog.StringValueConsoleDialog;
import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Movable;

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
			RacingSimulation simulation = simulation().get();			
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
	
	// The new RacersPositionInformer class should be created instead of this method
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
	
	private List<Movable> vehicles() {
		List<Movable> vehicles = new ArrayList<>();
		try {
			// 700 kg, 50 m/s, 10 m/s^2			
//			Car car = new Car("Car", 700, 50, 10);			
			vehicles.add(new CarBuilderImpl()
				.name("Car")
				.weight(700)
				.maxSpeed(50)
				.acceleration(10)
				.get());
			
			// 1000 kg, 30 m/s, 5 m/s^2, 0/40 passengers							
//			Bus bus = new Bus("Bus", 1000, 30, 5, 40, 0); 
			vehicles.add(new BusBuilderImpl()
				.name("Bus")
				.weight(1000)
				.maxSpeed(30)
				.acceleration(5)
				.maxNumberOfPassengers(40)
				.numberOfPassengers(0)
				.get());
			
			// 1500 kg, 40 m/s, 7 m/s^2, 0/500 kg
//			Truck truck = new Truck("Truck", 1500, 40, 7, 500, 0);			
			vehicles.add(new TruckBuilderImpl()
				.name("Truck")
				.weight(1500)
				.maxSpeed(40)
				.acceleration(7)
				.maxPayloadWeight(500)
				.payloadWeight(0)
				.get());
		} catch (CreatingVehicleException e) {
			log.error("Error during creation of vehicles.", e);
		}
		
		return vehicles;
	}
	
	private Supplier<Racing> racing() {
		log.debug("Creating a new racing.");
		System.console().printf("Please specify the details about the racing you want to simulate.\n");		
		return new RacingBuilderImpl()
			.name(new StringValueConsoleDialog(
				"Please enter the racing name: ", 
				"You've entered the incorrect name.", 
				(s) -> !StringUtils.isBlank(s)))
			.distance(new DoubleValueConsoleDialog(
				"Please enter the racing distance in meters: ", 
				"You've entered the incorrect distance.", 
				(d) -> d>0));							
	}
	
	private Supplier<Double> timeStep() {
		log.debug("Specifying the simulation time step.");
		return new DoubleValueConsoleDialog(
			"Please enter the simlation time step in seconds: ", 
			"You've entered the incorrect simulation time step.", 
			(t) -> t > 0);			
	}
	
	private Supplier<RacingSimulation> simulation() {		
		return new RacingSimulationBuilderImpl()
			.racing(racing())
			.vehicles(this::vehicles)
			.timeStep(timeStep())
			.racerEventCallback((racer, event) -> System.out.println("Racer " + racer + " finished!"));			
	}

}
