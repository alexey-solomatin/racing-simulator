/**
 * 
 */
package com.smartech.course.racing.vehicle.payload;

/**
 * @author Alexey Solomatin
 *
 */
public class TruckPayload implements Payloadable {
	private double maxPayloadWeight;
	private double payloadWeight;

	/**
	 * 
	 */
	public TruckPayload(double maxPayloadWeight, double payloadWeight) {
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
	public void getPayloadWeight() {
		// TODO Auto-generated method stub

	}

}
