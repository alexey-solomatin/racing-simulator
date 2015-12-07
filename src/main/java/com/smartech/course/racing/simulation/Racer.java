package com.smartech.course.racing.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Movable;
import com.smartech.course.racing.vehicle.Vehicle;
import com.smartech.course.racing.vehicle.VehicleState;

/**
 * Racer which refers to {@link Vehicle} and {@link Racing}
 * and contains some specific racer data 
 * @author Alexey Solomatin
 *
 */
public class Racer extends Observable implements Raceable {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private String name;
	private Movable vehicle;
	private Racing racing;
	private VehicleState vehicleState;
	private boolean isFinished;
	private List<VehicleState> movingHistory = new ArrayList<>();
	
	public Racer(String name, Movable vehicle, Racing racing) {
		this.name = name;
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
			if (vehicleState != null)
				movingHistory.add(vehicleState);
			else
				log.warn("Cannot add a vehicle state to the moving history.");
		}
	}
	
	@Override
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * @return the vehicle
	 */
	public Movable getVehicle() {
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

	@Override
	public double getAverageSpeed() {
		return movingHistory.stream().mapToDouble((state)->state.getSpeed()).sum() / movingHistory.size();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Racer [name=" + name + ", vehicle=" + vehicle + ", racing=" + racing + ", vehicleState=" + vehicleState
				+ ", isFinished=" + isFinished + ", movingHistory=" + movingHistory + "]";
	}

}
