/**
 * 
 */
package com.smartech.course.racing.dialog.racing;

import org.apache.commons.lang3.StringUtils;

import com.smartech.course.racing.builder.racing.RacingBuilderImpl;
import com.smartech.course.racing.dialog.ConsoleDialog;
import com.smartech.course.racing.dialog.simple.DoubleValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.StringValueConsoleDialog;
import com.smartech.course.racing.simulation.Racing;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingCreationConsoleDialog extends ConsoleDialog<Racing> {

	/**
	 * @param questionMessage
	 * @param errorMessage
	 */
	public RacingCreationConsoleDialog() {
		super("Please specify the details about the racing you want to simulate.", "Error during racing creation.");
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dialog.ConsoleDialog#buildObject()
	 */
	@Override
	protected Racing buildObject() throws Exception {
		log.debug("Creating a new racing.");			
		return new RacingBuilderImpl()
			.name(new StringValueConsoleDialog(
				"Please enter the racing name: ", 
				"You've entered the incorrect name.", 
				(s) -> !StringUtils.isBlank(s)))
			.distance(new DoubleValueConsoleDialog(
				"Please enter the racing distance in meters: ", 
				"You've entered the incorrect distance.", 
				(d) -> d>0))
			.build();
	}

}
