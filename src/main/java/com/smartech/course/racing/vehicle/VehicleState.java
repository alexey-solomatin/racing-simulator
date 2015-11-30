package com.smartech.course.racing.vehicle;

public class VehicleState {		
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