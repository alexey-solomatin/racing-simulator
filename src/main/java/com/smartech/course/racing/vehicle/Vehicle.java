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

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "VehicleState [time=" + time + ", speed=" + speed + ", position=" + position + "]";
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(position);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(speed);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(time);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			VehicleState other = (VehicleState) obj;
			if (Double.doubleToLongBits(position) != Double.doubleToLongBits(other.position))
				return false;
			if (Double.doubleToLongBits(speed) != Double.doubleToLongBits(other.speed))
				return false;
			if (Double.doubleToLongBits(time) != Double.doubleToLongBits(other.time))
				return false;
			return true;
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
