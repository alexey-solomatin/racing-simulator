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

import com.smartech.course.racing.exception.MovingException;

/**
 * Races is a vehicle which is able to move on the racing track
 * @author Alexey Solomatin
 *
 */
public class Vehicle extends DynamicObject implements Movable {	
	protected double acceleration;
	
	protected double position;
	protected double time;	
	protected double speed;
	
	public Vehicle(String name, double weight, double maxSpeed, double acceleration) {
		// TODO: add checking parameters
		super(name, weight, maxSpeed);		
		this.acceleration = acceleration;
		this.speed = 0;
		this.time = 0;
		this.position = 0;
	}
	
	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.Movable#move(double)
	 */
	@Override
	public void move(double time) throws MovingException {
		if (time < 0)
			throw new MovingException("Moving time cannot be negative.");		
		
		double timeOfAcceleratedMoving = calculateSpeed(time) >= calculateMaxSpeed() ?
			(calculateMaxSpeed() - speed) / calculateAcceleration() :
			time;				
		moveWithAcceleration(timeOfAcceleratedMoving);
		moveWithoutAcceleration(time - timeOfAcceleratedMoving);			
	}
	
	protected double calculateAcceleration() {
		return acceleration;
	}
	
	protected double calculateMaxSpeed() {
		return maxSpeed;
	}
	
	public void reset() {
		position = 0;
		speed = 0;
		time = 0;
	}
	
	private double calculateSpeed(double stepTime) {
		double nextSpeed = speed + calculateAcceleration()*stepTime;
		return nextSpeed > maxSpeed ? maxSpeed : nextSpeed;
	}
	
	private void moveWithAcceleration(double stepTime) {		
		position += speed*stepTime + (calculateAcceleration()*stepTime*stepTime/2);
		speed += calculateAcceleration()*stepTime;
		time += stepTime;
	}
	
	private void moveWithoutAcceleration(double stepTime) {		
		position += this.speed * stepTime;
		time += stepTime;
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
	 * @return the position
	 */
	public double getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(double position) {
		this.position = position;
	}

	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(double time) {
		this.time = time;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
}
