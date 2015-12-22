/**
 * 
 */
package com.smartech.course.racing.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.smartech.course.racing.database.RacingSimulatorDbDataSource;
import com.smartech.course.racing.exception.DataAccessException;
import com.smartech.course.racing.vehicle.Bus;

/**
 * @author Alexey Solomatin
 *
 */
public class BusJDBCDao extends AbstractJDBCDao<Bus> {
	private static volatile BusJDBCDao instance;
	
	/**
	 * @param dataSource
	 */
	public BusJDBCDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public static BusJDBCDao getInstance() {
		BusJDBCDao localInstance = instance;
		if (localInstance == null) {
			synchronized (BusJDBCDao.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new BusJDBCDao(RacingSimulatorDbDataSource.getInstance().get());
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
				+ "b.num_passengers, "
				+ "b.max_num_passengers "
				+ "from bus b "
				+ "	inner join vehicle v "
				+ "		on v.id = b.id ";
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
		return "{call create_bus(?,?,?,?,?,?,?)}";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getUpdateQuery()
	 */
	@Override
	protected String getUpdateQuery() {		
		return "{call update_bus(?,?,?,?,?,?,?)}";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getDeleteQuery()
	 */
	@Override
	protected String getDeleteQuery() {
		return "delete from vehicle where id = ?; ";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForCreate(java.sql.PreparedStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForCreate(CallableStatement statement, Bus bus)
			throws DataAccessException, SQLException {
		statement.setString("sName", bus.getName());
		statement.setDouble("dWeight", bus.getWeight());
		statement.setDouble("dMaxSpeed", bus.getMaxSpeed());
		statement.setDouble("dAcceleration", bus.getAcceleration());
		statement.setLong("lNumPassengers", bus.getPayload().getNumberOfPassengers());
		statement.setLong("lMaxNumPassengers", bus.getPayload().getMaxNumberOfPassengers());
		statement.registerOutParameter("id", Types.BIGINT);
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForUpdate(java.sql.PreparedStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForUpdate(CallableStatement statement, Bus bus)
			throws DataAccessException, SQLException {		
		statement.setLong("id", bus.getId());
		statement.setString("sName", bus.getName());
		statement.setDouble("dWeight", bus.getWeight());
		statement.setDouble("dMaxSpeed", bus.getMaxSpeed());
		statement.setDouble("dAcceleration", bus.getAcceleration());
		statement.setLong("lNumPassengers", bus.getPayload().getNumberOfPassengers());
		statement.setLong("lMaxNumPassengers", bus.getPayload().getMaxNumberOfPassengers());
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#parseResultSet(java.sql.ResultSet)
	 */
	@Override
	protected List<Bus> parseResultSet(ResultSet rs) throws DataAccessException {
		List<Bus> result = new ArrayList<>();  
        try {
            while (rs.next()) {
                Bus bus = new Bus(
            		rs.getLong("id"),
            		rs.getString("name"), 
            		rs.getDouble("weight"),
            		rs.getDouble("max_speed"),
            		rs.getDouble("acceleration"),
    				rs.getLong("max_num_passengers"),
					rs.getLong("num_passengers"));                
                result.add(bus);
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return result;
	}

}
