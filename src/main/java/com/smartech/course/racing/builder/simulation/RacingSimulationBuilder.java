/**
 * 
 */
package com.smartech.course.racing.builder.simulation;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import com.smartech.course.racing.Racer;
import com.smartech.course.racing.Racing;
import com.smartech.course.racing.RacingSimulation;
import com.smartech.course.racing.vehicle.Movable;

/**
 * @author Alexey Solomatin
 *
 */
public interface RacingSimulationBuilder extends Supplier<RacingSimulation> {
	RacingSimulationBuilder racing(Racing racing);
	default RacingSimulationBuilder racing(Supplier<Racing> racingSupplier) {
		return racing(racingSupplier.get());
	}
	RacingSimulationBuilder timeStep(double timeStep);
	default RacingSimulationBuilder timeStep(Supplier<Double> timeStepSupplier) {
		return timeStep(timeStepSupplier.get());
	}	
	RacingSimulationBuilder vehicles(List<Movable> vehicles);		
	default RacingSimulationBuilder vehicles(Supplier<List<Movable>> vehiclesSupplier) {
		return vehicles(vehiclesSupplier.get());
	}
	RacingSimulationBuilder racerEventCallback(BiConsumer<Racer, Object> callback);
	default RacingSimulationBuilder racerEventCallback(Supplier<BiConsumer<Racer, Object>> callbackSupplier) {
		return racerEventCallback(callbackSupplier.get());
	}	
}
