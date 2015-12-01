/**
 * 
 */
package com.smartech.course.racing.vehicle.payload;

import com.smartech.course.racing.exception.CreatingVehicleException;

/**
 * Payload carried by a bus
 * @author Alexey Solomatin
 *
 */
public class BusPassengers implements PayloadCarriable {
	public final static double PASSENGER_WEIGHT = 70;
	
	private long maxNumberOfPassengers;
	private long numberOfPassengers;

	/**
	 * @throws CreatingVehicleException 
	 * 
	 */
	public BusPassengers(long maxNumberOfPassengers, long numberOfPassengers) throws CreatingVehicleException {
		if (maxNumberOfPassengers < 0 ||
			numberOfPassengers < 0 ||
			maxNumberOfPassengers < numberOfPassengers)
			throw new CreatingVehicleException("Cannot create BusPassengers with the specified parameters.");
		this.maxNumberOfPassengers = maxNumberOfPassengers;
		this.numberOfPassengers = numberOfPassengers;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.VehiclePayload#getMaxPayloadWeight()
	 */
	@Override
	public double getMaxPayloadWeight() {
		return PASSENGER_WEIGHT*maxNumberOfPassengers;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.VehiclePayload#getPayloadWeight()
	 */
	@Override
	public double getPayloadWeight() {
		return PASSENGER_WEIGHT*numberOfPassengers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BusPassengers [maxNumberOfPassengers=" + maxNumberOfPassengers + ", numberOfPassengers="
				+ numberOfPassengers + "]";
	}

	/**
	 * @return the maxNumberOfPassengers
	 */
	public long getMaxNumberOfPassengers() {
		return maxNumberOfPassengers;
	}

	/**
	 * @param maxNumberOfPassengers the maxNumberOfPassengers to set
	 */
	public void setMaxNumberOfPassengers(long maxNumberOfPassengers) {
		this.maxNumberOfPassengers = maxNumberOfPassengers;
	}

	/**
	 * @return the numberOfPassengers
	 */
	public long getNumberOfPassengers() {
		return numberOfPassengers;
	}

	/**
	 * @param numberOfPassengers the numberOfPassengers to set
	 */
	public void setNumberOfPassengers(long numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

}
