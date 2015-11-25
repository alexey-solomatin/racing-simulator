/**
 * 
 */
package com.smartech.course.racing.vehicle.payload;

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
	 * 
	 */
	public BusPassengers(long maxNumberOfPassengers, long numberOfPassengers) {
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

}
