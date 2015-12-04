package com.smartech.course.racing;

import java.util.Observable;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Vehicle;
import com.smartech.course.racing.vehicle.VehicleState;

/**
 * Racer which refers to {@link Vehicle} and {@link Racing}
 * and contains some specific racer data 
 * @author Alexey Solomatin
 *
 */
public class Racer extends Observable implements Raceable {
	private Vehicle vehicle;
	private Racing racing;
	private VehicleState vehicleState;
	private boolean isFinished;
	
	// TODO: define statistics fields
	
	public Racer() {
		// TODO Auto-generated constructor stub
	}
	
	public Racer(Vehicle vehicle, Racing racing) {
		this.vehicle = vehicle;
		this.racing = racing;
		this.vehicleState = new VehicleState();
	}
	
	@Override
	public void move(double time) throws MovingVehicleException {
		if (!isFinished) {
			vehicleState = vehicle.move(vehicleState, time);
			if (vehicleState.getPosition() >= racing.getDistance()) {
				isFinished = true;
				vehicleState = new VehicleState(vehicleState.getTime(), 
						vehicleState.getSpeed(), 
						racing.getDistance());
				setChanged();
				notifyObservers();				
			}
		}
	}
	
	@Override
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the racing
	 */
	public Racing getRacing() {
		return racing;
	}

	/**
	 * @param racing the racing to set
	 */
	public void setRacing(Racing racing) {
		this.racing = racing;
	}

	/**
	 * @return the vehicleState
	 */
	public VehicleState getVehicleState() {
		return vehicleState;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Racer [racing=" + racing + ", vehicle=" + vehicle + ", vehicleState=" + vehicleState + "]";
	}

}
