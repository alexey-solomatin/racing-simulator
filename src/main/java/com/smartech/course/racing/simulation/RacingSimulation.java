package com.smartech.course.racing.simulation;

import java.util.List;
import java.util.function.BiConsumer;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Movable;

/**
 * Racing simulation interface
 * @author Alexey Solomatin
 *
 */
public interface RacingSimulation {

	/**
	 * Registers the racer with the corresponding vehicle
	 * @param racerName the racer to be registered
	 * @param vehicle the vehicle to be registered for the racer
	 */
	void register(String racerName, Movable vehicle);

	/**
	 * Adds the racer event callback
	 * @param callback the racer event callback to be added
	 */
	void addRacerEventCallback(BiConsumer<Racer, Object> callback);

	/**
	 * Removes the racer event callback
	 * @param callback the callback to be removed
	 */
	void removeRacerEventCallback(BiConsumer<Racer, Object> callback);

	/**
	 * Runs the simulation.
	 * @throws MovingVehicleException
	 */
	void run() throws MovingVehicleException;

	/**
	 * Checks if the simulation is running.
	 * @return true if the simulation is running, otherwise - false
	 */
	boolean isRunning();

	/**
	 * Gets racer snapshots
	 * @return the list of racer snapshots
	 */
	List<Racer> getRacerSnapshots();

	/**
	 * Gets the simulation racing
	 * @return the racing for simulationS
	 */
	Racing getRacing();

	/**
	 * Gets the list of racers
	 * @return the list of competing racers
	 */
	List<Racer> getRacers();

}