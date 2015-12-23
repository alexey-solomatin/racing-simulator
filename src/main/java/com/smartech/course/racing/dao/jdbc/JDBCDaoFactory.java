/**
 * 
 */
package com.smartech.course.racing.dao.jdbc;

import com.smartech.course.racing.dao.BusDao;
import com.smartech.course.racing.dao.CarDao;
import com.smartech.course.racing.dao.CarTrailerDao;
import com.smartech.course.racing.dao.DaoFactory;
import com.smartech.course.racing.dao.RacingDao;
import com.smartech.course.racing.dao.TruckDao;
import com.smartech.course.racing.dao.VehicleDao;

/**
 * @author Alexey Solomatin
 *
 */
public class JDBCDaoFactory implements DaoFactory {
	private final RacingDao racingDao;
	private final VehicleDao vehicleDao;
	private final TruckDao truckDao;
	private final BusDao busDao;
	private final CarTrailerDao carTrailerDao;
	private final CarDao carDao;

	private static volatile DaoFactory instance;

	/**
	 * 
	 */
	private JDBCDaoFactory() {
		racingDao = RacingJDBCDao.getInstance();
		vehicleDao = VehicleJDBCDao.getInstance();
		truckDao = TruckJDBCDao.getInstance();
		busDao = BusJDBCDao.getInstance();
		carTrailerDao = CarTrailerJDBCDao.getInstance();
		carDao = CarJDBCDao.getInstance();
	}

	public static DaoFactory getInstance() {
		DaoFactory localInstance = instance;
		if (localInstance == null) {
			synchronized (JDBCDaoFactory.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new JDBCDaoFactory();
				}
			}
		}
		return localInstance;
	}
	
	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.jdbc.DaoFactory#getRacingDAO()
	 */
	@Override
	public RacingDao getRacingDao() {
		return racingDao;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.jdbc.DaoFactory#getTruckDao()
	 */
	@Override
	public TruckDao getTruckDao() {
		return truckDao;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.jdbc.DaoFactory#getBusDao()
	 */
	@Override
	public BusDao getBusDao() {
		return busDao;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.jdbc.DaoFactory#getCarTrailerDao()
	 */
	@Override
	public CarTrailerDao getCarTrailerDao() {
		return carTrailerDao;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.jdbc.DaoFactory#getCarDao()
	 */
	@Override
	public CarDao getCarDao() {
		return carDao;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.jdbc.DaoFactory#getVehicleDao()
	 */
	@Override
	public VehicleDao getVehicleDao() {
		return vehicleDao;
	}
}
