/**
 * 
 */
package com.smartech.course.racing;

import java.io.PrintWriter;
import java.time.Duration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.builder.simulation.RacingSimulationBuilder;
import com.smartech.course.racing.builder.simulation.RacingSimulationBuilderFactory;
import com.smartech.course.racing.builder.simulation.RacingSimulationBuilderFactory.RacingSimulationType;
import com.smartech.course.racing.builder.simulation.SingleThreadRacingSimulationBuilder;
import com.smartech.course.racing.builder.simulation.AbstractRacingSimulationBuilder;
import com.smartech.course.racing.builder.simulation.MultiThreadRacingSimulationBuilder;
import com.smartech.course.racing.dialog.racing.RacingCreationConsoleDialog;
import com.smartech.course.racing.dialog.simple.DoubleValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.InfoConsoleDialog;
import com.smartech.course.racing.dialog.simple.StringValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.YesNoConsoleDialog;
import com.smartech.course.racing.dialog.vehicle.VehicleSelectionAndCreationConsoleDialog;
import com.smartech.course.racing.simulation.MultiThreadRacingSimulation;
import com.smartech.course.racing.simulation.RacingSimulation;
import com.smartech.course.racing.utils.RacingSimulationConsole;
import com.smartech.course.racing.utils.RacingSimulationConsoleInformer;
import com.smartech.course.racing.utils.RacingSimulationWriter;

/**
 * Racing Simulator application
 * @author Alexey Solomatin
 *
 */
public class RacingSimulator {	
	private Logger log = LoggerFactory.getLogger(RacingSimulator.class);
	private final int PRINTING_THREAD_TIME_STEP = 5;
	private RacingSimulationType racingSimulationType;
	
	public RacingSimulator(RacingSimulationType racingSimulationType) {
		this.racingSimulationType = racingSimulationType;
	}

	/**
	 * Entry point to the application
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		new RacingSimulator(parseRacingSimulationType(args)).run();
	}
	
	private static RacingSimulationType parseRacingSimulationType(String[] args) {		
		return args != null && args.length != 0 && args[0].equalsIgnoreCase("-mt")
				? RacingSimulationType.MULTI_THREAD
				: RacingSimulationType.SINGLE_THREAD;		
	}
	
	private void run() {
		RacingSimulationConsoleInformer informer = null;
		try {
			log.info("Starting Racing Simulator.");
			log.info("The mode of racing simulation: {}.", racingSimulationType);
			showInfo();
			RacingSimulation simulation = simulation(racingSimulationType);
			informer = new RacingSimulationConsoleInformer(simulation, Duration.ofSeconds(PRINTING_THREAD_TIME_STEP));
			System.console().readLine("\nPlease press <ENTER> to start the racing simulation.");
			informer.start();
			System.console().printf("\nStart state:\n");
			RacingSimulationConsole.getInstance().printRacingSimulationBriefState(simulation);
			simulation.run();
			informer.stop();
			System.console().printf("\nFinish state:\n");
			RacingSimulationConsole.getInstance().printRacingSimulationFinalState(simulation);
			saveResults(simulation);
		} catch (Throwable e) {
			log.error("Fatal error.", e);
			System.err.println("Fatal error: " + e.getMessage());
		} finally {
			if (informer != null)
				informer.stop();
			log.info("Racing Simulator stopped.");
			System.console().readLine("\nPress <ENTER> to exit.");
		}
	}
	
	private void showInfo() {
		new InfoConsoleDialog("Racing Simulator is an application for simulation of races.\n"
			+ "Create a new simulation, specify the race details, add some racers, start the simulation, and enjoy the process!\n\n"
			+ "Please press <ENTER> to continue.\n\n")
			.get();		
	}	
	
	private void saveResults(RacingSimulation simulation) {
		while (new YesNoConsoleDialog("\nWould you like to save these results to file?").get()) {			
			String fileName = new StringValueConsoleDialog(
					"Please enter the name of a file where the result will be stored: ", 
					"You've entered the incorrect file name.", 
					(s) -> !StringUtils.isBlank(s))
					.get();
			try (RacingSimulationWriter writer = new RacingSimulationWriter(new PrintWriter(fileName))) {				
				writer.printRacingSimulationFinalState(simulation);
				System.console().printf("The simulation results have been stored.\n");
				break;
			} catch (Exception e) {
				log.error("Error during saving the simulation results.", e);
				System.err.println("Cannot save the simulation results to a file. Please try again.");
			}				
		}
	}
	
	private RacingSimulation simulation(RacingSimulationType type) {		
		RacingSimulationBuilder builder = RacingSimulationBuilderFactory.newRacingSimulationBuilder(type)
			.racing(new RacingCreationConsoleDialog())
			.timeStep(new DoubleValueConsoleDialog(
				"Please enter the simulation time step in seconds: ", 
				"You've entered the incorrect simulation time step.", 
				(t) -> t > 0))
			.racerEventCallback(RacingSimulationConsole.getInstance()::onRacerEvent);
		do {
			builder.racer(
				new StringValueConsoleDialog(
					"\nPlease enter the racer's name: ", 
					"You've entered the incorrect name.", 
					(s) -> !StringUtils.isBlank(s)), 
				new VehicleSelectionAndCreationConsoleDialog());
			if (!new YesNoConsoleDialog("\nWould you like to create one more racer?").get())						
				break;
		} while (true);

		return builder.build();			
	}	

}
