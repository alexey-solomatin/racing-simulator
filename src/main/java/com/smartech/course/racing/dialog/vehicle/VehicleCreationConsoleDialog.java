/**
 * 
 */
package com.smartech.course.racing.dialog.vehicle;

import com.smartech.course.racing.builder.vehicle.BusBuilderImpl;
import com.smartech.course.racing.builder.vehicle.CarBuilderImpl;
import com.smartech.course.racing.builder.vehicle.TruckBuilderImpl;
import com.smartech.course.racing.dialog.ConsoleDialog;
import com.smartech.course.racing.vehicle.Vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public class VehicleCreationConsoleDialog extends ConsoleDialog<Vehicle> {

	/**
	 * @param questionMessage
	 * @param errorMessage
	 */
	public VehicleCreationConsoleDialog() {
		super("Please create a new vehicle.", "Cannot create the specified vehicle.");
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dialog.ConsoleDialog#buildObject()
	 */
	@Override
	protected Vehicle buildObject() throws Exception {
		log.debug("Creating a new vehicle.");							
		String vehicleType = System.console().readLine("What type of a vehicle do you want to create? (car/truck/bus) ");
		log.debug("User decision of a vehicle type: {}.", vehicleType);			
		switch (vehicleType.toLowerCase()) {
			case "car":
			case "c":
				log.debug("Creating a car.");
				return new CarCreationConsoleDialog(new CarBuilderImpl()).get();						
			case "truck":
			case "t":
				log.debug("Creating a truck.");
				return new TruckCreationConsoleDialog(new TruckBuilderImpl()).get();
			case "bus":
			case "b":
				log.debug("Creating a bus.");
				return new BusCreationConsoleDialog(new BusBuilderImpl()).get();
			default:
				log.error("Unknown vehicle type: {}.", vehicleType);
				System.console().printf("I don't know how to create it!\n");
				throw new Exception("Unknown vehicle type: " + vehicleType);						
		}		
	}

}
