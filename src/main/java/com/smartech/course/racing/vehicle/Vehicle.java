/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.exception.MovingVehicleException;

/**
 * Races is a vehicle which is able to move on the racing track
 * @author Alexey Solomatin
 *
 */
public class Vehicle extends DynamicObject implements Movable {
	public static class VehicleState {		
		private final double time;
		private final double speed;
		private final double position;
		
		public VehicleState() {
			time = 0;
			speed = 0;
			position = 0;
		}
		
		/**
		 * @param time
		 * @param speed
		 * @param position
		 */
		public VehicleState(double time, double speed, double position) {
			super();
			this.time = time;
			this.speed = speed;
			this.position = position;
		}
		/**
		 * @return the time
		 */
		public double getTime() {
			return time;
		}
		/**
		 * @return the speed
		 */
		public double getSpeed() {
			return speed;
		}
		/**
		 * @return the position
		 */
		public double getPosition() {
			return position;
		}			
	}
	
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
		double timeOfAcceleratedMoving = calculateNewSpeed(curState, time) >= getMaxSpeed() ?
				(getMaxSpeed() - curState.getSpeed()) / calculateCurrentAcceleration() :
				time;				
		return curState.getPosition() + 
			moveWithAcceleration(curState.getSpeed(), timeOfAcceleratedMoving) +
			moveWithoutAcceleration(curState.getSpeed(), time - timeOfAcceleratedMoving);
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
