/**
 * 
 */
package com.smartech.course.racing.dialog.racing;

import java.util.List;

import com.smartech.course.racing.dao.DaoFactory;
import com.smartech.course.racing.dialog.ConsoleDialog;
import com.smartech.course.racing.dialog.simple.LongValueConsoleDialog;
import com.smartech.course.racing.simulation.Racing;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingSelectionConsoleDialog extends ConsoleDialog<Racing> {
	private List<Racing> racings;
	private final DaoFactory daoFactory;

	/**
	 * @param questionMessage
	 * @param errorMessage
	 */
	public RacingSelectionConsoleDialog(DaoFactory daoFactory) {
		super("Please select the racing you want to simulate.", "Cannot find the specified racing.");
		this.daoFactory = daoFactory;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dialog.ConsoleDialog#buildObject()
	 */
	@Override
	protected Racing buildObject() throws Exception {
		if (racings == null) {
			log.debug("Loading the racings from the database.");
			racings = daoFactory.getRacingDao().readAll();
		}
		log.debug("Selecting a racing.");
		if (racings != null && !racings.isEmpty()) {
			System.console().printf("There are following racings in the database:\n");
			racings.stream().forEach((r)->System.console().printf("\tID: %d, name: %s, distance: %.1f\n", r.getId(), r.getName(), r.getDistance()));
		} else
			System.console().printf("There are no racings in the database.\n");
		Long racingId = new LongValueConsoleDialog("Please enter the racing ID: ", "You've entered the wrong ID.", (id)->id>0).get();
		Racing racing = daoFactory.getRacingDao().read(racingId);
		if (racing != null) {
			return racing;
		} else
			throw new Exception("Cannot load a racing with ID " + racingId);		
	}

}