/**
 * 
 */
package com.smartech.course.racing.vehicle.payload;

/**
 * Simple payload carried by a truck or a car trailer
 * @author Alexey Solomatin
 *
 */
public class SimplePayload implements PayloadCarriable {
	private double maxPayloadWeight;
	private double payloadWeight;

	/**
	 * 
	 */
	public SimplePayload(double maxPayloadWeight, double payloadWeight) {
		this.maxPayloadWeight = maxPayloadWeight;
		this.payloadWeight = payloadWeight;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.PayloadCarriable#getMaxPayloadWeight()
	 */
	@Override
	public double getMaxPayloadWeight() {
		return maxPayloadWeight;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.PayloadCarriable#getPayloadWeight()
	 */
	@Override
	public double getPayloadWeight() {
		return payloadWeight;
	}

}
