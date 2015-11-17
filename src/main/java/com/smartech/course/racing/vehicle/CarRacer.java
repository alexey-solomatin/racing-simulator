/**
 * 
 */
package com.smartech.course.racing.vehicle;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Car racer. Vehicle extensions can be used with cars 
 * @author Alexey Solomatin
 *
 */
public class CarRacer extends Racer implements Extensible {
	private Collection<VehicleExtension> vehicleExtensions;

	/**
	 * 
	 */
	public CarRacer() {
		vehicleExtensions = new ArrayList<VehicleExtension>();
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.Racer#calculateAcceleration()
	 */
	@Override
	protected double calculateAcceleration() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.Racer#calculateSpeed()
	 */
	@Override
	protected double calculateSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void connect(VehicleExtension vehicleExtension) {
		this.vehicleExtensions.add(vehicleExtension);
	}

	@Override
	public void remove(VehicleExtension vehicleExtension) {
		this.vehicleExtensions.remove(vehicleExtension);
	}

}
