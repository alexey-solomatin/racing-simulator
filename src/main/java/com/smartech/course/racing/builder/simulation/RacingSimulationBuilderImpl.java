/**
 * 
 */
package com.smartech.course.racing.builder.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.Racer;
import com.smartech.course.racing.Racing;
import com.smartech.course.racing.RacingSimulation;
import com.smartech.course.racing.vehicle.Vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingSimulationBuilderImpl implements RacingSimulationBuilder {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private Racing racing;
	private List<Vehicle> vehicles;
	private List<BiConsumer<Racer, Object>> callbacks = new ArrayList<>();
	private Double timeStep;
	
	
	public RacingSimulationBuilderImpl() {		
	}
	
	@Override
	public RacingSimulationBuilder racing(Racing racing) {
		this.racing = racing;
		return this;
	}
	
	@Override
	public RacingSimulationBuilder vehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
		return this;		
	}

	@Override
	public RacingSimulationBuilder racerEventCallback(BiConsumer<Racer, Object> callback) {
		callbacks.add(callback);
		return this;
	}
	
	@Override
	public RacingSimulationBuilder timeStep(double timeStep) {
		this.timeStep = timeStep;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.builder.simulation.RacingSimulationBuilder#build()
	 */
	@Override
	public RacingSimulation build() {
		RacingSimulation simulation = new RacingSimulation(racing, timeStep);
		vehicles.stream().forEach(simulation::register);
		callbacks.stream().forEach(simulation::addRacerEventCallback);
		return simulation;
	}

}