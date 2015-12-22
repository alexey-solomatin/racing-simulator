/**
 * 
 */
package com.smartech.course.racing.dialog.vehicle;

import org.apache.commons.lang3.StringUtils;

import com.smartech.course.racing.builder.vehicle.CarBuilder;
import com.smartech.course.racing.config.AppProperties;
import com.smartech.course.racing.dialog.ConsoleDialog;
import com.smartech.course.racing.dialog.simple.StringValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.YesNoConsoleDialog;
import com.smartech.course.racing.vehicle.Car;
import com.smartech.course.racing.vehicle.Movable;

/**
 * @author Alexey Solomatin
 *
 */
public class CarCreationConsoleDialog extends ConsoleDialog<Car> {
	private CarBuilder carBuilder;

	/**
	 * @param questionMessage
	 * @param errorMessage
	 */
	public CarCreationConsoleDialog(CarBuilder carBuilder) {
		super("Please create a new car.", "The error occured during car creation.");
		this.carBuilder = carBuilder;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dialog.ConsoleDialog#buildObject()
	 */
	@Override
	protected Car buildObject() throws Exception {
		carBuilder
			.weight(AppProperties.getInstance().getDouble("car.weight"))
			.maxSpeed(AppProperties.getInstance().getDouble("car.maxSpeed"))
			.acceleration(AppProperties.getInstance().getDouble("car.acceleration"))
			.name(new StringValueConsoleDialog(
				"Please enter the name: ",
				"You've entered the incorrect name.", 
				(s) -> !StringUtils.isBlank(s)));
		if (new YesNoConsoleDialog("Would you like to create a trailer for this car?").get())
			carBuilder.trailer(new CarTrailerCreationConsoleDialog());
		return carBuilder.build();
	}

}
