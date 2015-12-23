package com.smartech.course.racing.dao;

public interface DaoFactory {

	RacingDao getRacingDao();

	/**
	 * @return the truckDao
	 */
	TruckDao getTruckDao();

	/**
	 * @return the busDao
	 */
	BusDao getBusDao();

	/**
	 * @return the carTrailerDao
	 */
	CarTrailerDao getCarTrailerDao();

	/**
	 * @return the carDao
	 */
	CarDao getCarDao();

	/**
	 * @return the vehicleDao
	 */
	VehicleDao getVehicleDao();

}