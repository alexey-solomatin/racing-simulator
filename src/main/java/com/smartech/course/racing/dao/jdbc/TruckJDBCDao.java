/**
 * 
 */
package com.smartech.course.racing.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.smartech.course.racing.dao.TruckDao;
import com.smartech.course.racing.database.RacingSimulatorDbDataSource;
import com.smartech.course.racing.exception.DataAccessException;
import com.smartech.course.racing.vehicle.Truck;

/**
 * @author Alexey Solomatin
 *
 */
class TruckJDBCDao extends AbstractJDBCDao<Truck> implements TruckDao {
	private static volatile TruckJDBCDao instance;

	/**
	 * @param dataSource
	 */
	private TruckJDBCDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public static TruckJDBCDao getInstance() {
		TruckJDBCDao localInstance = instance;
		if (localInstance == null) {
			synchronized (TruckJDBCDao.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new TruckJDBCDao(RacingSimulatorDbDataSource.getInstance().get());
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
				+ "t.payload_weight, "
				+ "t.max_payload_weight "
				+ "from truck t "
				+ "	inner join vehicle v "
				+ "		on v.id = t.id ";
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
		return "{call create_truck(?,?,?,?,?,?,?)}";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getUpdateQuery()
	 */
	@Override
	protected String getUpdateQuery() {
		return "{call update_truck(?,?,?,?,?,?,?)}";
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
	protected void prepareStatementForCreate(CallableStatement statement, Truck truck)
			throws DataAccessException, SQLException {		
		statement.setString("sName", truck.getName());
		statement.setDouble("dWeight", truck.getWeight());
		statement.setDouble("dMaxSpeed", truck.getMaxSpeed());
		statement.setDouble("dAcceleration", truck.getAcceleration());		
		statement.setDouble("dPayloadWeight", truck.getPayload().getPayloadWeight());
		statement.setDouble("dMaxPayloadWeight", truck.getPayload().getMaxPayloadWeight());
		statement.registerOutParameter("id", Types.BIGINT);		
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForUpdate(java.sql.CallableStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForUpdate(CallableStatement statement, Truck truck)
			throws DataAccessException, SQLException {
		statement.setLong("id", truck.getId());
		statement.setString("sName", truck.getName());
		statement.setDouble("dWeight", truck.getWeight());
		statement.setDouble("dMaxSpeed", truck.getMaxSpeed());
		statement.setDouble("dAcceleration", truck.getAcceleration());
		statement.setDouble("dPayloadWeight", truck.getPayload().getPayloadWeight());
		statement.setDouble("dMaxPayloadWeight", truck.getPayload().getMaxPayloadWeight());		
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#parseResultSet(java.sql.ResultSet)
	 */
	@Override
	protected List<Truck> parseResultSet(ResultSet rs) throws DataAccessException {
		List<Truck> result = new ArrayList<>();  
        try {
            while (rs.next()) {
            	Truck truck = new Truck(
            		rs.getLong("id"),
            		rs.getString("name"), 
            		rs.getDouble("weight"),
            		rs.getDouble("max_speed"),
            		rs.getDouble("acceleration"),
    				rs.getLong("max_payload_weight"),
					rs.getLong("payload_weight"));                
                result.add(truck);
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return result;
	}

}
