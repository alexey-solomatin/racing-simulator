/**
 * 
 */
package com.smartech.course.racing.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.smartech.course.racing.dao.VehicleDao;
import com.smartech.course.racing.database.RacingSimulatorDbDataSource;
import com.smartech.course.racing.exception.DataAccessException;
import com.smartech.course.racing.vehicle.Bus;
import com.smartech.course.racing.vehicle.Car;
import com.smartech.course.racing.vehicle.Truck;
import com.smartech.course.racing.vehicle.Vehicle;

/**
 * @author Alexey Solomatin
 *
 */
class VehicleJDBCDao extends AbstractJDBCDao<Vehicle> implements VehicleDao {
	private static volatile VehicleJDBCDao instance;

	/**
	 * @param dataSource
	 */
	private VehicleJDBCDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public static VehicleJDBCDao getInstance() {
		VehicleJDBCDao localInstance = instance;
		if (localInstance == null) {
			synchronized (VehicleJDBCDao.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new VehicleJDBCDao(RacingSimulatorDbDataSource.getInstance().get());
				}
			}
		}
		return localInstance;
	}
	
	@Override
	protected String getSelectAllQuery() {
		return "select "
				+ "v.id, "
				+ "v.vehicle_type, "
				+ "v.name, "
				+ "v.weight, "
				+ "v.max_speed, "
				+ "v.acceleration, "
				+ "c.trailer_id, "
				+ "t.payload_weight, "
				+ "t.max_payload_weight, "
				+ "b.num_passengers, "
				+ "b.max_num_passengers "
				+ "from vehicle v "
				+ "	left join car c "
				+ "		on v.id = c.id "
				+ "	left join truck t "
				+ "		on v.id = t.id "
				+ "	left join bus b "
				+ "		on v.id = b.id ";
	}
	
	@Override
	protected String getSelectQuery() {
		return getSelectAllQuery() + " where v.id = ?; ";
	}

	@Override
	protected String getCreateQuery() {
		return null;
	}

	@Override
	protected String getUpdateQuery()  {
		return null;
	}

	@Override
	protected String getDeleteQuery() {
		return "delete from vehicle where id = ?; ";
	}
	
	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForCreate(java.sql.CallableStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForCreate(CallableStatement statement, Vehicle object)
			throws DataAccessException, SQLException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForUpdate(java.sql.CallableStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForUpdate(CallableStatement statement, Vehicle object)
			throws DataAccessException, SQLException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#parseResultSet(java.sql.ResultSet)
	 */
	@Override
	protected List<Vehicle> parseResultSet(ResultSet rs) throws DataAccessException {
		List<Vehicle> result = new ArrayList<>();  
        try {
            while (rs.next()) {
            	long vType = rs.getLong("vehicle_type"); 
            	if (vType == 1L) {
            		Bus bus = new Bus(
                		rs.getLong("id"),
                		rs.getString("name"), 
                		rs.getDouble("weight"),
                		rs.getDouble("max_speed"),
                		rs.getDouble("acceleration"),
        				rs.getLong("max_num_passengers"),
    					rs.getLong("num_passengers"));
            		result.add(bus);
            	} else if (vType == 2L) {
            		Truck truck = new Truck(
                		rs.getLong("id"),
                		rs.getString("name"), 
                		rs.getDouble("weight"),
                		rs.getDouble("max_speed"),
                		rs.getDouble("acceleration"),
        				rs.getLong("max_payload_weight"),
    					rs.getLong("payload_weight"));                
                    result.add(truck);
            	} else if (vType == 3L) {
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
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return result;
	}

}
