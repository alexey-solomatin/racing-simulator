/**
 * 
 */
package com.smartech.course.racing.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.exception.DataAccessConnectionFailureException;
import com.smartech.course.racing.exception.DataAccessException;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractJDBCDao<T extends Persistable<Long>> implements GenericDao<T, Long> {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	private DataSource dataSource;

	/**
	 * 
	 */
	public AbstractJDBCDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	protected abstract String getSelectQuery();
	protected abstract String getSelectAllQuery();
	protected abstract String getCreateQuery();
	protected abstract String getUpdateQuery();
	protected abstract String getDeleteQuery();
	protected abstract void prepareStatementForCreate(CallableStatement statement, T object) throws DataAccessException, SQLException;
	protected abstract void prepareStatementForUpdate(CallableStatement statement, T object) throws DataAccessException, SQLException;
	protected abstract List<T> parseResultSet(ResultSet rs) throws DataAccessException;
	
	@Override
	public Long create(T object) throws SQLException {
		if (object.getId() != null)
			throw new DataAccessException("The object cannot be created.");
		log.debug("Creating {}.", object);
		try (Connection connection = getConnection()) {			
	        String sql = getCreateQuery();
	        try (CallableStatement statement = connection.prepareCall(sql)) {
	            prepareStatementForCreate(statement, object);
	            statement.execute();
	            return statement.getLong("id");
	        } catch (Exception e) {
	            throw new DataAccessException(e);
	        }	        
		}		
	}
	
	@Override
	public T read(Long key) throws SQLException {
		try (Connection connection = getConnection()) {
			log.debug("Reading by key {}.", key);
			List<T> list;
	        String sql = getSelectQuery();	        
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setLong(1, key);
	            ResultSet rs = statement.executeQuery();
	            list = parseResultSet(rs);
	        } catch (Exception e) {
	            throw new DataAccessException(e);
	        }
	        if (list == null || list.size() == 0) {
	            throw new DataAccessException("Record with PK = " + key + " not found.");
	        }
	        if (list.size() > 1) {
	            throw new DataAccessException("Received more than one record.");
	        }
	        return list.iterator().next();
		}		
	}
	
	@Override
	public List<T> readAll() throws SQLException {
		try (Connection connection = getConnection()) {
			log.debug("Reading everything.");
			List<T> list;
	        String sql = getSelectAllQuery();
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            ResultSet rs = statement.executeQuery();
	            list = parseResultSet(rs);
	        } catch (Exception e) {
	            throw new DataAccessException(e);
	        }
	        return list;
		}
	}
	
	@Override
	public void update(T object) throws SQLException {
		try (Connection connection = getConnection()) {
			log.debug("Updating {}.", object);
			String sql = getUpdateQuery();
	        try (CallableStatement statement = connection.prepareCall(sql)) {
	            prepareStatementForUpdate(statement, object);
	            statement.execute();	            
	        } catch (Exception e) {
	            throw new DataAccessException(e);
	        }
		}		
	}
	
	@Override
	public void delete(T object) throws SQLException {
		try (Connection connection = getConnection()) {
			log.debug("Deleting {}.", object);
			String sql = getDeleteQuery();
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            try {
	                statement.setObject(1, object.getId());
	            } catch (Exception e) {
	                throw new DataAccessException(e);
	            }
	            statement.executeUpdate();	            	            
	        } catch (Exception e) {
	            throw new DataAccessException(e);
	        }
		}		
	}

	protected final Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (final SQLException e) {
			throw new DataAccessConnectionFailureException(e);
		}

	}
}
