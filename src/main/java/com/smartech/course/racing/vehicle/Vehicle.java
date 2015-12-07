/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * Vehicle with its moving rules
 * @author Alexey Solomatin
 *
 */
public class Vehicle extends DynamicObject implements Movable {
	protected double acceleration;	
	
	public Vehicle(String name, double weight, double maxSpeed, double acceleration)  throws CreatingVehicleException {
		// TODO: add checking parameters
		super(name, weight, maxSpeed);		
		this.acceleration = acceleration;
	}
		
	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.Movable#move(com.smartech.course.racing.vehicle.Vehicle.VehicleState, double)
	 */
	@Override
	public VehicleState move(VehicleState curState, double time) throws MovingVehicleException {
		if (curState == null)
			throw new MovingVehicleException("The current vehicle state is not specified.");
		if (time < 0)
			throw new MovingVehicleException("The time for moving the vehicle cannot be negative.");
		return new VehicleState(
			curState.getTime()+time,
			calculateNewSpeed(curState, time), 
			calculateNewPosition(curState, time));
	}
	
	protected double calculateCurrentAcceleration() {
		return acceleration;
	}	
		
	protected double calculateNewSpeed(VehicleState curState, double time) throws MovingVehicleException {
		double nextSpeed = curState.getSpeed() + calculateCurrentAcceleration()*time;
		return nextSpeed > calculateCurrentMaxSpeed() ? calculateCurrentMaxSpeed() : nextSpeed;
	}
	
	protected double calculateNewPosition(VehicleState curState, double time) throws MovingVehicleException {
		double timeOfAcceleratedMoving = calculateNewSpeed(curState, time) >= calculateCurrentMaxSpeed() ?
				(calculateCurrentMaxSpeed() - curState.getSpeed()) / calculateCurrentAcceleration() :
				time;				
		return curState.getPosition() + 
			moveWithAcceleration(curState.getSpeed(), timeOfAcceleratedMoving) +
			moveWithoutAcceleration(calculateCurrentMaxSpeed(), time - timeOfAcceleratedMoving);
	}
	
	private double moveWithAcceleration(double speed, double time) {		
		return speed*time + (calculateCurrentAcceleration()*time*time/2);
		
	}
	
	private double moveWithoutAcceleration(double speed, double time) {		
		return speed * time;
	}

	/**
	 * @return the acceleration
	 */
	public double getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}	
}
