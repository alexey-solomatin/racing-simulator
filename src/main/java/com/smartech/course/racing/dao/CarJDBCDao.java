/**
 * 
 */
package com.smartech.course.racing.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.smartech.course.racing.database.RacingSimulatorDbDataSource;
import com.smartech.course.racing.exception.DataAccessException;
import com.smartech.course.racing.vehicle.Car;
import com.smartech.course.racing.vehicle.Truck;
import com.smartech.course.racing.vehicle.payload.CarTrailer;

/**
 * @author Alexey Solomatin
 *
 */
public class CarJDBCDao extends AbstractJDBCDao<Car> {
	private static volatile CarJDBCDao instance;
	
	/**
	 * @param dataSource
	 */
	private CarJDBCDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public static CarJDBCDao getInstance() {
		CarJDBCDao localInstance = instance;
		if (localInstance == null) {
			synchronized (CarJDBCDao.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new CarJDBCDao(RacingSimulatorDbDataSource.getInstance().get());
				}
			}
		}
		return localInstance;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getSelectQuery()
	 */
	@Override
	protected String getSelectAllQuery() {
		return "select "
				+ "v.id, "
				+ "v.vehicle_type, "
				+ "v.name, "
				+ "v.weight, "
				+ "v.max_speed, "
				+ "v.acceleration, "
				+ "c.trailer_id "							
				+ "from car c "
				+ "	inner join vehicle v "
				+ "		on v.id = c.id ";
	}
	
	@Override
	protected String getSelectQuery() {
		return getSelectAllQuery() + " where v.id = ?; ";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getCreateQuery()
	 */
	@Override
	protected String getCreateQuery() {
		return "{call create_car(?,?,?,?,?,?)}";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getUpdateQuery()
	 */
	@Override
	protected String getUpdateQuery() {
		return "{call update_car(?,?,?,?,?,?)}";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getDeleteQuery()
	 */
	@Override
	protected String getDeleteQuery() {
		return "delete from vehicle where id = ?; ";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForCreate(java.sql.CallableStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForCreate(CallableStatement statement, Car car)
			throws DataAccessException, SQLException {
		statement.setString("sName", car.getName());
		statement.setDouble("dWeight", car.getWeight());
		statement.setDouble("dMaxSpeed", car.getMaxSpeed());
		statement.setDouble("dAcceleration", car.getAcceleration());
		if (car.getPayload() != null)
			statement.setLong("lTrailerId", car.getPayload().getId());
		else
			statement.setObject("lTrailerId", null);
		statement.registerOutParameter("id", Types.BIGINT);
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForUpdate(java.sql.CallableStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForUpdate(CallableStatement statement, Car car)
			throws DataAccessException, SQLException {
		statement.setLong("id", car.getId());
		statement.setString("sName", car.getName());
		statement.setDouble("dWeight", car.getWeight());
		statement.setDouble("dMaxSpeed", car.getMaxSpeed());
		statement.setDouble("dAcceleration", car.getAcceleration());
		if (car.getPayload() != null) {
			if (car.getPayload().getId() != null)
				CarTrailerJDBCDao.getInstance().update(car.getPayload());
			else {
				Long trailerId = CarTrailerJDBCDao.getInstance().create(car.getPayload());
				statement.setLong("lTrailerId", trailerId);
			}
		}
							
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#parseResultSet(java.sql.ResultSet)
	 */
	@Override
	protected List<Car> parseResultSet(ResultSet rs) throws DataAccessException {
		List<Car> result = new ArrayList<>();  
        try {
            while (rs.next()) {            	
            	Car car = new Car(
            		rs.getLong("id"),
            		rs.getString("name"), 
            		rs.getDouble("weight"),
            		rs.getDouble("max_speed"),
            		rs.getDouble("acceleration"));
            	Long trailerId = rs.getObject("trailer_id", Long.class);
            	if (trailerId != null && trailerId != 0)
            		car.addTrailer(CarTrailerJDBCDao.getInstance().read(rs.getObject("trailer_id", Long.class)));
                result.add(car);
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return result;
	}

}
