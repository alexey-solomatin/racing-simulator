/**
 * 
 */
package com.smartech.course.racing.vehicle.builder.simulation;

import com.smartech.course.racing.vehicle.Vehicle;
import com.smartech.course.racing.vehicle.builder.racing.ConsoleRacingBuilder;
import com.smartech.course.racing.vehicle.builder.vehicle.VehicleBuilder;

/**
 * @author Alexey Solomatin
 *
 */
public class ConsoleRacingSimulationBuilder extends AbstractRacingSimulationBuilder {
	public ConsoleRacingSimulationBuilder() {
		super(new ConsoleRacingBuilder(), new VehicleBuilder() {

			@Override
			public Vehicle build() {
				// TODO Auto-generated method stub
				return null;
			}
			// TODO: JUST FOR TESTING. REPLACE BY THE VALID CODE!!!
		});
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.vehicle.builder.simulation.RacingSimulationBuilder#timeStep()
	 */
	@Override
	public RacingSimulationBuilder timeStep() {
		// getting the racing simulation time step
		while (true) {
			try {
				timeStep = Double.parseDouble(System.console().readLine("Please enter the racing simulation time step: "));
				if (timeStep < 0)
					throw new IllegalArgumentException();
				break;
			} catch (Exception e) {
				System.console().printf("You've entered the incorrect racing simulation time step.\nPlease try again.\n\n");
				log.error("Error during getting the racing simulation time step.", e);
			}								
		}				
		return this;
	}

	@Override
	protected boolean shouldBuildOneMoreVehicle() {
		System.console().printf("Would you like to specify one more vehicle for the racing simulation? (y/n)\n");
		String answer = System.console().readLine().trim();
		boolean shouldBeBuilt = !answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("yes");
		if (shouldBeBuilt)
			System.console().printf("\n\nPlease create a new vehicle.\n");
		return shouldBeBuilt;
	}

}
