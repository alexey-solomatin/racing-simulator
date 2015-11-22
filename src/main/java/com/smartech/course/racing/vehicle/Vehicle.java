/**
 * 
 */
package com.smartech.course.racing.vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public VehicleState move(VehicleState curState, double time) {
		return new VehicleState(
			curState.getTime()+time,
			calculateNewSpeed(curState, time), 
			calculateNewPosition(curState, time));
	}
		
	protected double calculateNewSpeed(VehicleState curState, double time) {
		double nextSpeed = curState.getSpeed() + getAcceleration()*time;
		return nextSpeed > maxSpeed ? maxSpeed : nextSpeed;
	}
	
	protected double calculateNewPosition(VehicleState curState, double time) {
		double timeOfAcceleratedMoving = calculateNewSpeed(curState, time) >= getMaxSpeed() ?
				(getMaxSpeed() - curState.getSpeed()) / getAcceleration() :
				time;				
		return curState.getPosition() + 
			moveWithAcceleration(curState.getSpeed(), timeOfAcceleratedMoving) +
			moveWithoutAcceleration(curState.getSpeed(), time - timeOfAcceleratedMoving);
	}
	
	public double getMaxSpeed() {
		return maxSpeed;
	}
	
	public double getAcceleration() {
		return acceleration;
	}		
	
	private double moveWithAcceleration(double speed, double time) {		
		return speed*time + (getAcceleration()*time*time/2);
		
	}
	
	private double moveWithoutAcceleration(double speed, double time) {		
		return speed * time;
	}
	
}
