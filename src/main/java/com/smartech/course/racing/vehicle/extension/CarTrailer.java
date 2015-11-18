/**
 * 
 */
package com.smartech.course.racing.vehicle.extension;

/**
 * @author Alexey Solomatin
 *
 */
public class CarTrailer {
	private String name;
	private double maxSpeed;
	private double weight;
	private double maxPayloadWeight;	
	private double payloadWeight;

	/**
	 * 
	 */
	public CarTrailer(double weigth, double maxPayloadWeight, double maxSpeed) {		
		//super(weight, maxPayloadWeight);
//		this.weight = weigth;
		this.maxSpeed = maxSpeed;		
	}
	
	public double getMaxSpeed() {	
		return maxSpeed;
	}
	

	public double getWeight() {
		return weight;
	}

	/**
	 * @return the maxPayload
	 */
	public double getMaxPayload() {
		return 0;
	}

	/**
	 * @param maxPayload the maxPayload to set
	 */
	public void setMaxPayload(double maxPayload) {
		//this.maxPayload = maxPayload;
	}

	/**
	 * @return the payload
	 */
	public double getPayload() {
		return 0;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(double payload) {
		//this.payload = payload;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		//this.weight = weight;
	}

	/**
	 * @param maxSpeed the maxSpeed to set
	 */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
}
