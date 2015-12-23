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

import com.smartech.course.racing.dao.RacingDao;
import com.smartech.course.racing.database.RacingSimulatorDbDataSource;
import com.smartech.course.racing.exception.DataAccessException;
import com.smartech.course.racing.simulation.Racing;

/**
 * @author Alexey Solomatin
 *
 */
class RacingJDBCDao extends AbstractJDBCDao<Racing> implements RacingDao {

	private static volatile RacingJDBCDao instance;

	/**
	 * @param dataSource
	 */
	private RacingJDBCDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public static RacingJDBCDao getInstance() {
		RacingJDBCDao localInstance = instance;
		if (localInstance == null) {
			synchronized (RacingJDBCDao.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new RacingJDBCDao(RacingSimulatorDbDataSource.getInstance().get());
				}
			}
		}
		return localInstance;
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getSelectQuery()
	 */
	@Override
	protected String getSelectQuery() {
		return getSelectAllQuery() + "where r.id = ? ";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getSelectAllQuery()
	 */
	@Override
	protected String getSelectAllQuery() {		
		return "select r.id, r.name, r.distance from racing r ";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getCreateQuery()
	 */
	@Override
	protected String getCreateQuery() {
		return "{call create_racing(?,?,?)}";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getUpdateQuery()
	 */
	@Override
	protected String getUpdateQuery() {
		return "{call create_racing(?,?,?)}";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#getDeleteQuery()
	 */
	@Override
	protected String getDeleteQuery() {
		return "delete from racing where id = ?; ";
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForCreate(java.sql.CallableStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForCreate(CallableStatement statement, Racing racing)
			throws DataAccessException, SQLException {
		statement.setString("sName", racing.getName());
		statement.setDouble("dDistance", racing.getDistance());		
		statement.registerOutParameter("id", Types.BIGINT);
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#prepareStatementForUpdate(java.sql.CallableStatement, com.smartech.course.racing.dao.Persistable)
	 */
	@Override
	protected void prepareStatementForUpdate(CallableStatement statement, Racing racing)
			throws DataAccessException, SQLException {
		statement.setLong("id", racing.getId());
		statement.setString("sName", racing.getName());
		statement.setDouble("dDistance", racing.getDistance());				
	}

	/* (non-Javadoc)
	 * @see com.smartech.course.racing.dao.AbstractJDBCDao#parseResultSet(java.sql.ResultSet)
	 */
	@Override
	protected List<Racing> parseResultSet(ResultSet rs) throws DataAccessException {
		List<Racing> result = new ArrayList<>();  
        try {
            while (rs.next()) {
            	Racing racing = new Racing(
            		rs.getLong("id"),
            		rs.getString("name"), 
            		rs.getDouble("distance"));                
                result.add(racing);
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return result;
	}

}
