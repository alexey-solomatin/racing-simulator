/**
 * 
 */
package com.smartech.course.racing.vehicle;

import java.util.Random;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * @author Alexey Solomatin
 *
 */
public class SpeedLosingVehicleWrapper implements Movable {
	private static double MIN_SPEED_LOSING_COEFFICIENT = 0.1;
	private static double MAX_SPEED_LOSING_COEFFICIENT = 0.1;
	private Movable vehicle;
	private double speedLosingPropability;
	
	private Random losingSpeedRandomGenerator;

	/**
	 * 
	 */
	public SpeedLosingVehicleWrapper(Movable vehicle, double speedLosingPropability) throws CreatingVehicleException {
		if (vehicle == null)
			throw new CreatingVehicleException("Cannot create SpeedLosingVehicleWrapper for the null vehicle.");
		if (speedLosingPropability < 0  || speedLosingPropability > 1)
			throw new CreatingVehicleException("Cannot create SpeedLosingVehicleWrapper with the probalility " + speedLosingPropability);
		this.vehicle = vehicle;
		this.speedLosingPropability = speedLosingPropability;
		losingSpeedRandomGenerator = new Random(vehicle.hashCode());
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.Movable#move(com.smartech.course.racing.vehicle.VehicleState, double)
	 */
	@Override
	public VehicleState move(VehicleState curState, double time) throws MovingVehicleException {
		VehicleState nextState = vehicle.move(curState, time);		
		return nextState != null
			? new VehicleState(nextState.getTime(), loseSomeSpeed(nextState.getSpeed()), nextState.getPosition())
			: null;		
	}
	
	private double loseSomeSpeed(double speed) {
		if (losingSpeedRandomGenerator.nextDouble() <= speedLosingPropability)
			return speed * (MIN_SPEED_LOSING_COEFFICIENT + losingSpeedRandomGenerator.nextDouble() * (MAX_SPEED_LOSING_COEFFICIENT - MIN_SPEED_LOSING_COEFFICIENT));
		else
			return speed;
	}

	@Override
	public String getDescription() {
		return vehicle != null ? vehicle.getDescription() : null;
	}

}
