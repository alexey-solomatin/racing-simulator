/**
 * 
 */
package com.smartech.course.racing.builder.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.simulation.AbstractRacingSimulation;
import com.smartech.course.racing.simulation.Racer;
import com.smartech.course.racing.simulation.Racing;
import com.smartech.course.racing.simulation.RacingSimulation;
import com.smartech.course.racing.simulation.SingleThreadRacingSimulation;
import com.smartech.course.racing.vehicle.Movable;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractRacingSimulationBuilder implements RacingSimulationBuilder {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	protected Racing racing;
	protected Map<String, Movable> racers = new HashMap<>();
	protected List<BiConsumer<Racer, Object>> callbacks = new ArrayList<>();
	protected Double timeStep;
	
	
	public AbstractRacingSimulationBuilder() {		
	}
	
	@Override
	public RacingSimulationBuilder racing(Racing racing) {
		this.racing = racing;
		return this;
	}	
	
	@Override
	public RacingSimulationBuilder racer(String racerName, Movable vehicle) {
		racers.put(racerName, vehicle);
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
//		SingleThreadRacingSimulation simulation = new SingleThreadRacingSimulation(racing, timeStep);
		RacingSimulation simulation = createSimulation();
		racers.entrySet().stream().forEach((entry)->simulation.register(entry.getKey(), entry.getValue()));
		callbacks.stream().forEach(simulation::addRacerEventCallback);
		return simulation;
	}
	
	protected abstract RacingSimulation createSimulation();

}
