/**
 * 
 */
package com.smartech.course.racing.vehicle.builder.racing;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.smartech.course.racing.Racing;

/**
 * @author Alexey Solomatin
 *
 */
public class ConsoleRacingBuilder extends AbstractRacingBuilder {
	

	/**
	 * 
	 */
	public ConsoleRacingBuilder() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.builder.racing.RacingBuilder#name()
	 */
	@Override
	public RacingBuilder name() {
		// getting the racing name
		name = null;
		while (StringUtils.isBlank(name)) {
			name = System.console().readLine("Please enter the racing name: ");
			if (StringUtils.isBlank(name))
				System.console().printf("You've entered the incorrect name.\nPlease try again.\n");					
		}				
		return this;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.builder.racing.RacingBuilder#distance()
	 */
	@Override
	public RacingBuilder distance() {
		// getting the racing distance
		while (true) {
			try {
				distance = Double.parseDouble(System.console().readLine("Please enter the racing distance: "));				
				break;
			} catch (Exception e) {
				System.console().printf("You've entered the incorrect distance.\nPlease try again.\n\n");
				log.error("Error during getting the racing distance.", e);
			}								
		}				
		return this;
	}

}
