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

	/**
	 * 
	 */
	public BusPassengers(long maxNumberOfPassengers, long numberOfPassengers) {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.VehiclePayload#getMaxPayloadWeight()
	 */
	@Override
	public double getMaxPayloadWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.VehiclePayload#getPayloadWeight()
	 */
	@Override
	public double getPayloadWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
