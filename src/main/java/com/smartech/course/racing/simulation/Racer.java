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
import com.smartech.course.racing.vehicle.event.VehicleEvent;

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
	private volatile boolean isFinished;
	private List<VehicleState> movingHistory = new ArrayList<>();
	private long speedLosingNumber;
	
	public Racer(String name, Movable vehicle, Racing racing) {
		this.name = name;
		this.vehicle = vehicle;
		this.racing = racing;
		this.vehicleState = new VehicleState();
		movingHistory.add(vehicleState);
	}
	
	@Override
	public synchronized void move(double time) throws MovingVehicleException {
		if (!isFinished) {
			VehicleState curVehicleState = vehicleState;
			vehicleState = vehicle.move(curVehicleState, time);
			if (vehicleState != null) {
				if (vehicleState.getSpeed() < curVehicleState.getSpeed()) {
					++speedLosingNumber;
					setChanged();
					notifyObservers(new VehicleEvent(String.format("Speed has been lost from %.1f m/s to %.1f m/s!", curVehicleState.getSpeed(), vehicleState.getSpeed())));
				}
				if (vehicleState.getPosition() >= racing.getDistance()) {
					isFinished = true;
					vehicleState = new VehicleState(vehicleState.getTime(), 
							vehicleState.getSpeed(), 
							racing.getDistance());
					setChanged();
					notifyObservers(new VehicleEvent("Finished!"));				
				}				
				movingHistory.add(vehicleState);
			} else
				log.warn("Cannot add a vehicle state to the moving history.");
		}
	}
	

	public synchronized Racer createSnapshot() {
		Racer racer = new Racer(name, vehicle, racing);				
		racer.vehicleState = new VehicleState(vehicleState);
		racer.isFinished = isFinished;
		return racer;
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
		return !movingHistory.isEmpty() 
			? movingHistory.stream().mapToDouble((state)->state.getSpeed()).sum() / movingHistory.size()
			: 0;
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

	@Override
	public long getSpeedLosingNumber() {
		return speedLosingNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Racer [name=" + name + ", vehicle=" + vehicle + ", racing=" + racing + ", vehicleState=" + vehicleState
				+ ", isFinished=" + isFinished + ", movingHistory=" + movingHistory + ", speedLosingNumber="
				+ speedLosingNumber + "]";
	}
}
