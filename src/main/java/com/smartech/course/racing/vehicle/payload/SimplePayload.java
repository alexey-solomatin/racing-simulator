/**
 * 
 */
package com.smartech.course.racing.vehicle.payload;

import com.smartech.course.racing.exception.CreatingVehicleException;

/**
 * Simple payload carried by a truck or a car trailer
 * @author Alexey Solomatin
 *
 */
public class SimplePayload implements PayloadCarriable {
	private final double maxPayloadWeight;
	private final double payloadWeight;

	/**
	 * @throws CreatingVehicleException 
	 * 
	 */
	public SimplePayload(double maxPayloadWeight, double payloadWeight) throws CreatingVehicleException {
		if (maxPayloadWeight < 0 ||
			payloadWeight < 0 ||
			maxPayloadWeight < payloadWeight)
			throw new CreatingVehicleException("Cannot create SimplePayload with the specified parameters.");
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimplePayload [maxPayloadWeight=" + maxPayloadWeight + ", payloadWeight=" + payloadWeight + "]";
	}

}
