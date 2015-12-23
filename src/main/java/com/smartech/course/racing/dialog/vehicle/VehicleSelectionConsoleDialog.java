/**
 * 
 */
package com.smartech.course.racing.dialog.vehicle;

import java.sql.SQLException;
import java.util.List;

import com.smartech.course.racing.dao.DaoFactory;
import com.smartech.course.racing.dialog.ConsoleDialog;
import com.smartech.course.racing.dialog.simple.LongValueConsoleDialog;
import com.smartech.course.racing.vehicle.Bus;
import com.smartech.course.racing.vehicle.Car;
import com.smartech.course.racing.vehicle.Movable;
import com.smartech.course.racing.vehicle.SpeedLosingVehicleWrapper;
import com.smartech.course.racing.vehicle.Truck;
import com.smartech.course.racing.vehicle.Vehicle;

/**
 * @author Alexey Solomatin
 *
 */
public class VehicleSelectionConsoleDialog extends ConsoleDialog<Movable> {	

	private static final double BUS_SPEED_LOSING_PROBABILITY = 0.05;
	private static final double TRUCK_SPEED_LOSING_PROBABILITY = 0.1;
	private static final double CAR_SPEED_LOSING_PROBABILITY = 0.2;
	
	private List<Vehicle> vehicles;
	
	private final DaoFactory daoFactory;

	/**
	 * @param questionMessage
	 * @param errorMessage
	 * @throws SQLException 
	 */
	public VehicleSelectionConsoleDialog(DaoFactory daoFactory) throws SQLException {
		super("Please select a vehicle.", "Cannot find the specified vehicle.");
		this.daoFactory = daoFactory;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dialog.ConsoleDialog#buildObject()
	 */
	@Override
	protected Movable buildObject() throws Exception {
		if (vehicles == null) {
			log.debug("Loading the vehicles from the database.");
			vehicles = daoFactory.getVehicleDao().readAll();
		}
		log.debug("Selecting a vehicle.");
		if (vehicles != null && !vehicles.isEmpty()) {
			System.console().printf("There are following vehicles in the database:\n");
			vehicles.stream().forEach((v)->System.console().printf("\tID: %d, name: %s, type: %s\n", v.getId(), v.getName(), v.getClass().getSimpleName()));
		} else
			System.console().printf("There are no vehicles in the database.\n");
		Long vehicleId = new LongValueConsoleDialog("Please enter the vehicle ID: ", "You've entered the wrong ID.", (id)->id>0).get();
		Vehicle vehicle = daoFactory.getVehicleDao().read(vehicleId);
		if (vehicle != null) {
			if (vehicle instanceof Car)
				return new SpeedLosingVehicleWrapper(vehicle, CAR_SPEED_LOSING_PROBABILITY);
			else if (vehicle instanceof Bus)
				return new SpeedLosingVehicleWrapper(vehicle, BUS_SPEED_LOSING_PROBABILITY);
			else if (vehicle instanceof Truck)
				return new SpeedLosingVehicleWrapper(vehicle, TRUCK_SPEED_LOSING_PROBABILITY);
			else
				throw new Exception("Cannot load a vehicle with ID " + vehicleId);
		} else
			throw new Exception("Cannot load a vehicle with ID " + vehicleId);					
	}

}
