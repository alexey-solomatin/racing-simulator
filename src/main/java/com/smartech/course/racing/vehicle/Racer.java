/**
 * 
 */
package com.smartech.course.racing.vehicle;

import com.smartech.course.racing.exception.MovingException;

/**
 * Races is a vehicle which is able to move on the racing track
 * @author Alexey Solomatin
 *
 */
public abstract class Racer extends Vehicle implements Movable {
	protected double position;
	protected double time;
	protected double acceleration;
	protected double speed;
	
	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.Movable#move(double)
	 */
	@Override
	public void move(double time) throws MovingException {
		if (time < 0)
			throw new MovingException("Moving time cannot be negative.");
		
		double timeOfAcceleratedMoving;
		double timeOfNotAcceleratedMoving;
		if (calculateSpeedWithAcceleratedMoving(time) > this.maxSpeed) {
			timeOfAcceleratedMoving = (maxSpeed - speed) / calculateAcceleration();
			timeOfNotAcceleratedMoving = time - timeOfAcceleratedMoving;
		} else {
			timeOfAcceleratedMoving = time;
			timeOfNotAcceleratedMoving = 0;
		}
		moveWithAcceleration(timeOfAcceleratedMoving);
		moveWithoutAcceleration(timeOfNotAcceleratedMoving);			
	}
	
	private double calculateSpeedWithAcceleratedMoving(double time) {
		return speed + calculateAcceleration()*time;
	}
	
	private void moveWithAcceleration(double time) {
		this.position += this.speed*time + (calculateAcceleration()*time*time/2);
		this.speed += calculateAcceleration()*time;
		this.time += time;
	}
	
	private void moveWithoutAcceleration(double time) {		
		this.position += this.speed * time;
		this.time += time;
	}
	
	protected abstract double calculateAcceleration();
	
	protected abstract double calculateSpeed();

	/**
	 * @return the position
	 */
	public double getPosition() {
		return position;
	}

	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
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

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}
	
}
