/**
 * 
 */
package com.smartech.course.racing.utils;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.PrintWriter;

import com.smartech.course.racing.simulation.Racer;
import com.smartech.course.racing.simulation.RacingSimulation;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingSimulationWriter implements Closeable, Flushable, AutoCloseable {
	private final PrintWriter writer;

	/**
	 * 
	 */
	public RacingSimulationWriter(PrintWriter writer) {
		this.writer = writer;
	}
	
	public void printRacingSimulationBriefState(RacingSimulation simulation) {
		writer.printf("-------------------------------------------------------------\n");
		simulation.getRacerSnapshots().stream().forEachOrdered(this::printRacerBriefState);
		writer.printf("-------------------------------------------------------------\n");
	}
	
	public void printRacingSimulationFinalState(RacingSimulation simulation) {
		writer.printf("-------------------------------------------------------------\n");
		writer.printf("Racing: %s, %.1f meters\n", simulation.getRacing().getName(), simulation.getRacing().getDistance());
		writer.printf("Racers:\n");		
		simulation
			.getRacers()
			.stream()
			.sorted((r1, r2)->Double.compare(r1.getVehicleState().getTime(), r2.getVehicleState().getTime()))
			.forEachOrdered(this::printRacerFinalState);
		writer.printf("-------------------------------------------------------------\n");
	}
	
	private void printRacerBriefState(Racer racer) {		
		writer.printf("%.1f s: %-10s in %-10s: \t%.1f/%.1f meters, speed: %.1f m/s\n", 
			racer.getVehicleState().getTime(), 
			racer.getName(),
			racer.getVehicle().getDescription(),
			racer.getVehicleState().getPosition(), 
			racer.getRacing().getDistance(),
			racer.getVehicleState().getSpeed());
	}
	
	private void printRacerFinalState(Racer racer) {		
		writer.printf("%.1f s - %-10s in %-10s, lost its speed %d times, avg. speed: %.1f m/s\n",			
			racer.getVehicleState().getTime(), 
			racer.getName(),
			racer.getVehicle().getDescription(),
			racer.getSpeedLosingNumber(),
			racer.getAverageSpeed());
	}

	@Override
	public void flush() throws IOException {
		if (writer != null)
			writer.flush();
	}

	@Override
	public void close() throws IOException {
		if (writer != null)
			writer.close();
	}

}
