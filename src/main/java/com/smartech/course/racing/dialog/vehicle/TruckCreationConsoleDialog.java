/**
 * 
 */
package com.smartech.course.racing.dialog.vehicle;

import org.apache.commons.lang3.StringUtils;

import com.smartech.course.racing.builder.vehicle.TruckBuilder;
import com.smartech.course.racing.config.AppProperties;
import com.smartech.course.racing.dialog.ConsoleDialog;
import com.smartech.course.racing.dialog.simple.DoubleValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.StringValueConsoleDialog;
import com.smartech.course.racing.exception.CreatingVehicleException;
import com.smartech.course.racing.vehicle.Truck;

/**
 * @author Alexey Solomatin
 *
 */
public class TruckCreationConsoleDialog extends ConsoleDialog<Truck> {
	private TruckBuilder truckBuilder;
	

	public TruckCreationConsoleDialog(TruckBuilder truckBuilder) {
		super("Please create a new truck", "The error occured during truck creation.");
		this.truckBuilder = truckBuilder;
	}

	@Override
	protected Truck buildObject() throws CreatingVehicleException {
		return truckBuilder
			.weight(AppProperties.getInstance().getDouble("truck.weight"))
			.maxSpeed(AppProperties.getInstance().getDouble("truck.maxSpeed"))					
			.acceleration(AppProperties.getInstance().getDouble("truck.acceleration"))
			.maxPayloadWeight(AppProperties.getInstance().getDouble("truck.maxPayloadWeight"))
			.name(new StringValueConsoleDialog(
				"Please enter the name: ",
				"You've entered the incorrect name.", 
				(s) -> !StringUtils.isBlank(s)))			
			.payloadWeight(new DoubleValueConsoleDialog(
				"Please enter the payload weight in kilograms: ", 
				"You've entered the incorrect payload weight.", 
				(d) -> d>0))
			.build();
	}

}
