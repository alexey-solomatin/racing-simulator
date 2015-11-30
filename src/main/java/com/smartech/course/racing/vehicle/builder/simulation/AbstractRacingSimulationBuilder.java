/**
 * 
 */
package com.smartech.course.racing.vehicle.builder.simulation;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.Racing;
import com.smartech.course.racing.RacingSimulation;
import com.smartech.course.racing.vehicle.Movable;
import com.smartech.course.racing.vehicle.builder.racing.RacingBuilder;
import com.smartech.course.racing.vehicle.builder.vehicle.VehicleBuilder;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractRacingSimulationBuilder implements RacingSimulationBuilder {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	protected Racing racing;
	protected Collection<Movable> vehicles;
	protected double timeStep;
	
	private RacingBuilder racingBuilder;
	private VehicleBuilder vehicleBuilder;
	
	public AbstractRacingSimulationBuilder(RacingBuilder racingBuilder, VehicleBuilder vehicleBuilder) {
		this.racingBuilder = racingBuilder;
		this.vehicleBuilder = vehicleBuilder;
	}
	
	@Override
	public RacingSimulationBuilder racing() {
		racing = racingBuilder.name().distance().build();
		return this;
	}
	
	@Override
	public RacingSimulationBuilder vehicles() {
		vehicles = new ArrayList<>();
		while (shouldBuildOneMoreVehicle())
			vehicles.add(vehicleBuilder.build()); // TODO: ADD REAL BUILDING HERE!!!
		return null;
	}

	protected abstract boolean shouldBuildOneMoreVehicle();

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.builder.simulation.RacingSimulationBuilder#build()
	 */
	@Override
	public RacingSimulation build() {
		RacingSimulation simulation = new RacingSimulation(racing, timeStep);
		vehicles.stream().forEach(simulation::register);
		// TODO: refactor adding this callback
		simulation.addRacerEventCallback((racer, event) -> System.out.println("Racer " + racer + " finished!"));
		return simulation;
	}

}
