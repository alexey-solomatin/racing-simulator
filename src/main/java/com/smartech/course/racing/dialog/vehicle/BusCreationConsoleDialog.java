/**
 * 
 */
package com.smartech.course.racing.dialog.vehicle;

import org.apache.commons.lang3.StringUtils;

import com.smartech.course.racing.builder.vehicle.BusBuilder;
import com.smartech.course.racing.config.AppProperties;
import com.smartech.course.racing.dialog.ConsoleDialog;
import com.smartech.course.racing.dialog.simple.LongValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.StringValueConsoleDialog;
import com.smartech.course.racing.vehicle.Movable;

/**
 * @author Alexey Solomatin
 *
 */
public class BusCreationConsoleDialog extends ConsoleDialog<Movable> {	
	private BusBuilder busBuilder;

	public BusCreationConsoleDialog(BusBuilder busBuilder) {
		super("Please create a new bus.", "The error occured during bus creation.");
		this.busBuilder = busBuilder;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dialog.ConsoleDialog#buildObject()
	 */
	@Override
	protected Movable buildObject() throws Exception {
		return busBuilder
			.weight(AppProperties.getInstance().getDouble("bus.weight"))
			.maxSpeed(AppProperties.getInstance().getDouble("bus.maxSpeed"))
			.acceleration(AppProperties.getInstance().getDouble("bus.acceleration"))
			.maxNumberOfPassengers(AppProperties.getInstance().getLong("bus.maxNumberOfPassengers"))
			.name(new StringValueConsoleDialog(
				"Please enter the name: ",
				"You've entered the incorrect name.", 
				(s) -> !StringUtils.isBlank(s)))			
			.numberOfPassengers(new LongValueConsoleDialog(
				"Please enter the number of passengers: ", 
				"You've entered the incorrect number of passengers.", 
				(n) -> n>=0))
			.build();
	}
	
	

}
