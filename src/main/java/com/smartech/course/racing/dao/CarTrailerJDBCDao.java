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
import com.smartech.course.racing.vehicle.Truck;
import com.smartech.course.racing.vehicle.payload.CarTrailer;

/**
 * @author Alexey Solomatin
 *
 */
public class CarTrailerJDBCDao extends AbstractJDBCDao<CarTrailer> {	
	private static volatile CarTrailerJDBCDao instance;
	
	/**
	 * @param dataSource
	 */
	private CarTrailerJDBCDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public static CarTrailerJDBCDao getInstance() {
		CarTrailerJDBCDao localInstance = instance;
		if (localInstance == null) {
			synchronized (CarTrailerJDBCDao.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new CarTrailerJDBCDao(RacingSimulatorDbDataSource.getInstance().get());
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
				+ "ct.id, "				
				+ "ct.name, "
				+ "ct.weight, "
				+ "ct.max_speed, "				
				+ "ct.payload_weight, "
				+ "ct.max_payload_weight "
				+ "from car_trailer ct ";				
	}
	
	@Override
	protected String getSelectQuery() {
		return getSelectAllQuery() + " where ct.id = ?; ";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getCreateQuery()
	 */
	@Override
	protected String getCreateQuery() {
		return "{call create_car_trailer(?,?,?,?,?,?)}";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getUpdateQuery()
	 */
	@Override
	protected String getUpdateQuery() {
		return "{call update_car_trailer(?,?,?,?,?,?)}";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getDeleteQuery()
	 */
	@Override
	protected String getDeleteQuery() {
		return "delete from car_trailer where id = ?; ";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForCreate(java.sql.CallableStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForCreate(CallableStatement statement, CarTrailer carTrailer)
			throws DataAccessException, SQLException {
		statement.setString("sName", carTrailer.getName());
		statement.setDouble("dWeight", carTrailer.getWeight());
		statement.setDouble("dMaxSpeed", carTrailer.getMaxSpeed());		
		statement.setDouble("dPayloadWeight", carTrailer.getPayload().getPayloadWeight());
		statement.setDouble("dMaxPayloadWeight", carTrailer.getPayload().getMaxPayloadWeight());
		statement.registerOutParameter("id", Types.BIGINT);
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForUpdate(java.sql.CallableStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForUpdate(CallableStatement statement, CarTrailer carTrailer)
			throws DataAccessException, SQLException {
		statement.setLong("id", carTrailer.getId());
		statement.setString("sName", carTrailer.getName());
		statement.setDouble("dWeight", carTrailer.getWeight());
		statement.setDouble("dMaxSpeed", carTrailer.getMaxSpeed());		
		statement.setDouble("dPayloadWeight", carTrailer.getPayload().getPayloadWeight());
		statement.setDouble("dMaxPayloadWeight", carTrailer.getPayload().getMaxPayloadWeight());

	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#parseResultSet(java.sql.ResultSet)
	 */
	@Override
	protected List<CarTrailer> parseResultSet(ResultSet rs) throws DataAccessException {
		List<CarTrailer> result = new ArrayList<>();  
        try {
            while (rs.next()) {
            	CarTrailer carTrailer = new CarTrailer(
            		rs.getLong("id"),
            		rs.getString("name"), 
            		rs.getDouble("weight"),
            		rs.getDouble("max_speed"),
    				rs.getLong("max_payload_weight"),
					rs.getLong("payload_weight"));                
                result.add(carTrailer);
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return result;
	}

}
