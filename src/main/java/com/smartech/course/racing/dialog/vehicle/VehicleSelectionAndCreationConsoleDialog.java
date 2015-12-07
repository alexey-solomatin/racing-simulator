/**
 * 
 */
package com.smartech.course.racing.dialog.vehicle;

import com.smartech.course.racing.builder.vehicle.BusBuilderImpl;
import com.smartech.course.racing.builder.vehicle.CarBuilderImpl;
import com.smartech.course.racing.builder.vehicle.TruckBuilderImpl;
import com.smartech.course.racing.dialog.ConsoleDialog;
import com.smartech.course.racing.vehicle.Movable;
import com.smartech.course.racing.vehicle.SpeedLosingVehicleWrapper;

/**
 * @author Alexey Solomatin
 *
 */
public class VehicleSelectionAndCreationConsoleDialog extends ConsoleDialog<Movable> {

	/**
	 * @param questionMessage
	 * @param errorMessage
	 */
	public VehicleSelectionAndCreationConsoleDialog() {
		super("Please create a new vehicle.", "Cannot create the specified vehicle.");
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dialog.ConsoleDialog#buildObject()
	 */
	@Override
	protected Movable buildObject() throws Exception {
		log.debug("Creating a new vehicle.");							
		String vehicleType = System.console().readLine("What type of a vehicle do you want to create? (car/truck/bus) ");
		log.debug("User decision of a vehicle type: {}.", vehicleType);			
		switch (vehicleType.toLowerCase()) {
			case "car":
			case "c":
				log.debug("Creating a car.");
				return new SpeedLosingVehicleWrapper(new CarCreationConsoleDialog(new CarBuilderImpl()).get(), 0.4);						
			case "truck":
			case "t":
				log.debug("Creating a truck.");
				return new SpeedLosingVehicleWrapper(new TruckCreationConsoleDialog(new TruckBuilderImpl()).get(), 0.2);
			case "bus":
			case "b":
				log.debug("Creating a bus.");
				return new SpeedLosingVehicleWrapper(new BusCreationConsoleDialog(new BusBuilderImpl()).get(), 0.1);
			default:
				log.error("Unknown vehicle type: {}.", vehicleType);
				System.console().printf("I don't know how to create it!\n");
				throw new Exception("Unknown vehicle type: " + vehicleType);						
		}		
	}

}
