/**
 * 
 */
package com.smartech.course.racing;

import java.time.Duration;
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
import com.smartech.course.racing.dialog.simple.DoubleValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.StringValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.YesNoConsoleDialog;
import com.smartech.course.racing.dialog.vehicle.BusCreationConsoleDialog;
import com.smartech.course.racing.dialog.vehicle.CarCreationConsoleDialog;
import com.smartech.course.racing.dialog.vehicle.TruckCreationConsoleDialog;
import com.smartech.course.racing.vehicle.Vehicle;

/**
 * Racing Simulator application
 * @author Alexey Solomatin
 *
 */
public class RacingSimulator {	
	private Logger log = LoggerFactory.getLogger(RacingSimulator.class);
	private final int PRINTING_THREAD_TIME_STEP = 5;
	

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
			RacingSimulation simulation = simulation();	
			RacingSimulationConsoleInformer informer = new RacingSimulationConsoleInformer(simulation, Duration.ofSeconds(PRINTING_THREAD_TIME_STEP));
			System.console().readLine("Plese press <ENTER> for starting the racing simulation.");
			informer.start();
			System.console().printf("Start state:\n");
			RacingSimulationConsole.getInstance().printRacingSimulationState(simulation);
			simulation.run();
			informer.stop();
			System.console().printf("Finish state:\n");
			RacingSimulationConsole.getInstance().printRacingSimulationState(simulation);
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
	
	private List<Vehicle> vehicles() {
		log.debug("Creating vehicles.");
		System.console().printf("You should specify some vehicles for the racing simulation.\n");
		List<Vehicle> vehicles = new ArrayList<>();		
		do {			
			// TODO: refactor with a choice dialog
			String vehicleType = System.console().readLine("What type of a vehicle do you want to create? (car/truck/bus) ");
			log.debug("User decision of a vehicle type: {}.", vehicleType);
			try {
				Vehicle vehicle = null;
				switch (vehicleType.toLowerCase()) {
					case "car":
					case "c":
						log.debug("Creating a car.");
						vehicle = new CarCreationConsoleDialog(new CarBuilderImpl()).get();
						break;
					case "truck":
					case "t":
						log.debug("Creating a truck.");
						vehicle = new TruckCreationConsoleDialog(new TruckBuilderImpl()).get();
						break;
					case "bus":
					case "b":
						log.debug("Creating a bus.");
						vehicle = new BusCreationConsoleDialog(new BusBuilderImpl()).get();
						break;
					default:
						log.error("Unknown vehicle type: {}.", vehicleType);
						System.console().printf("I don't know how to create it!\n");
						break;
				}
				log.debug("Created vehicle: {}.", vehicle);
				if (vehicle != null)
					vehicles.add(vehicle);
			} catch (RuntimeException e) {
				log.error("Error during vehicle creation.", e);
				System.console().printf("Error during vehicle creation!\n");
				throw e;
			} catch (Exception e) {
				log.error("Error during vehicle creation.", e);
				System.console().printf("Error during vehicle creation!\n");
				continue;
			}
			
			if (!new YesNoConsoleDialog("Would you like to create one more vehicle?").get())						
				break;			
		} while (true);
		
		return vehicles;
	}
	
	private Racing racing() {
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
				(d) -> d>0))
			.build();							
	}
	
	private Supplier<Double> timeStep() {
		log.debug("Specifying the simulation time step.");
		return new DoubleValueConsoleDialog(
			"Please enter the simulation time step in seconds: ", 
			"You've entered the incorrect simulation time step.", 
			(t) -> t > 0);			
	}
	
	private RacingSimulation simulation() {		
		return new RacingSimulationBuilderImpl()
			.racing(racing())
			.timeStep(timeStep())
			.vehicles(vehicles())			
			.racerEventCallback(RacingSimulationConsole.getInstance()::onRacerEvent)
			.build();			
	}

}
