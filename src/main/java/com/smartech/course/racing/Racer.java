package com.smartech.course.racing;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Movable;
import com.smartech.course.racing.vehicle.Vehicle;
import com.smartech.course.racing.vehicle.Vehicle.VehicleState;

/**
 * Racer which refers to {@link Vehicle} and {@link Racing}
 * and contains some specific racer data 
 * @author Alexey Solomatin
 *
 */
public class Racer implements Raceable {
	private Movable vehicle;
	private Racing racing;
	private VehicleState vehicleState;
	
	// TODO: define statistics fields
	
	public Racer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move(double time) throws MovingVehicleException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
