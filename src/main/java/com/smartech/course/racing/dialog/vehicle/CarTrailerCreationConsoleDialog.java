/**
 * 
 */
package com.smartech.course.racing.dialog.vehicle;

import org.apache.commons.lang3.StringUtils;

import com.smartech.course.racing.config.AppProperties;
import com.smartech.course.racing.dialog.ConsoleDialog;
import com.smartech.course.racing.dialog.simple.DoubleValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.StringValueConsoleDialog;
import com.smartech.course.racing.vehicle.payload.CarTrailer;

/**
 * @author Alexey Solomatin
 *
 */
public class CarTrailerCreationConsoleDialog extends ConsoleDialog<CarTrailer> {	

	/**
	 * @param questionMessage
	 * @param errorMessage
	 */
	public CarTrailerCreationConsoleDialog() {
		super("Please create a new car trailer", "The error occured during car trailer creation.");
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dialog.ConsoleDialog#buildObject()
	 */
	@Override
	protected CarTrailer buildObject() throws Exception {
		return new CarTrailer(
			new StringValueConsoleDialog(
				"Please enter the name: ",
				"You've entered the incorrect name.", 
				(s) -> !StringUtils.isBlank(s)).get(), 
			AppProperties.getInstance().getDouble("trailer.weight"), 
			AppProperties.getInstance().getDouble("trailer.maxSpeed"), 
			AppProperties.getInstance().getDouble("trailer.maxPayloadWeight"), 
			new DoubleValueConsoleDialog(
				"Please enter the payload weight in kilograms: ", 
				"You've entered the incorrect payload weight.", 
				(d) -> d>0).get());		
	}

}
