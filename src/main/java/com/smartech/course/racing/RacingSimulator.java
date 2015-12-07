/**
 * 
 */
package com.smartech.course.racing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.builder.racing.RacingBuilderImpl;
import com.smartech.course.racing.builder.simulation.RacingSimulationBuilder;
import com.smartech.course.racing.builder.simulation.RacingSimulationBuilderImpl;
import com.smartech.course.racing.builder.vehicle.BusBuilderImpl;
import com.smartech.course.racing.builder.vehicle.CarBuilderImpl;
import com.smartech.course.racing.builder.vehicle.TruckBuilderImpl;
import com.smartech.course.racing.dialog.racing.RacingCreationConsoleDialog;
import com.smartech.course.racing.dialog.simple.DoubleValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.InfoConsoleDialog;
import com.smartech.course.racing.dialog.simple.StringValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.YesNoConsoleDialog;
import com.smartech.course.racing.dialog.vehicle.BusCreationConsoleDialog;
import com.smartech.course.racing.dialog.vehicle.CarCreationConsoleDialog;
import com.smartech.course.racing.dialog.vehicle.TruckCreationConsoleDialog;
import com.smartech.course.racing.dialog.vehicle.VehicleSelectionAndCreationConsoleDialog;
import com.smartech.course.racing.simulation.Racing;
import com.smartech.course.racing.simulation.RacingSimulation;
import com.smartech.course.racing.utils.RacingSimulationConsole;
import com.smartech.course.racing.utils.RacingSimulationConsoleInformer;
import com.smartech.course.racing.vehicle.Movable;
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
			System.console().readLine("Plese press <ENTER> to start the racing simulation.");
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
		new InfoConsoleDialog("Racing Simulator is an application for simulation of races.\n"
			+ "Create a new simulation, specify the race details, add some racers, start the simulation, and enjoy the process!\n\n"
			+ "Please press <ENTER> to continue.\n\n")
			.get();		
	}		

//	private Racing racing() {
//		log.debug("Creating a new racing.");
//		System.console().printf("Please specify the details about the racing you want to simulate.\n");		
//		return new RacingBuilderImpl()
//			.name(new StringValueConsoleDialog(
//				"Please enter the racing name: ", 
//				"You've entered the incorrect name.", 
//				(s) -> !StringUtils.isBlank(s)))
//			.distance(new DoubleValueConsoleDialog(
//				"Please enter the racing distance in meters: ", 
//				"You've entered the incorrect distance.", 
//				(d) -> d>0))
//			.build();							
//	}	
	
	private RacingSimulation simulation() {		
		RacingSimulationBuilder builder = new RacingSimulationBuilderImpl()
			.racing(new RacingCreationConsoleDialog())
			.timeStep(new DoubleValueConsoleDialog(
				"Please enter the simulation time step in seconds: ", 
				"You've entered the incorrect simulation time step.", 
				(t) -> t > 0))
			.racerEventCallback(RacingSimulationConsole.getInstance()::onRacerEvent);
		do {
			builder.racer(
				new StringValueConsoleDialog(
					"Please enter the racer's name: ", 
					"You've entered the incorrect name.", 
					(s) -> !StringUtils.isBlank(s)), 
				new VehicleSelectionAndCreationConsoleDialog());
			if (!new YesNoConsoleDialog("Would you like to create one more racer?").get())						
				break;
		} while (true);

		return builder.build();			
	}

}
